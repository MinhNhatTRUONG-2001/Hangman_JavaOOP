package com.nhat.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class DrawObjects { //draw graphical parts
	@SuppressWarnings("serial")
	public static class DrawCanvas extends JPanel {
		@Override
	    public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       setBackground(Color.WHITE);
	       g.setColor(Color.BLACK);
	       g.fillRect(100, 350, 100, 10);
	       g.fillRect(145, 50, 10, 300);
	       g.fillRect(155, 50, 150, 10);
	       g.fillRect(295, 50, 10, 50);
		}
	}
	
	@SuppressWarnings("serial")
	public static class DrawBodyParts5 extends DrawCanvas {
		@Override
	    public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       g.setColor(Color.GREEN);
	       g.fillOval(275, 100, 50, 50);
		}
	}
	
	@SuppressWarnings("serial")
	public static class DrawBodyParts4 extends DrawBodyParts5 {
		@Override
	    public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       g.setColor(Color.GREEN);
	       g.fillRect(295, 150, 10, 100);
		}
	}
	
	@SuppressWarnings("serial")
	public static class DrawBodyParts3 extends DrawBodyParts4 {
		@Override
	    public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       Graphics2D g2d = (Graphics2D) g;
	       g2d.setColor(Color.GREEN);
	       g2d.translate(195, -160);
	       AffineTransform at = new AffineTransform();
	       at.rotate(Math.toRadians(45));
	       g2d.fill(at.createTransformedShape(new Rectangle(295, 150, 10, 75)));
		}
	}
	
	@SuppressWarnings("serial")
	public static class DrawBodyParts2 extends DrawBodyParts3 {
		@Override
	    public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       Graphics2D g2d = (Graphics2D) g;
	       g2d.setColor(Color.GREEN);
	       g2d.translate(-215, 423);
	       AffineTransform at = new AffineTransform();
	       at.rotate(Math.toRadians(-45));
	       g2d.fill(at.createTransformedShape(new Rectangle(295, 150, 10, 75)));
		}
	}
	
	@SuppressWarnings("serial")
	public static class DrawBodyParts1 extends DrawBodyParts2 {
		@Override
	    public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       Graphics2D g2d = (Graphics2D) g;
	       g2d.setColor(Color.GREEN);
	       g2d.translate(215, -335);
	       AffineTransform at = new AffineTransform();
	       at.rotate(Math.toRadians(45));
	       g2d.fill(at.createTransformedShape(new Rectangle(295, 150, 10, 75)));
		}
	}
	
	@SuppressWarnings("serial")
	public static class DrawBodyParts0 extends DrawBodyParts1 {
		@Override
	    public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       Graphics2D g2d = (Graphics2D) g;
	       g2d.setColor(Color.GREEN);
	       g2d.translate(-215, 423);
	       AffineTransform at = new AffineTransform();
	       at.rotate(Math.toRadians(-45));
	       g2d.fill(at.createTransformedShape(new Rectangle(295, 150, 10, 75)));
		}
	}
}
