package com.java.racer.object;

import com.java.racer.entities.Entity;
import com.java.racer.entities.EntityComponent;
import com.java.racer.entities.Player;

public class Physics{
	public static boolean Collision(Entity entity, EntityComponent entityComponent){
		if(entity.getBounds().intersects(entityComponent.getBounds())){
			return true;
		
		}
		return false;
	}

	public static boolean Collision(EntityComponent entityComponent, Entity entity){
		if(entityComponent.getBounds().intersects(entity.getBounds())){
			return true;
		}
		return false;
	}

	public static boolean Collision(Player entity, Entity enti){
		if(enti.getBounds().intersects(entity.getBounds())){
			return true;
		}
		return false;
	}
}