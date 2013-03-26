package com.eric.thinking.java.util;

import java.util.Random;

public class Enums {
	private static Random random = new Random(47);

	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	public static <T> T random(T[] values) {
		return values[random.nextInt(values.length)];
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(Enums.random(Activity.class));
		}
	}
}

enum Activity{
	SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FALLING, FLYINH
}
