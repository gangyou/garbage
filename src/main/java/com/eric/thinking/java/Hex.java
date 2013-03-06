package com.eric.thinking.java;

public class Hex {
	public static String format(byte[] data) {
		StringBuilder result = new StringBuilder();
		int n = 0;
		for (byte b : data) {
			// 以16为一行
			if (n % 16 == 0) {
				printHexLineStarter(result, n);
			}
			printByteInHex(result, b);
			// 输出换行
			n++;
			if(n % 16 == 0){
				result.append("\n");
			}
		}
		return result.toString();
	}

	private static void printByteInHex(StringBuilder result, byte b) {
		// 打印一个byte
		result.append(String.format("%02X ", b));
	}

	private static void printHexLineStarter(StringBuilder result, int n) {
		// 打印序号，以16为基，5位16进制
		result.append(String.format("%05X: ", n));
	}

	public static void main(String[] args) {
		System.out.println(Hex.format("Hello world".getBytes()));
	}
}
