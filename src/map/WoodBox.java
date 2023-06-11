package map;

import entity.Entity;
import main.Panel;

public class WoodBox extends MapObject {

	public WoodBox(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Wood Box";
		collision = true;
	}
}
