package com.mini.web.view;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

@Singleton
public class FreemarkerView implements IView, Serializable {
    private static final long serialVersionUID = -1L;
    private Configuration configuration;

    @Inject
    @Named("mini.mvc.view.prefix")
    private String prefix = "";

    @Inject
    @Named("mini.mvc.view.suffix")
    private String suffix;

    @Inject
    private ServletContext context;

    @Override
    public void generator(Map<String, Object> data, String viewPath, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String view = this.prefix + viewPath + this.suffix;
        try (PrintWriter out = response.getWriter()) {
            getTemplate(view).process(data, out);
        }
    }

    /**
     * 获取  Template 对象
     * @param viewPath 模板路径
     * @return Template 对象
     */
    protected final Template getTemplate(String viewPath) throws IOException {
        return getConfiguration(context).getTemplate(viewPath);
    }

    /**
     * 获取 Configuration 对象
     * @param context ServletContext对象
     * @return Configuration对象
     */
    protected final Configuration getConfiguration(ServletContext context) {
        if (configuration != null) return configuration;
        configuration = new MiniConfiguration(context);
        return this.configuration;
    }

    private static class MiniConfiguration extends Configuration implements Cloneable {
        public void setServletContext(ServletContext context) {
            setServletContextForTemplateLoading(context, null);
        }

        public MiniConfiguration(ServletContext context) {
            super(Configuration.VERSION_2_3_28);
            setServletContext(context);
        }
    }
}
