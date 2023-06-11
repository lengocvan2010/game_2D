package map;

import item.*;
import entity.Entity;
import entity.Player;
import main.Panel;

public class Doorlock extends MapObject {

	public Doorlock(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Door_lock";
		collision = true;
	}
	
	public void effect(Player player) {
		for (int i = 0; i < player.inventory.size(); i++) {
			if(player.inventory.get(i) instanceof OBJ_Key) {
				player.inventory.remove(i);
				for (int j = 0; j < gp.mapobj.length; j++) {
					if(gp.mapobj[j]==this) {
						gp.mapobj[j] = new Doorunlock(gp, x/gp.tileSizeX, y/gp.tileSizeY);
						break;
					}
				}
			}
		}
	}
}
