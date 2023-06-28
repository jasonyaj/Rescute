package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import object.OBJ_Bunny;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font bauhaus93, purisa;
	BufferedImage bunnyCountImage;
	public boolean messageOn = false;
	public String message = "";
	int messageTime = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		// Instantiate Font and object here instead of in draw method so as to not instantiate it 60 times per second
		//import in fonts
		try {
			InputStream inputStream = getClass().getResourceAsStream("/fonts/BAUHS93.TTF");
			bauhaus93 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			inputStream = getClass().getResourceAsStream("/fonts/PURISA.TTF");
			purisa = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		OBJ_Bunny bunny = new OBJ_Bunny();
		bunnyCountImage = bunny.image;
		
	}
	// attributes for message control
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	// ----- DRAW -----
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(purisa);
		g2.setColor(Color.white);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //anti-alias for fonts
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			// Do playState stuff later
			// level completion message
			if(gameFinished == true) {
				
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 70F)); // sets font to 30, NOTE: accepts float as argument 
				g2.setColor(Color.yellow);
				
				String text = "Congratulations!";
				int x = getXForCenteredText(text);
				int y = gp.screenHeight/2;;
				
				g2.drawString(text, x, y);
				//STOP THE GAME(gameThread)
				gp.gameThread = null; 
			}
			// display Bunny collect count
			else {
				
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
				g2.setColor(Color.white);
				g2.drawImage(bunnyCountImage, 30, 10, gp.tileSize, gp.tileSize, null);
				g2.drawString("x" + gp.player.hasBunny, 70, 40); // Parameters: text, x, y
				
				// MESSAGE
				if (messageOn == true) {
					
					g2.setFont(bauhaus93);
					g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
					
					String text = message;
					int x = getXForCenteredText(text);
					
					g2.drawString(message, x, 500);
					
					messageTime++;
					// 2 sec timer, then turns off message
					if(messageTime > 120) {
						messageTime = 0;
						messageOn = false;
					}
				}
			}
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
	}
	//screen render for titleState
	public void drawTitleScreen() {
		
		// BACKGROUND
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 144F));
		String text = "Rescute";
		int x = getXForCenteredText(text);
		int y = gp.tileSize*5;
		// text shadow
		// g2.setColor(Color.gray);
		// g2.drawString(text, x+1, y+3);
		// main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// IMAGE
		x = gp.screenWidth/2 - (gp.tileSize*2/2);
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1,  x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		
		text = "NEW GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD MAP";
		x = getXForCenteredText(text);
		y += 56;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXForCenteredText(text);
		y += 56;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}
	
	// screen render for pauseState
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/2;
		 
		g2.drawString(text, x, y);	
	}
	// method to get x coordinate of a text to center it on the screen
	public int getXForCenteredText(String text) {
		
		int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // using built in methods to find length of the text message
		int x = gp.screenWidth/2 - textLength/2;
		
		return x;
	}
}
