package graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import item.Item;
import main.Panel;
import main.UtilityTool;

public class DrawItem {
	Panel gp;
	public BufferedImage image;
	Item item;
	double screenX,screenY;
	
	public DrawItem(Panel gp,Item item) {
		this.gp = gp;
		this.item =item;
		getItemImage();
	}
	
	public void getItemImage() {
				switch(item.name) {
				case"Key":
					image=setupImage("key", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Heart":
					image = setupImage("heart_full", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Half_Heart":
					image = setupImage("heart_half", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Red Potion":
					image = setupImage("potion_red", gp.tileSizeX, gp.tileSizeY);
					break;
				case"PowerPole":
					image = setupImage("powerPole", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Blue Potion":
					image = setupImage("potion_blue", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Blue Shield":
					image = setupImage("shield_blue", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Wood Shield":
					image = setupImage("shield_wood", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Normal Sword":
					image = setupImage("sword_normal", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Magic Sword":
					image = setupImage("sword_magic", gp.tileSizeX, gp.tileSizeY);
					break;
				case "KeyOpenRockDoor":
					image = setupImage("KeyOpenRockDoor", gp.tileSizeX, gp.tileSizeY);
					break;
				case "Dầu ăn":
					image = setupImage("dauan", gp.tileSizeX, gp.tileSizeY);
					break;
				}
	}
	
	public void draw(Graphics2D g2) {
		
			
				screenX = item.x - gp.player.x + gp.player.screenX;
				screenY = item.y - gp.player.y + gp.player.screenY;
				
				// DRAW TILE IN SCREEN
				if( item.x + gp.tileSizeX > gp.player.x - gp.player.screenX &&
						item.x - gp.tileSizeX < gp.player.x + gp.player.screenX &&
						item.y + gp.tileSizeY > gp.player.y - gp.player.screenY &&
						item.y - gp.tileSizeY < gp.player.y + gp.player.screenY) {
					
					g2.drawImage(image, (int) screenX, (int) screenY, null);
				}
			
		
	}
	
	public BufferedImage setupImage(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/"+imagePath+".png"));
			image = uTool.scaleImage(image,width,height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
}
