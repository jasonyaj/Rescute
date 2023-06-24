package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Bunny extends SuperObject{

	public OBJ_Bunny() {
		
		name = "Bunny";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/bunny.png"));
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
