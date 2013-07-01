package com.eric.thinking.java.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class List extends JFrame {

	private static final long serialVersionUID = -6657779165368135972L;
	private String[] flavors = { "Chocolate", "Strawberry",
			"Vanilla Fudge Swirl", "Mint Chip", "Mocha Almond Fudge",
			"Rum Raisin", "Praline Cream", "Mud Pie" };
	private DefaultListModel<String> lItems = new DefaultListModel<String>();
	private JList<String> list = new JList<String>(lItems);
	private JTextArea t = new JTextArea(flavors.length, 20);
	private JButton b = new JButton("Add Item");
	private int count = 0;
	private ActionListener bl = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (count < flavors.length) {
				lItems.add(0, flavors[count++]);
			} else {
				// Disable, since there are no more
				// flavors left to be added to the list
				b.setEnabled(false);
			}
		}
	};
	private ListSelectionListener ll = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting())
				return;
			t.setText("");
			for (String item : list.getSelectedValuesList()) {
				t.append(item + "\n");
			}
		}
	};

	public List() {
		t.setEditable(false);
		setLayout(new FlowLayout());
		// Create borders for components
		Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);
		list.setBorder(brd);
		t.setBorder(brd);
		// Add the first four items to the list
		for (int i = 0; i < 4; i++) {
			lItems.addElement(flavors[count++]);
		}
		SwingConsole.addAll(this, t, list, b);
		// Register event listeners
		list.addListSelectionListener(ll);
		b.addActionListener(bl);
	}

	public static void main(String[] args) {
		SwingConsole.run(new List(), 250, 375);
	}
}
