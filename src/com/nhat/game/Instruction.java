package com.nhat.game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Instruction {
	Instruction(JFrame f) {
		f.getContentPane().removeAll();
		f.repaint();
		
		//Instruction title
		JPanel pt = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		JLabel hsTitle = new JLabel("INSTRUCTION");
		hsTitle.setFont(Main.m.titleFont);
		hsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pt.add(hsTitle);
		pt.setBackground(Main.m.bg);
		f.add(pt, BorderLayout.NORTH);
		
		//Instruction content
		JLabel inst = new JLabel("<html><div style='padding-left: 30px; padding-right: 30px; text-align: justify;'>"
				+ "In each round, the hidden word and its hidden letters are located on the right side of the screen. "
				+ "To guess the letters of the hidden word, simply click any of letter buttons on the bottom of the screen.<br><br>"
				+ "Each correct letter gives 100 points. Multiple letters of the same type are stacked, which means, for example, "
				+ "if you guess letter 'A', and there are 2 letters 'A' in the word, you will get 100 * 2 = 200 points.<br><br>"
				+ "Your score is also affected by the multiplier. For each 2 correct guesses streak, you will get 25% bonus points "
				+ "(maximum 100%) for each next correct guess. However, if you guess any letter wrong, your streak is set to 0, and "
				+ "if you guess wrong again, the multiplier decreases by 25%.<br><br>"
				+ "You have 6 lives (equivalent to 6 parts of the figure's body) in each round. The game is over when you run out of "
				+ "lives in any round. For each 3 correct guess streak, one life is added (a part of the body is revived).</div></html>");
		inst.setFont(Main.m.textFont2);
		f.add(inst, BorderLayout.CENTER);
		
		//"Back to main menu" button
		JPanel pb = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		JButton back = new JButton("Back to main menu");
		back.setBackground(Main.m.bg2);
		back.setHorizontalAlignment(SwingConstants.CENTER);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					f.dispose();
					Main.m = new Main();
			}
		});
		pb.add(back);
		pb.setBackground(Main.m.bg);
		f.add(pb, BorderLayout.SOUTH);
		
		f.setVisible(true);
	}
}
