package com.eric.learn.javacore.annotation;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ButtonFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3998353239444383902L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	private JPanel panel;
	private JButton yellowButton;
	private JButton redButton;
	private JButton blueButton;

	public ButtonFrame(){
		setTitle("ButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		add(panel);
		
		yellowButton = new JButton("Yellow");
		blueButton = new JButton("Blue");
		redButton = new JButton("Red");
		
		panel.add(yellowButton);
		panel.add(blueButton);
		panel.add(redButton);
		
		ActionListenerInstaller.processAnnotation(this);
	}
	
	@ActionListenerFor(source="yellowButton")
	public void yelloBackground(){
		panel.setBackground(Color.YELLOW);
	}
	
	@ActionListenerFor(source="blueButton")
	public void blueBackground(){
		panel.setBackground(Color.BLUE);
	}
	
	@ActionListenerFor(source="redButton")
	public void redBackground(){
		panel.setBackground(Color.red);
	}
	
	public static void main(String[] args) {
		JFrame frame = new ButtonFrame();
		frame.setVisible(true);
	}
}
