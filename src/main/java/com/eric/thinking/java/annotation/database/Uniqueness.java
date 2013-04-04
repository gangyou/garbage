package com.eric.thinking.java.annotation.database;

public @interface Uniqueness {
	Constraints constrains() default @Constraints(unique=true);
}
