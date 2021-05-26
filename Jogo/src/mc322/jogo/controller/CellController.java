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

import mc322.jogo.model.board.BoardModel;
import mc322.jogo.model.board.IBoardModelBuilder;
import mc322.jogo.model.board.components.City;
import mc322.jogo.model.board.components.Component;
import mc322.jogo.model.board.components.ConstructableComponent;
import mc322.jogo.view.board.AppView;

public class CellController implements IControllerCellView{

	private int x;
	private int y;
	private BoardController controller;
	public CellController(int x, int y,BoardController controller){
		super();
		this.x = x;
		this.y = y;
		this.controller = controller;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		IBoardModelBuilder board_model = controller.getBoardModel();
		if(SwingUtilities.isRightMouseButton(e)) {	
			JPopupMenu popup = new JPopupMenu();
			if(!board_model.isClaimed(x, y)) {
				JMenuItem menu_item = new JMenuItem("Adicionar");
				menu_item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){ 
						board_model.claim(x,y);
						controller.updateStats();
					} 
				});
				popup.add(menu_item);
			}
			else {
				List<ConstructableComponent> possible = board_model.getPossibleActions(x, y);
				if(possible.size() == 0){
					JMenuItem menu_item = new JMenuItem("None");
					popup.add(menu_item);
				}
				for(ConstructableComponent comp:possible) {
					JMenuItem menu_item = new JMenuItem(comp.getClass().getSimpleName());
					menu_item.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e){ 
							board_model.constructComponent(comp,x,y);
							controller.updateStats();
							controller.updateCellColor(x,y);
						} 
					});
					popup.add(menu_item);
				}
			}
			
		    popup.show(e.getComponent(),e.getX(),e.getY());
		    
		}
		else if(SwingUtilities.isLeftMouseButton(e)) {
			String info = board_model.getCellInfo(x, y);
			controller.setInfo(info);
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
