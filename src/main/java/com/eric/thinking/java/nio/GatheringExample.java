package com.eric.thinking.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GatheringExample {
	public static void main(String[] args) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("data/nio-new.txt", "rw");

		ByteBuffer bufferToWrite1 = ByteBuffer.allocate(10);
		for (int i = 0; i < 10; i++) {
			bufferToWrite1.put((byte) i);
		}

		ByteBuffer bufferToWrite2 = ByteBuffer.allocate(10);
		for (int i = 0; i < 10; i++) {
			bufferToWrite2.put((byte) i);
		}

		ByteBuffer[] buffers = { bufferToWrite1, bufferToWrite2 };

		FileChannel channel = aFile.getChannel();
		channel.write(buffers);

		aFile.close();
	}
}
