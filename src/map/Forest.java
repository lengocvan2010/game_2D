package map;

import entity.Entity;
import main.Panel;

public class Forest extends MapObject {

	public Forest(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Forest";
		collision =true;
	}
}
