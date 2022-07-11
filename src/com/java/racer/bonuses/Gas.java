package com.java.racer.bonuses;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.java.racer.art.Textures;
import com.java.racer.controlling.Controller;
import com.java.racer.entities.Entity;
import com.java.racer.main.Game;
import com.java.racer.object.GameObject;

public class Gas extends GameObject implements Entity{
	Textures textures;
	Game game;
	Controller controller;
	Random random = new Random();
	
	int speed = random.nextInt(3) + 1; 
	
	public Gas(double x, double y,Textures textures, Controller controller, Game game){
		super(x, y);
		this.textures = textures;
		this.controller = controller;
		this.game = game;
	}
	
	public void tick(){
		y += speed;
		
		if(y > (Game.HEIGHT * Game.SCALE)){
			x = random.nextInt(640);
			y = -10;
		}
	}

	public void render(Graphics graphics){
		graphics.drawImage(Textures.gas, (int)x, (int)y, 50, 50, null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 8, 8);
	}
	
	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}
	
}