package com.eric.thinking.java.gui;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloSwing {
	static final Random rand = new Random(47);

	public static void main(String[] args) {
		JFrame frame = new JFrame("Hello Swing");
		for (int i = 0; i < rand.nextInt(); i++) {
			System.out.println(i);
			frame.add(new JLabel("" + i));
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setVisible(true);
	}
}
