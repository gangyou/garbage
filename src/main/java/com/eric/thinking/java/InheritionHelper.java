package com.eric.thinking.java;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class InheritionHelper {

	public static void tree(Class clazz){
		Queue<String> stack = new ArrayBlockingQueue<String>(20);
		Class up = clazz;
		stack.add(up.getCanonicalName());
		while(up.getSuperclass() != null){
			up = up.getSuperclass();
			stack.add(up.getCanonicalName());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InheritionHelper.tree(new String().getClass());
	}

}
