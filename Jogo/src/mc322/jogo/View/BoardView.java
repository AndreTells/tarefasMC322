package mc322.jogo.View;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//add(new JLabel("<html>Text color: <font color='red'>red</font></html>"));
public class BoardView {
	public static JFrame frame;
	private static int cell_size;
	private static int font_size;
	
	private static void setStatisticPos(JLabel label,int board_length,int line,int str_len) {
		label.setBounds(cell_size*(board_length), font_size*line,str_len,font_size);
		label.setFont(new Font("Calibri", Font.BOLD, font_size));
	}
	
	public static void initBoardView(int board_height,int board_length){
		frame = new JFrame();
		frame.setTitle("jogo");
		frame.setSize(1000,660);
		frame.setLayout(null);
		
		cell_size = 60;
		font_size = 20;
		//imprime o tabuleiro
		for(int i =0;i<board_length;i++) {
			for(int j=0;j<board_height;j++) {
				Cell btn = new Cell(i+","+j);
				btn.setBounds(cell_size*i, cell_size*j, cell_size, cell_size);
				btn.setRolloverEnabled(false);
				btn.setFocusable(false);
				frame.add(btn);
			}
		}
		
		//imprime as estatisticas
		//populacao
		JLabel populacao = new JLabel("Populacao:----");
		setStatisticPos(populacao,board_length,0,200);
		frame.add(populacao);
		//producao
		JLabel producao = new JLabel("Producao:----");
		setStatisticPos(producao,board_length,1,200);
		frame.add(producao);
		//comida
		JLabel comida = new JLabel("Comida:----");
		setStatisticPos(comida,board_length,2,200);
		frame.add(comida);
		//felicidade
		JLabel felicidade = new JLabel("felicidade:----");
		setStatisticPos(felicidade,board_length,3,200);
		frame.add(felicidade);
		
		//Info da celula selecionada
		JTextArea info = new JTextArea("info:(informacoes da celula selecionada) \n"+
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus varius, eros id vehicula porttitor, tellus elit aliquam ipsum, ac vestibulum urna arcu eu magna. Nunc faucibus dolor non nunc finibus, id pretium massa commodo. Praesent pharetra consequat urna ac congue. Curabitur semper eleifend enim, eu tempor velit. Aliquam arcu nisi, sodales quis vulputate at, tempor ac odio. Suspendisse accumsan mauris tortor, eu finibus elit suscipit a. Etiam libero purus, dignissim sed vehicula id, aliquet vitae odio. Donec sollicitudin in orci dignissim commodo. Duis finibus vel libero vitae tempor. Phasellus quis sem ipsum. Nam arcu lacus, blandit efficitur sodales tempus, aliquet eget purus. Donec at massa porta, ultricies erat eu, maximus magna. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Phasellus viverra blandit libero a condimentum. Quisque molestie ipsum nibh. Mauris condimentum tempus justo eu rutrum.");
		info.setBounds(cell_size*(board_length), font_size*4, 350, 200);
		info.setFont(new Font("Calibri", Font.BOLD, font_size));
		info.setLineWrap(true);
		info.setBackground(new Color(255,255,255));
		info.setEditable(false);
		frame.add(info);
		
		//botao de próximo turno
		Button next_turn = new Button("proximo turno");
		next_turn.setBounds(cell_size*(board_length), (font_size*4) + 250 , 350, 50);
		next_turn.setBackground(new Color(0,255,0));
		frame.add(next_turn);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true); 
	}
	
}
