package com.nhat.game;

public class Record {
	private String name;
	private long score;
	private int round;
	
	Record(String name, long score, int round) {
		this.setName(name);
		this.setScore(score);
		this.setRound(round);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	
}
