package item;

import java.awt.Rectangle;

import entity.Player;
import main.Panel;
import projectile.*;

public class OBJ_SpecicalW extends Weapon{
	public OBJ_SpecicalW(Panel gp,int col,int row) {
		super(gp, col , row);
		name = "Dầu ăn";
		iATK = 99;
		type =3;
		attackArea = new Rectangle(0,0,48,48);
		description = "[" + name + "]\n ft.Neptune";
	}
	
	public void ieffect(Player player) {
		for (int i = 0; i < gp.proList.size(); i++) {
			if(gp.proList.get(i)==player.projectile) {
				gp.proList.remove(i);
				gp.drawPro.remove(i);
			}
		}
		player.projectile = new SpecialWSkill(gp, player);
	}


}
