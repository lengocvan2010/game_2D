package map;

import entity.Entity;
import main.Panel;

public class BigRock extends MapObject {

	public BigRock(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "BigRock";
		collision = true;
	}
}
