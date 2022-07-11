package com.java.racer.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.java.racer.art.BufferedImageLoader;
import com.java.racer.art.Textures;
import com.java.racer.controlling.Controller;
import com.java.racer.controlling.KeyInput;
import com.java.racer.controlling.MouseInput;
import com.java.racer.entities.Player;
import com.java.racer.gui.Panel;
import com.java.racer.menu.Menu;
import com.java.racer.entities.Entity;
import com.java.racer.entities.EntityComponent;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 120;
	public static final int HEIGHT = 180;
	public static final int SCALE = 4;
	private static final String NAME = "Racer";
	
	private Thread thread;
	@SuppressWarnings("unused")
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage textures = null;
	private Image back = new ImageIcon("res/road.png").getImage();  
	private Player player;
	private Textures tex;
	private Controller controller;
	private Panel panel;
	private Menu menu;
	
	boolean running = false;
	int enemyCount = 7;
	int gasCount = 3;
	int x,y;
	
	public LinkedList<Entity> entity;
	public LinkedList<EntityComponent> entityComponent;
	
	public static enum STATUS{
		MENU, GAME, WIN, LOSE;
	};
	
	public static STATUS status = STATUS.MENU;
	
	public void init(){
		requestFocus();
		BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
		try {
			textures = bufferedImageLoader.loadImage("res/textures.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player = new Player(185,550,this, tex, controller);
		controller = new Controller(tex, this);
		tex = new Textures(this);
		panel = new Panel(0, 0, tex, this);
		menu = new Menu();
		entity = controller.getEntity();
		entityComponent = controller.getEntityComponent();
		
		controller.addEnemy(enemyCount);
		controller.addBonuses(gasCount);
		this.addKeyListener(new KeyInput(this));	
		this.addMouseListener(new MouseInput());
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfticks = 60.0;
		double ns = 1000000000.0 /amountOfticks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			if(delta>=1){
				tick();
				updates++;
				delta--;
				
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			render();
			frames++;
			
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				System.out.println("ticks " + updates+", fps " + frames);
				updates=0;
				frames=0;
			}
			
		}		
		stop();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = bs.getDrawGraphics();
		
		graphics.drawImage(back, 0, 0, getWidth(), getHeight(), null);
		
		if(status == STATUS.GAME){
			player.render(graphics);
			controller.render(graphics);
			panel.render(graphics);
		}else if(status == STATUS.MENU){
			menu.render(graphics);
			graphics.drawString("Управление WASD/стрелочки",100,y+300);
			graphics.drawString("Чтобы выиграть нужно достичь 500 очков",35,y+320);
			graphics.drawString("Если здоровье будет равно 0 и бензин ",50,y+340);
			graphics.drawString("будет равен больше 300, то вы проиграете",35,y+360);
			graphics.drawString("Игра сделана gg35y [c] 2021",100,y+400);
		}else if(status == STATUS.WIN){
			graphics.drawImage(Textures.winTitle, 90, 250, 300, 300, null);
		}else if(status == STATUS.LOSE){
			graphics.drawImage(Textures.loseTitle, 90, 250, 350, 350, null);
		}
		
		graphics.dispose();
		bs.show();
	}
	
	public BufferedImage getImage(){
		return textures;
	}
	
	public void tick(){
		if(status == STATUS.GAME){
			player.tick();
			controller.tick();
		}
		
	}
	
	public synchronized void start(){
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running) return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public Game(){
		Dimension dimension = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMaximumSize(dimension);
		setSize(dimension);
	}
	
	public void keyPressed(KeyEvent keyEvent){
		int key = keyEvent.getKeyCode();
		
		if(status == STATUS.GAME){
			if(key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_D){
				player.setVelX(5);
			}else if(key == KeyEvent.VK_LEFT | key == KeyEvent.VK_A){
				player.setVelX(-5);	
			}else if(key == KeyEvent.VK_DOWN | key == KeyEvent.VK_S){
				player.setVelY(5);
			}else if(key == KeyEvent.VK_UP | key == KeyEvent.VK_W){
				player.setVelY(-5);
			}
		}
	}
	public void keyReleased(KeyEvent keyEvent){
		int key = keyEvent.getKeyCode();
		if(status == STATUS.GAME){
			if(key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_D){
				player.setVelX(5);
				Panel.gas--;
			}else if(key == KeyEvent.VK_LEFT | key == KeyEvent.VK_A){
				player.setVelX(-5);
				Panel.gas--;
			}else if(key == KeyEvent.VK_DOWN | key == KeyEvent.VK_S){
				player.setVelY(5);
				Panel.gas--;
			}else if(key == KeyEvent.VK_UP | key == KeyEvent.VK_W){
				player.setVelY(-5);
				Panel.gas--;
			}
		}
	}
	
	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getGasCount() {
		return enemyCount;
	}

	public void setGasCount(int gasCount) {
		this.gasCount = gasCount;
	}
	
	public static void main(String [] arr){
		JFrame jFrame = new JFrame(NAME);
		Game game = new Game();
		
		jFrame.add(game);
		jFrame.pack();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		
		game.start();
	}
}