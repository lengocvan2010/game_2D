package graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.*;
import main.Panel;
import projectile.Projectile;

public class DrawProjectile extends DrawEntity{
	public Projectile pro;
	public int screenX ;
	public int screenY ;
	public DrawProjectile(Panel gp,Projectile pro) {
		this.gp=gp;
		this.pro =pro;
		getImage();
	}
	
	public void getImage() {
		switch(pro.name) {
		case"Fire":
			up1 = setupImage("/projectile/fireball_up_1.png", gp.tileSizeX, gp.tileSizeY);
			up2 = setupImage("/projectile/fireball_up_2.png", gp.tileSizeX, gp.tileSizeY);
			down1 = setupImage("/projectile/fireball_down_1.png", gp.tileSizeX, gp.tileSizeY);
			down2 = setupImage("/projectile/fireball_down_2.png", gp.tileSizeX, gp.tileSizeY);
			left1 = setupImage("/projectile/fireball_left_1.png", gp.tileSizeX, gp.tileSizeY);
			left2 = setupImage("/projectile/fireball_left_2.png", gp.tileSizeX, gp.tileSizeY);
			right1 = setupImage("/projectile/fireball_right_1.png", gp.tileSizeX, gp.tileSizeY);
			right2 = setupImage("/projectile/fireball_right_2.png", gp.tileSizeX, gp.tileSizeY);
			break;
		case"MonsterP":
			up1 = setupImage("/projectile/rock_down_1.png", gp.tileSizeX, gp.tileSizeY);
			up2 = setupImage("/projectile/rock_down_1.png", gp.tileSizeX, gp.tileSizeY);
			down1 = setupImage("/projectile/rock_down_1.png", gp.tileSizeX, gp.tileSizeY);
			down2 = setupImage("/projectile/rock_down_1.png", gp.tileSizeX, gp.tileSizeY);
			left1 = setupImage("/projectile/rock_down_1.png", gp.tileSizeX, gp.tileSizeY);
			left2 = setupImage("/projectile/rock_down_1.png", gp.tileSizeX, gp.tileSizeY);
			right1 = setupImage("/projectile/rock_down_1.png", gp.tileSizeX, gp.tileSizeY);
			right2 = setupImage("/projectile/rock_down_1.png", gp.tileSizeX, gp.tileSizeY);
			break;
		case"PPS":
			up1 = setupImage("/projectile/powerPole1.png", gp.tileSizeX, gp.tileSizeY);
			up2 = setupImage("/projectile/powerPole2.png", gp.tileSizeX, gp.tileSizeY);
			down1 = setupImage("/projectile/powerPole1.png", gp.tileSizeX, gp.tileSizeY);
			down2 = setupImage("/projectile/powerPole2.png", gp.tileSizeX, gp.tileSizeY);
			left1 = setupImage("/projectile/powerPole1.png", gp.tileSizeX, gp.tileSizeY);
			left2 = setupImage("/projectile/powerPole2.png", gp.tileSizeX, gp.tileSizeY);
			right1 = setupImage("/projectile/powerPole1.png", gp.tileSizeX, gp.tileSizeY);
			right2 = setupImage("/projectile/powerPole2.png", gp.tileSizeX, gp.tileSizeY);
		break;
		case"Ném dầu ăn":
			up1 = setupImage("/projectile/dauan1.png", gp.tileSizeX, gp.tileSizeY);
			up2 = setupImage("/projectile/dauan2.png", gp.tileSizeX, gp.tileSizeY);
			down1 = setupImage("/projectile/dauan1.png", gp.tileSizeX, gp.tileSizeY);
			down2 = setupImage("/projectile/dauan2.png", gp.tileSizeX, gp.tileSizeY);
			left1 = setupImage("/projectile/dauan1.png", gp.tileSizeX, gp.tileSizeY);
			left2 = setupImage("/projectile/dauan2.png", gp.tileSizeX, gp.tileSizeY);
			right1 = setupImage("/projectile/dauan1.png", gp.tileSizeX, gp.tileSizeY);
			right2 = setupImage("/projectile/dauan2.png", gp.tileSizeX, gp.tileSizeY);
			break;
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		screenX = pro.x - gp.player.x + gp.player.screenX;
		screenY = pro.y - gp.player.y + gp.player.screenY;
		if( pro.x + gp.tileSizeX > gp.player.x - gp.player.screenX &&
				pro.x - gp.tileSizeX < gp.player.x + gp.player.screenX &&
				pro.y + gp.tileSizeY > gp.player.y - gp.player.screenY &&
				pro.y - gp.tileSizeY < gp.player.y + gp.player.screenY) {
		switch(pro.direction){
			case 1:
					if(pro.spriteNum==1) {
						image = up1;}
					if(pro.spriteNum==2) {
						image = up2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY;
				break;
			case 2:
				
					if(pro.spriteNum==1) {
						image = down1;}
					if(pro.spriteNum==2) {
						image = down2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY;
				
				break;
			case 3:
					if(pro.spriteNum==1) {
						image = left1;}
					if(pro.spriteNum==2) {
						image = left2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY;
				break;
			case 4:
				
					if(pro.spriteNum==1) {
						image = right1;}
					if(pro.spriteNum==2) {
						image = right2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY;
				
				break;
		}
		g2.drawImage(image, screenX, screenY,null);}
	}
}
