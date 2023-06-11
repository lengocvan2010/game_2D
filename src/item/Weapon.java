package item;

import java.awt.Rectangle;

import entity.Player;
import main.Panel;
import projectile.PowerPoleSkill;


public class Weapon extends Item{
	public int iATK;
	public Rectangle attackArea;
	public Weapon(Panel gp, int col, int row) {
		super(gp, col, row);
		type =3;
	}
	
	public void ieffect(Player player) {
		
	}

}
