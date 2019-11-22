package com.mini.web.argument.request.uri;

import com.mini.core.util.reflect.MiniParameter;
import com.mini.web.argument.ArgumentResolver;
import com.mini.web.argument.annotation.RequestParam;
import com.mini.web.interceptor.ActionInvocation;

import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Named
@Singleton
public final class ArgumentResolverMapRequestUri implements ArgumentResolver {

    @Override
    public boolean supportParameter(MiniParameter parameter) {
        if (Map.class != parameter.getType()) {
            return false;
        }
        if (parameter.getAnnotation(RequestParam.class) == null) {
            return false;
        }
        Type type = parameter.getParameterizedType();
        if (type instanceof ParameterizedType) {
            var t = (ParameterizedType) type;
            var arr = t.getActualTypeArguments();
            if (arr == null || arr.length != 2) {
                return false;
            }
            if (!arr[0].getTypeName().equals(String.class.getName())) {
                return false;
            }
            return arr[1].getTypeName().equals(String.class.getName());
        }
        return false;
    }

    @Override
    public Object getValue(MiniParameter parameter, ActionInvocation invocation) {
        return new HashMap<>(invocation.getUriParameters());
    }
}
