package item;

import java.awt.Rectangle;
import java.io.Serializable;

import entity.Entity;
import entity.Object;
import entity.Player;
import main.Panel;
import map.MapObject;

public class Item extends MapObject {
	public int value,iATK,iDEF;
	public int time;
	
	public String description="";
	public int type; //1 dung ngay, 2 cho vao inventory, 3 weapon, 4 shield, 5 use for object
	
	public Item(Panel gp,int col,int row) {
		super(gp, col, row);
		solidArea = new Rectangle(4,4,40,40);
		
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		
		time =1000;
	}
	
	public void ieffect(Player entity) {
		
	}
	
	public void update() {
		if(type==1||type ==2) {
			time--;
			if(time==0) {
				for (int i = 0; i < gp.item.length; i++) {
					if(gp.item[i]==this) {
						gp.item[i]=null;
					}
				}
			}
		}
	}
	
}
