package entity;

import java.io.Serializable;
import java.util.Random;

import graphic.DrawItem;
import item.*;
import main.Panel;

public class Monster extends Entity{
	
	public int ActionCounter =0;
	public int timeSpawn;
	public int attack;//1 attack 2 no attack
	public String name;
	public boolean onPath=false;
	Random random = new Random();
	public Monster(Panel gp) {
		super(gp);
		direction = 2;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		speed = 2;
		tempSpeed=speed;
		
		attack=0;
	}
	
	public void attackPlayer() {
		boolean touchPlayer = gp.check.checkPlayer(this);
		gp.check.checkTile(this);
		if(touchPlayer==true) {
			if(gp.player.invincible==false) {
				int damage=ATK-gp.player.DEF;
				if(damage>0) gp.player.HP=gp.player.HP-damage;
				else gp.player.HP--;
				gp.playSE(3);
				gp.player.invincible=true;
			}
		}
	}
	
	public void XuLiVaCham() {
		collisionOn = false;
		gp.check.checkTile(this);
		gp.check.checkPlayer(this);
		int i = gp.check.checkObject(this,gp.mapobj);
		if(i!=999) {
			gp.mapobj[i].effect(this);
		}else tempSpeed = speed;
		gp.check.checkEntity(this, gp.monsters);
	}
	
	
	public void searchPath(int goalCol, int goalRow){
        int startCol= (x+solidArea.x)/gp.tileSizeX;
        int startRow = (y+solidArea.y)/gp.tileSizeY;
        gp.pFinder.setNodes(startCol, startRow,goalCol,goalRow,this);
        if(gp.pFinder.search()==true){
            //next X& Y
            int nextX=gp.pFinder.pathList.get(0).col*gp.tileSizeX;
            int nextY=gp.pFinder.pathList.get(0).row*gp.tileSizeY;
            // Entity's solidArea position
            int enLeftX = x+solidArea.x;
            int enRightX= x+solidArea.x+solidArea.width;
            int enTopY = y+solidArea.y;
            int enBottomY=y+solidArea.y+solidArea.height;
            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSizeX){
                direction = 1;
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSizeX){
                direction = 2;
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSizeY){
                // left or right
                if(enLeftX > nextX){
                    direction = 3;
                }
                if(enLeftX < nextX){
                    direction = 4;
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                // up or left
                direction = 1;
                XuLiVaCham();
                if(collisionOn == true){
                    direction = 3;
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                // up or right
                direction = 1;
                XuLiVaCham();
                if(collisionOn== true){
                    direction = 4;
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                // down or left
                direction = 2;
                XuLiVaCham();
                if(collisionOn == true){
                    direction =3;
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                // down or right
                direction = 2;
                XuLiVaCham();
                if(collisionOn == true){
                    direction = 4;
                }
            }
        }
	}
	
	public void update() {
		int xDistance = Math.abs(x - gp.player.x)/gp.tileSizeX;
        int yDistance = Math.abs(y - gp.player.y)/gp.tileSizeY;
        int tileDistance = (xDistance+yDistance);
        
        if(onPath == false && tileDistance < 8 && attack==1){
        	onPath = true;
        }else if(tileDistance>8) {onPath=false;}
		setAction();
		invincibleSetup();
		XuLiVaCham();
		attackPlayer();
		move();
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
	}
	
	public void setAction() {
		
		if(HP<MaxHP/2) {
			tempSpeed=speed+1;
		}
		if(onPath==true) {
			int goalCol=(gp.player.x+gp.player.solidArea.x)/gp.tileSizeX;
            int goalRow=(gp.player.y+gp.player.solidArea.y)/gp.tileSizeY;
            searchPath(goalCol,goalRow);
		}
		else {
			
			ActionCounter++;
			if(ActionCounter==120) {
				int i = random.nextInt(60)+1;
				
				if(i<=15){
					direction=1;}
				if(i>15&&i<=30){
					direction = 2;
				}
				if(i>30&&i<=45){
					direction = 3;
				}
				if(i>45){
					direction = 4;
				}
				ActionCounter=0;
			}
		}
	}
	public void die() {
		if(HP<=0) {
			gp.playSE(5);
			dropItem();
		}
	}
	public void dropItem() {
		int a = random.nextInt(10);
		Item item = null;
		for (int i = 0; i < gp.item.length; i++) {
			if(gp.item[i]==null) {
				switch(a) {
				case 1:
					item = new OBJ_Potion_Blue(gp,x/gp.tileSizeX,y/gp.tileSizeY);
					break;
				case 2:
					item = new OBJ_Potion_Red(gp,x/gp.tileSizeX,y/gp.tileSizeY);
					break;
				case 3:
				case 4:
				case 5:
				case 6:
					item = new OBJ_Heart(gp,x/gp.tileSizeX,y/gp.tileSizeY);
					break;
				case 7:
				case 8:
				case 9:
				case 10:
					item = new OBJ_HeartHalf(gp,x/gp.tileSizeX,y/gp.tileSizeY);
					break;
				}
				if(item!=null) {
					gp.item[i]=item;
					gp.drawI[i] = new DrawItem(gp, gp.item[i]);
				break;}
			}
		}
	}
}
