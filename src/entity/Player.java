package entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;


import graphic.DrawItem;
import graphic.DrawPlayer;
import graphic.DrawProjectile;
import item.*;
import main.Panel;

public class Player extends Entity{
	//vi tri ban dau
	Point startPos; 
	
	
	public int STR;//suc manh
	public int DEX;//deo dai
	public int mpCountTime=0;
	//camera
	public final int screenX;
	public final int screenY;
	
	public int tempX,tempY;//for draw
	
	public int levelExp;
	//inventory
	public ArrayList<Item> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	//trang bi
	public Weapon currentWeapon;
	public Shield currentShield;
	//tan cong
	public boolean attacking;
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	
	public Player(Panel gp) {
		super(gp);
		screenX = gp.screenWidth/2 - (gp.tileSizeX/2);
		screenY = gp.screenHeight/2 - (gp.tileSizeY/2); 	
		startPos = new Point(14,10);
		setDefault();
	}
	
	public void setDefault() {
		x= startPos.x*gp.tileSizeX;
		y=startPos.y*gp.tileSizeY;
	
		solidArea = new Rectangle(10,20,28,28);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		currentShield = new OBJ_Shield_Wood(gp, 0, 0);
		currentWeapon = new OBJ_Sword_Normal(gp, 0, 0);
		direction=2;
		lv=1;
		speed = 4;
		tempSpeed=speed;
		STR=2;
		DEX =1;
		MaxHP = 20;
		HP=MaxHP;
		MaxMP = 4;
		MP=MaxMP;
		exp=0;
		levelExp=4;
		
		equipShield();
		equipWeapon();
		setInventory();
		
	}
	
	public void setInventory() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
	}
	
	public void equipWeapon() {
		attackArea = currentWeapon.attackArea;
		this.ATK= STR*3/2 + currentWeapon.iATK;
		currentWeapon.ieffect(this);
	}
	public void equipShield() {
		this.DEF= DEX*3/2 + currentShield.iDEF;
	}
	
	public void checkHPMP() {
		if(mpCountTime>=1000) {
			MP++;
			mpCountTime=0;
		} else mpCountTime++;
		
		if(HP>MaxHP) HP = MaxHP;
		if(MP>MaxMP) MP = MaxMP;
		if(MP<0) MP=0;	
		
		if(HP<=0) {
			HP=0;
			gp.GameOver=true;
		}
	}
	
	public void checkLVUp(){
		if(exp>=levelExp) {
			gp.playSE(10);
			exp=exp-levelExp;
			lv++;
			levelExp=lv*2;
			MaxHP+=lv/4+1;
			if(lv%10==0) MaxMP+=1;
			STR+=2+lv/2;
			DEX+=1+lv/2;
			this.ATK= STR*3/2 + currentWeapon.iATK;
			this.DEF= DEX*3/2 + currentShield.iDEF;
			gp.Ui.addMessage("LV UP!!! Your level is " + lv+"!!!");
		}
		
	}
	
	public void XuLiVaCham() {
		collisionOn = false;
		
		gp.check.checkTile(this);
		//checkMapObject
		int mapObjectIndex = gp.check.checkObject(this,gp.mapobj);
		interactMapObject(mapObjectIndex);
		
		//check Monster collision
		int monsterIndex = gp.check.checkEntity(this, gp.monsters);
		interactMonster(monsterIndex);
	
		int itemIndex = gp.check.checkObject(this, gp.item);
		interactItem(itemIndex);
	}
	
	public void interactItem(int i) {
		if(i!=999) {
			switch(gp.item[i].type) {
			case 1:
				gp.item[i].ieffect(this);
				gp.item[i]=null;
				gp.drawI[i]=null;
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				String text;
				if(inventory.size() != maxInventorySize) {
					
					inventory.add(gp.item[i]);
					text = "Got a " + gp.item[i].name + "!";
					gp.item[i]=null;
					gp.drawI[i]=null;
					}
				else {
					text = "Inventory is full!";
				}
				gp.Ui.addMessage(text);
				break;
			}
			 
		}
	}
	
	public void selectItem() {
		
		int itemIndex = gp.Ui.getItemIndexOnSlot();
		
		if(itemIndex < inventory.size()) {
			
			Item selectedItem = inventory.get(itemIndex);
		
			if(selectedItem.type == 3 ) {
				currentWeapon = (Weapon)selectedItem;
				equipWeapon();
				gp.drawP  = new DrawPlayer(this, gp);
			}
			if(selectedItem.type == 4) {
				currentShield = (Shield) selectedItem;
				equipShield();
			}
			if(selectedItem.type == 2) {
				selectedItem.ieffect(this);
				inventory.remove(itemIndex);
			}
		}
	}
	
	public void removeItem() {
		
		int itemIndex = gp.Ui.getItemIndexOnSlot();
		
		if(itemIndex < inventory.size()) {
			
			Item selectedItem = inventory.get(itemIndex);
		
			if(selectedItem.type == 3 ) {
				if(currentWeapon!=(Weapon)selectedItem) {
					for (int i = 0; i < gp.item.length; i++) {
						if(gp.item[i]==null) {
							gp.item[i] = selectedItem;
							gp.item[i].x = startPos.x*gp.tileSizeX;
							gp.item[i].y = startPos.y*gp.tileSizeY;
							gp.drawI[i] = new DrawItem(gp, gp.item[i]);
							String text = "Drop " + selectedItem.name + "!";
							gp.Ui.addMessage(text);
							break;
						}
					}
					
					inventory.remove(itemIndex);
				}
				
			}
			if(selectedItem.type == 4) {
				if(currentShield!=(Shield)selectedItem) {
					for (int i = 0; i < gp.item.length; i++) {
						if(gp.item[i]==null) {
							gp.item[i] = selectedItem;
							gp.item[i].x = startPos.x*gp.tileSizeX;
							gp.item[i].y = startPos.y*gp.tileSizeY;
							gp.drawI[i] = new DrawItem(gp, gp.item[i]);
							String text = "Drop " + selectedItem.name + "!";
							gp.Ui.addMessage(text);
							break;
						}
					}
					inventory.remove(itemIndex);
				}
					
			}
			if(selectedItem.type == 2) {
				inventory.remove(itemIndex);
				String text = "Drop " + selectedItem.name + "!";
				gp.Ui.addMessage(text);
			}
			
		}
		
	}
	
	public void interactMapObject(int i) {
		if(i!=999) {
			gp.mapobj[i].effect(this);
		}
	}
	
	
	public void interactMonster(int i) {
		if(i!=999) {
			if(invincible==false) {
				int damage=gp.monsters[i].ATK-DEF;
				if(damage>0) HP=HP-damage;
				else HP--;
				gp.playSE(3);
				invincible=true;
			}
		}
	}

	public void attack() {
		attacking=true;
		
		spriteCounter++;
		
		if(spriteCounter<10) {
			spriteNum=1;
		}
		if(spriteCounter>10&&spriteCounter<=25) {
			spriteNum=2;
			
			int currentX = x;
			int currentY = y;
			int solidAreaWidth=solidArea.width;
			int solidAreaHeight=solidArea.height;
			
			switch(direction) {
			case 1:
				y-=attackArea.height;break;
			case 2:
				y+=attackArea.height; break;
			case 3: 
				x-=attackArea.width; break;
			case 4: 
				x+=attackArea.width; break;
			}
			
			solidArea.width=attackArea.width;
			solidArea.height=attackArea.height;
			
			int monsterIndex = gp.check.checkEntity(this, gp.monsters);
			damageMonster(monsterIndex,ATK);
			
			x = currentX;
			y=currentY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			
		}
		if(spriteCounter>25) {
			gp.playSE(8);
			spriteNum=1;
			spriteCounter=0;
			attacking=false;
			gp.keyH.attack=false;
		}
	}
	
	public void specialAttack() {
		if(timeProjectile>0) timeProjectile--;
		if(gp.keyH.specialA==true) {
		if(projectile!=null) {
				if(MP>=projectile.cost&&projectile.alive==false&&timeProjectile==0) {
					MP-=projectile.cost;
					gp.playSE(4);
					projectile.set(x, y, direction,true,ATK);
					gp.drawPros = new DrawProjectile(gp, projectile);
					gp.proList.add(projectile);
					gp.drawPro.add(gp.drawPros);
					timeProjectile=projectile.time;
				}
			}
		}
		gp.keyH.specialA=false;
	}
	
	public void damageMonster(int i,int ATK) {
		if(i!=999) {
			if(gp.monsters[i].invincible==false) {
				int damage = (int) (ATK-gp.monsters[i].DEF*2) ;
				if(damage<=0) {damage=1;}
				gp.monsters[i].HP-=damage;
				gp.playSE(3);
				gp.Ui.addMessage(damage + " damage!");
				gp.monsters[i].invincible=true;
				gp.monsters[i].attack=1;
				if(gp.monsters[i].HP<=0) {
					if(gp.monsters[i] instanceof Boss) {
						gp.win=true;
					}else {
						gp.monsters[i].die();
						gp.Ui.addMessage("Killed the " + gp.monsters[i].name + "!");
						exp+=gp.monsters[i].exp;
						gp.monsters[i]=null;
					}
				}	
			}
		}	
	}
		
	public void update() {
		invincibleSetup();
		specialAttack();
		if(gp.keyH.attack==true) {
			attack();
		}
		else if(gp.keyH.upPressed==true||gp.keyH.downPressed==true||gp.keyH.leftPressed==true||gp.keyH.rightPressed==true) {
			if(gp.keyH.upPressed==true) {
				direction = 1;
			}
			else if(gp.keyH.downPressed==true) {
				direction = 2;
			}
			else if(gp.keyH.leftPressed==true) {
				direction = 3;
			}
			else if(gp.keyH.rightPressed==true) {
				direction = 4;
			}
			spriteCounter++;
			if(spriteCounter>15) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if(spriteNum==2) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
			XuLiVaCham();
			if(gp.keyH.dash==true) {
				tempSpeed=speed+2;
			}else tempSpeed=speed;
			move();
		}
		checkLVUp();
		checkHPMP();
	}
}
