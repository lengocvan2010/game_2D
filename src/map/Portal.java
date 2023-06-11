package map;

import entity.Entity;
import entity.Player;
import main.Panel;

public class Portal extends MapObject{

	public Portal(Panel gp, int col, int row) {
		super(gp, col, row);
		name = "Portal";
		collision=true;
	}
	public void effect(Player player) {
		gp.Ui.addMessage("TELEPORT!!!");
		gp.playSE(9);
		player.x = gp.tileSizeX*54;
		player.y = gp.tileSizeY*42;
		gp.stopMusic();
		gp.playMusic(1);
		
	}
}
