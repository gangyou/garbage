package com.eric.thinking.java.annotation;

public class Testable {
	public void execute() {
		System.out.println("Executing...");
	}

	@Test
	void testExecute() {
		execute();
	}
}
