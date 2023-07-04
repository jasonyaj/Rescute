package main;

import object.OBJ_Bunny;
import object.OBJ_RabbitDen;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int mapNumber = 0;
		int i= 0;
		
		// Map 1(tutorial)
		gp.obj[mapNumber][i] = new OBJ_Bunny(gp);
		gp.obj[mapNumber][i].x = 14 * gp.tileSize;
		gp.obj[mapNumber][i].y = 1 * gp.tileSize;
		i++;
		
		gp.obj[mapNumber][i] = new OBJ_Bunny(gp);
		gp.obj[mapNumber][i].x = 9 * gp.tileSize;
		gp.obj[mapNumber][i].y = 7 * gp.tileSize;
		i++;
		
		gp.obj[mapNumber][i] = new OBJ_RabbitDen(gp);
		gp.obj[mapNumber][i].x = 2 * gp.tileSize;
		gp.obj[mapNumber][i].y = 1 * gp.tileSize;
		
		// Map 2
		mapNumber = 1;
		
		gp.obj[mapNumber][i] = new OBJ_Bunny(gp);
		gp.obj[mapNumber][i].x = 14 * gp.tileSize;
		gp.obj[mapNumber][i].y = 1 * gp.tileSize;
		i++;
		
		gp.obj[mapNumber][i] = new OBJ_Bunny(gp);
		gp.obj[mapNumber][i].x = 9 * gp.tileSize;
		gp.obj[mapNumber][i].y = 7 * gp.tileSize;
		i++;
		
		gp.obj[mapNumber][i] = new OBJ_RabbitDen(gp);
		gp.obj[mapNumber][i].x = 2 * gp.tileSize;
		gp.obj[mapNumber][i].y = 1 * gp.tileSize;
		
		// Map 3
		mapNumber = 2;
		
		gp.obj[mapNumber][i] = new OBJ_Bunny(gp);
		gp.obj[mapNumber][i].x = 10 * gp.tileSize;
		gp.obj[mapNumber][i].y = 1 * gp.tileSize;
		i++;
		
		gp.obj[mapNumber][i] = new OBJ_Bunny(gp);
		gp.obj[mapNumber][i].x = 7 * gp.tileSize;
		gp.obj[mapNumber][i].y = 8 * gp.tileSize;
		i++;
		
		gp.obj[mapNumber][i] = new OBJ_Bunny(gp);
		gp.obj[mapNumber][i].x = 3 * gp.tileSize;
		gp.obj[mapNumber][i].y = 3 * gp.tileSize;
		i++;
		
		gp.obj[mapNumber][i] = new OBJ_RabbitDen(gp);
		gp.obj[mapNumber][i].x = 13 * gp.tileSize;
		gp.obj[mapNumber][i].y = 11 * gp.tileSize;
	}
}
