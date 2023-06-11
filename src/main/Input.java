package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener{
	Panel gp;
	public boolean upPressed,downPressed,leftPressed,rightPressed,interact,attack,specialA,tab,enterPressed,dash;
	
	public Input(Panel gp) {
		this.gp=gp;
	}
	
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
			
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			if(code==KeyEvent.VK_W) {
				gp.Ui.commandNum--;
				if(gp.Ui.commandNum<0)gp.Ui.commandNum=1;
			}
			if(code==KeyEvent.VK_S) {
				gp.Ui.commandNum++;
				if(gp.Ui.commandNum >1)gp.Ui.commandNum=0;
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.Ui.commandNum==1) {
					System.exit(0);
				}else
				if(gp.Ui.commandNum==0) {
					gp.newGame();
				}	
			}
				
		}
		else if(gp.gameState == gp.playState) {
			if(code==KeyEvent.VK_SHIFT) {
				dash=true;
			}
			if(code==KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code==KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code==KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code==KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code==KeyEvent.VK_L) {
				interact = true;
			}
			if(code==KeyEvent.VK_J) {
				attack=true;
			}
			if(code==KeyEvent.VK_K) {
				specialA=true;
			}
		
			if(code==KeyEvent.VK_P) {	
				gp.gameState = gp.pauseState;
				System.out.println("a");
			}
			if(code==KeyEvent.VK_ESCAPE) {
				gp.gameState=gp.optionsState;
			}
			if(code==KeyEvent.VK_E) {
				gp.gameState = gp.statusState;
			}
			if(tab==true) {characterState(code);}
		}	
		else if(gp.gameState==gp.pauseState) {
			if(code==KeyEvent.VK_P) {
				gp.gameState=gp.playState;
			}
		}
		
		else if(gp.gameState == gp.optionsState) {	
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			int maxCommandNum = 0;
			switch(gp.Ui.subState) {
			case 0:maxCommandNum = 4;break;
			case 4:maxCommandNum = 1; break;
			}
			if(code==KeyEvent.VK_W) {
				gp.Ui.commandNum--;
				if(gp.Ui.commandNum<0)gp.Ui.commandNum=maxCommandNum;
			}
			if(code==KeyEvent.VK_S) {
				gp.Ui.commandNum++;
				if(gp.Ui.commandNum >maxCommandNum)gp.Ui.commandNum=0;
			}
			
			if(code == KeyEvent.VK_A) {
				if(gp.Ui.subState == 0) {
					if(gp.Ui.commandNum == 0 && gp.sound.volumeScale > 0) {
						gp.sound.volumeScale--;
						gp.sound.checkVolume();
					}
					if(gp.Ui.commandNum == 1 && gp.se.volumeScale > 0) {
						gp.se.volumeScale--;
					}
				}
			}
			if(code == KeyEvent.VK_D) {
				if(gp.Ui.subState == 0) {
					if(gp.Ui.commandNum == 0 && gp.sound.volumeScale < 5) {
						gp.sound.volumeScale++;
						gp.sound.checkVolume();

					}
					if(gp.Ui.commandNum == 1 && gp.se.volumeScale < 5) {
						gp.se.volumeScale++;
					}
				}
			}
			
		}else if(gp.gameState==gp.statusState) {
				characterState(code);
		}else if(gp.gameState==gp.overState) {
				gameOverState(code);
		}else if(gp.gameState==gp.victoryState) {
			if(code==KeyEvent.VK_ESCAPE) {
				gp.gameState=gp.titleState;
			}
		}
	}
	
	
	public void characterState(int code) {
		if(code==KeyEvent.VK_E) {
			gp.gameState=gp.playState;
		}
		if(code == KeyEvent.VK_W) {
			if(gp.Ui.slotRow != 0) {
				gp.Ui.slotRow--;
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.Ui.slotRow != 3) {
				gp.Ui.slotRow++;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.Ui.slotCol != 0) {
				gp.Ui.slotCol--;
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.Ui.slotCol != 4) {
				gp.Ui.slotCol++;
			}
		}
		if(code==KeyEvent.VK_Q) {
			gp.player.selectItem();
		}
		if(code==KeyEvent.VK_R) {
			gp.player.removeItem();
		}
	}
	
public void gameOverState(int code) {
		
		if(code == KeyEvent.VK_W) {
			gp.Ui.commandNum--;
			if(gp.Ui.commandNum < 0) {
				gp.Ui.commandNum = 0;
			}
		}
		
		if(code == KeyEvent.VK_S) {
			gp.Ui.commandNum++;
			if(gp.Ui.commandNum > 1) {
				gp.Ui.commandNum = 1;
			}
		}
		
		if(code == KeyEvent.VK_ENTER) {
			if(gp.Ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.newGame();;
			}
			else if(gp.Ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.Ui.commandNum=0;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_SHIFT) {
			dash=false;
		}
		if(code==KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code==KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code==KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code==KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code==KeyEvent.VK_L) {
			interact = false;
		}
	}
}
