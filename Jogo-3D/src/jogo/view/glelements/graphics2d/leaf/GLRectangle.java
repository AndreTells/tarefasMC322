package jogo.view.glelements.graphics2d.leaf;

import com.jogamp.opengl.GL2;

import jogo.view.IActor;
import jogo.view.glelements.graphics2d.GL2DObserver;
import jogo.view.glelements.graphics2d.GLElement;
import jogo.view.glelements.graphics2d.IComposite2DGraphics;
import jogo.view.glelements.graphics2d.ILeaf2DGraphics;

public class GLRectangle extends GLElement implements ILeaf2DGraphics{
	private float pos_x;
	private float pos_y;

	private String id;
	private IComposite2DGraphics parent;
	
	private float width;
	private float height;
	
	private float[] color;
	private float border_radius;
	
	//trhow exception in case radius is bigger than width or height
	public GLRectangle(String id,IComposite2DGraphics parent,float pos_x,float pos_y,float width,float height,float border_radius,float[] color) {
		this.id = id;
		this.parent = parent;
		parent.addChild(this);
		
		this.setPosition(pos_x, pos_y);
		this.setDims(height, width);
		
		this.border_radius = border_radius;
		this.setColor(color);
		
	}
	
	@Override
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(pos_x, pos_y, 0);
		gl.glColor4f(color[0], color[1], color[2], color[3]);
		
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		
		gl.glVertex2f(width/2.0f, height/2.0f);
		
		gl.glVertex2f(width, border_radius);
		gl.glVertex2f(width, height-border_radius);
		
		gl.glVertex2f((width-border_radius), (height-border_radius));
		gl.glVertex2f((width-border_radius), height);
		
		gl.glVertex2f(border_radius, height);
		gl.glVertex2f(border_radius, (height-border_radius));
		
		gl.glVertex2f(0, (height-border_radius));
		gl.glVertex2f(0, border_radius);
		
		gl.glVertex2f(border_radius,border_radius);
		gl.glVertex2f(border_radius, 0);
		
		gl.glVertex2f((width-border_radius), 0);
		gl.glVertex2f((width-border_radius), border_radius);
		
		gl.glVertex2f(width, border_radius);
		
		gl.glEnd();
		double start_angle = 0;
		double end_angle = (float) Math.PI/2;
		float[] side_x = new float[] {
				(width-border_radius),
				border_radius,
				border_radius,
				(width-border_radius)};
		float[] side_y = new float[] {
				(height-border_radius),
				(height-border_radius),
				border_radius,
				border_radius};
		
		for(int i=0;i<4;i++) {
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			
			gl.glVertex2f(side_x[i], side_y[i]);
			for(double j=start_angle;j<=end_angle;j+=Math.PI/60) {
				gl.glVertex2f(side_x[i]+(border_radius*((float)Math.cos(j))),
							side_y[i]+(border_radius*((float)Math.sin(j))));
			}
			gl.glEnd();
			
			
			start_angle+=Math.PI/2;
			end_angle+=Math.PI/2;
		}
		
		gl.glPopMatrix();
	}

	@Override
	public void setPosition(float pos_x, float pos_y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDims(float width,float height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void dispose() {
		this.removeActionObserver();
		if(parent!=null) {
			parent.removeChild(this.getID());
		}
	}

	@Override
	public float[] getPos() {
		return new float[] {pos_x,pos_y};
	}

	@Override
	public float[] getDims() {
		return new float[] {width,height};
	}

	@Override
	public IComposite2DGraphics getParent() {
		return this.parent;
	}

	@Override
	public void setActionObserver(IActor actor) {
		this.getMouse().addActionObservers(this.id, new GL2DObserver(this,actor,2));
	}

	@Override
	public void removeActionObserver() {
		this.getMouse().removeActionObserver(this.id);
	}

	@Override
	public void setColor(float[] color) {
		this.color = color;
	}

}
