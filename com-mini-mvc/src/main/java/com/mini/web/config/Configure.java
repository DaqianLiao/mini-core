package com.mini.web.config;

import com.google.inject.Injector;
import com.mini.web.annotation.Action;
import com.mini.web.argument.ArgumentResolver;
import com.mini.web.handler.ExceptionHandler;
import com.mini.web.interceptor.ActionInterceptor;
import com.mini.web.interceptor.ActionProxy;
import com.mini.web.view.IView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import java.util.*;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static java.util.Comparator.comparingInt;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.split;

@Singleton
public final class Configure {
    private final List<ActionInterceptor> globalInterceptors = new ArrayList<>();
    private final Map<HttpServlet, ServletElement> servlets = new HashMap<>();
    private final Set<ArgumentResolver> argumentResolvers = new HashSet<>();
    private final List<ExceptionHandler> handlerList = new ArrayList<>();
    private final Map<Filter, FilterElement> filters = new HashMap<>();
    private final Set<EventListener> listeners = new HashSet<>();
    private final MappingMap mappingMap = new MappingMap();
    private IView view;

    // 基础配置
    private String encodingCharset = "UTF-8"; // 编码
    private String asyncSupported = "true"; // 是否支持异步返回
    private String urlPatterns = "*.htm"; // 拦截路径

    // 默认文件上传配置（大小限制、缓冲区大小配置，路径配置）
    private String multipartEnabled = "true"; // 开启文件上传
    private String fileSizeThreshold = "4096";  // 上传文件缓冲区大小
    private String maxRequestSize = "-1";  // 上传文件总大小限制
    private String maxFileSize = "-1";  // 上传文件单个文件大小限制
    private String location = "/temp"; // 上传文件临时路径

    // 默认日期时间格式配置
    private String dateTimeFormat = "yyyy-MM-dd HH[:mm[:ss]]";
    private String dateFormat = "yyyy[-MM[-dd]]";
    private String timeFormat = "HH[:mm[:ss]]";

    // 默认视图配置（视图路径前缀和后缀）
    private String ViewPrefix = "/WEB-INF/";
    private String viewSuffix = ".ftl";

    // 依赖注入容器
    private Injector injector;

    /**
     * 编码
     * @param encodingCharset 编码
     */
    @Inject
    public void setEncodingCharset(
            @Named("mini.http.encoding.charset")
            @Nullable String encodingCharset) {
        this.encodingCharset = encodingCharset;
    }

    /**
     * 是否支持异步返回
     * @param asyncSupported true-是
     */
    @Inject
    public void setAsyncSupported(
            @Named("mini.http.async.supported")
            @Nullable String asyncSupported) {
        this.asyncSupported = asyncSupported;
    }

    /**
     * 默认Servlet拦截路径
     * @param urlPatterns 拦截路径
     */
    @Inject
    public void setUrlPatterns(
            @Named("mini.http.servlet.url-patterns")
            @Nullable String urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    /**
     * 是否开启文件上传功能
     * @param multipartEnabled "true"-是
     */
    @Inject
    public void setMultipartEnabled(
            @Named("mini.http.multipart.enabled")
            @Nullable String multipartEnabled) {
        this.multipartEnabled = multipartEnabled;
    }

    /**
     * 文件上传缓冲区大小
     * @param fileSizeThreshold 缓冲区大小
     */
    @Inject
    public void setFileSizeThreshold(
            @Named("mini.http.multipart.file-size-threshold")
            @Nullable String fileSizeThreshold) {
        this.fileSizeThreshold = fileSizeThreshold;
    }

    /**
     * 文件上传请求大小限制
     * @param maxRequestSize 请求大小限制
     */
    @Inject
    public void setMaxRequestSize(
            @Named("mini.http.multipart.max-request-size")
            @Nullable String maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }

    /**
     * 文件上传单个文件大小限制
     * @param maxFileSize 单个文件大小限制
     */
    @Inject
    public void setMaxFileSize(
            @Named("mini.http.multipart.max-file-size")
            @Nullable String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    /**
     * 文件上传临时目录
     * @param location 临时目录
     */
    @Inject
    public void setLocation(
            @Named("mini.http.multipart.location")
            @Nullable String location) {
        this.location = location;
    }

    /**
     * 日期时间默认格式化
     * @param dateTimeFormat 格式化
     */
    @Inject
    public void setDateTimeFormat(
            @Named("mini.http.datetime-format")
            @Nullable String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    /**
     * 日期默认格式化
     * @param dateFormat 格式化
     */
    @Inject
    public void setDateFormat(
            @Named("mini.http.date-format")
            @Nullable String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * 时间默认格式化
     * @param timeFormat 格式化
     */
    @Inject
    public void setTimeFormat(
            @Named("mini.http.time-format")
            @Nullable String timeFormat) {
        this.timeFormat = timeFormat;
    }

    /**
     * 默认视图路径前缀
     * @param viewPrefix 视图路径前缀
     */
    @Inject
    public void setViewPrefix(
            @Named("mini.mvc.view.prefix")
            @Nullable String viewPrefix) {
        ViewPrefix = viewPrefix;
    }

    /**
     * 默认视图路径后缀
     * @param viewSuffix 视图路径后缀
     */
    @Inject
    public void setViewSuffix(
            @Named("mini.mvc.view.suffix")
            @Nullable String viewSuffix) {
        this.viewSuffix = viewSuffix;
    }

    /**
     * 依赖注入容器上下文
     * @param injector 容器上下文
     */
    @Inject
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    /**
     * 获取配置文件编码
     * @return 配置文件编码
     */
    public final String getEncodingCharset() {
        return encodingCharset;
    }

    /**
     * 获取异步支持
     * @return true-支持异步
     */
    public final boolean isAsyncSupported() {
        if (asyncSupported == null) {
            return false;
        }
        return parseBoolean(asyncSupported);
    }

    /**
     * 获取默认 Servlet 访问路径
     * @return Servlet 访问路径
     */
    public final String[] getUrlPatterns() {
        return split(urlPatterns, "[,]");
    }

    /**
     * 获取是否开启上传文件功能
     * @return true-开启
     */
    public final boolean isMultipartEnabled() {
        if (multipartEnabled == null) {
            return false;
        }
        return parseBoolean(multipartEnabled);
    }

    /**
     * 获取文件上传缓冲区大小
     * @return 文件上传缓冲区大小
     */
    public final int getFileSizeThreshold() {
        if (fileSizeThreshold == null) {
            return 0;
        }
        return parseInt(fileSizeThreshold);
    }

    /**
     * 同时上传文件的总大小
     * @return 同时上传文件的总大小
     */
    public final long getMaxRequestSize() {
        if (maxRequestSize == null) {
            return 0;
        }
        return Long.parseLong(maxRequestSize);
    }

    /**
     * 单个文件大小限制
     * @return 单个文件大小限制
     */
    public final long getMaxFileSize() {
        if (maxFileSize == null) {
            return 0;
        }
        return Long.parseLong(maxFileSize);
    }

    /**
     * 上传文件临时目录
     * @return 上传文件临时目录
     */
    public final String getLocation() {
        return location;
    }

    /**
     * 默认日期时间格式
     * @return 默认日期时间格式
     */
    public final String getDateTimeFormat() {
        return dateTimeFormat;
    }

    /**
     * 默认日期格式
     * @return 默认日期格式
     */
    public final String getDateFormat() {
        return dateFormat;
    }

    /**
     * 默认时间格式
     * @return 默认时间格式
     */

    public final String getTimeFormat() {
        return timeFormat;
    }

    /**
     * 视图路径前缀
     * @return 视图路径前缀
     */

    public final String getViewPrefix() {
        return ViewPrefix;
    }

    /**
     * 视图路径后缀
     * @return 视图路径后缀
     */

    public final String getViewSuffix() {
        return viewSuffix;
    }

    /**
     * 添加一个Action代理对象
     * @param uri   访问Action的URL
     * @param proxy 代理对象实例
     * @return 当前对象
     */
    public final Configure addActionProxy(String uri, @Nonnull ActionProxy proxy) {
        mappingMap.put(uri, proxy);
        return this;
    }

    /**
     * 根据URI获取一个Action代理对象
     * @param uri 访问Action的URI
     * @return Action代理对象
     */
    public final Map<Action.Method, ActionProxy> getActionProxy(String uri) {
        return mappingMap.get(uri);
    }

    /**
     * 获取所有的Action代理对象
     * @return 所有的Action代理对象
     */
    public final Set<ActionProxy> getAllActionProxy() {
        return mappingMap.getActionProxySet();
    }


    /**
     * 添加一个监听器
     * @param listener 监听器
     * @return 当前对象
     */

    public final Configure addListener(Class<? extends EventListener> listener) {
        listeners.add(injector.getInstance(listener));
        return this;
    }

    /**
     * 获取所有监听器
     * @return 监听器
     */
    public final Set<EventListener> getListeners() {
        return listeners;
    }

    /**
     * 添加一个Servlet
     * @param clazz Servlet Class 对象
     * @return ServletElement 对象
     */
    public final ServletElement addServlet(Class<? extends HttpServlet> clazz) {
        HttpServlet servlet = injector.getInstance(clazz);
        ServletElement ele = new ServletElement().setServlet(servlet);
        return defaultIfNull(servlets.putIfAbsent(servlet, ele), ele);
    }

    /**
     * 获取所有Servlet
     * @return Servlet
     */
    public final Collection<ServletElement> getServlets() {
        return servlets.values();
    }

    /**
     * 添加一个过虑器
     * @param clazz 过虑器
     * @return 过虑器对象
     */
    public final FilterElement addFilter(Class<? extends Filter> clazz) {
        Filter filter = injector.getInstance(clazz);
        FilterElement ele = new FilterElement().setFilter(filter);
        return defaultIfNull(filters.putIfAbsent(filter, ele), ele);
    }

    /**
     * 删除一个默认的过虑器
     * @param filter 过虑器
     * @return 当前对象
     */

    public final Configure removeFilter(Class<? extends Filter> filter) {
        filters.remove(injector.getInstance(filter));
        return this;
    }

    /**
     * 获取所有过虑器
     * @return 过虑器
     */
    public final Collection<FilterElement> getFilters() {
        return filters.values();
    }

    /**
     * 添加一个参数解析器
     * @param clazz 解析器类
     * @return 当前对象
     */

    public final Configure addArgumentResolvers(Class<? extends ArgumentResolver> clazz) {
        argumentResolvers.add(injector.getInstance(clazz));
        return this;
    }

    /**
     * 获取所有的参数解析器类
     * @return 所有的参数解析器类
     */
    public final Set<ArgumentResolver> getArgumentResolvers() {
        return argumentResolvers;
    }

    /**
     * 注册一个全局的拦截器
     * @param clazz 拦截器类对象
     * @return {@code this}
     */
    public final Configure registerGlobalInterceptors(Class<? extends ActionInterceptor> clazz) {
        globalInterceptors.add(injector.getInstance(clazz));
        return this;
    }

    /**
     * 获取全局拦截器对象
     * @return 全局拦截器对象
     */
    @Nonnull
    public final List<ActionInterceptor> getGlobalInterceptors() {
        return globalInterceptors;
    }

    /**
     * 根据拦截器实现类获取拦截器实例
     * @return 拦截器实例
     */
    @Nonnull
    public final ActionInterceptor getInterceptor(Class<? extends ActionInterceptor> interceptor) {
        requireNonNull(injector, "Injector can not be null");
        return requireNonNull(injector.getInstance(interceptor));
    }

    /**
     * 注册一个异常处理器
     * @param handler 处理器
     * @return @this
     */
    public final Configure registerExceptionHandler(Class<? extends ExceptionHandler> handler) {
        handlerList.add(Objects.requireNonNull(injector.getInstance(handler)));
        handlerList.sort(comparingInt(ExceptionHandler::handlerOnExecute));
        return this;
    }

    /**
     * 获取所有的异常处理器
     * @return 异常处理器列表
     */
    @Nonnull
    public final List<ExceptionHandler> getExceptionHandlerList() {
        return this.handlerList;
    }

    /**
     * 设置视图实现类
     * @param viewClass 视图实现类
     * @return @this
     */
    public final Configure setViewClass(Class<? extends IView> viewClass) {
        this.view = injector.getInstance(viewClass);
        return this;
    }

    /**
     * 获取视图实现类实例
     * @return 视图实现类实例
     */
    public final IView getView() {
        return this.view;
    }
}
