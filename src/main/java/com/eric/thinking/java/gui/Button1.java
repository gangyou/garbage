package com.eric.thinking.java.gui;

import static com.eric.thinking.java.gui.SwingConsole.run;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Button1 extends JFrame {

	private static final long serialVersionUID = 5810998039613539533L;
	private JButton b1 = new JButton("Button 1"), b2 = new JButton("Button 2");

	public Button1() {
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
	}

	public static void main(String[] args) {
		run(new Button1(), 200, 100);
	}
}
