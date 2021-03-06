package com.mini.core.mvc.validation;

import javax.annotation.Nonnull;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.Set;

import static java.util.Optional.ofNullable;

public final class MiniConstraintViolationException extends ConstraintViolationException {
    public MiniConstraintViolationException(@Nonnull ConstraintViolationException exception) {
        super(exception.getMessage(), exception.getConstraintViolations());
    }

    @Nonnull
    public final String getFirstErrorMessage() throws RuntimeException {
        return ofNullable(getConstraintViolations()).map(Set::iterator)
                .filter(Iterator::hasNext).map(Iterator::next)
                .map(ConstraintViolation::getMessage)
                .filter(it -> !it.isBlank())
                .orElse("Bad Request");
    }
}
