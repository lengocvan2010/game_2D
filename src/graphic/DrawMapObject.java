package graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Panel;
import main.UtilityTool;
import map.MapObject;

public class DrawMapObject {
	Panel gp;
	BufferedImage image;
	MapObject mo;
	double screenX,screenY;
	
	public DrawMapObject(Panel gp,MapObject mo) {
		this.gp = gp;
		this.mo =mo;
		getItemImage();
	}
	
	public void getItemImage() {
				switch(mo.name) {
				case"Forest":
					image = setupImage("forest", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Quit":
					image = setupImage("quit", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Rock":
					image = setupImage("rock", gp.tileSizeX, gp.tileSizeY);
					break;
				case"BigRock":
					image=setupImage("bigRock", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Boat":
					image = setupImage("boat", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Trees":
					image = setupImage("trees", gp.tileSizeX, gp.tileSizeY);
					break;
				case"HouseSeaSide":
					image = setupImage("houseSeaSide", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Mess":
					image = setupImage("mess", gp.tileSizeX, gp.tileSizeY);
					break;
				case"RockOnSea1":
					image = setupImage("rockOnSea1", gp.tileSizeX, gp.tileSizeY);
					break;
				case"RockOnSea2":
					image = setupImage("rockOnSea2", gp.tileSizeX, gp.tileSizeY);
					break;
				case"SignPost":
					image = setupImage("signPost", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream11":
					image = setupImage("stream11", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream12":
					image = setupImage("stream12", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream13":
					image = setupImage("stream13", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream21":
					image = setupImage("stream21", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream22":
					image = setupImage("stream22", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream23":
					image = setupImage("stream23", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream31":
					image = setupImage("stream31", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream32":
					image = setupImage("stream32", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Stream33":
					image = setupImage("stream33", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Vortex":
					image = setupImage("vortex", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Wood Box":
					image = setupImage("woodBox", gp.tileSizeX, gp.tileSizeY);
					break;
				case "Big Box":
					image = setupImage("bigBox", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Door_lock":
					image = setupImage("Door_lock", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Door_unlock":
					image = setupImage("Door_unlock", gp.tileSizeX, gp.tileSizeY);
					break;
				case "Treasure_lock":
					image = setupImage("Treasure_lock", gp.tileSizeX, gp.tileSizeY);
					break;
				case "Treasure_unlock":
					image = setupImage("Treasure_unlock", gp.tileSizeX, gp.tileSizeY);
					break;
				case"RockDoor_lock":
					image = setupImage("Dgate22", gp.tileSizeX, gp.tileSizeY);
					break;
				case"Portal":
					image = setupImage("door", gp.tileSizeX, gp.tileSizeY);
					break;
				case"PineTree1":
					image = setupImage("PineTree1", gp.tileSizeX, gp.tileSizeY);
					break;
				case"PineTree2":
					image = setupImage("PineTree2", gp.tileSizeX, gp.tileSizeY);
					break;
				case"SummonTrap":
					image = setupImage("PoisonSummonMonster", gp.tileSizeX, gp.tileSizeY);
					break;
				}
	}
	
	public void draw(Graphics2D g2) {
		
			
				screenX = mo.x - gp.player.x + gp.player.screenX;
				screenY = mo.y - gp.player.y + gp.player.screenY;
				
				// DRAW TILE IN SCREEN
				if( mo.x + gp.tileSizeX > gp.player.x - gp.player.screenX &&
						mo.x - gp.tileSizeX < gp.player.x + gp.player.screenX &&
						mo.y + gp.tileSizeY > gp.player.y - gp.player.screenY &&
						mo.y - gp.tileSizeY < gp.player.y + gp.player.screenY) {
					
					g2.drawImage(image, (int) screenX, (int) screenY, null);
				}
			
		
	}
	
	public BufferedImage setupImage(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/mapObject/"+imagePath+".png"));
			image = uTool.scaleImage(image,width,height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
