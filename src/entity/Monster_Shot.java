package entity;


import java.io.Serializable;

import graphic.DrawProjectile;
import main.Panel;
import projectile.Rock;

public class Monster_Shot extends Monster{

	public Monster_Shot(Panel gp) {
		super(gp);
		name="SpiderMonster";
		timeSpawn=450;
		projectile = new Rock(gp, this);
		exp=8;
		MaxHP = 50;
		HP = MaxHP;
		DEF = 5;
		ATK = 30;
	}
	
	public void update() {
		super.update();
		shot();
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
