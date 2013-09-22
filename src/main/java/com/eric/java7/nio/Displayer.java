package com.eric.java7.nio;

public class Displayer {
	protected static void println(Object msg){
		System.out.println(msg.toString());
	}

	protected static void println(String format, Object... args) {
		System.out.println(String.format(format, args));
	}
}
