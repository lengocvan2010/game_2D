package map;

import entity.Entity;
import main.Panel;

public class BigBox extends MapObject{

	public BigBox(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Big Box";
		collision = true;
	}
}
