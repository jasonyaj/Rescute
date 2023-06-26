package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Bunny;

public class UI {

	GamePanel gp;
	Font arial_30, arial_80; // declare font variable
	BufferedImage bunnyCountImage;
	public boolean messageOn = false;
	public String message = "";
	int messageTime = 0;
	public boolean gameFinished = false;
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		// Instantiate Font and obj here instead of in draw method so as to not instantiate it 60 times per second
		arial_30 = new Font("Arial", Font.PLAIN, 30); // Parameters: font, font style, and size
		arial_80 = new Font("Arial", Font.BOLD, 70);
		OBJ_Bunny bunny = new OBJ_Bunny();
		bunnyCountImage = bunny.image;
		
	}
	// attributes for message control
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		// level completion message
//		if(gameFinished = true) {
//			System.out.println("FINISHED");
//			
//			g2.setFont(arial_80); 
//			g2.setColor(Color.yellow);
//			
//			String text;
//			int textLength;
//			int x;
//			int y;
//			
//			text = "Congratulations!";
//			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // using built in methods to find length of the text message
//			
//			// center of screen coordinates for messages(NOTE:message y axis is at baseline of font)
//			x = gp.screenWidth/2 - textLength/2;
//			y = gp.screenHeight/2;
//			
//			g2.drawString(text, x, y);
//			//STOP THE GAME
//			gp.gameThread = null; 
//		}
//		// display Bunny collect count
//		else {
			System.out.println("BUNNY");
			
			g2.setFont(arial_30); 
			g2.setColor(Color.white);
			g2.drawImage(bunnyCountImage, 30, 10, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasBunny, 70, 40); // Parameters: text, x, y
			
			// MESSAGE
			if (messageOn == true) {
				
	//			g2.setFont(g2.getFont().deriveFont(40F)); // sets arial_30 to 40 font, NOTE: accepts float as argument
				g2.drawString(message, 120, 500);
				
				messageTime++;
				// 2 sec timer, then turns off message
				if(messageTime > 120) {
					messageTime = 0;
					messageOn = false;
				}
			}
		}
//	}
}
