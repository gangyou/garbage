package com.eric.thinking.java.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestEOF {
	public static void main(String[] args) throws IOException {
		DataInputStream in = new DataInputStream(
				new BufferedInputStream(new FileInputStream(
						"src/main/java/com/eric/thinking/java/io/TestEOF.java")));
		while (in.available() != 0) {
			System.out.print((char) in.readByte());
		}
		in.close();
	}
}
