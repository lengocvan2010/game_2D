package projectile;

import java.awt.Rectangle;
import java.io.Serializable;

import entity.Entity;
import entity.Player;
import main.Panel;
import map.*;

public class Projectile extends Entity {
	Entity user;
	public String name;
	public boolean alive;
	public int time;
	public int cost;
	public Projectile(Panel gp,Entity user) {
		super(gp);
		this.user=user;
		cost=1;
	}
	public void set(int x,int y,int direction,boolean alive,int ATK) {
		this.direction=direction;
		this.x=x;
		this.y=y;
		this.alive=alive;
		HP=MaxHP;
		collisionOn=false;
		solidArea = new Rectangle(15,20,18,18);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void update() {
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
		if(user instanceof Player) {
			playerP();
		}
		else {
			monsterP();
		}
		if(this.collisionOn) user.projectile.alive=false;
		if(HP<=0) {user.projectile.alive=false;}
	}
	
	public void playerP() {
		int i = gp.check.checkEntity(this, gp.monsters);
		if(i!=999) {
			if(gp.monsters[i].invincible==false) {
				gp.player.damageMonster(i, this.ATK);
			}
			user.projectile.alive=false;
		}
		int x = gp.check.checkObject(this, gp.mapobj);
		if(x!=999) {	
			if(gp.mapobj[x].name=="Forest") {
				gp.mapobj[x]=null;
			}		
		}
	}
	public void monsterP() {
		boolean touchPlayer = gp.check.checkPlayer(this);
		if(touchPlayer==true) {
			if(gp.player.invincible==false) {
				int damage=this.ATK-gp.player.DEF*2;
				if(damage>0) gp.player.HP=gp.player.HP-damage;
				else gp.player.HP--;
				gp.playSE(3);
				gp.player.invincible=true;
			}
			user.projectile.alive=false;
		}
	}
	
	
}
