package item;

import main.Panel;

public class Shield extends Item{
	public int iDEF;
	
	public Shield(Panel gp, int col, int row) {
		super(gp, col, row);
		type =4;
	}

}
