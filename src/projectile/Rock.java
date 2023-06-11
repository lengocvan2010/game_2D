package projectile;

import java.io.Serializable;

import entity.Entity;
import main.Panel;

public class Rock extends Projectile{

	public Rock(Panel gp, Entity user) {
		super(gp, user);
		name="MonsterP";
		speed=6;
		time = 50;
		tempSpeed=speed;
		MaxHP=50;
		HP=MaxHP;
		ATK=user.ATK;
	}
	public void set(int x,int y,int direction,boolean alive,int ATK) {
		super.set(x, y, direction, alive, ATK);
		this.ATK=user.ATK;
		
	}
}
