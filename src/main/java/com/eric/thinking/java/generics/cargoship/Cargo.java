package com.eric.thinking.java.generics.cargoship;

import java.util.Random;

import com.eric.thinking.java.generics.Generator;

public class Cargo {
	private static long count = 0;
	private final long id = count++;
	private String description;

	public Cargo(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Cargo ID: , " + id + "Description: " + description + "";
	}
	
	public static Generator<Cargo> generator = new Generator<Cargo>(){
		private Random random = new Random(47);
		@Override
		public Cargo next() {
			return new Cargo("Test" + random.nextInt());
		}
		
	};
	
}
