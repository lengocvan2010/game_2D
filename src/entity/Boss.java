package entity;

import java.awt.Point;
import java.awt.Rectangle;

import graphic.DrawMonster;
import graphic.DrawProjectile;
import main.Panel;
import projectile.Fire;

	public class Boss extends Monster{
	public int soquai = 0;
	public int timesummon = 0;
	
	public Boss(Panel gp) {
		super(gp);
		speed = 3;
		tempSpeed=speed;
		MaxHP=500;
		HP=MaxHP;
		ATK=40;
		DEF=30;
		timeSpawn=1000;
		name="Boss";
		projectile = new Fire(gp, this);
		attack=0;
		exp=30;
	}
	
	public void update() {
		super.update();
		shot();
	}
	
	public void summonMonster() {
		if(timesummon>0&&soquai<2) timesummon--;
		if(collisionOn==false&&soquai<2&&timesummon<=0) {
			gp.playSE(6);
			int a = gp.countM+soquai;
			int x=0,y=0;
			switch(this.direction) {
			case 1:
				 x = this.x;
				 y = this.y-gp.tileSizeY;
				 break;
			case 2:
				 x = this.x;
				 y = this.y+gp.tileSizeY;
				
				 break;
			case 3:
				 x = this.x-gp.tileSizeX;
				 y = this.y;
				
				 break;
			case 4:
				 x = this.x+gp.tileSizeX;
				 y = this.y;
				 
				 break;
			}
			if(soquai==0) {
				gp.monsters[a] = new Monster_A(gp);
			}else {gp.monsters[a]=new Monster_Shot(gp);}
			gp.monsters[a].x = x;
			gp.monsters[a].y = y;
			gp.monsters[a].attack = 1;
			gp.monsters[a].ATK = 20;
			gp.monsters[a].DEF = 20;
			gp.monsters[a].MaxHP = 200;
			gp.monsters[a].HP = gp.monsters[a].MaxHP;
			gp.drawM[a] = new DrawMonster(gp.monsters[a], gp);
			soquai++;
			timesummon=200;
		}
	}
	
	public void setAction() {
		if(HP<MaxHP/2) {
			tempSpeed=speed+1;	
			summonMonster();
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
	public void shot() {
		if(attack==1) {
			if(timeProjectile>0) timeProjectile--;
			if(timeProjectile==0 && projectile.alive==false) {
				projectile.set(x, y, direction,true,ATK);
				gp.drawPros = new DrawProjectile(gp, projectile);
				gp.proList.add(projectile);
				gp.drawPro.add(gp.drawPros);
				timeProjectile=projectile.time;
			}
		}
	}


}
