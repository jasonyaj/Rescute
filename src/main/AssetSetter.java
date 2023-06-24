package main;

import object.OBJ_Bunny;
import object.OBJ_RabbitDen;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Bunny();
		gp.obj[0].x = 14 * gp.tileSize;
		gp.obj[0].y = 1 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Bunny();
		gp.obj[1].x = 9 * gp.tileSize;
		gp.obj[1].y = 7 * gp.tileSize;
		
		gp.obj[2] = new OBJ_RabbitDen();
		gp.obj[2].x = 2 * gp.tileSize;
		gp.obj[2].y = 1 * gp.tileSize;
	}
}
