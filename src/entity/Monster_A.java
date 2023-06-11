package entity;

import java.io.Serializable;

import main.Panel;

public class Monster_A extends Monster{
	public Monster_A(Panel gp) {
		super(gp);
		timeSpawn=300;
		name="Slime";
		exp=4;
		MaxHP = 20;
		HP = MaxHP;
		DEF = 2;
		ATK = 10;
	}
	
	public void update() {
		super.update();
	}
}
