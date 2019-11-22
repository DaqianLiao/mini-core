package com.mini.core.util.reflect;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

public final class MiniParameter {
    @Nonnull
    private final Parameter parameter;
    private final String name;
    private Object value;

    public MiniParameter(@Nonnull Parameter parameter, String name) {
        this.parameter = parameter;
        this.name      = name;
    }

    @Nonnull
    public Parameter getParameter() {
        return parameter;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean equals(Object object) {
        if (object instanceof MiniParameter) {
            MiniParameter p = (MiniParameter) object;
            return parameter.equals(p.parameter);
        }
        if (object instanceof Parameter) {
            return parameter.equals(object);
        }
        return false;
    }

    public int hashCode() {
        return parameter.hashCode();
    }

    public boolean isNamePresent() {
        return parameter.isNamePresent();
    }

    public String toString() {
        return parameter.toString();
    }

    public Executable getDeclaringExecutable() {
        return parameter.getDeclaringExecutable();
    }

    public int getModifiers() {
        return parameter.getModifiers();
    }

    public String getName() {
        return name;
    }

    public Type getParameterizedType() {
        return parameter.getParameterizedType();
    }

    public Class<?> getType() {
        return parameter.getType();
    }

    public AnnotatedType getAnnotatedType() {
        return parameter.getAnnotatedType();
    }

    public boolean isImplicit() {
        return parameter.isImplicit();
    }

    public boolean isSynthetic() {
        return parameter.isSynthetic();
    }

    public boolean isVarArgs() {
        return parameter.isVarArgs();
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return parameter.getAnnotation(annotationClass);
    }

    public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        return parameter.getAnnotationsByType(annotationClass);
    }

    public Annotation[] getDeclaredAnnotations() {
        return parameter.getDeclaredAnnotations();
    }

    public <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        return parameter.getDeclaredAnnotation(annotationClass);
    }

    public <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        return parameter.getDeclaredAnnotationsByType(annotationClass);
    }

    public Annotation[] getAnnotations() {
        return parameter.getAnnotations();
    }
}
