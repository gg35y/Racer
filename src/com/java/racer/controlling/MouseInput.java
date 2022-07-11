package com.java.racer.controlling;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.java.racer.main.Game;

public class MouseInput implements MouseListener{
	public void mouseClicked(MouseEvent mouseEvent){
		
	}
		
	public void mousePressed(MouseEvent mouseEvent){		
	}

	public void mouseReleased(MouseEvent mouseEvent){
		int mx = mouseEvent.getX();
		int my = mouseEvent.getY();
		
		if(mx >= (Game.WIDTH /2) + 125 && mx <= (Game.WIDTH / 2) + 255){
			if(my >= 150 && my <= 200){
				Game.status = Game.STATUS.GAME;
			}
		}
		
		if(mx >= (Game.WIDTH /2) + 125 && mx <= (Game.WIDTH / 2) + 255){
			if(my >= 200 && my <= 250){
				System.exit(1);
			}
		}
		
	}

	public void mouseEntered(MouseEvent mouseEvent){
		
	}

	public void mouseExited(MouseEvent mouseEvente){
	
	}
}