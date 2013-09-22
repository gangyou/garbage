package com.eric.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class BiSearchWithForkJoin extends RecursiveAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1993470069023564550L;
	private final int threshold;
	private final BinarySearcher searcher;
	public int result;
	private final int numberToSearch;

	public BiSearchWithForkJoin(BinarySearcher searcher, int threshold, int numberToSearch){
		this.searcher = searcher;
		this.threshold = threshold;
		this.numberToSearch = numberToSearch;
	}

	@Override
	protected void compute(){
		if(searcher.size < threshold){
			result = searcher.search(numberToSearch);
		}else{
			int midPoint = searcher.size /2;
			BiSearchWithForkJoin left = new BiSearchWithForkJoin(searcher.sub(0, midPoint),
				threshold, numberToSearch);
			BiSearchWithForkJoin right = new BiSearchWithForkJoin(searcher.sub(midPoint+1, searcher.size), 
				threshold, numberToSearch);
			invokeAll(left, right);
			result = Math.max(left.result, right.result);
		}
	}

	private static final int[] data = new int[10000000];
	static{
		for(int i = 0; i < 10000000; i++){
			data[i] = i;
		}
	}

	public static void main(String[] args){
		BinarySearcher searcher = new BinarySearcher(data, 0, data.length);
		int threshold = 100;
		int nThread = 10;

		BiSearchWithForkJoin bswfj = new BiSearchWithForkJoin(searcher, threshold, 1000000);
		ForkJoinPool fjPool = new ForkJoinPool(nThread);
		fjPool.invoke(bswfj);
		System.out.printf("Result is: %d%n", bswfj.result);
	}
}