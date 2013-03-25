package com.eric.thinking.java.containers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
	private static final int BSIZE = 1024;
	public static void main(String args[]) throws Exception{
		FileChannel fc = new FileOutputStream(new File("data.out")).getChannel();
		fc.write(ByteBuffer.wrap("Some text.".getBytes()));
		fc.close();
		
		fc = new RandomAccessFile("data.out", "rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		
		fc = new FileInputStream(new File("data.out")).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		fc.read(buffer);
		buffer.flip();
		while(buffer.hasRemaining()){
			System.out.print((char) buffer.get());
		}
	}
}
