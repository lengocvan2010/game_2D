package item;

import entity.Entity;
import main.Panel;

public class OBJ_Shield_Blue extends Shield {

	public OBJ_Shield_Blue(Panel gp, int col,int row) {
		super(gp,col,row);
		name = "Blue Shield";
		iDEF = 5;
		description = "[" + name + "]\nA shiny blue shield.";
	}
}
