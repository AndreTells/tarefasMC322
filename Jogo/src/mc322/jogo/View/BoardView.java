package mc322.jogo.View;

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

//add(new JLabel("<html>Text color: <font color='red'>red</font></html>"));
public class BoardView {
	public static JFrame frame;
	private static int font_size;
	private static int board_length;
	private static int board_height;
	
	public static void initBoardView(int board_height_i,int board_length_i,int font_size_i){
		createBoardView(board_height_i,board_length_i,font_size_i);
	}	
	public static void initBoardView() {
		createBoardView(10,10,15);
	}
	
	private static void createBoardView(int board_height_i,int board_length_i,int font_size_i) {
		frame = new JFrame();
		board_height = board_height_i;
		board_length = board_length_i;
		font_size = font_size_i;
		
		frame.setTitle("jogo");
		frame.setSize(1000,600);
		
		JPanel panel = new JPanel(new GridBagLayout());
		//frame.setLayout(new GridBagLayout());
		frame.setContentPane(panel);
		
		GridBagConstraints panel_c = new GridBagConstraints();
		
		panel_c.fill = GridBagConstraints.BOTH;
		JPanel board_panel = new JPanel(new GridBagLayout());
		
		panel_c.gridx = 0;
		panel_c.gridy = 0;
		panel_c.gridheight = 6;
		panel_c.weightx = 0.7;
		panel_c.weighty = 1;
		panel.add(board_panel,panel_c);
		
		GridBagConstraints borad_c = new GridBagConstraints();
		borad_c.weightx=1;
		borad_c.weighty=1;
		//c.fill = GridBagConstraints.HORIZONTAL;
		borad_c.fill = GridBagConstraints.BOTH;
		//imprime o tabuleiro
				for(int i =0;i<board_length;i++) {
					for(int j=0;j<board_height;j++) {
						
						borad_c.gridx = i;
						borad_c.gridy = j;
						
						CellView btn = new CellView(i+","+j);
						btn.setRolloverEnabled(false);
						btn.setFocusable(false);
						board_panel.add(btn,borad_c);
					}
				}
		/*
		JPanel stats_panel = new JPanel(new GridBagLayout());
		panel_c.gridx = 1;
		panel_c.gridy = 0;
		panel_c.weightx = 0.3;
		panel_c.weighty = 1;
		panel.add(stats_panel,panel_c);*/
		GridBagConstraints stats_c = new GridBagConstraints();
		stats_c.fill = GridBagConstraints.BOTH;
		stats_c.gridx = 1;
		stats_c.weighty = 0.1;
		
		//populacao
		JLabel populacao = new JLabel("Populacao:----");
		populacao.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 0;
		panel.add(populacao,stats_c);
				
		//producao
		JLabel producao = new JLabel("Producao:----");
		producao.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 1;
		panel.add(producao,stats_c);
				
		//comida
		JLabel comida = new JLabel("Comida:----");
		comida.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 2;
		panel.add(comida,stats_c);
				
		//felicidade
		JLabel felicidade = new JLabel("felicidade:----");
		felicidade.setFont(new Font("Calibri", Font.BOLD, font_size));
		stats_c.gridy = 3;
		panel.add(felicidade,stats_c);
		
		
		JTextArea  info = new JTextArea ("1");
		info.setPreferredSize(new Dimension(300,100));
		info.setFont(new Font("Calibri", Font.BOLD, font_size));
		info.setLineWrap(true);
		info.setBackground(new Color(255,255,255));
		stats_c.weighty = 0.4;
		stats_c.gridy = 4;
		panel.add(info,stats_c);
		
		//botao de próximo turno
		Button next_turn = new Button("proximo turno");
		next_turn.setBackground(new Color(0,255,0));
		stats_c.weighty = 0.2;
		stats_c.gridy = 5;
		panel.add(next_turn,stats_c);
		
		
		/*
		JPanel board_panel = new JPanel(new GridBagLayout());
		board_panel.setBackground(new Color(0,255,0));
		
		panel_c.gridx = 0;
		panel_c.gridy = 0;
		panel_c.weightx = 1;
		panel_c.weighty = 1;
		panel.add(board_panel,panel_c);
		
		GridBagConstraints borad_c = new GridBagConstraints();
		borad_c.weightx=1;
		borad_c.weighty=1;
		//c.fill = GridBagConstraints.HORIZONTAL;
		borad_c.fill = GridBagConstraints.BOTH;
		
		
		//imprime o tabuleiro
		for(int i =0;i<board_length;i++) {
			for(int j=0;j<board_height;j++) {
				
				borad_c.gridx = i;
				borad_c.gridy = j;
				
				CellView btn = new CellView(i+","+j);
				btn.setRolloverEnabled(false);
				btn.setFocusable(false);
				board_panel.add(btn,borad_c);
			}
		}
		
		JPanel stats_panel = new JPanel(new GridBagLayout());
		panel_c.gridx = 1;
		panel_c.gridy = 0;
		panel_c.weightx = 0.3;
		panel_c.weighty = 1;
		panel.add(stats_panel,panel_c);
		
		GridBagConstraints stats_c = new GridBagConstraints();
		stats_c.weightx=1;
		stats_c.weighty=1;
		stats_c.fill = GridBagConstraints.BOTH;
		JLabel  info = new JLabel ("<html>info:(informacoes da celula selecionada) Lorem ipsum dolor sit amet, consectetur adi piscing elit. Phasellus varius, eros id vehicula porttito.</html>");
		
		stats_panel.add(info,stats_c);
		/*
		//c.fill = GridBagConstraints.HORIZONTAL;
		stats_c.fill = GridBagConstraints.BOTH;
		stats_c.gridx = 0;
		//Info da celula selecionada
		JTextArea info = new JTextArea(50,50);
		info.setColumns(100); 
		JScrollPane scrollPane = new JScrollPane( info );

		stats_panel.add(info);
		
		info.setText("info:(informacoes da celula selecionada) \n"
		+"Lorem ipsum dolor sit amet, consectetur adi"
		+ "piscing elit. Phasellus varius, eros id vehicula porttito.");
		stats_c.fill = GridBagConstraints.HORIZONTAL;
		stats_c.gridy = 3;
		stats_c.gridwidth = 2;
		info.setFont(new Font("Calibri", Font.BOLD, font_size));
		info.setLineWrap(true);
		info.setBackground(new Color(255,255,255));
		info.setActionMap(null);
		CustomListener lis = new CustomListener(frame);
		info.addComponentListener(lis);
		info.setEditable(true);
		stats_panel.add(info,stats_c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//imprime as estatisticas
		//populacao
		JLabel populacao = new JLabel("Populacao:----");
		populacao.setFont(new Font("Calibri", Font.BOLD, font_size));
		setPosStatistics(c,0);
		panel.add(populacao,c);
				
		//producao
		JLabel producao = new JLabel("Producao:----");
		producao.setFont(new Font("Calibri", Font.BOLD, font_size));
		setPosStatistics(c,1);
		panel.add(producao,c);
		
		//comida
		JLabel comida = new JLabel("Comida:----");
		comida.setFont(new Font("Calibri", Font.BOLD, font_size));
		setPosStatistics(c,2);
		panel.add(comida,c);
		
		//felicidade
		JLabel felicidade = new JLabel("felicidade:----");
		felicidade.setFont(new Font("Calibri", Font.BOLD, font_size));
		setPosStatistics(c,3);
		panel.add(felicidade,c);
		
		
		c.fill = GridBagConstraints.NONE;
		//Info da celula selecionada
		JTextArea info = new JTextArea("info:(informacoes da celula selecionada) \n"
		+"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus varius, eros id vehicula porttitor, tellus elit aliquam ipsum, ac vestibulum urna arcu eu magna.");
		c.gridx = board_length;
		c.gridy = 4;
		c.gridheight = 6;
		info.setFont(new Font("Calibri", Font.BOLD, font_size));
		info.setLineWrap(true);
		info.setBackground(new Color(255,255,255));
	//	info.setEditable(false);
		panel.add(info,c);
		
		
		//botao de próximo turno
		Button next_turn = new Button("proximo turno");
		next_turn.setBounds(cell_size*(board_length), (font_size*4) + 250 , 350, 50);
		next_turn.setBackground(new Color(0,255,0));
		panel.add(next_turn);
		*/
		
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}
	
	
	
}
