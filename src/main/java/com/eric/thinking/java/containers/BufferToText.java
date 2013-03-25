package com.eric.thinking.java.containers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {
	private static final int BSIZE = 1024;

	public static void main(String[] args) throws Exception {
		FileChannel fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text ".getBytes()));
		fc.close();

		fc = new FileInputStream("data2.txt").getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		fc.read(buffer);
		buffer.flip();
		System.out.println(buffer.asCharBuffer());

		buffer.rewind();
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decode with " + encoding + ": "
				+ Charset.forName(encoding).decode(buffer));
		
	}
}
