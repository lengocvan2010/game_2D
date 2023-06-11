package map;

import java.awt.Rectangle;

import entity.Monster_A;
import entity.Monster_Shot;
import entity.Player;
import graphic.DrawMonster;
import main.Panel;

public class TrapSummon extends MapObject{

	public TrapSummon(Panel gp, int col, int row) {
		super(gp, col, row);
		name = "SummonTrap";
		solidArea = new Rectangle(-4*gp.tileSizeX,-4*gp.tileSizeY,gp.tileSizeX*8, gp.tileSizeY*8);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		collision =false;
	}
	
	public void effect(Player player) {
		
		int a = 4;
		while(a>0)
		for (int i = 0; i < gp.monsters.length; i++) {
			if(gp.monsters[i]==null) {
				
				switch(a) {
				case 4:
					gp.monsters[i]=new Monster_A(gp);
					gp.monsters[i].x=this.x - gp.tileSizeX;
					gp.monsters[i].y=this.y;
					break;
				case 3:
					gp.monsters[i]=new Monster_A(gp);
					gp.monsters[i].x=this.x + gp.tileSizeX;
					gp.monsters[i].y=this.y;
					break;
				case 2:
					gp.monsters[i]=new Monster_Shot(gp);
					gp.monsters[i].x=this.x;
					gp.monsters[i].y=this.y-gp.tileSizeY;
					break;
				case 1:
					gp.monsters[i]=new Monster_Shot(gp);
					gp.monsters[i].x=this.x;
					gp.monsters[i].y=this.y+gp.tileSizeY;
					break;
				}
				gp.drawM[i] =new DrawMonster(gp.monsters[i], gp);
				gp.monsters[i].attack=1;
				a--;
				break;
			}
		}
		
		for (int i = 0; i < gp.mapobj.length; i++) {
			if(gp.mapobj[i]==this) {
				gp.mapobj[i]=null;
				break;
				
			}
		}
		gp.playSE(6);
	}

}
