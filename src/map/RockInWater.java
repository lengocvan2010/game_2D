package map;

import entity.*;
import main.Panel;

public class RockInWater extends MapObject{

	public RockInWater(Panel gp,int col,int row) {
		super(gp,col,row);
		name="RockInWater";
		collision=true;
		
	}
	public void effect(Entity entity) {
		
	}

}
