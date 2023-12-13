package asmr.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
public @interface Service {
	String name();
}