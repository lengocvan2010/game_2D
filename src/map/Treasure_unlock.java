package map;

import entity.Entity;
import main.Panel;

public class Treasure_unlock extends MapObject{

	public Treasure_unlock(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Treasure_unlock";
		collision = false;
	}
}
