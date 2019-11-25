package com.mini.web.argument.paging;

import com.mini.core.jdbc.model.Paging;
import com.mini.core.util.StringUtil;
import com.mini.core.util.reflect.MiniParameter;
import com.mini.web.interceptor.ActionInvocation;
import org.apache.commons.lang3.math.NumberUtils;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Named
@Singleton
public class ArgumentResolverPagingRequestParam extends ArgumentResolverPaging {

    @Override
    public boolean supportParameter(MiniParameter parameter) {
        return Paging.class == parameter.getType();
    }

    private String getParamValue(String name, ActionInvocation invocation) {
        HttpServletRequest request = invocation.getRequest();
        String value = request.getParameter(name);
        if (StringUtil.isNotBlank(value)) {
            return value;
        }
        // 获取路径上的参数信息
        Map<String, String> uriParam = invocation.getUriParameters();
        return (value = uriParam.get(name)) == null ? null : value;
    }

    @Override
    protected int getPageValue(ActionInvocation invocation) {
        return ofNullable(getParamValue("page", invocation))
                .filter(v -> !v.isBlank())
                .map(NumberUtils::toInt)
                .orElse(0);
    }

    @Override
    protected int getLimitValue(ActionInvocation invocation) {
        return ofNullable(getParamValue("limit", invocation))
                .filter(v -> !v.isBlank())
                .map(NumberUtils::toInt)
                .orElse(0);
    }
}
