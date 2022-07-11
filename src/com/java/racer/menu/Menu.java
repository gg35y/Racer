package com.java.racer.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.java.racer.main.Game;

public class Menu{
	private Image menu = new ImageIcon("res/title.png").getImage();
	public Rectangle playButton = new Rectangle((Game.WIDTH/2)+125,150,100,50);
	public Rectangle quitButton = new Rectangle((Game.WIDTH/2)+125,210,100,50);
	
	int width = 800, height = 800; 
	
	public void render(Graphics graphics){
		graphics.drawImage(menu, 0,0, width ,height, null);
		
		Graphics2D graphics2d = (Graphics2D)graphics;
		Font textButton = new Font("arial", Font.BOLD, 20);
		graphics.setColor(Color.white);
		graphics.setFont(textButton);
		graphics.drawString("Играть", playButton.x+20,playButton.y + 35);
		graphics2d.draw(playButton);
		graphics.drawString("Выйти", quitButton.x+20,quitButton.y + 35);
		graphics2d.draw(quitButton);
	}
}