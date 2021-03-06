package com.bus.validate.rule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeValueValidate {

	String message () default "";
	
	String name () default "";
	
	String min ();
	
	String max ();
	
}
