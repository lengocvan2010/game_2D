package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Object;
import entity.Player;
import main.Panel;
import main.UtilityTool;

public class MapObject extends Object{
	
	public String name;
	public boolean collision;
	
	int screenX,screenY;
	
	public MapObject(Panel gp,int col,int row) {
		
		this.gp=gp;
		solidArea = new Rectangle(0,0,48,48);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	
		x=col*gp.tileSizeX;
		y=row*gp.tileSizeY;
	}
	
	public void effect(Entity entity) {}
	
	public void effect(Player player) {}
	
	
}
