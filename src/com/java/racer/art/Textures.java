package com.java.racer.art;

import java.awt.image.BufferedImage;

import com.java.racer.main.Game;

public class Textures{
	public static BufferedImage player;
	public static BufferedImage enemy;
	public static BufferedImage gamePanel;
	public static BufferedImage health;
	public static BufferedImage gas;
	public static BufferedImage winTitle;
	public static BufferedImage loseTitle;
	private Art art;
	
	public Textures(Game game){
		art = new Art(game.getImage());
		
		getTextures();
	}
	
	public void getTextures(){
		player = art.getImage(1, 1, 32, 32);
		enemy = art.getImage(4, 1, 32, 32);
		gamePanel = art.getImage(1, 4, 32, 32);
		health = art.getImage(1, 6, 8, 8);
		gas = art.getImage(2, 6, 8, 8);		
		winTitle = art.getImage(1, 12, 32, 32);
		loseTitle = art.getImage(4, 11, 64, 64);
	}
}