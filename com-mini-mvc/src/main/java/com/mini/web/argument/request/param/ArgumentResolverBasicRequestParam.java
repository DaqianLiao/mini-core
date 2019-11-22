package com.mini.web.argument.request.param;

import com.mini.core.util.StringUtil;
import com.mini.core.util.reflect.MiniParameter;
import com.mini.web.argument.ArgumentResolverBasic;
import com.mini.web.argument.annotation.RequestParam;
import com.mini.web.interceptor.ActionInvocation;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

@Singleton
public final class ArgumentResolverBasicRequestParam extends ArgumentResolverBasic {
    @Override
    public boolean supportParameter(MiniParameter parameter) {
        RequestParam param = parameter.getAnnotation(RequestParam.class);
        return param != null && super.supportParameter(parameter);
    }

    @Nonnull
    @Override
    protected String getParameterName(MiniParameter parameter) {
        RequestParam param = parameter.getAnnotation(RequestParam.class);
        if (param == null || StringUtil.isBlank(param.value())) {
            return parameter.getName();
        }
        return param.value();
    }

    @Override
    protected String getValue(String name, ActionInvocation invocation) {
        return invocation.getRequest().getParameter(name);
    }
}
