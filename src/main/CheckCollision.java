package main;


import entity.Entity;

import map.MapObject;


public class CheckCollision{
	Panel gp;
	
	public CheckCollision(Panel gp) {
		this.gp =gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX =  entity.x + entity.solidArea.x;
		int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY =  entity.y + entity.solidArea.y;
		int entityBottomWorldY =  entity.y + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSizeX;
		int entityRightCol = entityRightWorldX/gp.tileSizeX;
		int entityTopRow = entityTopWorldY/gp.tileSizeY;
		int entityBottomRow = entityBottomWorldY/gp.tileSizeY;
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case 1: {
			entityTopRow = (entityTopWorldY - entity.tempSpeed)/gp.tileSizeY;
			tileNum1 = gp.aSetter.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.aSetter.mapTileNum[entityRightCol][entityTopRow];
			if(gp.aSetter.tile[tileNum1].collision == true || gp.aSetter.tile[tileNum2].collision == true)  {
				entity.collisionOn = true;
			}
			break;
		}
		case 2: {
			entityBottomRow = (entityBottomWorldY + entity.tempSpeed)/gp.tileSizeY;
			tileNum1 = gp.aSetter.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.aSetter.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.aSetter.tile[tileNum1].collision == true || gp.aSetter.tile[tileNum2].collision == true)  {
				entity.collisionOn = true;
			}
			break;
		}
		case 3: {
			entityLeftCol = (entityLeftWorldX - entity.tempSpeed)/gp.tileSizeX;
			tileNum1 = gp.aSetter.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.aSetter.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.aSetter.tile[tileNum1].collision == true || gp.aSetter.tile[tileNum2].collision == true)  {
				entity.collisionOn = true;
			}
			break;
		}
		case 4: {
			entityRightCol = (entityRightWorldX + entity.tempSpeed)/gp.tileSizeY;
			tileNum1 = gp.aSetter.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.aSetter.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.aSetter.tile[tileNum1].collision == true || gp.aSetter.tile[tileNum2].collision == true)  {
				entity.collisionOn = true;
			}
			break;
		}
		
		}
	}
	
	
	public int checkObject(Entity entity,MapObject[] target) {
		int index = 999;
		
		for (int i = 0; i < target.length; i++) {
			if(target[i]!=null) {
				//get entity solid
				entity.solidArea.x = entity.x + entity.solidArea.x;
				entity.solidArea.y = entity.y + entity.solidArea.y;
				//get object solid
				target[i].solidArea.x = target[i].x +target[i].solidArea.x;
				target[i].solidArea.y = target[i].y + target[i].solidArea.y;
				
				switch(entity.direction) {
				case 1:
					entity.solidArea.y-=entity.tempSpeed;break;
				case 2:
					entity.solidArea.y+=entity.tempSpeed;break;
				case 3:
					entity.solidArea.x-=entity.tempSpeed;break;
				case 4:
					entity.solidArea.x+=entity.tempSpeed;break;
				}
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if(target[i].collision==true) {
						entity.collisionOn = true;
					}
					index = i;
				}
				//reset solid
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;		
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		return index;
	}
	
	public int checkEntity(Entity entity,Entity[] target) {
		int index = 999;
		
		for (int i = 0; i < target.length; i++) {
			if(target[i]!=null) {
				//get entity solid
				entity.solidArea.x = entity.x + entity.solidArea.x;
				entity.solidArea.y = entity.y + entity.solidArea.y;
				//get object solid
				target[i].solidArea.x = target[i].x +target[i].solidArea.x;
				target[i].solidArea.y = target[i].y + target[i].solidArea.y;
				
				switch(entity.direction) {
				case 1:
					entity.solidArea.y-=entity.tempSpeed;break;
				case 2:
					entity.solidArea.y+=entity.tempSpeed;break;
				case 3:
					entity.solidArea.x-=entity.tempSpeed;break;
				case 4:
					entity.solidArea.x+=entity.tempSpeed;break;
				}
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if(entity!=target[i]) {
					entity.collisionOn = true;
					index = i;}
				}
				//reset solid
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;		
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		return index;
	}
	
	//check player collision (use for npc or monster)
	public boolean checkPlayer(Entity entity) {
		boolean touchPlayer = false;
		
		//get entity solid
		entity.solidArea.x = entity.x + entity.solidArea.x;
		entity.solidArea.y = entity.y + entity.solidArea.y;
		//get object solid
		gp.player.solidArea.x = gp.player.x +gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case 1:
			entity.solidArea.y-=entity.tempSpeed;break;
		case 2:
			entity.solidArea.y+=entity.tempSpeed;break;
		case 3:
			entity.solidArea.x-=entity.tempSpeed;break;
		case 4:
			entity.solidArea.x+=entity.tempSpeed;break;
		}
		
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			touchPlayer = true;
		}
		
		//reset solid
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	
		return touchPlayer;
	}

}
