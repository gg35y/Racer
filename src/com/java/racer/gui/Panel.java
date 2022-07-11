package com.java.racer.gui;

import java.awt.Graphics;

import com.java.racer.art.Textures;
import com.java.racer.main.Game;

public class Panel{
	private double x,y;
	public static int health = 25;
	public static int gas = 60;
	public static int score = 0;
	
	@SuppressWarnings("unused")
	private Textures textures; 
	Game game;
	
	public Panel(double x, double y, Textures textures, Game game){
		this.x = x;
		this.y = y;
		this.textures = textures;
		this.game = game;
	}
	
	public void render(Graphics graphics){
		graphics.drawImage(Textures.gamePanel, (int)x + 0, (int)y + 650, 700, 100, null);
		graphics.drawImage(Textures.health, (int)x + 20, (int)y + 660, 40, 40, null);
		graphics.drawString(String.valueOf(health), 60, 690);
		graphics.drawImage(Textures.gas, (int)x + 90, (int)y + 660, 40, 40, null);
		graphics.drawString(String.valueOf(gas), 135, 690);
		graphics.drawString("Score: ", 160, 690);
		graphics.drawString(String.valueOf(score), 200, 690);	
	}
}