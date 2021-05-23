package mc322.jogo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import mc322.jogo.model.BoardModel;
import mc322.jogo.model.components.City;
import mc322.jogo.view.BoardView;

public class CellController implements MouseListener{

	private int x;
	private int y;
	public CellController(int x, int y){
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(SwingUtilities.isRightMouseButton(e)) {	
			JPopupMenu popup = new JPopupMenu();
			JMenuItem menu_item_city = new JMenuItem("City");
			menu_item_city.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){ 
					BoardModel.addComponent(new City(),x,y);
					Color color = BoardModel.getCellColor(x, y);
					BoardView.setCellColor(color, x, y);
					System.out.println(x+","+y);
				} 
			});
			popup.add(menu_item_city);
		    /*popup.add(new JMenuItem("Copy"));
		    popup.add(new JMenuItem("Paste"));
		    popup.addSeparator();
		    popup.add(new JMenuItem("SelectAll"));*/
		    popup.show(e.getComponent(),e.getX(),e.getY());
		    //System.out.println(x+","+y);
		    
		}
		else if(SwingUtilities.isLeftMouseButton(e)) {
			String info_s = BoardModel.getCellInfo(x, y);
			BoardView.setInfo(info_s);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
