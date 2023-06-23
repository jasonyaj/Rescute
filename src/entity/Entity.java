package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int x, y;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // *BufferedImage, describes an Image w/an accesible buffer of image data(how we store image files)
	public String direction;
	
	// used for sprite count and boolean to set image render
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
}
