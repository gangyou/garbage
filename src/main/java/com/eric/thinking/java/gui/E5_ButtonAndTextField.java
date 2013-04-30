package com.eric.thinking.java.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class E5_ButtonAndTextField extends JFrame {

	private static final long serialVersionUID = -8945368840177111L;
	final private JButton b1 = new JButton("Button 1"), b2 = new JButton(
			"Button 2"), b3 = new JButton("Button 3");
	final private JTextField txt = new JTextField(10);

	class ButtonListener implements ActionListener {

		private String display;

		public ButtonListener(String display) {
			this.display = display;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			txt.setText(display);
		}

	}

	public E5_ButtonAndTextField() {
		setLayout(new FlowLayout());
		b1.addActionListener(new ButtonListener("b1"));
		b2.addActionListener(new ButtonListener("b2"));
		b3.addActionListener(new ButtonListener("b3"));
		SwingConsole.addAll(this, b1, b2, b3, txt);
	}

	public static void main(String[] args) {
		SwingConsole.run(new E5_ButtonAndTextField(), 300, 150);
	}
}
