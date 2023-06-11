package item;

import entity.Entity;
import entity.Player;
import main.Panel;

public class OBJ_Potion_Red extends Item {

	public OBJ_Potion_Red(Panel gp,int col,int row) {
		super(gp,col,row);

		name = "Red Potion";
		value = 50;
		type=2;
		description = "[" + name + "]\nHeals your HP by " + value + ".";
	}
	
	public void ieffect(Player entity) {
		gp.Ui.addMessage("HP + " + value);
		gp.playSE(7);
		entity.HP += value;
		entity.checkHPMP();
	}
	
}
