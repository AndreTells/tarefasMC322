package jogo.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;

import jogo.controller.NextTurnController;
import jogo.view.glelements.Button;
import jogo.view.glelements.ButtonListener;
import jogo.view.glelements.IContainer;
import jogo.view.glelements.Label;
import jogo.view.glelements.RoundedRectangle;

public class UI implements KeyListener, IContainer{
	
	private int width;
	private int height;
	private boolean active;
	
	private RoundedRectangle menu_backdrop;
	private Label population;
	private Label production;
	private Label food;
	private Label info;
	private Button next_turn;

	
	public UI(int font_size,float UI_size_x,float margin) {
		this.active = true;
		
		this.menu_backdrop = new RoundedRectangle(1f,UI_size_x,0.02f,1-(UI_size_x),0);
		
		this.population = new Label("population: ---",font_size,1-(UI_size_x*2)+margin,1-(margin+0.05f),false);
		this.production = new Label("production: ---",font_size,1-(UI_size_x*2)+margin,1-(margin+0.18f),false);
		this.food = new Label("food: ---",font_size,1-(UI_size_x*2)+margin,1-(margin+0.31f),false);
		this.info = new Label("info: ---",font_size,1-(UI_size_x*2)+margin,1-(margin+0.44f),false);
		this.next_turn = new Button("Next Turn",font_size,1-UI_size_x,-1+0.09f+margin,0.08f,UI_size_x-(margin),0.02f);
	}
	
	public void setDims(int width,int height) {
		this.width = width;
		this.height = height;
	}
	
	public void setNextTurnListener(GLCanvas gc) {
		gc.addMouseListener(new ButtonListener( next_turn,this,new NextTurnController() ));
	}
	
	public boolean isActive() {
		return active;
	}

	public int[] getDims() {
		return new int[] {width,height};
	}
	
	public void draw(GL2 gl,float transparency) {
		if(!active) {
			return;
		}
		
		gl.glColor4f(0.09f, 0.1f, 0.1f,transparency);
		menu_backdrop.draw(gl);

		
		population.setScreenDims(width, height);
		population.draw(gl);
		
		production.setScreenDims(width, height);
		production.draw(gl);
		
		food.setScreenDims(width, height);
		food.draw(gl);
		
		info.setScreenDims(width, height);
		info.draw(gl);
		
		//button
		gl.glColor4f(0.26f, 0.52f, 0.96f,transparency);
		next_turn.setScreenDims(width, height);
		next_turn.draw(gl);
		
		
	}

	public void drawRoundRectangle(GL2 gl,float height,float width,float radius) {
		gl.glBegin(GL2.GL_TRIANGLE_FAN);

		gl.glVertex2f(0, 0);
		gl.glVertex2f(width, -(height-radius));
		gl.glVertex2f(width, (height-radius));
		
		gl.glVertex2f((width-radius), (height-radius));
		gl.glVertex2f((width-radius), height);
		
		gl.glVertex2f(-(width-radius), height);
		gl.glVertex2f(-(width-radius), (height-radius));
		
		gl.glVertex2f(-width, (height-radius));
		gl.glVertex2f(-width, -(height-radius));
		
		gl.glVertex2f(-(width-radius),-(height-radius));
		gl.glVertex2f(-(width-radius), -height);
		
		gl.glVertex2f((width-radius), -height);
		gl.glVertex2f((width-radius), -(height-radius));
		
		gl.glVertex2f(width, -(height-radius));
		gl.glEnd();
		
		
		float start_angle = 0;
		float end_angle = (float) Math.PI/2;
		float[] side_x = new float[] {1,-1,-1, 1};
		float[] side_y = new float[] {1, 1,-1,-1};
		
		for(int i=0;i<4;i++) {
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			
			gl.glVertex2f(side_x[i]*(width-radius), side_y[i]*(height-radius));
			for(float j=start_angle;j<=end_angle;j+=Math.PI/60) {
				gl.glVertex2f( side_x[i]*(width-radius)+(radius*((float)Math.cos(j))),side_y[i]*(height-radius)+(radius*((float)Math.sin(j))));
			}
			gl.glEnd();
			
			
			start_angle+=Math.PI/2;
			end_angle+=Math.PI/2;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			active = active? false:true;
			//System.out.println("yay");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}