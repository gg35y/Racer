package com.java.racer.controlling;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.java.racer.art.Textures;
import com.java.racer.bonuses.Gas;
import com.java.racer.entities.Enemy;
import com.java.racer.entities.Entity;
import com.java.racer.entities.EntityComponent;
import com.java.racer.main.Game;

public class Controller{
	private  LinkedList<Entity> entity = new LinkedList<Entity>(); 
	private LinkedList<EntityComponent> entityComponent = new LinkedList<EntityComponent>(); 
	
	Entity enti;
	EntityComponent entiCom;
	Textures textures;
	Random random;
	Game game;
	
	public Controller(Textures textures, Game game){
		this.textures = textures;
		this.game = game;
		random = new Random();
	}
	
	public void tick(){
		for(int i = 0; i < entity.size(); i++){
			enti = entity.get(i);
			enti.tick();
		}
		
		for(int i = 0; i < entityComponent.size(); i++){
			entiCom = entityComponent.get(i);
			entiCom.tick();
		}
	}
	
	public void render(Graphics graphics){
		for(int i = 0; i < entity.size(); i++){
			enti = entity.get(i);
			enti.render(graphics);
		}
		
		for(int i = 0; i < entityComponent.size(); i++){
			entiCom = entityComponent.get(i);
			entiCom.render(graphics);
		}
	}
	
	public void addEnemy(int enemyCount){
		for(int i = 0; i < 7; i++){
			addEntity(new Enemy(random.nextInt(640), 100, textures, this, game));
		}
	}
	
	public void addBonuses(int gasCount){
		for(int i = 0; i < 3; i++){
			addEntity(new Gas(random.nextInt(640), 100, textures, this, game));
		}
	}
	
	
	public void addEntity(Entity enti){
		entity.add(enti);
	}
	
	public void removeEntity(Entity enti){
		entity.add(enti);
	}
	
	public void addEntity(EntityComponent entiCom){
		entityComponent.add(entiCom);
	}
	
	public void removeEntity(EntityComponent entiCom){
		entityComponent.add(entiCom);
	}

	public LinkedList<Entity> getEntity(){
		return entity;
	}
	
	public LinkedList<EntityComponent> getEntityComponent(){
		return entityComponent;
	}
}