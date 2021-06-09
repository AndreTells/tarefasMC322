package jogo.view.glelements.graphics2d.leaf;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import jogo.view.IActor;
import jogo.view.glelements.graphics2d.GL2DObserver;
import jogo.view.glelements.graphics2d.GLElement;
import jogo.view.glelements.graphics2d.IComponent2DGraphics;
import jogo.view.glelements.graphics2d.IComposite2DGraphics;
import jogo.view.glelements.graphics2d.ILeaf2DGraphics;

public class GLLabel extends GLElement implements ILeaf2DGraphics{
	private float pos_x;
	private float pos_y;

	private String id;
	private IComposite2DGraphics parent;
	
	private float width;
	private float height;
	
	private String[] text;
	private float[] color;
	private final float line_spacing = 0.05f;
	private final int font = GLUT.BITMAP_HELVETICA_12;
	private GLUT glut;
	
	private float z_index;
	private boolean center;
	
	public GLLabel(String id,IComposite2DGraphics parent,
			float pos_x,float pos_y,
			String text,float[] color) {
		this.setPosition(pos_x, pos_y);
		
		this.id = id;
		this.parent = parent;
		parent.addChild(this);
		
		this.setColor(color);
		this.text =text.split("\n");
		
		this.glut = new GLUT();
	
		this.z_index = 1;
	}
	
	public void center(boolean center) {
		this.center = center;
	}
	
	public GLLabel(String id,IComposite2DGraphics parent,
			float pos_x,float pos_y,
			String text,float[] color,float z_index) {
		this.setPosition(pos_x, pos_y);
		
		this.id = id;
		this.parent = parent;
		parent.addChild(this);
		
		this.setColor(color);
		this.text =text.split("\n");
		
		this.glut = new GLUT();
		
		this.z_index = z_index;
	}
	
	public void draw(GL2 gl) {
		gl.glColor4f(color[0],color[1],color[2],color[3]);
		float y = this.pos_y+(line_spacing)*(text.length -1);
		for(String sentence: text) {
			gl.glPushMatrix();
			gl.glTranslatef(pos_x, y, 0);
			gl.glRasterPos2f(0, 0);
			
			glut.glutBitmapString(font, sentence);
			gl.glPopMatrix();
			
			y-=line_spacing;
		}
		
	}

	@Override
	public void setPosition(float pos_x, float pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	@Override
	public void setDims(float width,float height) {}

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

	public float[] getPos() {
		return new float[] {pos_x,pos_y};
	}
	
	public float[] getDims() {
		float width = glut.glutBitmapLength(font,"");
		for(String sentence: text) {
			float sentence_width = glut.glutBitmapLength(font,sentence);
			if(width < sentence_width) {
				//System.out.println(sentence_width);
				width = sentence_width;
			}
		}
		float[] top_dims = this.getTop().getDims();
		return new float[] {width/top_dims[0],line_spacing*text.length};
	}

	@Override
	public IComposite2DGraphics getParent() {
		return this.parent;
	}

	@Override
	public void setActionObserver(IActor actor) {
		mouse.addActionObservers(this.id, new GL2DObserver(this,actor,2));
	}

	@Override
	public void removeActionObserver() {
		mouse.removeActionObserver(this.id);
	}
	
	@Override
	public void setColor(float[] color) {
		this.color = color;
	}
	
	public void setText(String text) {
		this.text = text.split("\n");
	}
	
}
