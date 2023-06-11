package main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.*;
import graphic.DrawItem;
import graphic.DrawMapObject;
import graphic.DrawMonster;
import projectile.SpecialWSkill;
import item.*;
import map.*;

public class AssetSetter{

	Panel gp;
	int mapObjectNumber[][];
	public Tile[] tile;
	public int mapTileNum[][];
	Random ran = new Random();
	public MonsterData monsterdata[] = new MonsterData[30];

	public AssetSetter(Panel gp) {
		this.gp = gp;
		tile = new Tile[5000];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		mapObjectNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
	}
	
	
	public void loadTile(String mapPath) {
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col<gp.maxWorldCol&& row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col< gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num	= Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row]=num;
					col++;
					}
				if(col==gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			
		}
	}
	
	public void getTileImage() {
		// FOREST MAP
					setup(100, "rockOnWater", false);
					setup(110, "grass1", false);
					setup(111, "grass2", false);
					setup(112, "water", true);
					setup(113, "seaside11", true);
					setup(114, "seaside12", true);
					setup(115, "seaside13", true);
					setup(116, "seaside21", true);
					setup(117, "seaside23", true);
					setup(118, "seaside31", true);
					setup(119, "seaside32", true);
					setup(120, "seaside33", true);
					setup(121, "wooden", false);
					setup(123, "seaside'_11", true);
					setup(124, "seaside'_12", true);
					setup(125, "seaside'_13", true);
					setup(126, "seaside'_21", true);
					setup(127, "seaside'_23", true);
					setup(128, "seaside'_31", true);
					setup(129, "seaside'_32", true);
					setup(130, "seaside'_33", true);
					setup(131, "road11", false);
					setup(132, "road12", false);
					setup(133, "road13", false);
					setup(134, "road21", false);
					setup(135, "road22", false);
					setup(136, "road23", false);
					setup(137, "road31", false);
					setup(138, "road32", false);
					setup(139, "road33", false);
					setup(140, "bridge", false);
					setup(141, "wood11", true);
					setup(142, "wood12", true);
					setup(143, "wood13", true);
					setup(144, "wood21", true);
					setup(145, "wood22", true);
					setup(146, "wood23", true);
					setup(147, "wood31", true);
					setup(148, "wood32", true);
					setup(149, "wood33", true);
					setup(150, "wooden1", false);
					
				// ICE MAP
					
					setup(200, "ice", false);
					setup(201, "ice1", false);
					setup(202, "icerock", false);
					setup(273, "icewall11", true);
					setup(274, "icewall12", true);
					setup(275, "icewall13", true);
					setup(276, "icewall21", true);
					setup(277, "icewall23", true);
					setup(278, "icewall31", true);
					setup(279, "icewall32", true);
					setup(280, "icewall33", true);
					setup(283, "icewall'_11", true);
					setup(284, "icewall'_12", true);
					setup(285, "icewall'_13", true);
					setup(286, "icewall'_21", true);
					setup(287, "icewall'_23", true);
					setup(288, "icewall'_31", true);
					setup(289, "icewall'_32", true);
					setup(290, "icewall'_33", true);	
					
				// POISON MAP
					
					// FULL WALL
					setup(300, "wall21up", true);
					setup(301, "wall11up", true);
					setup(302, "wall12up", true);
					setup(303, "wall13up", true);
					setup(304, "wall14up", true);
					setup(305, "wall15up", true);
					setup(306, "wall16up", true);
					setup(307, "wall11down", true);
					setup(308, "wall12down", true);
					setup(309, "wall14down", true);
					setup(310, "grid", true);
					
					setup(391, "wall17up", true);
					setup(392, "wall17down", true);
					setup(393, "wall26", true);
					setup(394, "wall27", true);
					setup(395, "wall27down", true);
					setup(396, "wall27up", true);
					setup(397, "wall31", true);
					setup(398, "wall32", true);
					
					
					// HAFT WALL
					setup(311, "leftPoisonBank", true);
					setup(312, "leftPoison", true);
					
					setup(313, "leftWall11", true);
					setup(314, "leftWall21", true);
					setup(315, "leftWall31", true);
					
					setup(316, "leftWall_11", true);
					setup(317, "leftWall_21", true);
					setup(318, "leftWall_31", true);
					
					setup(320, "rightWall_11", true);
					setup(319, "rightWall_21", true);
					
					setup(371, "rightWall11", true);
					setup(372, "rightWall21", true);
					setup(373, "rightWall31", true);
					setup(374, "rightWall41", true);
					setup(375, "rightWall51", true);
					setup(376, "rightWall61", true);
					setup(377, "rightBank", true);
					setup(378, "rightPoison", true);
					
					// GATE
					setup(321, "gate11", true);
					setup(322, "gate12", false);
					setup(323, "gate13", true);
					setup(324, "gate21", true);
					setup(325, "gate22", true);
					setup(326, "gate23", true);
					setup(327, "RockOnPoison", false);
					setup(328, "fireGate", true);
					
					setup(351, "Dgate11", true);
					setup(352, "Dgate12", true);
					setup(353, "Dgate13", true);
					setup(354, "Dgate21", true);
					setup(355, "Dgate22", true);
					setup(356, "Dgate23", true);
					
					// POISON AND BANK
					setup(330, "poison0", true);
					setup(331, "poison1", true);
					setup(332, "poison2", true);
					setup(333, "poison3", true);
					setup(334, "poison4", true);
					setup(335, "poison5", true);
					setup(336, "bank1", true);
					setup(337, "bank2", true);
					setup(338, "bank3", true);
					setup(339, "woodBridge1", false);
					setup(340, "woodBridge2", false);
					
					// FLOOR
					setup(341, "floorPoisonLand", false);
					setup(342, "floor", false);
					setup(343, "floor0", false);
					setup(344, "floor01", false);
					
					setup(361, "carpet11", false);
					setup(362, "carpet12", false);
					setup(363, "carpet13", false);
					setup(364, "carpet21", false);
					setup(365, "carpet22", false);
					setup(366, "carpet23", false);
					setup(367, "carpet31", false);
					setup(368, "carpet32", false);
					setup(369, "carpet33", false);
					
					// BOOKCASE
					setup(345, "bookCase11", true);
					setup(346, "bookCase13", true);
					setup(347, "bookCase21", true);
					
				// FIRE MAP
					setup(400, "fire", false);
					setup(401, "fire1", false);
					setup(402, "fire4", false);
					setup(403, "firewall11", true);
					setup(404, "firewall12", true);
					setup(405, "firewall13", true);
					setup(406, "firewall21", true);
					setup(407, "firewall23", true);
					setup(408, "firewall31", true);
					setup(409, "firewall32", true);
					setup(410, "firewall33", true);
					setup(413, "firewall'_11", true);
					setup(414, "firewall'_12", true);
					setup(415, "firewall'_13", true);
					setup(416, "firewall'_21", true);
					setup(417, "firewall'_23", true);
					setup(418, "firewall'_31", true);
					setup(419, "firewall'_32", true);
					setup(420, "firewall'_33", true);
	}

	public void setup(int index, String imageName, boolean collision) {
	
	UtilityTool uTool = new UtilityTool();
	
	try {
		
		tile[index] = new Tile();
		tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
		tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSizeX, gp.tileSizeY);
		tile[index].collision = collision;
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public void drawTile(Graphics2D g2) {
		int col=0,row=0;
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			int tileNum = mapTileNum[col][row];
			int x = col*gp.tileSizeX;
			int y = row*gp.tileSizeY;
			

			double screenX = x - gp.player.x + gp.player.screenX;
			double screenY = y - gp.player.y + gp.player.screenY;
			
			// DRAW TILE IN SCREEN
			if( x + gp.tileSizeX > gp.player.x - gp.player.screenX &&
				x - gp.tileSizeX < gp.player.x + gp.player.screenX &&
				y + gp.tileSizeY > gp.player.y - gp.player.screenY &&
				y - gp.tileSizeY < gp.player.y + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, null);
			}
			col++;
		
			if(col==gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}	
	}
	
	public void setupGame() {
		loadMap();
		setItem();
		setMonster();
	}
	
	public void spawnMonster(int i) {
		
			switch(monsterdata[i].name) {
			case"Slime":
				gp.monsters[i] = new Monster_A(gp);
				break;
			case"SpiderMonster":
				gp.monsters[i] = new Monster_Shot(gp);
				break;
			case"Boss":
				gp.monsters[i] = new Boss(gp);
				break;
			}
			gp.monsters[i].x = monsterdata[i].x;
			gp.monsters[i].y = monsterdata[i].y;
			monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
			gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		
	}
	
	public void setMonster() {
		int i=0;
		gp.monsters[i] = new Monster_A(gp);
		gp.monsters[i].x = 24*gp.tileSizeX;
		gp.monsters[i].y = 8*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
		
		gp.monsters[i] = new Monster_A(gp);
		gp.monsters[i].x = 43*gp.tileSizeX;
		gp.monsters[i].y = 8*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
		
		gp.monsters[i] = new Monster_A(gp);
		gp.monsters[i].x = 52*gp.tileSizeX;
		gp.monsters[i].y = 11*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
		
		gp.monsters[i] = new Monster_A(gp);
		gp.monsters[i].x = 54*gp.tileSizeX;
		gp.monsters[i].y = 19*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
		
		gp.monsters[i] = new Monster_Shot(gp);
		gp.monsters[i].x = 44*gp.tileSizeX;
		gp.monsters[i].y = 25*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
		
		gp.monsters[i] = new Monster_Shot(gp);
		gp.monsters[i].x = 44*gp.tileSizeX;
		gp.monsters[i].y = 48*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
		
		gp.monsters[i] = new Monster_Shot(gp);
		gp.monsters[i].x = 33*gp.tileSizeX;
		gp.monsters[i].y = 48*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
		
		gp.monsters[i] = new Monster_Shot(gp);
		gp.monsters[i].x = 45*gp.tileSizeX;
		gp.monsters[i].y = 26*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
		
		gp.monsters[i] = new Boss(gp);
		gp.monsters[i].x = 56*gp.tileSizeX;
		gp.monsters[i].y = 40*gp.tileSizeY;
		monsterdata[i] = new MonsterData(gp.monsters[i].name, gp.monsters[i].x, gp.monsters[i].y, gp.monsters[i].timeSpawn);
		gp.drawM[i] = new DrawMonster(gp.monsters[i], gp);
		gp.countM++;
		i++;
			
	}
	public void setItem() {
		int i = 0;
		
		gp.item[i] = new OBJ_PowerPole(gp,21,16);
		gp.drawI[i] = new DrawItem(gp, gp.item[i]); 
		i++;
		
		gp.item[i] = new OBJ_Shield_Blue(gp,17,20);
		gp.drawI[i] = new DrawItem(gp, gp.item[i]); 
		i++;

		gp.item[i] = new OBJ_Sword_Magic(gp,14,10);
		gp.drawI[i] = new DrawItem(gp, gp.item[i]); 
		i++;
		
		gp.item[i] = new OBJ_SpecicalW(gp, 14, 9);
		gp.drawI[i] = new DrawItem(gp, gp.item[i]); 
		i++;
		
		gp.item[i] = new OBJ_KeyOpenRockDoor(gp, 37, 43);
		gp.drawI[i] = new DrawItem(gp, gp.item[i]);
		i++;
		

		
	}
	
	public void loadMap() {
		loadMapObject("/maps/map01_object.txt");
		loadTile("/maps/map01_tile.txt");
		
	}
	
	public void loadMapObject(String mapName) {
		
		try {
			
			InputStream iStream = getClass().getResourceAsStream(mapName);
			BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
			
			int i = 0;
			int col = 0;	int row = 0;
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					if(num == 111) {
						gp.mapobj[i] = new RockOnSea1(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					if(num == 112) {
						gp.mapobj[i] = new RockOnSea2(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 113) {
						gp.mapobj[i] = new Rock(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 114) {
						gp.mapobj[i] = new BigRock(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					// OBJECTS ON SEA
					
					if(num == 121) {
						gp.mapobj[i] = new Boat(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 122) {
						gp.mapobj[i] = new Mess(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 123) {
						gp.mapobj[i] = new HouseSeaSide(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 124) {
						gp.mapobj[i] = new Vortex(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					// OBJECTS ON LAND
					
					if(num == 131) {
						gp.mapobj[i] = new Trees(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 132) {
						gp.mapobj[i] = new Forest(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 133) {
						gp.mapobj[i] = new SignPost(gp,col,row); 
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					// STREAM
					if(num == 141) {
						gp.mapobj[i] = new Stream11(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 142) {
						gp.mapobj[i] = new Stream12(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 143) {
						gp.mapobj[i] = new Stream13(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 144) {
						gp.mapobj[i] = new Stream21(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 145) {
						gp.mapobj[i] = new Stream22(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 146) {
						gp.mapobj[i] = new Stream23(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 147) {
						gp.mapobj[i] = new Stream31(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 148) {
						gp.mapobj[i] = new Stream32(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 149) {
						gp.mapobj[i] = new Stream33(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					// OBJECT 
					
					if(num == 200) {
						gp.mapobj[i] = new BigBox(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 201) {
						gp.mapobj[i] = new WoodBox(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 202) {
						gp.mapobj[i] = new PineTree1(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 203) {
						gp.mapobj[i] = new PineTree2(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 204) {
						gp.mapobj[i] = new Treasure_lock(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num == 300) {
						gp.mapobj[i] = new RockDoor_lock(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					
					if(num==999) {
						gp.mapobj[i] = new TrapSummon(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					if(num==992) {
						gp.mapobj[i] = new Portal(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
					if(num==987) {
						gp.mapobj[i] = new Quit(gp,col,row);
						gp.drawMO[i] = new DrawMapObject(gp, gp.mapobj[i]);
						i++;
					}
				
					
					
					col++;
					
				}
				if(col == gp.maxWorldCol) {
					
					col = 0;	row++;
					
				}
				
			}
			br.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
