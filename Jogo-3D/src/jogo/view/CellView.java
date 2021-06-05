package jogo.view;

import com.jogamp.opengl.GL2;

import jogo.view.customobject.Obj3D;

public class CellView implements ICellViewController{
	private float pos[];
	private Obj3D obj;
	
	public CellView(float [] pos, Obj3D obj) {
		this.pos = pos;
		this.obj = obj;
	}
	
	protected float[] getAabbMin() {
		float[] obj_aabb_min = obj.getAabbMin();
		return new float[] {obj_aabb_min[0]+pos[0],obj_aabb_min[1]+pos[1],obj_aabb_min[2]+pos[2]};
	}
	
	protected float[] getAabbMax() {
		float[] obj_aabb_max = obj.getAabbMax();
		return new float[] {obj_aabb_max[0]+pos[0],obj_aabb_max[1]+pos[1],obj_aabb_max[2]+pos[2]};
	}
	
	public void setObj(String obj_name) {
		Obj3D obj = GameModels.getModel(obj_name);
		if(obj==null) {
			System.out.println(obj_name);
		}
		this.obj = obj;
	}

	protected void addGL(GL2 gl){
		if(obj == null) {
			return;
		}
		gl.glPushMatrix();
		gl.glTranslatef(pos[0],pos[1],pos[2]);
		obj.addGL(gl);
		gl.glPopMatrix();
	}
}
