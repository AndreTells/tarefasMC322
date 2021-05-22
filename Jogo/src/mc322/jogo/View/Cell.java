package mc322.jogo.View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Cell extends JButton implements ActionListener{

	private int i_colour;
	private Color colour_list[];
	public Cell(String string) {
		super(string);
		this.addActionListener(this);
		this.i_colour = 0;
		this.colour_list = new Color[5];
		this.colour_list[0] = new Color(122,232,90);
		this.colour_list[1] = new Color(240,230,140);
		this.colour_list[2] = new Color(0,100,0);
		this.colour_list[3] = new Color(30,144,255);
		this.colour_list[4] = new Color(139,69,19);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setBackground(this.colour_list[this.i_colour]);
		this.i_colour +=1;
		if(this.i_colour == this.colour_list.length) {
			this.i_colour = 0;
		}
	}
	
	
}
