package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bunny extends SuperObject{

	GamePanel gp;
	
	public OBJ_Bunny(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Bunny";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/bunny.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// set own hitbox/Rectangle
//		solidArea.x = 2;
//		solidArea.y = 2;
//		solidArea.width = 12;
//		solidArea.height = 12;
	}
}
