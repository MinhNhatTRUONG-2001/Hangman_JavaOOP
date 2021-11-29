package com.nhat.game;

public class Player {
	private long score;
	private int round;
	private double multiplier; //range: 1.0-2.0, step: 0.25
	private int streak_mul;
	private int streak_life;
	private int lives; // range: 0-6, step: 1
	
	Player(long score, int round, double multiplier, int streak_mul, int streak_life, int lives) {
		this.setScore(score);
		this.setRound(round);
		this.setMultiplier(multiplier);
		this.setStreak_mul(streak_mul);
		this.setStreak_life(streak_life);
		this.setLives(lives);
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

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

	public int getStreak_mul() {
		return streak_mul;
	}

	public void setStreak_mul(int streak_mul) {
		this.streak_mul = streak_mul;
	}

	public int getStreak_life() {
		return streak_life;
	}

	public void setStreak_life(int streak_life) {
		this.streak_life = streak_life;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void addScore(int value, int letters) {
		this.score += (long)(value * letters * this.multiplier);
	}
	
	public void changeMultiplier(double value) {
		if (this.multiplier < 2 && value > 0 || this.multiplier > 1 && value < 0) {
			this.multiplier += value;
		}
	}
	
	public void changeLives(int value) {
		if (this.lives < 6 && value > 0 || this.lives > 0 && value < 0) {
			this.lives += value;
		}
	}
}
