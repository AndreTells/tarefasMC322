package jogo.controller;

import java.awt.event.MouseEvent;

import jogo.view.glelements.IActor;

public class CellController implements IActor{
	private int map_x;
	private int map_y;
	private NextTurnController controller;
	
	public CellController(int map_x,int map_y,NextTurnController controller) {
		this.map_x = map_x;
		this.map_y = map_y;
		this.controller = controller;
	}
	@Override
	public void act(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(map_x+" "+map_y);
		if(e.getButton() == e.BUTTON1) {
			controller.view.setInfo(controller.model.getCellInfo(map_x, map_y));
		}
	}

}
