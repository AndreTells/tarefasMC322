package jogo.view.glelements;

import com.jogamp.opengl.GL2;

public class RoundedRectangle extends GLElement{
	private float height;
	private float width;
	private float radius;
	
	public RoundedRectangle(float height,float width,float radius,float pos_x, float pos_y) {
		super(pos_x,pos_y);
		this.height = height;
		this.width = width;
		this.radius = radius;
	}
	
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(pos_x, pos_y, 0);
		
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
		
		gl.glPopMatrix();
	}

	public int[] getDims(int container_width,int container_height) {
		return new int[] {(int)((width)*(container_width/2.0f)),(int)((height)*(container_height/2.0f))};
	}
}
