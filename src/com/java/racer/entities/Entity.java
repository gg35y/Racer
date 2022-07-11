package com.java.racer.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Entity{
	public void tick();
	public void render(Graphics graphics);
	public Rectangle getBounds();
	public double getX();
	public double getY();
}