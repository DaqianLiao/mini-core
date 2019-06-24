package com.mini.web.interceptor;

import com.mini.util.reflect.MiniParameter;
import com.mini.web.annotation.Action;
import com.mini.web.model.IModel;
import com.mini.web.view.IView;

import javax.annotation.Nonnull;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

public interface ActionInvocation {

    /**
     * 获取目标方法对象
     * @return 目标方法对象
     */
    Method getMethod();

    ///**
    // * 获取目标方法Action 注解
    // * @return 目标方法Action 注解
    // */
    //Action getAction();

    /**
     * 获取Controller类Class对象
     * @return Class对象
     */
    Class<?> getClazz();
    //
    //
    ///**
    // * 获取 Controller 注解
    // * @return Controller
    // */
    //Controller getController();

    /**
     * 获取Controller类实例
     * @return Controller类实例
     */
    Object getInstance();

    /**
     * 获取页面数据模型实现类
     * @return 页面数据模型实现类
     */
    Class<? extends IModel<?>> getModelClass();

    /**
     * 获取控制器支持的方法
     * @return 控制器支持的方法
     */
    Action.Method[] getSupportMethod();

    /**
     * 获取所有拦截器对象
     * @return 拦截器对象
     */
    @Nonnull
    List<ActionInterceptor> getInterceptors();


    /**
     * 获取 Action Url 访问路径
     * @return Action Url 访问路径
     */
    String getUrl();


    /**
     * 获取 Action 视图路径
     * @return Action 视图路径
     */
    String getViewPath();


    /**
     * 获取数据模型
     * @return 数据模型
     */
    IModel<?> getModel();


    /**
     * 获取 Action 视图实现
     * @return Action 视图实现
     */
    IView getView();

    /**
     * 获取 HttpServletRequest
     * @return HttpServletRequest
     */
    HttpServletRequest getRequest();


    /**
     * 获取 HttpServletResponse
     * @return HttpServletResponse
     */
    HttpServletResponse getResponse();


    /**
     * 获取 HttpSession
     * @return HttpSession
     */
    HttpSession getSession();

    /**
     * 获取 ServletContext
     * @return ServletContext
     */
    ServletContext getServletContext();

    /**
     * 获取目标方法的所有参数信息
     * @return 所有参数信息
     */
    @Nonnull
    MiniParameter[] getParameters();

    /**
     * 获取所有参数值
     * @return 所有参数值
     */
    @Nonnull
    Object[] getParameterValues();


    /**
     * 调用目标方法或者下一个拦截器
     * @return 目标方法返回值
     */
    Object invoke() throws Throwable;


}
