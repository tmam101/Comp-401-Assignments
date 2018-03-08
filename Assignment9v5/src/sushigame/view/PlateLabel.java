package sushigame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;

public class PlateLabel extends JLabel {
	private Graphics2D g2;
	private Shape circle;
	private Color color;

	public PlateLabel(Color color) {
		this.color = color;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		circle = new Ellipse2D.Double(25, 0, 40, 40);
		g2.setPaint(color);
		g2.fill(circle);
		g2.draw(circle);
	}

	public void changeColor(Color c) {
		color = c;
		repaint();
	}

	public void setIsVisible(boolean truth) {
		this.setVisible(truth);
	}

}
