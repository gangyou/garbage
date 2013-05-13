package com.eric.thinking.java.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.eric.thinking.java.generics.Generator;
import com.eric.thinking.java.util.RandomGenerator;

public class TextPane extends JFrame {
	private static final long serialVersionUID = -8518866529833579525L;
	private JTextPane tp = new JTextPane();
	private JButton b = new JButton("Add Text");
	private static Generator<String> sg = new RandomGenerator.String(7);

	public TextPane() {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 10; i++) {
					tp.setText(tp.getText() + sg.next() + "\n");
				}
			}
		});
		add(new JScrollPane(tp));
		add(BorderLayout.SOUTH, b);
	}

	public static void main(String[] args) {
		SwingConsole.run(new TextPane(), 475, 425);
	}
}
