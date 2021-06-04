package jogo.view.customobject;

import java.awt.Color;
import java.nio.FloatBuffer;

import com.jogamp.opengl.GL2;

public class Face3D {
	private Vec3D vec_1;
	private Vec3D vec_2;
	private Vec3D vec_3;
	private Color color;
	private Vec3D normal;
	public Face3D(Vec3D vec_1,Vec3D vec_2,Vec3D vec_3,Color color,Vec3D normal) {
		this.vec_1 = vec_1;
		this.vec_2 = vec_2;
		this.vec_3 = vec_3;
		this.color = color;
		this.normal = normal;
	}
	public void addGL(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLES);
	//	FloatBuffer buff_color = FloatBuffer.wrap(new float[] {color.getRed()/256f, color.getBlue()/256f, color.getBlue()/256f}); creates a float buffer
		gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, FloatBuffer.wrap(new float[] {color.getRed()/256f, color.getBlue()/256f, color.getBlue()/256f,1f}) );
		gl.glNormal3f(normal.x, normal.y, normal.z);
		vec_1.addGL(gl);
		vec_2.addGL(gl);
		vec_3.addGL(gl);
	}
	
	public String toString() {
		return "f "+vec_1+" | "+vec_2+" | "+vec_3;
	}
}
