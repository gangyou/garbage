package com.eric.thinking.java.gui;

import static com.eric.thinking.java.gui.SwingConsole.run;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class E3_SubmitSwingPrograme extends JFrame {
	private static final long serialVersionUID = -307030082066888322L;
	JLabel label;

	public E3_SubmitSwingPrograme() {
		this.label = new JLabel("A label");
		add(label);
	}

	public static void main(String[] args) throws InterruptedException {
		final E3_SubmitSwingPrograme ssp = new E3_SubmitSwingPrograme();
		run(ssp, 300, 100);
		TimeUnit.SECONDS.sleep(1);
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				ssp.label.setText("Hey! It is Different!");
			}
		});
	}
}
