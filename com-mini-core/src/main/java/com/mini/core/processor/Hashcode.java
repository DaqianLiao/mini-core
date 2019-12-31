package com.mini.core.processor;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface Hashcode {
	/**
	 * 是否包含父类的字段
	 * @return true-j是(默认-true)
	 */
	boolean includeSuper() default true;
}
