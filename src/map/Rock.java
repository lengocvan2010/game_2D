package map;

import javax.imageio.ImageIO;

import entity.Entity;
import main.Panel;

public class Rock extends MapObject{
	
	public Rock(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Rock";
		collision=true;
	}
	public void effect(Entity entity) {
		
	}
}
