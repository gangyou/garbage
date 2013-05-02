package com.eric.thinking.java.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.eric.thinking.java.util.Countries;

public class TextArea extends JFrame {

	private static final long serialVersionUID = -8978882550159091284L;
	private JButton b = new JButton("Add Data"), c = new JButton("Clear Data");
	private JTextArea t = new JTextArea(20, 40);
	private Map<String, String> map = new HashMap<String, String>();

	public TextArea() {
		map.putAll(Countries.capitals());
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Map.Entry<String, String> me : map.entrySet()) {
					t.append(me.getKey() + " : " + me.getValue() + "\n");
				}
			}
		});

		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.setText("");
			}
		});
		setLayout(new FlowLayout());
		SwingConsole.addAll(this, new JScrollPane(t), b, c);
	}

	public static void main(String[] args) {
		SwingConsole.run(new TextArea(), 475, 425);
	}
}
