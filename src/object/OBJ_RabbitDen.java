package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_RabbitDen extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_RabbitDen(GamePanel gp) {
		
		this.gp = gp;
		
		name = "RabbitDen";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/blackhole.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
