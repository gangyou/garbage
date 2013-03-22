package com.eric.thinking.java.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CharacterCounter {
	Map<Character, Integer> counter = new HashMap<Character, Integer>();

	public CharacterCounter(String fileName) {
		for (char ch : TextFile.read(fileName).toCharArray()) {
			if (counter.containsKey(ch)) {
				counter.put(ch, counter.get(ch) + 1);
			} else {
				counter.put(ch, 1);
			}
		}
	}

	public void display() {
		for (Entry<Character, Integer> item : counter.entrySet()) {
			char ch = item.getKey();
			int times = item.getValue();
			System.out.println(ch + " appears " + times + " times");
		}
	}

	public static void main(String[] args) {
		CharacterCounter c = new CharacterCounter(
				"src/main/java/com/eric/thinking/java/io/CharacterCounter.java");
		c.display();
	}
}
