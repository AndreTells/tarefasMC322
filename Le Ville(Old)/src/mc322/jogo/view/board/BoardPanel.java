package mc322.jogo.view.board;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mc322.jogo.controller.BoardController;
import mc322.jogo.controller.IControllerBoardView;
import mc322.jogo.controller.IControllerCellView;

public class BoardPanel extends JPanel implements IBoardViewController{
	private static final long serialVersionUID = -7689849203267198502L;
	private MapPanel map_panel;
	private JLabel population;
	private JLabel production;
	private JLabel food;
	private JTextArea  info;
	private Button next_turn;
	
	public BoardPanel(int map_height, int map_length,int font_size){
		super();
		this.setLayout(new GridBagLayout());
		GridBagConstraints map_pane_c = new GridBagConstraints();
		
		map_panel = new MapPanel(map_height, map_length);
		map_pane_c.gridx = 0;
		map_pane_c.gridy = 0;
		map_pane_c.gridheight = 5;
		map_pane_c.weightx = 0.7;
		map_pane_c.weighty = 1;
		map_pane_c.fill = GridBagConstraints.BOTH;
		
		this.add(map_panel,map_pane_c);
			
		
		GridBagConstraints stats_c = new GridBagConstraints();
		stats_c.fill = GridBagConstraints.BOTH;
		stats_c.gridx = 1;
		stats_c.weighty = 0.1;
		
		//populacao
		population = new JLabel("Populacao:----");
		population.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 0;
		this.add(population,stats_c);
				
		//producao
		production = new JLabel("Producao:----");
		production.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 1;
		this.add(production,stats_c);
					
		//comida
		food = new JLabel("Comida:----");
		food.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 2;
		this.add(food,stats_c);
		
		
		info = new JTextArea ("info:(informacoes da celula selecionada) Lorem ipsum dolor sit amet, consectetur adi piscing elit. Phasellus varius, eros id vehicula porttito.");
		info.setPreferredSize(new Dimension(300,100));
		info.setFont(new Font("Calibri", Font.BOLD, font_size));
		info.setLineWrap(true);
		info.setEditable(false);
		info.setBackground(new Color(255,255,255));
		stats_c.weighty = 0.5;
		stats_c.gridy = 3;
		this.add(info,stats_c);
		
		//botao de próximo turno
		next_turn = new Button("proximo turno");
		next_turn.setBackground(new Color(0,255,0));
		stats_c.weighty = 0.2;
		stats_c.gridy = 4;
		this.add(next_turn,stats_c);
	}

	
	//set methods
	public void setInfo(String info_s) {
		info.setText(info_s);
	}
	public void setPopulation(String population_i) {
		population.setText("Populacao: "+population_i);
	}
	public void setProduction(String production_i) {
		production.setText("Producao: "+production_i);
	}
	public void setFood(String food_i) {
		food.setText("Comida: "+food_i);	
	}
	public void setCellColor(Color color, int x,int y) {
		this.map_panel.setCellColor(color,x,y);
	}	
	public void setGameOver() {
		setInfo("GAME OVER");
	}

	public void setBoardController(IControllerBoardView controller) {
		this.next_turn.addActionListener(controller);
	}

	public void setCellController(IControllerCellView controller,int x,int y) {
		this.map_panel.setCellController(controller, x, y);
	}
}
