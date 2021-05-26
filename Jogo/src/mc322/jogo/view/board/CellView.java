package mc322.jogo.view.board;

import javax.swing.JButton;

import mc322.jogo.controller.CellController;

public class CellView extends JButton{
	private static final long serialVersionUID = -3039247586271474780L;

	public CellView(String string,int x, int y) {
		super(string);
		this.addMouseListener(new CellController(x,y));
		this.setBorderPainted(false);
	}
	
}
