package map;

import javax.imageio.ImageIO;

import entity.Entity;
import main.Panel;

public class Trees extends MapObject{
	
	public Trees(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "Trees";
		collision=false;
		
	}
	public void effect(Entity entity) {
		
		
	}
}
