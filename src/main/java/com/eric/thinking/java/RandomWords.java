package com.eric.thinking.java;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class RandomWords implements Readable {
	private static Random rand = new Random(47);
	private static final char[] captials = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char[] lower = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final char[] vowels = "aeiou".toCharArray();
	private int count;
	
	public RandomWords(int count){this.count = count;}
	@Override
	public int read(CharBuffer cb) throws IOException {
		if(count-- == 0){
			return -1;
		}
		cb.append(captials[rand.nextInt(captials.length)]);
		for(int i = 0; i < 4; i++){
			cb.append(vowels[rand.nextInt(vowels.length)]);
			cb.append(lower[rand.nextInt(lower.length)]);
		}
		cb.append("\n");
		return 10;
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(new RandomWords(10));
		while(s.hasNext()){
			System.out.println(s.next());
		}
	}
}
