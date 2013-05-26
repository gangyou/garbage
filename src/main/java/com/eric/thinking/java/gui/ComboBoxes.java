package com.eric.thinking.java.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ComboBoxes extends JFrame {

	private static final long serialVersionUID = 98999301509774301L;
	private String[] description = { "Ebullient", "Obtuse", "Recalcitrant",
			"Brillian", "Somescent", "Timorous", "Florid", "Put rescent" };
	private JTextField t = new JTextField(15);
	private JComboBox<String> c = new JComboBox<String>();
	private JButton b = new JButton("Add items");
	private int count = 0;

	public ComboBoxes() {
		for (int i = 0; i < 4; i++) {
			c.addItem(description[count++]);
		}
		t.setEditable(false);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (count < description.length) {
					c.addItem(description[count++]);
				}
			}
		});
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t.setText("index: " + c.getSelectedIndex() + " "
						+ ((JComboBox) e.getSource()).getSelectedItem());
			}
		});
		setLayout(new FlowLayout());
		SwingConsole.addAll(this, t, c, b);
	}

	public static void main(String[] args) {
		SwingConsole.run(new ComboBoxes(), 200, 175);
	}
}
