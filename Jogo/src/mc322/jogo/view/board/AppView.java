package mc322.jogo.view.board;

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
public class AppView implements IViewBuilder{
	private static JFrame frame;
	private BoardPanel board_panel;
	
	public AppView(){
		frame = new JFrame();
		frame.setTitle("jogo");
		frame.setSize(1000,600);
		this.board_panel = new BoardPanel(10,10,15);
		frame.setContentPane(board_panel);
		
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}
	
	public IBoardViewController getBoardPanel() {
		return this.board_panel;
	}
}
