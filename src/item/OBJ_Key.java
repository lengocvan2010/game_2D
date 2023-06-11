package item;

import entity.Entity;
import main.Panel;

public class OBJ_Key extends Item{

	public OBJ_Key(Panel gp,int col, int row) {
		super(gp,col,row);
		name="Key";
		value =1;
		type = 5;
		description = "[" + name + "]\n To open the door.";
	}

}
