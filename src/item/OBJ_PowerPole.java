package item;

import java.awt.Rectangle;

import entity.Entity;
import entity.Player;
import main.Panel;
import projectile.Fire;
import projectile.PowerPoleSkill;

public class OBJ_PowerPole extends Weapon {

	public OBJ_PowerPole(Panel gp,int col,int row) {
		super(gp, col , row);
		name = "PowerPole";
		iATK = 50;
		type =3;
		attackArea = new Rectangle(0,0,48,28);
		description = "[" + name + "]\n \"!!!\"\n--WuKong--";
	}
	
	public void ieffect(Player player) {
		for (int i = 0; i < gp.proList.size(); i++) {
			if(gp.proList.get(i)==player.projectile) {
				gp.proList.remove(i);
				gp.drawPro.remove(i);
			}
		}
		player.projectile = new PowerPoleSkill(gp, player);
	}
	
	
}
