package com.benefire.framework.repository.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.benefire.framework.repository.id.GeneratorType;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface GeneratedValue {
	
	GeneratorType generator() default GeneratorType.SNOWFLAKE_IP;

}
