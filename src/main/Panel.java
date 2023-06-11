package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Pathfinder.PathFinding;
import Pathfinder.PathFindingS;
import entity.*;
import entity.Object;
import graphic.*;
import item.Item;
import map.*;
import projectile.Projectile;


public class Panel extends JPanel implements Runnable{
	
	public final int originalTileSize = 16;
	public final int scale = 3;
	
	public final int tileSizeX = originalTileSize*scale;
	public final int tileSizeY = originalTileSize*scale;
	public final int maxScreenCol = 22;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSizeX*maxScreenCol;
	public final int screenHeight = tileSizeY*maxScreenRow;
	
	public final int maxWorldCol = 70;
	public final int maxWorldRow = 56;
	
	public final int FPS=60;
	public boolean GameOver,win;
	
	
	
	//System
	public Graphics2D g2;
	public Thread gameThread;
	public UI Ui=new UI(this);
	public Input keyH = new Input(this);
	public CheckCollision check = new CheckCollision(this);
	public Sound sound = new Sound();
	public Sound se = new Sound();
	
	//Entity
	public Player player;
	public  DrawPlayer drawP;
	public Monster monsters[];
	public int countM=0;
	public DrawMonster drawM[];
	public MapObject mapobj[];
	public DrawMapObject drawMO[];
	public Item item[];
	public DrawItem drawI[];
	public AssetSetter aSetter;
	public PathFinding pFinder = new PathFinding(this);
	public PathFindingS pFinderS = new PathFindingS(this);
	public ArrayList<Object> oList = new ArrayList<Object>();
	public ArrayList<Projectile> proList = new ArrayList<Projectile>();
	public ArrayList<DrawProjectile> drawPro = new ArrayList<DrawProjectile>();
	public DrawProjectile drawPros;
	
	public boolean musicOn;
	public boolean seOn;
	
	// Game State
		public int gameState;
		public final int titleState = 0;
		public final int playState = 1;
		public final int pauseState = 2;
		public final int overState=9;
		public final int optionsState=5;
		public final int statusState = 3;
		public final int victoryState=10;
		
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void setupGame() {
		aSetter.setupGame();
		gameState = titleState;
	}
	
	public void run() {
		double delta=0;
		long currentTime = 0;
		long lastTime = System.nanoTime();
		double drawInterval = 1000000000/FPS;
		while (gameThread!=null) {
			currentTime = lastTime;
			lastTime = System.nanoTime();
			delta += (-currentTime +lastTime)/drawInterval;
			if(delta>=1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {
		checkGame();
		if(gameState==playState) {
			for (int i = 0; i < countM; i++) {
				if(monsters[i]==null&&aSetter.monsterdata[i]!=null) {
					aSetter.monsterdata[i].timespawn--;
					if (aSetter.monsterdata[i].timespawn==0) {
						aSetter.spawnMonster(i);
					}
				}
			}
			for (int i = 0; i < item.length; i++) {
				if(item[i]!=null) {
				item[i].update();}
			}
			for (int i = 0; i <monsters.length; i++) {
				if(monsters[i]!=null) {
					monsters[i].update();
				}
			}
			player.update();
			for (int i = 0; i < proList.size(); i++) {
				if(proList.get(i)!=null) {
					if(proList.get(i).alive==true) {
						proList.get(i).update();}
					else {
						proList.remove(i);
						drawPro.remove(i);
					}
				}
			}
		}
		else {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		if(gameState==titleState) {
			Ui.draw(g2);
		} else if(gameState==victoryState) {
			Ui.draw(g2);
		}
		else if(gameState==overState) {
			Ui.draw(g2);
		}
		else {
			aSetter.drawTile(g2);
			for (int i = 0; i < item.length; i++) {
				if(item[i]!=null&&drawI[i]!=null) {
					drawI[i].draw(g2);
				}
			}
			for (int j = 0; j < mapobj.length; j++) {
				if(mapobj[j]!=null) {
					drawMO[j].draw(g2);
				}
			}
			oList.add(player);
			for(int i = 0; i< monsters.length; i++) {
				if(monsters[i] != null) {
					oList.add(monsters[i]);
				}
			}
			Collections.sort(oList, new Comparator<Object>() {

				@Override
				public int compare(Object e1, Object e2) {
					
					int result = Integer.compare(e1.y, e2.y);
					return result;
				}
			});
			// DRAW ENTITIES
			for(int i = 0; i< oList.size(); i++) {	
				if(oList.get(i)==player) {
					drawP.draw(g2);
				}
				for (int j = 0; j < monsters.length; j++) {
					if(oList.get(i)==monsters[j]&&drawM[j]!=null) {
						drawM[j].draw(g2);
					}
				}
			}
			// EMPTY ENTITY LIST
			oList.clear();
			
			for (int i = 0; i < proList.size(); i++) {
				if(proList.get(i)!=null) {
					drawPro.get(i).draw(g2);
				}
			}
			Ui.draw(g2);
		}
		
			
	}
	
	public void checkGame() {
		if(GameOver==true) {
			GameOver=false;
			if(musicOn==true) {stopMusic();
				playSE(11);
			
			}
			gameState = overState;
		}else if(win==true) {
			win =false;
			if(musicOn==true) {stopMusic();
				musicOn=true;
				//player victory music
				
				gameState = victoryState;
			}	
		}
	}
	
	public void newGame(){
		gameThread = null;
		resetGame();
		createObject();
		
		setupGame();
		
		Ui=new UI(this);
		
		musicOn=true;
		seOn=true;
		playMusic(0);
		startGame();
		
		gameState = playState;
		
	}	
	
	public void createObject() {
		oList.clear();
		proList.clear();
		drawPro.clear();
		
		player = new Player(this);
		drawP = new DrawPlayer(player, this);
		
		mapobj = new MapObject[1000];
		item = new Item[100];
		monsters = new Monster[30];
		
		drawMO = new DrawMapObject[1000];
		drawM = new DrawMonster[30];
		drawI = new DrawItem[100];
		
		aSetter = new AssetSetter(this);	
	}
	
	public void resetGame() {
		aSetter=null;
		player = null;
		drawP = null;
		
		mapobj = null;
		item = null;
		monsters = null;
		
		drawMO = null;
		drawM = null;
		drawI = null;
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic() {
		sound.stop();
	}
	public void playSE(int i) {
		if(seOn==true) {
		se.setFile(i);
		se.play();}
	}
}
