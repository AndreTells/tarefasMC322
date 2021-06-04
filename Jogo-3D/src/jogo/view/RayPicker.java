package jogo.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.math.VectorUtil;

import jogo.view.glelements.IActor;

public class RayPicker implements MouseListener{
	private float[] aabb_min;
	private float[] aabb_max;
	private IActor actor;
	
	private Matrix4 inverse_projection_matrix;
	private Matrix4 inverse_view_matrix;
	private float[] eye;
	//camera
	
	RayPicker(){
		this.eye = new float[]{0,0,0};
	}
	
	public void setAabbMin(float[] aabb_min) {this.aabb_min = aabb_min;}
	public void setAabbMax(float[] aabb_max) {this.aabb_max = aabb_max;}
	public void setActor(IActor actor) {this.actor = actor;}
	public void setInverseProjectionMatrix(Matrix4 inverse_projection_matrix) {this.inverse_projection_matrix = inverse_projection_matrix;}
	public void setInverseViewMatrix(Matrix4 inverse_view_matrix) {this.inverse_view_matrix = inverse_view_matrix;}
	public void setEye(float[] eye) {this.eye = eye;}
	
	@Override
	public void mouseClicked(MouseEvent e) {		
		//System.out.println(e.getY());
		float x = ((2.0f*e.getX())/e.getComponent().getWidth()) - 1.0f;
		float y = 1.0f - (2.0f * e.getY())/e.getComponent().getHeight();
		float z = 1.0f;
		float[] ray_nds = new float[] {x,y,z};
		
		
		float[] ray_clip = new float[] {ray_nds[0],ray_nds[1],-1.0f,1.0f};
		
		
		float[] ray_eye = new float[4];
		inverse_projection_matrix.multVec(ray_clip, ray_eye);
		ray_eye[2] = -1.0f;
		ray_eye[3] =  0.0f;
		
		
		float[] ray_wor_4d = new float[4];
		inverse_view_matrix.multVec(ray_eye, ray_wor_4d);
		float[] ray_wor = new float[3];
		for(int i=0;i<3;i++) {
			ray_wor[i] = ray_wor_4d[i];
		}
		ray_wor = VectorUtil.normalizeVec3(ray_wor);
		//System.out.println(ray_wor[0]+" | "+ray_wor[1]+" | "+ray_wor[2]);
		rayCast(ray_wor,e);
	}
	
	private void rayCast(float[] ray_dir,MouseEvent e) {
		float[] current = new float[3];
		for(int i =0;i<3;i++) {
			current[i] = eye[i];
		}
		float step_size = 0.1f;
		float[] step = new float[3];
		VectorUtil.scaleVec3(step, ray_dir, step_size);
		float dist = 10;
		float i = 0;
		while(i<dist) {
			if((aabb_min[0] < current[0]  && current[0]< aabb_max[0]) && (aabb_min[1] < current[1]  && current[1] < aabb_max[1]) && (aabb_min[2] < current[2]  && current[2] < aabb_max[2])) {
				//System.out.println(current[0]+" "+current[1]+" "+current[2]);
				actor.act(e);
				break;
			}
			i+=step_size;
			VectorUtil.addVec3(current, current, step);
		}
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
