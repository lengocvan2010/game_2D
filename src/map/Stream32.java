package map;

import entity.Entity;

import main.Panel;

public class Stream32 extends MapObject {

	public Stream32(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Stream32";
		collision = true;
	}
}
