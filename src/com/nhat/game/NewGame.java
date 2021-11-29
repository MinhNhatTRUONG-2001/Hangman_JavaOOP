package com.nhat.game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.nhat.game.DrawObjects.DrawBodyParts0;
import com.nhat.game.DrawObjects.DrawBodyParts1;
import com.nhat.game.DrawObjects.DrawBodyParts2;
import com.nhat.game.DrawObjects.DrawBodyParts3;
import com.nhat.game.DrawObjects.DrawBodyParts4;
import com.nhat.game.DrawObjects.DrawBodyParts5;
import com.nhat.game.DrawObjects.DrawCanvas;

public class NewGame {
	private Player you;
	private DrawCanvas figure;
	private Word wordList;
	private String guessingWord;
	private StringBuilder wordPattern;
	private JLabel displayScore;
	private JLabel displayMultiplier;
	private JLabel displayWord;
	private JLabel status;
	private int lettersRemaining;
	private JPanel bottom0;
	
	//Set up a new game - Round 1
	NewGame(JFrame f) {
		you = new Player(0, 1, 1, 0, 0, 6);
		wordList = new Word();
		
		gameOperation(f);
	}
	
	//Refresh everything in the frame for a new round
	public void newRound(JFrame f) {
		//Rewrite round and lives attributes
		you.setRound(you.getRound() + 1);
		you.setLives(6);
		you.setStreak_life(0);
		
		gameOperation(f);
	}
	
	//How everything in a round works
	public void gameOperation(JFrame f) {
		//repaint the frame and add an initial hang
		f.getContentPane().removeAll();
		f.repaint();
		figure = new DrawCanvas();
		figure.setSize(950, 650);
		f.add(figure);
		
		//choose a random word from the word list
		int id = (int)(Math.random() * wordList.wordBank.size());
		guessingWord = wordList.wordBank.get(id);
		wordList.wordBank.remove(id);
		
		//generate word pattern
		wordPattern = new StringBuilder("");
		for (int i = 0; i < guessingWord.length(); i++) {
			wordPattern.append("-");
		}
		
		//set initial letters remaining counter
		lettersRemaining = guessingWord.length();
		
		//generate and add objects (quit button, round number) on the top of the frame
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.setBackground(Main.m.bg);
		top.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		JButton quitGame = new JButton("Quit");
		quitGame.setBackground(Main.m.bg2);
		quitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame quitF = new JFrame();
				quitF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				int a = JOptionPane.showConfirmDialog(quitF, "Are you sure to quit this game? All your current progress will be lost (forever)!", 
						"Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (a == JOptionPane.YES_OPTION) {
					f.dispose();
					Main.m = new Main();
				}
				quitF.dispose();
			}
		});
		JLabel round = new JLabel("Round " + you.getRound());
		round.setHorizontalAlignment(JLabel.CENTER);
		round.setFont(Main.m.titleFont2);
		top.add(quitGame, BorderLayout.WEST);
		top.add(round, BorderLayout.CENTER);
		f.getContentPane().add(top, BorderLayout.NORTH);
		
		//generate and add information of the current game (score, multiplier, guessing word) on the right side of the frame
		JPanel info = new JPanel(new GridLayout(2, 1));
		info.setPreferredSize(new Dimension(400, 600));
		info.setBackground(Color.WHITE);
		Border infoBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		info.setBorder(infoBorder);
		JPanel scoreAndMul = new JPanel(new GridLayout(1, 2));
		scoreAndMul.setBackground(Color.WHITE);
		displayScore = new JLabel("  Score: " + you.getScore());
		displayMultiplier = new JLabel("  Multiplier: " + you.getMultiplier() + "x");
		displayWord = new JLabel("  Guessing word (" + guessingWord.length() + " letters): " + wordPattern);
		displayScore.setFont(Main.m.textFont2);
		displayMultiplier.setFont(Main.m.textFont2);
		displayWord.setFont(Main.m.textFont2);
		displayScore.setBorder(infoBorder);
		displayMultiplier.setBorder(infoBorder);
		displayWord.setBorder(infoBorder);
		scoreAndMul.add(displayScore);
		scoreAndMul.add(displayMultiplier);
		info.add(scoreAndMul);
		info.add(displayWord);
		f.getContentPane().add(info, BorderLayout.EAST);
		
		//generate and add status field and letter buttons on the bottom of the frame
		JPanel bottom = new JPanel(new GridLayout(3, 1));
		bottom0 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
		JPanel bottom1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel bottom2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		bottom0.setBackground(Main.m.bg2);
		bottom0.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		bottom1.setBackground(Main.m.bg);
		bottom2.setBackground(Main.m.bg);
		status = new JLabel("Click a letter below to guess");
		status.setHorizontalAlignment(JLabel.CENTER);
		status.setFont(Main.m.textFont2);
		JButton next = new JButton("Next Round");
		next.setBackground(Color.YELLOW);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newRound(f);
			}
		});
		bottom0.add(status);
		//Commands when a button is clicked
		ActionListener l = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton letter = (JButton) e.getSource();
				letter.setEnabled(false);
				String s = letter.getText();
				char c = s.charAt(0);
				boolean isFound = false;
				int correctLetters = 0;
				//Add letters if the guess is correct
				for (int i = 0; i < guessingWord.length(); i++) {
					if (guessingWord.charAt(i) == c) {
						isFound = true;
						wordPattern.setCharAt(i, c);
						correctLetters++;
						lettersRemaining--;
					}
				}
				displayWord.setText("  Guessing word (" + guessingWord.length() + " letters): " + wordPattern);
				if (isFound == true) { //If the player answer correctly
					status.setText("Correct! " + lettersRemaining + (lettersRemaining > 1 ? " letters " : " letter ") + "left.");
					status.setForeground(Color.GREEN);
					//100 is the base score for each correct letter (will be increased based on numbers of correct letters and multiplier)
					you.addScore(100, correctLetters);
					you.setStreak_mul(you.getStreak_mul() + 1);
					//+0.25x whenever you answer correctly 2 letters in a row
					if (you.getStreak_mul() == 2) {
						you.changeMultiplier(0.25);
						you.setStreak_mul(0);
					}
					//+1 life whenever you answer correctly 3 letters in a row
					you.setStreak_life(you.getStreak_life() + 1);
					if (you.getStreak_life() == 3) {
						you.changeLives(1);
						JPanel d;
						switch (you.getLives()) {
						case 6:
							d = new DrawCanvas();
							f.add(d);
							break;
						case 5:
							d = new DrawBodyParts5();
							f.add(d);
							break;
						case 4:
							d = new DrawBodyParts4();
							f.add(d);
							break;
						case 3:
							d = new DrawBodyParts3();
							f.add(d);
							break;
						case 2:
							d = new DrawBodyParts2();
							f.add(d);
							break;
						case 1:
							d = new DrawBodyParts1();
							f.add(d);
							break;
						}
						you.setStreak_life(0);
					}
					displayScore.setText("  Score: " + you.getScore());
					displayMultiplier.setText("  Multiplier: " + you.getMultiplier() + "x");
					if (lettersRemaining == 0) {
						//Show congrats message on the status bar
						status.setText("YAY!!! You have completed this round!");
						status.setForeground(new Color(0, 100, 0));
						//Show Next Round button if there is/are word(s) available to solve...
						if (wordList.wordBank.size() > 0) {
							bottom0.add(next);
							Component[] letterButtons1 = bottom1.getComponents();
							for (Component b : letterButtons1) {
								b.setEnabled(false);
							}
							Component[] letterButtons2 = bottom2.getComponents();
							for (Component b : letterButtons2) {
								b.setEnabled(false);
							}
						}
						//...or show congratulation dialog box if the player solved all words
						else {
							JFrame gameoverF = new JFrame();
							String name;
							do {
								name = JOptionPane.showInputDialog(gameoverF, "You have solved all " + you.getRound() + " words.\nYour final score is "
										+ you.getScore() + ".\nEnter your name to save your score in High Score list.", "Congratulation!", JOptionPane.QUESTION_MESSAGE);
								if (name == null) {
									name = "Anonymous Player";
								}
							} while (name.contains("|"));
							//Save to highscore.txt file
							try {
								File hs = new File("highscore.txt");
								if (!hs.exists()) {
									hs.createNewFile();
								}
								FileWriter hsw = new FileWriter(hs, true);
								hsw.append(name + "|" + you.getScore() + "|" + you.getRound() + "\n");
								hsw.flush();
								hsw.close();
							}
							catch (IOException err) {
								JFrame error = new JFrame("Error!");
								JOptionPane.showMessageDialog(error, "An error occurred");
								err.printStackTrace();
							}
							f.dispose();
							Main.m = new Main();
							gameoverF.dispose();
						}
					}
				}
				else { //If the player makes a wrong guess
					you.changeLives(-1);
					status.setText("Incorrect! " + you.getLives() + (you.getLives() > 1 ? " lives" : " life") + " left.");
					status.setForeground(Color.RED);
					//-0.25x if multiplier streak is 0, otherwise just reset multiplier streak to 0
					if (you.getStreak_mul() == 0) {
						you.changeMultiplier(-0.25);
					}
					else {
						you.setStreak_mul(0);
					}
					you.setStreak_life(0);
					JPanel d;
					switch (you.getLives()) {
					case 5:
						d = new DrawBodyParts5();
						f.add(d);
						break;
					case 4:
						d = new DrawBodyParts4();
						f.add(d);
						break;
					case 3:
						d = new DrawBodyParts3();
						f.add(d);
						break;
					case 2:
						d = new DrawBodyParts2();
						f.add(d);
						break;
					case 1:
						d = new DrawBodyParts1();
						f.add(d);
						break;
					case 0:
						d = new DrawBodyParts0();
						f.add(d);
						break;
					}
					displayScore.setText("  Score: " + you.getScore());
					displayMultiplier.setText("  Multiplier: " + you.getMultiplier() + "x");
					if (you.getLives() == 0) {
						//Show game over message (+ final score, number of round passed), input player's name in a dialog
						JFrame gameoverF = new JFrame();
						String name = " ";
						do {
							name = JOptionPane.showInputDialog(gameoverF, "You run out of lives and the game is over.\nYou passed "
									+ (you.getRound() - 1) + (you.getRound() - 1 > 1 ? " rounds" : " round") + " and your final score is "
									+ you.getScore() + ".\nEnter your name to save your score in High Score list.", "Game over!", JOptionPane.QUESTION_MESSAGE);
							if (name == null || name.trim() == "") {
								name = "Anonymous Player";
							}
						} while (name.contains("|"));

						//Save to highscore.txt file
						try {
							File hs = new File("highscore.txt");
							if (!hs.exists()) {
								hs.createNewFile();
							}
							FileWriter hsw = new FileWriter(hs, true);
							hsw.append(name + "|" + you.getScore() + "|" + (you.getRound() - 1) + "\n");
							hsw.flush();
							hsw.close();
						}
						catch (IOException err) {
							JFrame error = new JFrame("Error!");
							JOptionPane.showMessageDialog(error, "An error occurred");
							err.printStackTrace();
						}
						f.dispose();
						Main.m = new Main();
						gameoverF.dispose();
					}
				}
			}
		};
		JButton A = new JButton("A");
		A.setBackground(Main.m.bg2);
		A.addActionListener(l);
		JButton B = new JButton("B");
		B.setBackground(Main.m.bg2);
		B.addActionListener(l);
		JButton C = new JButton("C");
		C.setBackground(Main.m.bg2);
		C.addActionListener(l);
		JButton D = new JButton("D");
		D.setBackground(Main.m.bg2);
		D.addActionListener(l);
		JButton E = new JButton("E");
		E.setBackground(Main.m.bg2);
		E.addActionListener(l);
		JButton F = new JButton("F");
		F.setBackground(Main.m.bg2);
		F.addActionListener(l);
		JButton G = new JButton("G");
		G.setBackground(Main.m.bg2);
		G.addActionListener(l);
		JButton H = new JButton("H");
		H.setBackground(Main.m.bg2);
		H.addActionListener(l);
		JButton I = new JButton("I");
		I.setBackground(Main.m.bg2);
		I.addActionListener(l);
		JButton J = new JButton("J");
		J.setBackground(Main.m.bg2);
		J.addActionListener(l);
		JButton K = new JButton("K");
		K.setBackground(Main.m.bg2);
		K.addActionListener(l);
		JButton L = new JButton("L");
		L.setBackground(Main.m.bg2);
		L.addActionListener(l);
		JButton M = new JButton("M");
		M.setBackground(Main.m.bg2);
		M.addActionListener(l);
		JButton N = new JButton("N");
		N.setBackground(Main.m.bg2);
		N.addActionListener(l);
		JButton O = new JButton("O");
		O.setBackground(Main.m.bg2);
		O.addActionListener(l);
		JButton P = new JButton("P");
		P.setBackground(Main.m.bg2);
		P.addActionListener(l);
		JButton Q = new JButton("Q");
		Q.setBackground(Main.m.bg2);
		Q.addActionListener(l);
		JButton R = new JButton("R");
		R.setBackground(Main.m.bg2);
		R.addActionListener(l);
		JButton S = new JButton("S");
		S.setBackground(Main.m.bg2);
		S.addActionListener(l);
		JButton T = new JButton("T");
		T.setBackground(Main.m.bg2);
		T.addActionListener(l);
		JButton U = new JButton("U");
		U.setBackground(Main.m.bg2);
		U.addActionListener(l);
		JButton V = new JButton("V");
		V.setBackground(Main.m.bg2);
		V.addActionListener(l);
		JButton W = new JButton("W");
		W.setBackground(Main.m.bg2);
		W.addActionListener(l);
		JButton X = new JButton("X");
		X.setBackground(Main.m.bg2);
		X.addActionListener(l);
		JButton Y = new JButton("Y");
		Y.setBackground(Main.m.bg2);
		Y.addActionListener(l);
		JButton Z = new JButton("Z");
		Z.setBackground(Main.m.bg2);
		Z.addActionListener(l);
		bottom1.add(A);
		bottom1.add(B);
		bottom1.add(C);
		bottom1.add(D);
		bottom1.add(E);
		bottom1.add(F);
		bottom1.add(G);
		bottom1.add(H);
		bottom1.add(I);
		bottom1.add(J);
		bottom1.add(K);
		bottom1.add(L);
		bottom1.add(M);
		bottom2.add(N);
		bottom2.add(O);
		bottom2.add(P);
		bottom2.add(Q);
		bottom2.add(R);
		bottom2.add(S);
		bottom2.add(T);
		bottom2.add(U);
		bottom2.add(V);
		bottom2.add(W);
		bottom2.add(X);
		bottom2.add(Y);
		bottom2.add(Z);
		bottom.add(bottom0);
		bottom.add(bottom1);
		bottom.add(bottom2);
		f.getContentPane().add(bottom, BorderLayout.SOUTH);
		
		//Additional setting for the frame
		f.setBackground(new Color(245, 255, 250));
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent w) {
				JFrame exitF = new JFrame();
				exitF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				int a = JOptionPane.showConfirmDialog(exitF, "Are you sure to close this application?\nAll your current progress will be lost (forever)!",
						"Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (a == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				else {
					f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}
}