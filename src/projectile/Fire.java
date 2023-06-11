package projectile;

import java.awt.Rectangle;
import java.io.Serializable;

import entity.Entity;
import entity.Player;
import main.Panel;

public class Fire extends Projectile{
	
	public Fire(Panel gp,Entity entity) {
		super(gp,entity);
		name="Fire";
		speed=6;
		tempSpeed=speed;
		this.ATK=user.ATK*3/2; 
		if(user instanceof Player) {
			MaxHP=120;
			time=100;
		}else {
			MaxHP=60;
			time=80;
		}
		HP=MaxHP;
	}
	
	public void set(int x,int y,int direction,boolean alive,int ATK) {
		super.set(x, y, direction, alive, ATK);
		this.ATK=user.ATK*3/2;
	}
}
