package com.eric.thinking.java.generics;

public class BasicGeneratorDemo {
	public static void main(String[] args) {
		Generator<CounterObject> gen = BasicGenerator.create(CounterObject.class);
		for (int i =0 ;i < 10; i++){
			System.out.println(gen.next());
		}
		
		Generator<CounterObject> gen2 = new BasicGenerator<CounterObject>(CounterObject.class);
		for (int i =0 ;i < 10; i++){
			System.out.println(gen2.next());
		}
	}
}
