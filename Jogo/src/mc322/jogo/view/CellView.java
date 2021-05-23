package mc322.jogo.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import mc322.jogo.controller.CellController;

public class CellView extends JButton{

	public CellView(String string,int x, int y) {
		super(string);
		this.addMouseListener(new CellController(x,y));
	}
	
}
