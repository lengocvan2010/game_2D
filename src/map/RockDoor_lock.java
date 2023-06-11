package map;

import entity.Player;
import item.OBJ_Key;
import item.OBJ_KeyOpenRockDoor;
import main.Panel;

public class RockDoor_lock extends MapObject{
	public RockDoor_lock(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "RockDoor_lock";
		collision = true;
	}
	
	public void effect(Player player) {
		int i;
		for (i = 0; i < player.inventory.size(); i++) {
			if(player.inventory.get(i) instanceof OBJ_KeyOpenRockDoor) {
				player.inventory.remove(i);
				gp.playSE(2);
				for (int j = 0; j < gp.mapobj.length; j++) {
					if(gp.mapobj[j]==this) {
						gp.mapobj[j] = null;//new Portal(gp, x/gp.tileSizeX, y/gp.tileSizeY);
						break;
					}
				}
			}else if(i==player.inventory.size()-1) {
				gp.Ui.addMessage("Need Key!");
			}
		}
		

	}
}