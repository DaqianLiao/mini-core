package com.mini.web.handler;

import com.mini.web.model.IModel;
import com.mini.web.util.ResponseCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * <P>该处理器在Action和拦截器执行之后，Model提交方法之前</P>
 * @param <T> 异常类型
 * @author xchao
 */
public interface ExceptionHandler<T extends Throwable> extends ResponseCode {
    /**
     * 该值所有处理器中不能重复
     * <P>配置该值时，不能小于"0"</P>
     * <P>该值越小，执行优先级越高</P>
     * @return 处理顺序
     */
    int handlerOnExecute();

    /**
     * 获取异常的超类类型
     * @return 异常超类类型
     */
    Class<T> getExceptionClass();

    /**
     * 全局异常处理方法
     * @param model     数据模型渲染器
     * @param exception 异常信息
     * @param request   HttpServletRequest 对象
     * @param response  HttpServletResponse 对象
     */
    void handler(IModel<?> model, Throwable exception, HttpServletRequest request, HttpServletResponse response);
}
