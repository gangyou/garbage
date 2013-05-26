package com.eric.thinking.java.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Checkboxes extends JFrame {

	private static final long serialVersionUID = -2951817129362559666L;

	private JTextArea t = new JTextArea(6, 15);
	private JCheckBox cb1 = new JCheckBox("Check Box1"), cb2 = new JCheckBox(
			"Check Box 2"), cb3 = new JCheckBox("Check Box3");

	public Checkboxes() {
		cb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				trace("1", cb1);
			}
		});
		cb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				trace("2", cb2);
			}
		});

		cb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				trace("3", cb3);
			}
		});
		setLayout(new FlowLayout());
		SwingConsole.addAll(this, new JScrollPane(t), cb1, cb2, cb3);
	}

	protected void trace(String b, JCheckBox cb) {
		if (cb.isSelected()) {
			t.append("Box " + b + " Set \n");
		} else {
			t.append("Box " + b + " Cleared\n");
		}
	}

	public static void main(String[] args) {
		SwingConsole.run(new Checkboxes(), 200, 300);
	}
}
