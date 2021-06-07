package jogo.view.glelements.graphics3d;

import com.jogamp.opengl.math.VectorUtil;

public class Camera {
	private float[] eye;
	private float[] target;
	private float[] up;

	
	public Camera(float[] eye, float[] target, float[] up) {
		this.eye = eye;
		this.target = target;
		this.up = up;
	}
	
	public float[] getEye() {
		return eye;
	}

	public float[] getTarget() {return target;}
	
	public float[] getUp() {return up;}
	
	public void move(float[] delta) {
		VectorUtil.addVec3(eye, delta, eye);
		VectorUtil.addVec3(target, delta, target);
	}

}