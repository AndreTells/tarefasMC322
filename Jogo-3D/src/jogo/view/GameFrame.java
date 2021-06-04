package jogo.view;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	GameView game_view;
	
	public GameFrame(){
		this.setSize(1000,600);  
		this.setResizable(false);
	   	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	   	game_view = new GameView(this);
	}
}
