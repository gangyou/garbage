package com.eric.thinking.java.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;

public class Buttons extends JFrame {
	private static final long serialVersionUID = 7149234512231155729L;
	private JButton jb = new JButton("JButton");
	private BasicArrowButton up = new BasicArrowButton(BasicArrowButton.NORTH),
			down = new BasicArrowButton(BasicArrowButton.SOUTH),
			right = new BasicArrowButton(BasicArrowButton.EAST),
			left = new BasicArrowButton(BasicArrowButton.WEST);

	public Buttons() {
		setLayout(new FlowLayout());
		SwingConsole.addAll(this, jb, new JToggleButton("JToggleButton"),
				new JCheckBox("JCheckBox"), new JRadioButton("JRadioButton"));
		JPanel jp = new JPanel();
		jp.setBorder(new TitledBorder("Directions"));
		jp.add(up);
		jp.add(down);
		jp.add(left);
		jp.add(right);
		add(jp);
	}

	public static void main(String[] args) {
		SwingConsole.run(new Buttons(), 350, 200);
	}
}
