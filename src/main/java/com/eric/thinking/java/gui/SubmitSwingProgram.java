package com.eric.thinking.java.gui;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SubmitSwingProgram extends JFrame {
	private static final long serialVersionUID = -3442955257246893507L;
	final JLabel label;
	static SubmitSwingProgram ssp;

	public SubmitSwingProgram() {
		super("Hello World");
		label = new JLabel("A Label");
		add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 100);
		setVisible(true);
	}

	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ssp = new SubmitSwingProgram();
			}

		});
		TimeUnit.SECONDS.sleep(1);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ssp.label.setText("Hey! It is Different!");
			}

		});
	}
}
