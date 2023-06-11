package item;

import entity.Entity;
import main.Panel;

public class OBJ_KeyOpenRockDoor extends Item {

	public OBJ_KeyOpenRockDoor(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "KeyOpenRockDoor";
		type=5;
		description = "[" + name + "]\n To open the door.";
	}
}
