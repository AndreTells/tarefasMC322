package mc322.jogo.view.board;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import mc322.jogo.controller.IControllerCellView;

public class MapPanel extends JPanel{
	private static final long serialVersionUID = 3308892951178274255L;
	private static CellView [][] map;
	
	public MapPanel(int map_height, int map_length) {
		super();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		map = new CellView[map_height][map_length];
		for(int i =0;i< map_height;i++) {
			for(int j=0;j<map_length;j++) {
				c.gridx = i;
				c.gridy = j;
				//i+","+j
				CellView btn = new CellView("",i,j);
				map[j][i] = btn;
				btn.setRolloverEnabled(false);
				btn.setFocusable(false);
				this.add(btn,c);
			}
		}
	}

	public void setCellColor(Color color, int x,int y) {
		map[y][x].setBackground(color);
	}
	public void setCellController(IControllerCellView controller,int x,int y) {
		map[y][x].addMouseListener(controller);
	}
}
