package map;

import java.util.Random;

import entity.Entity;
import entity.Player;
import graphic.DrawMapObject;
import item.OBJ_Heart;
import item.OBJ_HeartHalf;
import item.OBJ_Potion_Blue;
import item.OBJ_Potion_Red;
import main.Panel;

public class Treasure_lock extends MapObject {

	public Treasure_lock(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Treasure_lock";
		collision = true;
	}
	public void effect(Player player) {
		if(gp.keyH.interact==true) {
			gp.playSE(2);
			for (int i = 0; i < gp.item.length; i++) {
				if (gp.item[i]==null) {
					Random r = new Random();
					int a = r.nextInt(50);
					if(a<20) {
						gp.item[i] = new OBJ_Potion_Blue(gp,x/gp.tileSizeX,y/gp.tileSizeY);
						break;
					}
					else if(a>=20&&a<40) {
						gp.item[i] = new OBJ_Potion_Red(gp,x/gp.tileSizeX,y/gp.tileSizeY);
						break;
					}
					else {
						gp.item[i] = new OBJ_Potion_Red(gp,x/gp.tileSizeX,y/gp.tileSizeY);
						gp.item[i+1] = new OBJ_Potion_Blue(gp,x/gp.tileSizeX,y/gp.tileSizeY);
						break;
					}
				}
			}
			for (int i = 0; i < gp.mapobj.length; i++) {
				if(gp.mapobj[i]==this) {
					gp.mapobj[i] = new Treasure_unlock(gp, x/gp.tileSizeX, y/gp.tileSizeY);
					gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
					break;
				}
			}
		}
	}
}
