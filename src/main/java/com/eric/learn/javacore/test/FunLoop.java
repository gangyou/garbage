package com.eric.learn.javacore.test;

public class FunLoop {
	public static void main(String[] args) {
		long[] huge_array = new long[(int) Math.pow(2, 25)];
		buildHugeArray(huge_array);
		
		sequenceVisit(huge_array);
		batchVisitBy3(huge_array);
		batchVisitBy7(huge_array);
	}
	
	private static void batchVisitBy3(long[] huge_array){
		long start = System.currentTimeMillis();
		long length = huge_array.length;
		long loop_times = length - length % 3;
		for(int i = 0; i < loop_times; i+= 3){
			long l = huge_array[i];
			l = huge_array[i + 1];
			l = huge_array[i + 2];
		}
		long end = System.currentTimeMillis();
		System.out.println("3 times batch visit cost " + (end -start) + " milliseconds");
	}

	private static void batchVisitBy7(long[] huge_array) {
		long start = System.currentTimeMillis();
		long length = huge_array.length;
		long loop_times = length - length % 7;
		for(int i = 0; i < loop_times; i+= 7){
			long l = huge_array[i];
			l = huge_array[i + 1];
			l = huge_array[i + 2];
			l = huge_array[i + 3];
			l = huge_array[i + 4];
			l = huge_array[i + 5];
			l = huge_array[i + 6];
		}
		long end = System.currentTimeMillis();
		System.out.println("7 times batch visit cost " + (end -start) + " milliseconds");
	}

	private static void sequenceVisit(long[] huge_array) {
		long start = System.currentTimeMillis();
		long length = huge_array.length;
		for(int i =0 ; i< length; i++){
			long l = huge_array[i];
		}
		long end = System.currentTimeMillis();
		System.out.println("sequence visit cost " + (end -start) + " milliseconds");
	}

	private static void buildHugeArray(long[] huge_array) {
		for(int i=0; i < huge_array.length; i++){
			huge_array[i] = (long) (Math.random() * Integer.MAX_VALUE);
		}
	}
}
