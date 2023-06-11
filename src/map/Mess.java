package map;

import entity.Entity;
import main.Panel;

public class Mess extends MapObject{

	public Mess(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Mess";
		collision = false;
	}
}
