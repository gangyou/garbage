package com.eric.thinking.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelExample {
	public static void main(String[] args) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		// create a buffer with capacity of 48 bytes
		ByteBuffer buffer = ByteBuffer.allocate(48);

		// read into buffer
		int bytesRead = inChannel.read(buffer);
		while (bytesRead != -1) {
			System.out.println("Read " + bytesRead);
			
			// make buffer ready to read
			buffer.flip();

			while (buffer.hasRemaining()) {
				// read 1 byte at a time
				System.out.print((char) buffer.get());
			}
			// make buffer ready to write
			buffer.clear();
			bytesRead = inChannel.read(buffer);
		}
		aFile.close();
	}
}
