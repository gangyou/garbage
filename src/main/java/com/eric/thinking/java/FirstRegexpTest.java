package com.eric.thinking.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstRegexpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Arline ate eight apples and one orange while Anita hasn't any";
		Pattern p = Pattern.compile("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b");
		Matcher m = p.matcher(text);
		System.out.println(m.matches());
	}

}
