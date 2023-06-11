package item;

import java.awt.Rectangle;

import entity.Player;
import main.Panel;
import projectile.Fire;

public class OBJ_Sword_Magic extends Weapon{
	public OBJ_Sword_Magic(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Magic Sword";
		iATK = 10;
		attackArea = new Rectangle(0,0,36,36);
		description = "[" + name + "]\n It has magic!!!";
	}
	public void ieffect(Player player) {
		for (int i = 0; i < gp.proList.size(); i++) {
			if(gp.proList.get(i)==player.projectile) {
				gp.proList.remove(i);
				gp.drawPro.remove(i);
			}
		}
		player.projectile = new Fire(gp, player);
	}

}
