package com.eric.thinking.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BufferedInputFile {
	public static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = in.readLine()) != null) {
			sb.append(s + "\n");
		}
		in.close();
		return sb.toString();
	}

	public static void printVerbose(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		List<String> lines = new ArrayList<String>();
		String s;
		while ((s = in.readLine()) != null) {
			lines.add(s.toUpperCase() + "\n");
		}
		Collections.reverse(lines);
		for (String line : lines) {
			System.out.print(line);
		}
		in.close();
	}

	public static void main(String[] args) throws IOException {
		System.out
				.print(read("src/main/java/com/eric/thinking/java/io/BufferedInputFile.java"));
		printVerbose("src/main/java/com/eric/thinking/java/io/BufferedInputFile.java");
	}
}
