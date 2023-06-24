package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_RabbitDen extends SuperObject{
	
	public OBJ_RabbitDen() {
		
		name = "RabbitDen";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/blackhole.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
