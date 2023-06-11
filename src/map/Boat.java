package map;

import entity.Entity;
import main.Panel;


public class Boat extends MapObject {

	public Boat(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Boat";
		collision = false;
	}
}
