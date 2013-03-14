package com.eric.thinking.java.generics;

public class CounterObject {
	private static int count = 0;
	private final int id = count++;
	public int id(){
		return id;
	}
	public String toString(){
		return "CounterObject " + id;
	}
}
