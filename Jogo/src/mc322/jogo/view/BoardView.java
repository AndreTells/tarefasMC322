package mc322.jogo.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import mc322.jogo.controller.BoardController;

//add(new JLabel("<html>Text color: <font color='red'>red</font></html>"));
public class BoardView {
	private static JFrame frame;
	private static JPanel game_panel;
	private static CellView [][] map;
	private static JLabel population;
	private static JLabel production;
	private static JLabel food;
	private static JLabel happiness;
	private static JTextArea  info;
	
	public static void init(int map_height,int map_length,int font_size){
		createGamePanel(map_height,map_length,font_size);
	}	
	public static void init() {
		createGamePanel(10,10,15);
	}
	
	private static void createGamePanel(int map_height,int map_length,int font_size) {
		frame = new JFrame();
		
		frame.setTitle("jogo");
		frame.setSize(1000,600);
		
		game_panel = new JPanel(new GridBagLayout());
		frame.setContentPane(game_panel);
		
		GridBagConstraints panel_c = new GridBagConstraints();
		
		panel_c.fill = GridBagConstraints.BOTH;
		JPanel map_panel = new JPanel(new GridBagLayout());
		
		panel_c.gridx = 0;
		panel_c.gridy = 0;
		panel_c.gridheight = 6;
		panel_c.weightx = 0.7;
		panel_c.weighty = 1;
		game_panel.add(map_panel,panel_c);
		
		GridBagConstraints borad_c = new GridBagConstraints();
		borad_c.weightx=1;
		borad_c.weighty=1;
		//c.fill = GridBagConstraints.HORIZONTAL;
		borad_c.fill = GridBagConstraints.BOTH;
		//imprime o tabuleiro
		map = new CellView[map_height][map_length];
		for(int i =0;i< map_height;i++) {
			for(int j=0;j<map_length;j++) {
						
				borad_c.gridx = i;
				borad_c.gridy = j;
				//i+","+j
				CellView btn = new CellView("",i,j);
				map[j][i] = btn;
				btn.setRolloverEnabled(false);
				btn.setFocusable(false);
				map_panel.add(btn,borad_c);
			}
		}
				
		GridBagConstraints stats_c = new GridBagConstraints();
		stats_c.fill = GridBagConstraints.BOTH;
		stats_c.gridx = 1;
		stats_c.weighty = 0.1;
		
		//populacao
		population = new JLabel("Populacao:----");
		population.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 0;
		game_panel.add(population,stats_c);
				
		//producao
		production = new JLabel("Producao:----");
		production.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 1;
		game_panel.add(production,stats_c);
				
		//comida
		food = new JLabel("Comida:----");
		food.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 2;
		game_panel.add(food,stats_c);
				
		//felicidade
		happiness = new JLabel("felicidade:----");
		happiness.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 3;
		game_panel.add(happiness,stats_c);
		
		
		info = new JTextArea ("info:(informacoes da celula selecionada) Lorem ipsum dolor sit amet, consectetur adi piscing elit. Phasellus varius, eros id vehicula porttito.");
		info.setPreferredSize(new Dimension(300,100));
		info.setFont(new Font("Calibri", Font.BOLD, font_size));
		info.setLineWrap(true);
		info.setEditable(false);
		info.setBackground(new Color(255,255,255));
		stats_c.weighty = 0.4;
		stats_c.gridy = 4;
		game_panel.add(info,stats_c);
		
		//botao de próximo turno
		Button next_turn = new Button("proximo turno");
		next_turn.setBackground(new Color(0,255,0));
		stats_c.weighty = 0.2;
		stats_c.gridy = 5;
		next_turn.addActionListener(new BoardController());
		game_panel.add(next_turn,stats_c);
		
		
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}

	//-------------set methods---------------
	public static void setInfo(String info_s) {
		info.setText(info_s);
	}
	public static void setPopulation(int population_i) {
		population.setText("Populacao: "+population_i);
	}
	public static void setProduction(int production_i) {
		production.setText("Producao: "+production_i);
	}
	public static void setFood(int food_i) {
		if(food_i >0) {
			food.setText("Comida: +"+food_i);	
		}
		else {
			food.setText("Comida: "+food_i);
		}
	}
	public static void setHappiness(int happiness_i) {
		happiness.setText("felicidade: "+happiness_i);
	}
	
	public static void setCellColor(Color color, int x,int y) {
		map[y][x].setBackground(color);
	}
	
}
