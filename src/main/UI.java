package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import graphic.DrawItem;
import item.Item;



public class UI {
	Panel gp;
	Graphics2D g2;
	Font times;
	public int commandNum = 0;
	int subState = 0;
	BufferedImage image;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public String currentDialogue = "";
	public int slotCol = 0;
	public int slotRow = 0;
	
	public UI (Panel gp) {
		this.gp=gp;
		try {
			InputStream is = getClass().getResourceAsStream("/font/times.ttf");
			times = Font.createFont(Font.TRUETYPE_FONT,is );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
	}
	
	public void drawMessage() {
		
		int messageX = gp.tileSizeX;
		int messageY = gp.tileSizeY*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i = 0; i< message.size(); i++) {
			
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; 
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(times);
		g2.setColor (Color.white);
		//Title State
		if(gp.gameState==gp.titleState) {
			drawTitleScreen();
		}else if(gp.gameState==gp.statusState) {drawCharacterScreen(); drawInventory();drawMessage();}
		//playState
		else if (gp.gameState==gp.playState) {
			int col =0;
			while(col<gp.maxScreenCol) {
				Color c = new Color(0, 0, 0, 210); //black
				g2.setColor(c);
				g2.fillRect(col*gp.tileSizeX, 0, gp.tileSizeX, gp.tileSizeY);
				g2.fillRect(col*gp.tileSizeX, gp.tileSizeY, gp.tileSizeX, gp.tileSizeY);
				col++;
			}
			drawMessage();
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,20f));

			g2.drawString("LV:"+gp.player.lv, gp.tileSizeX*15-gp.tileSizeX/2,gp.tileSizeY);
			double l =(double)gp.player.exp /(double)gp.player.levelExp;
			double expBar = l*(double)gp.tileSizeX*3;
			g2.setColor(new Color(60,35,35));
			g2.fillRect(gp.tileSizeX*16,gp.tileSizeY-17, gp.tileSizeX*3, 17);
			
			g2.setColor(Color.green);
			g2.fillRect(gp.tileSizeX*16,gp.tileSizeY-15,(int)expBar, 15);
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,18f));
			g2.drawString(gp.player.exp+"/"+gp.player.levelExp, gp.tileSizeX*17+12, gp.tileSizeY-3);
			
			double h =(double)gp.player.HP /(double)gp.player.MaxHP;
			double playerHpBar = h*(double)gp.tileSizeX*4;
			g2.setColor(new Color(35,35,35));
			g2.fillRect(gp.tileSizeX,gp.tileSizeY-38, gp.tileSizeX*4, 17);
			
			g2.setColor(new Color(255,0,30));
			g2.fillRect(gp.tileSizeX+1,gp.tileSizeY-38,(int)playerHpBar, 15);
			g2.setColor(Color.white);
			g2.drawString("HP:"+gp.player.HP+"/"+gp.player.MaxHP, gp.tileSizeX+5, gp.tileSizeY/2);
			
			
			double m =(double)gp.player.MP /(double)gp.player.MaxMP;
			double playerMpBar = m*(double)gp.tileSizeX*4;
			g2.setColor(new Color(35,35,35));
			g2.fillRect(gp.tileSizeX,gp.tileSizeY-14, gp.tileSizeX*4, 17);
			
			g2.setColor(Color.blue);
			g2.fillRect(gp.tileSizeX+1,gp.tileSizeY-14,(int)playerMpBar, 15);
			g2.setColor(Color.white);
			g2.drawString("MP:"+gp.player.MP+"/"+gp.player.MaxMP, gp.tileSizeX+5, gp.tileSizeY);
			if(gp.player.projectile!=null) {
			double s =(double)(gp.player.timeProjectile) /(double)gp.player.projectile.time;
			double timeSkill = s*(double)34;
			
			switch (gp.player.projectile.name) {
			case "Fire":
				image = setupImage("/projectile/fireball_right_1.png", 35, 35);
				break;
			case"PPS":
				image = setupImage("/projectile/powerPole1.png", 35, 35);
				break;
			case "Ném dầu ăn":
				image = setupImage("/objects/dauan.png", gp.tileSizeX*2/3, gp.tileSizeY*2/3);
				break;
			}
			
			g2.drawImage(image,10, 10, null);
			    
			
			g2.setColor(Color.white);
			g2.fillRect(11,11, 34,(int)timeSkill);
			}
			
			for (int i = 0; i < gp.monsters.length; i++) {
				if(gp.monsters[i]!=null) {					
					double screenX = gp.monsters[i].x - gp.player.x + gp.player.screenX;
					double screenY = gp.monsters[i].y - gp.player.y + gp.player.screenY;
					if( gp.monsters[i].x + gp.tileSizeX > gp.player.x - gp.player.screenX &&
							gp.monsters[i].x - gp.tileSizeX < gp.player.x + gp.player.screenX &&
							gp.monsters[i].y + gp.tileSizeY > gp.player.y - gp.player.screenY &&
							gp.monsters[i].y - gp.tileSizeY < gp.player.y + gp.player.screenY&&gp.monsters[i].onPath) {
						double p =(double) gp.monsters[i].HP/(double)gp.monsters[i].MaxHP;
						double monsterHpBar = p*(double)gp.tileSizeX;
						g2.setColor(new Color(35,35,35));
						g2.fillRect((int)screenX-1,(int) screenY-12, gp.tileSizeX, 12);
						
						g2.setColor(new Color(255,0,30));
						g2.fillRect((int)screenX,(int)screenY-11,(int)monsterHpBar, 10);
						
					}				
			}
			
		}
			}
		else if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		else if (gp.gameState ==gp.optionsState) {
			drawOptionsScreen();
		}
		else if(gp.gameState==gp.overState) {
			drawGameOverScreen();
		}else if(gp.gameState==gp.victoryState) {
			drawWinScreen();
		}
	}
	
	public void drawWinScreen() {
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text="CONGRATULATION";
		int x = getXforCenteredText(text);
		int y=gp.screenHeight/2;
		g2.drawString(text, x, y);
		
		text = "Ấn ESC để ra màn hình chính";
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		g2.drawString(text, x+gp.tileSizeX, y+2*gp.tileSizeY);
	}
	
	public void drawGameOverScreen() {
		g2.setColor(Color.RED);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text="GAME OVER";
		int x = getXforCenteredText(text);
		int y=gp.screenHeight/2;
		g2.drawString(text, x, y);
		
		//retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSizeY*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		// Back
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
	}
	
  
	public void drawTitleScreen() {
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text="GAME2D";
		int x=getXforCenteredText(text);
		int y=gp.tileSizeY*2;
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		// CHEN ANH
		
		
		image = setupImage("/monster/boss_down1.png",gp.tileSizeX *3, gp.tileSizeY*3);
		
		x = gp.screenWidth/2- (gp.tileSizeX*2)+gp.tileSizeX/3+gp.tileSizeX/4;
		y+=gp.tileSizeY *2;
		g2.drawImage(image,x, y, null);
		    
		
		
		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		
		text="Game Mới";
		x=getXforCenteredText(text);
		y=gp.tileSizeY*8;
		g2.drawString(text, x, y);
		if(commandNum==0) {
			g2.drawString(">", x-gp.tileSizeX, y);
		}
		
		text="Thoát";
		x=getXforCenteredText(text);
		y=gp.tileSizeY*9;
		g2.drawString(text, x, y);
		if(commandNum==1) {
			g2.drawString(">", x-gp.tileSizeX, y);
		}
		
	}
	public void drawPauseScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text="PAUSE";
		int y=gp.screenHeight/2;
		int x = getXforCenteredText(text);
		g2.drawString(text, x,y);
	}
	public int getXforCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x=gp.screenWidth/2-length/2;
		return x;
		
	}
	
	
public void drawCharacterScreen() {
		
		// CREATE A FRAME
		final int frameX = gp.tileSizeX;
		final int frameY = gp.tileSizeY;
		final int frameWidth = gp.tileSizeX*5;
		final int frameHeight = gp.tileSizeY*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSizeY;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("Level", textX, textY);	textY += lineHeight;
		g2.drawString("HP", textX, textY);	textY += lineHeight;
		g2.drawString("MP", textX, textY);	textY += lineHeight;
		g2.drawString("Speed", textX, textY);	textY += lineHeight;
		g2.drawString("Sức mạnh", textX, textY);	textY += lineHeight;
		g2.drawString("Dẻo Dai", textX, textY);	textY += lineHeight;
		g2.drawString("Tấn công", textX, textY);	textY += lineHeight;
		g2.drawString("Phòng thủ", textX, textY);	textY += lineHeight;
		g2.drawString("EXP", textX, textY);	textY += lineHeight;
		g2.drawString("Vũ khí", textX, textY);	textY += lineHeight + 15;
		g2.drawString("Khiên", textX, textY);	textY += lineHeight;
		
		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + gp.tileSizeY; 
		String value;
		
		value = String.valueOf(gp.player.lv);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		value = String.valueOf(gp.player.HP + "/" + gp.player.MaxHP);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		value = String.valueOf(gp.player.MP + "/" + gp.player.MaxMP);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		value = String.valueOf(gp.player.speed);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		value = String.valueOf(gp.player.STR);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		value = String.valueOf(gp.player.DEX);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		value = String.valueOf(gp.player.ATK);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		value = String.valueOf(gp.player.DEF);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		value = String.valueOf(gp.player.exp + "/" + gp.player.levelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);	textY += lineHeight;
		
		textY += gp.tileSizeY;
		image = getItemImage(gp.player.currentWeapon);
		g2.drawImage(image, tailX - gp.tileSizeX, textY - gp.tileSizeY-gp.tileSizeY/2, null);
		textY += gp.tileSizeY;
		image = getItemImage(gp.player.currentShield);
		g2.drawImage(image, tailX - gp.tileSizeX, textY -gp.tileSizeY-gp.tileSizeY/2, null);
		
		
		
	}
	
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 210); //black
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255); // white
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}

	public BufferedImage setupImage(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
			image = uTool.scaleImage(image,width,height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	
	
	
	public void drawOptionsScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameX= gp.tileSizeX*7-24;
		int frameY= gp.tileSizeY;
		int frameWidth= gp.tileSizeX*8;
		int frameHeight= gp.tileSizeY*9;
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		switch (subState) {
			case 0:options_top(frameX, frameY); break;
			case 3:options_control(frameX, frameY); break;
			case 4:options_endGame(frameX, frameY); break;
		}
		gp.keyH.enterPressed= false;
	}
	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;
		
		//Title
		String text ="Options";
		textX= getXforCenteredText(text);
		textY= frameY + gp.tileSizeY;
		g2.drawString(text, textX, textY);
		
		textX= frameX+gp.tileSizeX;
		textY+=gp.tileSizeY;
		
		g2.drawString("Music", textX, textY);
		 if(commandNum == 0) {
	    	  g2.drawString(">", textX-25, textY);
	    	  if(gp.keyH.enterPressed ==true) {
	    		  if(gp.musicOn==false) {
	    			gp.musicOn=true;
	    		  	gp.playMusic(0);}
	    		  else if(gp.musicOn==true) {
		    		gp.musicOn=false;
	    		  	gp.stopMusic();}
	    	  }
	    	  //subState=1;
	    	  
	      }
		
		//SE
		textY+=gp.tileSizeY;
		g2.drawString("SE", textX, textY);
		 if(commandNum == 1) {
	    	  g2.drawString(">", textX-25, textY);
	    	  if(gp.keyH.enterPressed ==true) {
	    		  if(gp.seOn==false)
	    			  gp.seOn=true;
	    		  else
	    		  if(gp.seOn==true)
		    		 gp.seOn=false;
	    		  
	    	  }
	    	 // subState=2;
	    	  
	      }
		//control
		textY+=gp.tileSizeY;
		g2.drawString("Control", textX, textY);
		 if(commandNum == 2) {
	    	  g2.drawString(">", textX-25, textY);
	    	  if(gp.keyH.enterPressed ==true) {
	    		  subState=3;
	    		  commandNum=0;
	    	  }
	    	  
	      }
		//End Game
		textY+=gp.tileSizeY;
		g2.drawString("End Game", textX, textY);
		 if(commandNum == 3) {
	    	  g2.drawString(">", textX-25, textY);
	    	  if(gp.keyH.enterPressed ==true) {
	    		  subState=4;
	    		  commandNum=0;
	    	  }
	    	  
	      }
		//Back
		textY+=gp.tileSizeY;
		g2.drawString("Back", textX, textY);
		 if(commandNum == 4) {
	    	  g2.drawString(">", textX-25, textY);
	    	  if(gp.keyH.enterPressed ==true) {
	    		  gp.gameState=gp.playState;
	    		  commandNum=0;
	    	  }
	    	  
	      }
		// MUSIC VOLUME
		 	textX = frameX + (int)(gp.tileSizeX*4.5);
			textY = frameY + gp.tileSizeY + 30;
			g2.drawRect(textX, textY, 120, 24);
			int volumeWidth = 24 * gp.sound.volumeScale;
			g2.fillRect(textX, textY, volumeWidth, 24);
			
			// SE VOLUME
			textY += gp.tileSizeY;
			g2.drawRect(textX, textY, 120, 24);
			volumeWidth = 24 * gp.se.volumeScale;
			g2.fillRect(textX, textY, volumeWidth, 24);
	}
	
	public void options_endGame(int frameX, int frameY){
		String text ="ARE YOU SURE?";
		int textX= getXforCenteredText(text);
		int textY= frameY + gp.tileSizeY*2;
		g2.drawString(text, textX, textY);
		//YES/NO
		textY= frameY+gp.tileSizeY*4-20;
		g2.drawString("YES", textX, textY);
		
		
        
		if(commandNum==0||commandNum==3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed==true) {
				subState=0;
				commandNum=0;
				//gp.saveData();
				gp.gameState=gp.titleState;
			}
		}
		
		textY= frameY+gp.tileSizeY*5-20;
		g2.drawString("NO", textX, textY);
		
		if(commandNum==1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed==true) {
				subState=0;
				commandNum=0;
			}
		}
		
		
	}
	public void options_control(int frameX, int frameY){
		
		String text ="Options";
		int textX= getXforCenteredText(text);
		int textY= frameY + gp.tileSizeY;
		g2.drawString(text, textX, textY);
		textX=frameX+gp.tileSizeX;
		textY=frameY+gp.tileSizeY*2-gp.tileSizeX/4;
		g2.drawString("Pause", textX, textY);
		textX=frameX+gp.tileSizeX*4;
		g2.drawString("P", textX, textY);
		
		textX=frameX+gp.tileSizeX;	
		textY=frameY+gp.tileSizeY*3-gp.tileSizeX/4;
		g2.drawString("Interact", textX, textY);
		textX=frameX+gp.tileSizeX*4;
		g2.drawString("L", textX, textY);
		
		
		textX=frameX+gp.tileSizeX;
		 textY=frameY+gp.tileSizeY*4-gp.tileSizeX/4;
		g2.drawString("Attack", textX, textY);
		textX=frameX+gp.tileSizeX*4;
		g2.drawString("J", textX, textY);
		
		
		textX=frameX+gp.tileSizeX;	
		 textY=frameY+gp.tileSizeY*5-gp.tileSizeX/4;
		g2.drawString("Skill", textX, textY);
		textX=frameX+gp.tileSizeX*4;
		g2.drawString("K", textX, textY);
		
		
		textX=frameX+gp.tileSizeX;	
		 textY=frameY+gp.tileSizeY*6-gp.tileSizeX/4;
		g2.drawString("Options", textX, textY);
		textX=frameX+gp.tileSizeX*4;
		g2.drawString("ESC", textX, textY);
		
		
		textX=frameX+gp.tileSizeX;	
		 textY=frameY+gp.tileSizeY*7-gp.tileSizeX/4;
		g2.drawString("Move", textX, textY);
		textX=frameX+gp.tileSizeX*4;
		g2.drawString("W/A/S/D", textX, textY);
		
		textX=frameX+gp.tileSizeX;	
		 textY=frameY+gp.tileSizeY*8-gp.tileSizeX/4;
		g2.drawString("Túi đồ", textX, textY);
		textX=frameX+gp.tileSizeX*4;
		g2.drawString("E", textX, textY);
			
		
		//OK
		textX=frameX+gp.tileSizeX;
		textY= frameY+gp.tileSizeY*9-gp.tileSizeX/2;
		g2.drawString("OK", textX, textY);
		commandNum=0;
		if(commandNum==0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed==true) {
				subState=0;
				commandNum=0;
			}
		}
		
		
	}
	
	
	
	public void drawInventory() {
		
		int frameX = gp.tileSizeX * 9;
		int frameY = gp.tileSizeY;
		int frameWidth = gp.tileSizeX * 6;
		int frameHeight = gp.tileSizeY * 5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
	
		// SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSizeX+3;
		
		// DRAW PLAYER'S ITEMS
		for(int i = 0; i< gp.player.inventory.size(); i++) {
			if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
					gp.player.inventory.get(i) == gp.player.currentShield) {
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSizeX, gp.tileSizeY, 10, 10);
			}
			
			
			image = getItemImage(gp.player.inventory.get(i));
			g2.drawImage(image, slotX, slotY, null);
			
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
				
			}
		}
		
		// CURSOR
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.tileSizeX;
		int cursorHeight = gp.tileSizeY;
		
		// DRAW CURSORS
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		// DESCRIPTION FRAME
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSizeY*3;
		
		// DRAW DESCRIPTION TEXT
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSizeY;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		if(itemIndex < gp.player.inventory.size()) {
			
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			
			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
				
				g2.drawString(line, textX, textY);
				textY += 32;
			}
		}
		//
		
		int qFrameX = frameX;
		int qFrameY = frameY + frameHeight+gp.tileSizeY*3;
		int qFrameWidth = frameWidth;
		int qFrameHeight = gp.tileSizeY*3;
		textX = qFrameX + 20;
		textY = qFrameY + gp.tileSizeY;
		drawSubWindow(qFrameX, qFrameY, qFrameWidth, qFrameHeight);
		g2.drawString("Q để chọn/dùng item", textX, textY);
		textY+=gp.tileSizeY;
		g2.drawString("R để bỏ Item", textX, textY);
		
	}
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	
	public BufferedImage getItemImage(Item item) {
		BufferedImage image =null;
		switch (item.type) {
		case 2: {
			switch(item.name) {
			case"Red Potion":
				image = setupImage("/objects/potion_red.png", gp.tileSizeX, gp.tileSizeY);
				break;
			case"Blue Potion":
				image = setupImage("/objects/potion_blue.png", gp.tileSizeX, gp.tileSizeY);
				break;
			}
		}
		break;
		case 4:
		switch(item.name) {
		case"Blue Shield":
			image = setupImage("/objects/shield_blue.png", gp.tileSizeX, gp.tileSizeY);
			break;
		case"Wood Shield":
			image = setupImage("/objects/shield_wood.png", gp.tileSizeX, gp.tileSizeY);
			break;
		}
		break;
		case 3:
		switch(item.name) {
		case"PowerPole":
			image = setupImage("/objects/powerPole.png", gp.tileSizeX, gp.tileSizeY);
			break;
		case"Normal Sword":
			image = setupImage("/objects/sword_normal.png", gp.tileSizeX, gp.tileSizeY);
			break;
		case"Magic Sword":
			image = setupImage("/objects/sword_magic.png", gp.tileSizeX, gp.tileSizeY);
			break;
		case "Dầu ăn":
			image = setupImage("/objects/dauan.png", gp.tileSizeX, gp.tileSizeY);
			break;
		}
		break;
		case 5:
			switch(item.name) {
			case"Key":
				image = setupImage("/objects/key.png", gp.tileSizeX, gp.tileSizeY);
				break;
			case "KeyOpenRockDoor":
				image = setupImage("/objects/KeyOpenRockDoor.png", gp.tileSizeX, gp.tileSizeY);
				break;
			}
			break;
		}
		return image;
	}
	
}