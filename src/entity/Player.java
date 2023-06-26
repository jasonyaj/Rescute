package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	public int hasBunny = 0;
	public int savedBunny = 2;

	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle();
		solidArea.x = 8; // sideNote: make value into a variable for scalability
		solidArea.y = 12; // sideNote: make value into a variable for scalability
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32; // sideNote: make value into a variable for scalability
		solidArea.height = 32; // sideNote: make value into a variable for scalability

		setDefaultValues();
		getPlayerImage();
		

	}

	public void setDefaultValues() {

		// Set player's default position
		x = 100;
		y = 100;
		speed = 4; // amount of pixel movement
		direction = "down";

	}

	public void getPlayerImage() {

		try {

			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		// NOTE: Java has X and Y axis start at 0,0 top left hand corner. "+" moves
		// right and down. "-" moves close back to 0,0

		// putting key handler inside this "true" statement makes sprite change happen only when a key input is pressed
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";	
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true); // int because method returns index "1987"
			pickUpObject(objIndex);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					y -= speed;
					break;
				case "down":
					y += speed;
					break;
				case "left":
					x -= speed;
					break;
				case "right":
					x += speed;
					break;
				}
			}
			
			// ----- SPRITE IMAGE SWITCH LOGIC -----
			spriteCounter++; // every frame will increase counter by 1
			if (spriteCounter > 15) { // counter reaches 11 and this if statement occurs
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

		}

	}
	
	// method to pick up object when collision/intersect event happens
	public void pickUpObject(int i) {
		
		if(i != 1987) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Bunny":
				gp.playSE(1);
				hasBunny++;
				gp.obj[i] = null; // this will delete the object when touched
				gp.ui.showMessage("Hurry, save the bunny, drop it in the den.");
				break;
			case "RabbitDen":
				if(hasBunny > 0) {
					while(hasBunny > 0) {
						gp.playSE(3); // ----- FIX ME, so fast, can only here it once, FIX ME ----
						hasBunny--;
						savedBunny--;
						if(savedBunny == 0) {
							gp.playSE(2);
							
						}
					}
				}
				else {
					gp.ui.showMessage("There is "+savedBunny+" left to save.");
					
				}
				System.out.println("Bunny: "+hasBunny);
				break;
			}
		}
	}

	public void draw(Graphics2D g2) {

		// TEST rectangle to be replaced with sprite/images after testing
		// g2.setColor(Color.white);
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

	}

}
