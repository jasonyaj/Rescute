package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8200516253908377332L;
	
	// SCREEN SETTINGS
	final int orginalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = orginalTileSize * scale; // 48x48 tile
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768px
	final int screenHeight = tileSize * maxScreenRow; // 576px
	
	// FPS
	int FPS = 60; 
	
	// ------ INSTANTIATE -----
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // set to true for better rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true); // GamePanel can be set to "focus" to receive key input
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	// ----- GAME TIME LOOP & INTERVAL -----
	// delta method
//	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS;
//		double delta = 0;
//		long lastTime = System.nanoTime();
//		long currentTime;
//		// FPS calculation attributes
//		long timer = 0;
//		int drawCount = 0;
//		
//		while(gameThread != null) {
//			
//			currentTime = System.nanoTime();
//			
//			delta += (currentTime - lastTime)/drawInterval;
//			timer += (currentTime - lastTime);
//			lastTime = currentTime;
//			
//			if(delta >= 1) {
//				update(); // update information such as character position
//				repaint(); // repaint() is how you call the paintComponent method, draw thw screen with updated information
//				delta--;
//				drawCount++;
//			}
//			
//			// FPS
//			if(timer >= 1000000000) {
//				System.out.println("FPS: " + drawCount);
//				drawCount = 0;
//				timer = 0;
//			}
//			
//			
//		}
//		
//	}
	// Delta method from SimplePong
//	@Override
//	public void run() {
//		this.requestFocus();
//		
//		//game timer
//		long lastTime = System.nanoTime();
//		double amountOfTicks = 60.0; // FPS
//		double ns = 1000000000 / amountOfTicks;
//		double delta = 0;
//		long timer = System.currentTimeMillis();
//		int frames = 0;
//		while (gameThread != null) {
//			long now = System.nanoTime();
//			delta += (now - lastTime) / ns;
//			lastTime = now;
//			while (delta >= 1) {
//				update();
//				delta--;
//			}
//			if (gameThread != null) 
//				repaint();
//			frames++;
//			
//			if (System.currentTimeMillis()-timer > 1000) {
//				timer += 1000;
//				System.out.println("FPS: " + frames);
//				frames = 0;
//			}
//		}
//		stop();
//	}
//	//for use w/Delta SimplePong Method
//	private void stop() {
//		try {
//			gameThread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//	}
	// Sleep Method
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;// 0.01666 sec
		double nextDrawTime  = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			update();
			repaint();
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; // convert nanosecond to millisecond because .sleep expects millisecond
				
				//edge case for if update and repaint takes more than drawInterval, no time left
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
	}
	
	
	public void update() {
		// NOTE: Java has X and Y axis start at 0,0 top left hand corner. "+" moves right and down. "-" moves close back to 0,0
		
		player.update();
		
	}
	public void paintComponent(Graphics g) { // paintComponent is actually a built in method in Java to draw things in JPanel
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
		
	}
	
}
