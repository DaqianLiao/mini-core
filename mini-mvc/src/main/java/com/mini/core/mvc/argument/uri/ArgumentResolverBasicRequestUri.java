package com.mini.core.mvc.argument.uri;

import com.mini.core.mvc.annotation.Param;
import com.mini.core.mvc.argument.ArgumentResolverBasic;
import com.mini.core.mvc.interceptor.ActionInvocation;
import com.mini.core.mvc.support.config.Configures;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("DuplicatedCode")
public final class ArgumentResolverBasicRequestUri extends ArgumentResolverBasic {

    @Autowired
    public ArgumentResolverBasicRequestUri(@NotNull Configures configures) {
        super(configures);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public boolean supportParameter(MethodParameter parameter) {
        Param param = parameter.getParameterAnnotation(Param.class);
        if (param == null || param.value() != Param.Value.URI) {
            return false;
        }
        return super.supportParameter(parameter);
    }

    @Override
    protected String getValue(String name, ActionInvocation invocation) {
        return invocation.getRequest().getParameter(name);
    }
}
