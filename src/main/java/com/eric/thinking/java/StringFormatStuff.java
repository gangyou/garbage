package com.eric.thinking.java;

public class StringFormatStuff {
	private int i = 1;
	private long l = 10L;
	private float f = 0.2f;
	private double d = 5.8d;
	
	@Override
	public String toString() {
		return String.format("%d %d %f %f", i, l, f, d);
	}
	
	public static void main(String[] args) {
		StringFormatStuff sfs = new StringFormatStuff();
		System.out.println(sfs);
	}
}
