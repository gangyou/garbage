package com.eric.thinking.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatteringExample {
	public static void main(String[] args) throws IOException {
		ByteBuffer buffer1 = ByteBuffer.allocate(6);
		ByteBuffer buffer2 = ByteBuffer.allocate(6);
		ByteBuffer[] buffers = { buffer1, buffer2 };

		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel channel = aFile.getChannel();

		channel.read(buffers);

		for (ByteBuffer b : buffers) {
			b.flip();
			while (b.hasRemaining()) {
				System.out.print((char) b.get());
			}
			System.out.println();
		}
		aFile.close();
	}
}
