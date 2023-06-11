package graphic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.*;
import main.Panel;


public class DrawMonster extends DrawEntity{
	
	public Monster entity;
	
	public int screenX ;
	public int screenY ;
	
	public DrawMonster(Monster entity,Panel gp) {
		this.entity = entity;
		this.gp = gp;
		xS=gp.tileSizeX;
		yS=gp.tileSizeY;
		getImage();
	}
	
	public void getImage() {
		switch(entity.name) {
		case"SpiderMonster":
			up1 = setupImage("/monster/spider_up1.png",gp.tileSizeX,gp.tileSizeY);
			up2 = setupImage("/monster/spider_up2.png",gp.tileSizeX,gp.tileSizeY);
			down1 = setupImage("/monster/spider_down1.png",gp.tileSizeX,gp.tileSizeY);
			down2 = setupImage("/monster/spider_down2.png",gp.tileSizeX,gp.tileSizeY);
			left1 = setupImage("/monster/spider_left1.png",gp.tileSizeX,gp.tileSizeY);
			left2 = setupImage("/monster/spider_left2.png",gp.tileSizeX,gp.tileSizeY);
			right1 = setupImage("/monster/spider_right1.png",gp.tileSizeX,gp.tileSizeY);
			right2 = setupImage("/monster/spider_right2.png",gp.tileSizeX,gp.tileSizeY);
		
			break;
		case"Slime":
			up1 = setupImage("/monster/Slimeblue1.png",gp.tileSizeX,gp.tileSizeY);
			up2 = setupImage("/monster/Slimeblue2.png",gp.tileSizeX,gp.tileSizeY);
			down1 = setupImage("/monster/Slimeblue1.png",gp.tileSizeX,gp.tileSizeY);
			down2 = setupImage("/monster/Slimeblue2.png",gp.tileSizeX,gp.tileSizeY);
			left1 = setupImage("/monster/Slimeblue1.png",gp.tileSizeX,gp.tileSizeY);
			left2 = setupImage("/monster/Slimeblue2.png",gp.tileSizeX,gp.tileSizeY);
			right1 = setupImage("/monster/Slimeblue1.png",gp.tileSizeX,gp.tileSizeY);
			right2 = setupImage("/monster/Slimeblue2.png",gp.tileSizeX,gp.tileSizeY);
	
			break;
		case"Boss":
			up1 = setupImage("/monster/boss_up1.png",gp.tileSizeX,gp.tileSizeY);
			up2 = setupImage("/monster/boss_up2.png",gp.tileSizeX,gp.tileSizeY);
			down1 = setupImage("/monster/boss_down1.png",gp.tileSizeX,gp.tileSizeY);
			down2 = setupImage("/monster/boss_down2.png",gp.tileSizeX,gp.tileSizeY);
			left1 = setupImage("/monster/boss_left1.png",gp.tileSizeX,gp.tileSizeY);
			left2 = setupImage("/monster/boss_left2.png",gp.tileSizeX,gp.tileSizeY);
			right1 = setupImage("/monster/boss_right1.png",gp.tileSizeX,gp.tileSizeY);
			right2 = setupImage("/monster/boss_right2.png",gp.tileSizeX,gp.tileSizeY);
			
			break;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		screenX = entity.x - gp.player.x + gp.player.screenX;
		screenY = entity.y- gp.player.y + gp.player.screenY;
		
		if( entity.x + gp.tileSizeX > gp.player.x - gp.player.screenX &&
			entity.x - gp.tileSizeX < gp.player.x + gp.player.screenX &&
			entity.y + gp.tileSizeY > gp.player.y - gp.player.screenY &&
			entity.y - gp.tileSizeY < gp.player.y + gp.player.screenY) {
			
		switch(entity.direction){
			case 1:
				if(entity.spriteNum==1) {
					image = up1;}
				if(entity.spriteNum==2) {
					image = up2;}
				break;
			case 2:
				if(entity.spriteNum==1) {
					image = down1;}
				if(entity.spriteNum==2) {
					image = down2;}
				break;
			case 3:
				if(entity.spriteNum==1) {
					image = left1;}
				if(entity.spriteNum==2) {
					image = left2;}
				break;
			case 4:
				if(entity.spriteNum==1) {
					image = right1;}
				if(entity.spriteNum==2) {
					image = right2;}
				break;
			}
		
		if(entity.invincible==true) {	
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
			
			g2.drawImage(image, screenX, screenY, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));			
		}else {
			g2.drawImage(image, screenX, screenY, null);}
		
		}
	}
		
}
	

