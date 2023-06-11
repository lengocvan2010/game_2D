package projectile;

import java.awt.Rectangle;

import entity.Entity;
import main.Panel;

public class PowerPoleSkill extends Projectile{

	public PowerPoleSkill(Panel gp, Entity user) {
		super(gp, user);
		solidArea= new Rectangle(0,0,48,48);
		
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		
		name="PPS";
		speed=7;
		tempSpeed=speed;
		MaxHP=140;
		ATK=5*user.ATK; 
		time=150;
		HP=MaxHP;
		cost=1;
		
	}
	public void update() {
		int goalCol=(int)(user.x+user.solidArea.x)/gp.tileSizeX;
        int goalRow=(int)(user.y+user.solidArea.y)/gp.tileSizeY;
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
		HP--;
		move();
		int i = gp.check.checkEntity(this, gp.monsters);
		collisionOn=false;
		if(i!=999) {
			if(gp.monsters[i].invincible==false) {
				gp.player.damageMonster(i, this.ATK);
			}
		}
		int x = gp.check.checkObject(this, gp.mapobj);
		collisionOn=false;
		if(x!=999) {
			
			searchPath(goalCol, goalRow);
		}
		if(gp.keyH.interact==true) {
			
			searchPath(goalCol, goalRow);	
			
		}
		if(HP<=100) {
			searchPath(goalCol, goalRow);
			
		}
		if(HP<=0) {user.projectile.alive=false;}
		
	}
	
	public void searchPath(int goalCol,int goalRow){
		
        int startCol= (x+solidArea.x)/gp.tileSizeX;
        int startRow = (y+solidArea.y)/gp.tileSizeY;
        gp.pFinderS.setNodes(startCol, startRow,goalCol,goalRow,this);
        if(gp.pFinderS.search()==true){
            //next X& Y
            int nextX=gp.pFinderS.pathList.get(0).col*gp.tileSizeX;
            int nextY=gp.pFinderS.pathList.get(0).row*gp.tileSizeY;
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
            if(Math.abs((int)this.x/gp.tileSizeX-goalCol)<=1&&Math.abs((int)this.y/gp.tileSizeY-goalRow)<=1) {	
				user.projectile.alive=false;
				user.timeProjectile=0;
			}
            
        }
	}
	public void set(int x,int y,int direction,boolean alive,int ATK) {
		super.set(x, y, direction, alive, ATK);
		this.ATK = user.ATK*5;
	}
	
	public void XuLiVaCham() {
		collisionOn = false;
	}
	
}
