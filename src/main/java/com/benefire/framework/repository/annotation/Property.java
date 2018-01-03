package com.benefire.framework.repository.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Property {
	/**
	 * gnore property
	 * @return
	 */
	String [] gnores() default "";
	/**
	 * include property
	 * @return
	 */
	String[] includes() default "";

}
