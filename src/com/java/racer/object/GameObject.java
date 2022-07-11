package com.java.racer.object;

import java.awt.Rectangle;

public class GameObject{
	public double x,y;
	
	public GameObject(double x, double y){
		this.x = x;
		this.y = y;		
	}
	
	public Rectangle getRectangle(){
		return new Rectangle((int)x, (int)y, 32, 32);		
	}
}