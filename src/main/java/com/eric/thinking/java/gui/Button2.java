package com.eric.thinking.java.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import static com.eric.thinking.java.gui.SwingConsole.*;

public class Button2 extends JFrame {
	private static final long serialVersionUID = -7835207563399620680L;
	private JButton b1 = new JButton("Button 1"), b2 = new JButton("Button 2");
	private JTextField txt = new JTextField(10);

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = ((JButton) e.getSource()).getText();
			txt.setText(name);
		}

	}

	private ButtonListener listener = new ButtonListener();

	public Button2() {
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		setLayout(new FlowLayout());
		addAll(this, b1, b2, txt);
	}

	public static void main(String[] args) {
		run(new Button2(), 200, 150);
	}
}
