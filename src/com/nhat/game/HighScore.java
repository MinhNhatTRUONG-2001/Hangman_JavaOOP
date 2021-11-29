package com.nhat.game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.*;

public class HighScore {
	HighScore(JFrame f) {
		f.getContentPane().removeAll();
		f.repaint();
		
		//High Score title
		JPanel pt = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		JLabel hsTitle = new JLabel("HIGHSCORE - TOP 10");
		hsTitle.setFont(Main.m.titleFont);
		hsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pt.add(hsTitle);
		pt.setBackground(Main.m.bg);
		f.add(pt, BorderLayout.NORTH);
		
		//Read and sort records from highscore.txt file
		//- Read
		ArrayList<Record> records = new ArrayList<Record>();
		try {
			File hs = new File("highscore.txt");
			Scanner hsr = new Scanner(hs);
			while(hsr.hasNextLine()) {
				String temp = hsr.nextLine();
				String[] rDetails = temp.split("\\|");
				String rName = rDetails[0];
				long rScore = Long.parseLong(rDetails[1]);
				int rRound = Integer.parseInt(rDetails[2]);
				records.add(new Record(rName, rScore, rRound));
			}
			hsr.close();
		}
		catch (FileNotFoundException err) {
			JFrame errMes = new JFrame("Error!");
			JOptionPane.showMessageDialog(errMes, "Can't retrieve High Score data!");
			err.printStackTrace();
		}
		//- Sort
		records.sort(new ScoreThenRoundComparator());
		
		//Display high score
		JPanel phs = new JPanel(new GridLayout(11, 4, 0, 0));
		JPanel t1 = new JPanel();
		JLabel rank = new JLabel("Rank");
		rank.setForeground(Color.WHITE);
		rank.setFont(Main.m.textFont2);
		t1.add(rank);
		t1.setBackground(new Color(0, 100, 0));
		phs.add(t1);
		JPanel t2 = new JPanel();
		JLabel name = new JLabel("Name");
		name.setForeground(Color.WHITE);
		name.setFont(Main.m.textFont2);
		t2.add(name);
		t2.setBackground(new Color(0, 100, 0));
		phs.add(t2);
		JPanel t3 = new JPanel();
		JLabel score = new JLabel("Score");
		score.setForeground(Color.WHITE);
		score.setFont(Main.m.textFont2);
		t3.add(score);
		t3.setBackground(new Color(0, 100, 0));
		phs.add(t3);
		JPanel t4 = new JPanel();
		JLabel round = new JLabel("Round");
		round.setForeground(Color.WHITE);
		round.setFont(Main.m.textFont2);
		t4.add(round);
		t4.setBackground(new Color(0, 100, 0));
		phs.add(t4);
		int i;
		for (i = 0; (i < 10) ? (i < records.size()) : (i < 10) ; i++) {
			JPanel d1 = new JPanel();
			JLabel rk = new JLabel((i + 1) + "");
			rk.setFont(Main.m.textFont2);
			d1.add(rk);
			d1.setBackground(Main.m.bg2);
			phs.add(d1);
			JPanel d2 = new JPanel();
			JLabel n = new JLabel(records.get(i).getName());
			n.setFont(Main.m.textFont);
			d2.add(n);
			d2.setBackground(Main.m.bg2);
			phs.add(d2);
			JPanel d3 = new JPanel();
			JLabel s = new JLabel(records.get(i).getScore() + "");
			s.setFont(Main.m.textFont);
			d3.add(s);
			d3.setBackground(Main.m.bg2);
			phs.add(d3);
			JPanel d4 = new JPanel();
			JLabel rd = new JLabel(records.get(i).getRound() + "");
			rd.setFont(Main.m.textFont);
			d4.add(rd);
			d4.setBackground(Main.m.bg2);
			phs.add(d4);
		}
		while (i < 10) {
			JPanel d1 = new JPanel();
			JLabel rk = new JLabel((i + 1) + "");
			rk.setFont(Main.m.textFont2);
			d1.add(rk);
			d1.setBackground(Main.m.bg2);
			phs.add(d1);
			JPanel d2 = new JPanel();
			JLabel n = new JLabel("-");
			n.setFont(Main.m.textFont);
			d2.add(n);
			d2.setBackground(Main.m.bg2);
			phs.add(d2);
			JPanel d3 = new JPanel();
			JLabel s = new JLabel("-");
			s.setFont(Main.m.textFont);
			d3.add(s);
			d3.setBackground(Main.m.bg2);
			phs.add(d3);
			JPanel d4 = new JPanel();
			JLabel rd = new JLabel("-");
			rd.setFont(Main.m.textFont);
			d4.add(rd);
			d4.setBackground(Main.m.bg2);
			phs.add(d4);
			i++;
		}
		phs.setBackground(Main.m.bg);
		f.add(phs);
		
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
	
	//Used in array sort function. Player with higher score, or higher round if the scores are equal, comes first
	public class ScoreThenRoundComparator implements Comparator<Record> {
		@Override
		public int compare(Record r1, Record r2) {
			int result;
			if (r1.getScore() < r2.getScore()) {
				result = 1;
			}
			else if (r1.getScore() > r2.getScore()) {
				result = -1;
			}
			else {
				if (r1.getRound() < r2.getRound()) {
					result = 1;
				}
				else if (r1.getRound() > r2.getRound()) {
					result = -1;
				}
				else {
					result = 0;
				}
			}
			return result;
		}
	}
}
