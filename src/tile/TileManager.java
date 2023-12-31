package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new Tile[10];
		mapTileNum = new int[gp.maxMap][gp.maxScreenCol][gp.maxScreenRow];

		getTileImage();
		loadMap("/maps/map01.txt", 0); // PARAMETERS: filepath, assign an index number
		loadMap("/maps/map02.txt", 1);
		loadMap("/maps/map03.txt", 2);
	}

	public void getTileImage() {

			setup(0, "grass01", false);
			setup(1, "tree", true);
			setup(2, "water00", true);
	}
	public void setup(int index, String imageFileName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageFileName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// Method to load created .txt map by grabbing number value
	public void loadMap(String mapPath, int mapNumber) {

		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

				String line = br.readLine(); // read a single col and make "line" that string of

				// loop through a line, convert from string to int, insert value into mapTileNum
				while (col < gp.maxScreenCol) {

					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[mapNumber][col][row] = num;
					col++;
				}
				if (col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();

		} catch (Exception e) {

		}

	}

	public void draw(Graphics2D g2) {
		// MANUALLY draw a 5x5 space
//		g2.drawImage(tile[1].image, 0, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 96, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 144, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 192, 0, gp.tileSize, gp.tileSize, null);
//		
//		g2.drawImage(tile[1].image, 0, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 48, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 96, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 144, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 192, 48, gp.tileSize, gp.tileSize, null);
//		
//		g2.drawImage(tile[1].image, 0, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 48, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 96, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 144, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 192, 96, gp.tileSize, gp.tileSize, null);
//		
//		g2.drawImage(tile[1].image, 0, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 48, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 96, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 144, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 192, 144, gp.tileSize, gp.tileSize, null);
//		
//		g2.drawImage(tile[1].image, 0, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 48, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 96, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 144, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 192, 192, gp.tileSize, gp.tileSize, null);

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

			int tileNum = mapTileNum[gp.currentMap][col][row];

			g2.drawImage(tile[tileNum].image, x, y, null);
			col++;
			x += gp.tileSize;

			if (col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
	}
}
