package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W) {
				gp.playSE(5);
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.playSE(5);
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.playSE(4);
					gp.gameState = gp.playState;
				}
				if(gp.ui.commandNum == 1) {
					gp.playSE(4);
					// add "load map" feature later use RyiSnow ep.17
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		
		//PLAY STATE
		//player control
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		// game control
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.titleState;
		}
		if(code == KeyEvent.VK_SPACE) { //toggle switch for pause state when "SPACEBAR" is pressed
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
			else if(gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}

}
