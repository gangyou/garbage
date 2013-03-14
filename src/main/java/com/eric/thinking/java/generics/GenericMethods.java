package com.eric.thinking.java.generics;

public class GenericMethods {
	public <T> void f(T x){
		System.out.println(x.getClass().getName());
	}
	
	public <X,Y,Z> void f(X x, Y y, String z){
		System.out.println(x.getClass().getName());
		System.out.println(y.getClass().getName());
		System.out.println(z.getClass().getName());
	}
	
	public static void main(String[] args) {
		GenericMethods  gm = new GenericMethods();
		gm.f("");
		gm.f(1);
		gm.f(1.0);
		gm.f(1.0F);
		gm.f('c');
		gm.f(gm);
		gm.f("", 1, "");
	}
}
