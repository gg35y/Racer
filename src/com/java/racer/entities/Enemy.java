package com.java.racer.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.java.racer.art.Textures;
import com.java.racer.controlling.Controller;
import com.java.racer.main.Game;
import com.java.racer.object.GameObject;

public class Enemy extends GameObject implements EntityComponent{	
	@SuppressWarnings("unused")
	private Textures textures;
	@SuppressWarnings("unused")
	private Controller controller;
	@SuppressWarnings("unused")
	private Game game;
	Random random = new Random();
	
	int speed = random.nextInt(3) + 1;
	
	public Enemy(double x, double y, Textures textures, Controller controller, Game game){
		super(x,y);
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
		graphics.drawImage(Textures.enemy, (int)x, (int)y, 100, 100, null);
	}

	public double getY(){
		return y;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);		
	}
}