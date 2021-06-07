package jogo.view;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	TEST game_view;
	
	public GameFrame(){
		this.setSize(1000,600);  
		this.setResizable(false);
	   	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	   	game_view = new TEST(this);
	}
	public TEST getView() {
		return game_view;
	}
}
