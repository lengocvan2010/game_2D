package map;

import entity.Entity;
import main.Panel;

public class HouseSeaSide extends MapObject {

	public HouseSeaSide(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "HouseSeaSide";
		collision = true;
	}
}
