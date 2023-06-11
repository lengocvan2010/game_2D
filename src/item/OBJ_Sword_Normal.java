package item;

import java.awt.Rectangle;

import entity.Entity;
import entity.Player;
import main.Panel;
import projectile.Fire;


public class OBJ_Sword_Normal extends Weapon {

	public OBJ_Sword_Normal(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Normal Sword";
		iATK = 5;
		attackArea = new Rectangle(0,0,36,36);
		description = "[" + name + "]\n Old sword.";
	}
	
	public void ieffect(Player player) {
			for (int i = 0; i < gp.proList.size(); i++) {
				if(gp.proList.get(i)==player.projectile) {
					gp.proList.remove(i);
					gp.drawPro.remove(i);
				}
			}
			player.projectile = null;
		
	}
}
