package graphic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import entity.Entity;
import entity.Player;
import main.Panel;


public class DrawPlayer extends DrawEntity{
	
	BufferedImage au1,au2,ad1,ad2,al1,al2,ar1,ar2;
	public Player player;
	public DrawPlayer(Player entity,Panel gp) {
		this.player = entity;
		this.gp=gp;
		getImage();
	}
	
	public void getImage() {
		switch(player.currentWeapon.name) {
		case"Normal Sword":
		case"Magic Sword":
			up1 = setupImage("/player/player_up1.png",gp.tileSizeX,gp.tileSizeY);
			up2 = setupImage("/player/player_up2.png",gp.tileSizeX,gp.tileSizeY);
			down1 = setupImage("/player/player_down1.png",gp.tileSizeX,gp.tileSizeY);
			down2 = setupImage("/player/player_down2.png",gp.tileSizeX,gp.tileSizeY);
			left1 = setupImage("/player/player_left1.png",gp.tileSizeX,gp.tileSizeY);
			left2 = setupImage("/player/player_left2.png",gp.tileSizeX,gp.tileSizeY);
			right1 = setupImage("/player/player_right1.png",gp.tileSizeX,gp.tileSizeY);
			right2 = setupImage("/player/player_right2.png",gp.tileSizeX,gp.tileSizeY);
			
			au1 = setupImage("/player/player_attack_up1.png", gp.tileSizeX, gp.tileSizeY*2);
			au2 = setupImage("/player/player_attack_up2.png", gp.tileSizeX, gp.tileSizeY*2);
			ad1 = setupImage("/player/player_attack_down1.png", gp.tileSizeX, gp.tileSizeY*2);
			ad2 = setupImage("/player/player_attack_down2.png", gp.tileSizeX, gp.tileSizeY*2);
			al1 = setupImage("/player/player_attack_left1.png", gp.tileSizeX*2, gp.tileSizeY);
			al2 = setupImage("/player/player_attack_left2.png", gp.tileSizeX*2, gp.tileSizeY);
			ar1 = setupImage("/player/player_attack_right1.png", gp.tileSizeX*2, gp.tileSizeY);
			ar2 = setupImage("/player/player_attack_right2.png", gp.tileSizeX*2, gp.tileSizeY);
			break;
		case"PowerPole":
			up1 = setupImage("/player/playerP_up1.png",gp.tileSizeX,gp.tileSizeY);
			up2 = setupImage("/player/playerP_up2.png",gp.tileSizeX,gp.tileSizeY);
			down1 = setupImage("/player/playerP_down1.png",gp.tileSizeX,gp.tileSizeY);
			down2 = setupImage("/player/playerP_down2.png",gp.tileSizeX,gp.tileSizeY);
			left1 = setupImage("/player/playerP_left1.png",gp.tileSizeX,gp.tileSizeY);
			left2 = setupImage("/player/playerP_left2.png",gp.tileSizeX,gp.tileSizeY);
			right1 = setupImage("/player/playerP_right1.png",gp.tileSizeX,gp.tileSizeY);
			right2 = setupImage("/player/playerP_right2.png",gp.tileSizeX,gp.tileSizeY);
			
			au1 = setupImage("/player/player_attackP_up1.png", gp.tileSizeX, gp.tileSizeY*2);
			au2 = setupImage("/player/player_attackP_up2.png", gp.tileSizeX, gp.tileSizeY*2);
			ad1 = setupImage("/player/player_attackP_down1.png", gp.tileSizeX, gp.tileSizeY*2);
			ad2 = setupImage("/player/player_attackP_down2.png", gp.tileSizeX, gp.tileSizeY*2);
			al1 = setupImage("/player/player_attackP_left1.png", gp.tileSizeX*2, gp.tileSizeY);
			al2 = setupImage("/player/player_attackP_left2.png", gp.tileSizeX*2, gp.tileSizeY);
			ar1 = setupImage("/player/player_attackP_right1.png", gp.tileSizeX*2, gp.tileSizeY);
			ar2 = setupImage("/player/player_attackP_right2.png", gp.tileSizeX*2, gp.tileSizeY);
			break;
		case"Dầu ăn":
			up1 = setupImage("/player/playerD_up1.png",gp.tileSizeX,gp.tileSizeY);
			up2 = setupImage("/player/playerD_up2.png",gp.tileSizeX,gp.tileSizeY);
			down1 = setupImage("/player/playerD_down1.png",gp.tileSizeX,gp.tileSizeY);
			down2 = setupImage("/player/playerD_down2.png",gp.tileSizeX,gp.tileSizeY);
			left1 = setupImage("/player/playerD_left1.png",gp.tileSizeX,gp.tileSizeY);
			left2 = setupImage("/player/playerD_left2.png",gp.tileSizeX,gp.tileSizeY);
			right1 = setupImage("/player/playerD_right1.png",gp.tileSizeX,gp.tileSizeY);
			right2 = setupImage("/player/playerD_right2.png",gp.tileSizeX,gp.tileSizeY);
			
			au1 = setupImage("/player/playerD_attack_up1.png", gp.tileSizeX, gp.tileSizeY*2);
			au2 = setupImage("/player/playerD_attack_up2.png", gp.tileSizeX, gp.tileSizeY*2);
			ad1 = setupImage("/player/playerD_attack_down1.png", gp.tileSizeX, gp.tileSizeY*2);
			ad2 = setupImage("/player/playerD_attack_down2.png", gp.tileSizeX, gp.tileSizeY*2);
			al1 = setupImage("/player/playerD_attack_left1.png", gp.tileSizeX*2, gp.tileSizeY);
			al2 = setupImage("/player/playerD_attack_left2.png", gp.tileSizeX*2, gp.tileSizeY);
			ar1 = setupImage("/player/playerD_attack_right1.png", gp.tileSizeX*2, gp.tileSizeY);
			ar2 = setupImage("/player/playerD_attack_right2.png", gp.tileSizeX*2, gp.tileSizeY);
			break;
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		player.tempX=player.screenX;
		player.tempY=player.screenY;
		
		switch(player.direction){
			case 1:
				if(player.attacking==true) {
					if(player.spriteNum==1) {
						image = au1;}
					if(player.spriteNum==2) {
						image = au2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY*2;
					player.tempY-=gp.tileSizeY;
				}
				if(player.attacking==false) {
					if(player.spriteNum==1) {
						image = up1;}
					if(player.spriteNum==2) {
						image = up2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY;
					
				}
				break;
			case 2:
				if(player.attacking==true) {
					if(player.spriteNum==1) {
						image = ad1;}
					if(player.spriteNum==2) {
						image = ad2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY*2;
				}if(player.attacking==false) {
					if(player.spriteNum==1) {
						image = down1;}
					if(player.spriteNum==2) {
						image = down2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY;
				}
				break;
			case 3:
				if(player.attacking==true) {
					if(player.spriteNum==1) {
						image = al1;}
					if(player.spriteNum==2) {
						image = al2;}
					xS = gp.tileSizeX*2;
					yS= gp.tileSizeY;
					player.tempX-=gp.tileSizeX;
				}if(player.attacking==false) {
					if(player.spriteNum==1) {
						image = left1;}
					if(player.spriteNum==2) {
						image = left2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY;
					
				}
				break;
			case 4:
				if(player.attacking==true) {
					if(player.spriteNum==1) {
						image = ar1;}
					if(player.spriteNum==2) {
						image = ar2;}
					xS = gp.tileSizeX*2;
					yS= gp.tileSizeY;
				}
				if(player.attacking==false) {
					if(player.spriteNum==1) {
						image = right1;}
					if(player.spriteNum==2) {
						image = right2;}
					xS = gp.tileSizeX;
					yS= gp.tileSizeY;
				}
				break;
		}
		if(player.invincible==true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g2.drawImage(image,player.tempX,player.tempY,xS,yS,null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));				
		}
		else {	g2.drawImage(image,player.tempX,player.tempY,xS,yS,null);}
	}
}
	
	

