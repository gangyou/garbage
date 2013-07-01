package com.eric.thinking.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TansformExample {
	public static void main(String[] args) throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("data/nio-data.txt",
				"rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("data/nio-to.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		toChannel.transferFrom(fromChannel, position, count);
		// fromChannel.transferTo(position, count, target)

		fromFile.close();
		toFile.close();
	}
}
