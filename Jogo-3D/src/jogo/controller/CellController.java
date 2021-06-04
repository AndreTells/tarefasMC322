package jogo.controller;

import java.awt.event.MouseEvent;

import jogo.view.glelements.IActor;

public class CellController implements IActor{
	int map_x;
	int map_y;
	
	public CellController(int map_x,int map_y) {
		this.map_x = map_x;
		this.map_y = map_y;
	}
	@Override
	public void act(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(map_x+" "+map_y);
	}

}
