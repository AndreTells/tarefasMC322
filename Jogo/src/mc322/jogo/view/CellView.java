package mc322.jogo.view;

import javax.swing.JButton;

import mc322.jogo.controller.CellController;

public class CellView extends JButton{

	public CellView(String string,int x, int y) {
		super(string);
		this.addMouseListener(new CellController(x,y));
		this.setBorderPainted(false);
	}
	
}
