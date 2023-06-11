package entity;

import java.awt.Rectangle;
import java.io.Serializable;

import main.Panel;
import projectile.Projectile;

public class Entity extends Object{
	//Entity Status
	public int lv,speed;
	public int MP,MaxMP;
	public int direction; //1 up, 2 down, 3 left, 4 right
	public int HP,MaxHP,ATK,DEF;
	public int exp=0;
	public int tempSpeed;
	//Skill
	public Projectile projectile;
	public int timeProjectile=0;
	
	//Bat Tu
	public boolean invincible = false;
	public int invincibleTime = 0;
	
	//XuLyVaCham
	public boolean collisionOn = false;
	

	//for draw
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	
	public Entity(Panel gp) {
		solidArea = new Rectangle(10,20,28,28);
		this.gp=gp;
	}

	public void move() {
		if(collisionOn==false) {
			switch(direction) {
			case 1:
				y-=tempSpeed; break;
			case 2:
				y+=tempSpeed; break;
			case 3:
				x-=tempSpeed; break;
			case 4:
				x+=tempSpeed; break;
			}
		}
		
	}
	public void invincibleSetup() {
		if(this.invincible==true) {
			this.invincibleTime++;
			if(this.invincibleTime>90) {
				this.invincible=false;
				this.invincibleTime=0;
			}
		}
	}
	public void XuLiVaCham() {
		
	}
	
	public void update() {
		
	}
}
