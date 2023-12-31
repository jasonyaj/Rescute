package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Rescute: Bunny Edition");
		
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null); // null sets window to be centered of screen
		window.setVisible(true);
		
		gamepanel.setupGame();
		gamepanel.startGameThread();
	}

}
