package com.eric.forkjoin;

import java.util.Arrays;

public class BinarySearcher {

	private final int[] numbers;
	private final int start;
	private final int end;
	public final int size;

	public BinarySearcher(int[] numbers, int start, int end){
		this.numbers = numbers;
		this.start = start;
		this.end = end;
		this.size = end - start;
	}

	public int search(int numberToSearch){
		return Arrays.binarySearch(numbers, start, end, numberToSearch);
	}

	public BinarySearcher sub(int subStart, int subEnd){
		return new BinarySearcher(numbers, start+subStart, start+subEnd);
	}
}
