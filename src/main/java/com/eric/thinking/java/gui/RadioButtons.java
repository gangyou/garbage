package com.eric.thinking.java.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RadioButtons extends JFrame {

	private static final long serialVersionUID = 2049506492512334568L;
	private JTextField t = new JTextField(15);
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton rb1 = new JRadioButton("one", false),
			rb2 = new JRadioButton("two", false), rb3 = new JRadioButton(
					"three", false);
	private ActionListener al = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			t.setText("Radio button "
					+ ((JRadioButton) e.getSource()).getText());
		}
	};

	public RadioButtons() {
		rb1.addActionListener(al);
		rb2.addActionListener(al);
		rb3.addActionListener(al);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		t.setEditable(false);
		setLayout(new FlowLayout());
		SwingConsole.addAll(this, t, rb1, rb2, rb3);
	}

	public static void main(String[] args) {
		SwingConsole.run(new RadioButtons(), 200, 125);
	}
}
