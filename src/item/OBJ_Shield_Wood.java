package item;

import entity.Entity;
import main.Panel;

public class OBJ_Shield_Wood extends Shield {

	public OBJ_Shield_Wood(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Wood Shield";
		iDEF = 3;
		description = "[" + name + "]\n Old shield.";
	}

}
