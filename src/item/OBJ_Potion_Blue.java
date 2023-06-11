package item;

import entity.Entity;
import entity.Player;
import main.Panel;

public class OBJ_Potion_Blue extends Item {

	public OBJ_Potion_Blue(Panel gp,int col,int row) {
		super(gp,col,row);

		name = "Blue Potion";
		value = 4;
		type=2;
		description = "[" + name + "]\nRegen your MP by " + value + ".";
	}
	
	public void ieffect(Player entity) {
		gp.Ui.addMessage("MP + " + value);
		entity.MP += value;
		entity.checkHPMP();
	}
	
}
