package item;

import entity.Entity;
import entity.Player;
import main.Panel;

public class OBJ_Heart extends Item{

	public OBJ_Heart(Panel gp,int col,int row) {

		super(gp,col,row);
		name = "Heart";
		value = 12;
		type=1;
		
	}
	
	public void ieffect(Player entity) {
		gp.Ui.addMessage("HP + " + value);
		entity.HP += value;
	}
}
