package com.mini.web.argument.defaults;

import com.mini.core.inject.annotation.Associated;
import com.mini.core.util.reflect.MiniParameter;
import com.mini.web.argument.ArgumentResolverBasic;
import com.mini.web.interceptor.ActionInvocation;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@Singleton
public final class ArgumentResolverBasicDefault extends ArgumentResolverBasic {
    @Override
    public boolean supportParameter(MiniParameter parameter) {
        for (var annotation : parameter.getAnnotations()) {
            if (annotation.getClass().getAnnotation( //
                    Associated.class) != null) {
                return false;
            }
        }
        return super.supportParameter(parameter);
    }

    @Nonnull
    @Override
    protected String getParameterName(MiniParameter parameter) {
        return parameter.getName();
    }

    @Override
    protected String getValue(String name, ActionInvocation invocation) {
        return defaultIfBlank(invocation.getRequest().getParameter(name), //
                invocation.getUriParameters().get(name));
    }
}
