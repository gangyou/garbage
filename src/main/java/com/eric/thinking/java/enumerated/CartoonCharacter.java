package com.eric.thinking.java.enumerated;

import java.util.Random;

import com.eric.thinking.java.generics.Generator;

public enum CartoonCharacter implements Generator<CartoonCharacter> {
	SLAPPY, SPANKU, PUNCHY, SILLY, BOUNCY, BUTTY, BOB;

	private static Random random = new Random(47);

	@Override
	public CartoonCharacter next() {
		return values()[random.nextInt(values().length)];
	}
	
	public static void main(String[] args) {
		CartoonCharacter cc = CartoonCharacter.BOB;
		Generator<CartoonCharacter> g = (Generator<CartoonCharacter>) cc;
		for(int i = 0; i < 10; i++){
			System.out.println(g.next());
		}
	}
}
