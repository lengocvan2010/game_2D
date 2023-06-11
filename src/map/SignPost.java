package map;

import entity.Entity;
import main.Panel;

public class SignPost extends MapObject {

	public SignPost(Panel gp,int col,int row) {
		super(gp,col,row);
		name = "SignPost";
		collision = false;
	}
}
