package com.java.racer.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.java.racer.art.Textures;
import com.java.racer.controlling.Controller;
import com.java.racer.gui.Panel;
import com.java.racer.main.Game;
import com.java.racer.object.GameObject;
import com.java.racer.object.Physics;
import com.java.racer.sound.Sound;

public class Player extends GameObject implements Entity{
	public double x, y, velX, velY;
	
	@SuppressWarnings("unused")
	private Textures textures;
	Game game;
	Controller controller;
	Sound hitSound = new Sound("res/hit.wav", 0.6);
	Sound pickupSound = new Sound("res/pickup.wav", 0.6);
	
	public Player(double x, double y, Game game, Textures textures, Controller controller){
		super(x,y);
		this.textures = textures;
		this.game = game;
		this.controller = controller;
	}

	public void tick(){
		x += velX;
		y += velY;

		if(x >= 400) x = 400;
		if(y >= 556) y = 556;

		if(x <= 0) setVelX(-velX);	
		if(x >= Game.WIDTH * Game.SCALE) setVelX(-velX);	
	
		if(y <= 0) setVelY(-velY);
		if(y >= Game.HEIGHT * Game.SCALE) setVelY(-velY);
		
		
		for(int i = 0; i < game.entityComponent.size(); i++){
			EntityComponent entiCom = game.entityComponent.get(i);
			if(Physics.Collision(this, entiCom)){
				Panel.health-= 5;
				hitSound.audio();
				hitSound.setVolume();
			} 
			if(Panel.health == 0){
				Game.status = Game.STATUS.LOSE;
			}
		}
		
		for(int i = 0; i < game.entity.size(); i++){
			Entity enti = game.entity.get(i);
			if(Physics.Collision(this, enti)){
				Panel.score++;
				Panel.gas += 30;
				pickupSound.audio();
				pickupSound.setVolume();
			}
		}
		if(Panel.gas == 0 | Panel.gas >= 300) Game.status = Game.STATUS.LOSE;
		if(Panel.score == 500) Game.status = Game.STATUS.WIN;
	}
	public void render(Graphics graphics){
		graphics.drawImage(Textures.player, (int)x, (int)y, 100, 100, null);
	}
	
	public double getVelX(){
		return velX;
	}

	public void setVelX(double velX){
		this.velX = velX;
	}

	public double getVelY(){
		return velY;
	}

	public void setVelY(double velY){
		this.velY = velY;
	}

	public double getX(){
		return x;
	}

	public void setX(double x){
		this.x = x;
	}

	public double getY(){
		return y;
	}

	public void setY(double y){
		this.y = y;
	}

	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}