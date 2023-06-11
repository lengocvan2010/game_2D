package main;

import java.awt.Point;

public class MonsterData {
	//để lưu dữ liệu quái trên map và spawn chúng
	public String name;
	public int x,y;
	public int timespawn;
	
	public MonsterData(String name,int x, int y,int time) {
		this.name=name;
		this.x=x;
		this.y=y;
		this.timespawn=time;
	}
}
