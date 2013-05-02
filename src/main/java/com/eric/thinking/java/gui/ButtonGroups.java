package com.eric.thinking.java.gui;

import java.awt.FlowLayout;
import java.lang.reflect.Constructor;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class ButtonGroups extends JFrame {
	private static String[] ids = { "June", "Ward", "Beaver", "Wally", "Eddie",
			"Lumpy" };

	static JPanel makeBPanel(Class<? extends AbstractButton> kind, String[] ids) {
		ButtonGroup bg = new ButtonGroup();
		JPanel jp = new JPanel();
		String title = kind.getName();
		System.out.println(kind.getName());
		title = title.substring(title.lastIndexOf('.') + 1);
		jp.setBorder(new TitledBorder(title));
		for (String id : ids) {
			AbstractButton ab = new JButton("failed");
			try {
				Constructor<?> ctor = kind.getConstructor(String.class);
				ab = (AbstractButton) ctor.newInstance(id);
			} catch (Exception ex) {
				System.err.println("can't create " + kind);
			}
			bg.add(ab);
			jp.add(ab);
		}
		return jp;
	}

	public ButtonGroups() {
		setLayout(new FlowLayout());
		SwingConsole.addAll(this, makeBPanel(JButton.class, ids));
		SwingConsole.addAll(this, makeBPanel(JToggleButton.class, ids));
		SwingConsole.addAll(this, makeBPanel(JCheckBox.class, ids));
		SwingConsole.addAll(this, makeBPanel(JRadioButton.class, ids));
	}

	public static void main(String[] args) {
		SwingConsole.run(new ButtonGroups(), 500, 350);
	}
}
