package com.java.racer.controlling;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.java.racer.main.Game;

public class KeyInput extends KeyAdapter{
	Game game;
	
	public KeyInput(Game game){
		this.game = game;
	}
	
	public void KeyPressed(KeyEvent keyEvent){
		game.keyPressed(keyEvent);
	}
	
	public void keyReleased(KeyEvent keyEvent){
		game.keyReleased(keyEvent);
	}
	
}