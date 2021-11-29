package com.nhat.game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	
	static Main m;
	Font titleFont = new Font("", Font.BOLD, 32);
	Font textFont = new Font("", Font.PLAIN, 12);
	Font titleFont2 = new Font("", Font.BOLD, 24);
	Font textFont2 = new Font("", Font.BOLD, 16);
	Font buttonFont = new Font("", Font.BOLD, 18);
	Color bg = new Color(204, 255, 102); //Light green
	Color bg2 = new Color(255, 255, 224); //Light yellow
	
	Main() {
		//create a new main menu frame
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout(0, 20));
		f.setTitle("Hangman Game - IT & Technology Collection v1.0 by Truong Minh Nhat");
		f.getContentPane().setBackground(bg);
		
		//name of the game
		JPanel pt = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		JLabel gameName = new JLabel("HANGMAN - IT & TECHNOLOGY COLLECTION");
		gameName.setFont(titleFont);
		gameName.setHorizontalAlignment(SwingConstants.CENTER);
		pt.add(gameName);
		pt.setBackground(bg);
		f.add(pt, BorderLayout.NORTH);
		
		//four buttons corresponding to four features
		JPanel pb = new JPanel(new GridLayout(4, 3, 100, 30));
		
		JButton newGame = new JButton("New Game");
		newGame.setBackground(Color.YELLOW);
		newGame.setFont(buttonFont);
		newGame.setHorizontalAlignment(SwingConstants.CENTER);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewGame(f);
			}
		});
		
		JButton highScore = new JButton("High Score");
		highScore.setBackground(bg2);
		highScore.setFont(buttonFont);
		highScore.setHorizontalAlignment(SwingConstants.CENTER);
		highScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HighScore(f);
			}
		});
		
		JButton instruction = new JButton("Instruction");
		instruction.setBackground(bg2);
		instruction.setFont(buttonFont);
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		instruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Instruction(f);
			}
		});
		
		JButton exit = new JButton("Exit");
		exit.setBackground(bg2);
		exit.setFont(buttonFont);
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//arrange four buttons to the correct place
		for (int i = 1; i <= 12; i++) { 
			switch (i) {
			case 2:
				pb.add(newGame);
				break;
			case 5:
				pb.add(highScore);
				break;
			case 8:
				pb.add(instruction);
				break;
			case 11:
				pb.add(exit);
				break;
			default: //empty grids are filled by small JPanels
				JPanel empty = new JPanel();
				empty.setBackground(bg); //avoid leaving gray holes on the screen
				pb.add(empty);
			}
		}
		
		pb.setBackground(bg);
		f.add(pb, BorderLayout.CENTER);
		
		//copyright text line
		JPanel pc = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		JLabel copyright = new JLabel("(C) 2021 Truong Minh Nhat");
		copyright.setFont(textFont);
		copyright.setHorizontalAlignment(SwingConstants.CENTER);
		pc.add(copyright);
		pc.setBackground(bg);
		f.add(pc, BorderLayout.SOUTH);
		
		//Additional setting for main JFrame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(950, 650);
		f.setResizable(false);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		m = new Main();
	}

}
