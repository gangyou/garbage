package com.eric.thinking.java.typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ShowMethods {
	public static void main(String args[]){
		if(args.length < 1){
			throw new IllegalArgumentException("Please tell me which class you want to know!");
		}
		try{
			Class<?> clazz = Class.forName(args[0]);
			Method[] methods = clazz.getDeclaredMethods();
			Constructor<?>[] constructors = clazz.getConstructors();
			for(Method m : methods){
				System.out.println(m.toString());
			}
			for(Constructor<?> c : constructors){
				System.out.println(c.toString());
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace(System.out);
		}
	}
}
