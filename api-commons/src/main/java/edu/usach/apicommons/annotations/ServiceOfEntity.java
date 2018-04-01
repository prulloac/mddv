package edu.usach.apicommons.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
public @interface ServiceOfEntity {
	@AliasFor("entityName")
	String value() default "";

	@AliasFor("value")
	String entityName() default "";
}
