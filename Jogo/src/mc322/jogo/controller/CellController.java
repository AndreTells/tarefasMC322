package mc322.jogo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import mc322.jogo.model.BoardModel;
import mc322.jogo.model.components.City;
import mc322.jogo.model.components.Component;
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
			List<Component> possible = BoardModel.getPossibleActions(x, y);
			if(possible.size() == 0) {
				JMenuItem menu_item = new JMenuItem("None");
				popup.add(menu_item);
			}
			for(Component comp:possible) {
				JMenuItem menu_item = new JMenuItem(comp.getClass().getSimpleName());
				menu_item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){ 
						BoardModel.addComponent(comp,x,y);
						Color color = BoardModel.getCellColor(x, y);
						BoardView.setCellColor(color, x, y);
					} 
				});
				popup.add(menu_item);
			}
			
		    popup.show(e.getComponent(),e.getX(),e.getY());
		    
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
