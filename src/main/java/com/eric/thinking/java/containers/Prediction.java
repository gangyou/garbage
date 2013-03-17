package com.eric.thinking.java.containers;

import java.util.Random;

public class Prediction {
	private static Random random = new Random(47);
	private boolean shadown = random.nextDouble() > 0.5;

	@Override
	public String toString() {
		if (shadown) {
			return "Six more weeks of Winter.";
		} else {
			return "Early Spring.";
		}
	}
	
	public static void main(String[] args) {
		Random random = new Random(47);
		System.out.println(random.nextInt(100));
		System.out.println(random.nextInt(100));
		System.out.println(random.nextInt(100));
	}
}
