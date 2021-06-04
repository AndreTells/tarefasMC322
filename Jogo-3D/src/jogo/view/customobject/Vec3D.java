package jogo.view.customobject;

import com.jogamp.opengl.GL2;

public class Vec3D {
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vec3D(float x,float y,float z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1.0f;
	}
	
	public Vec3D(float x,float y,float z,float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	
	public void rotateY(float theta) {
		this.x =  this.x*((float)Math.cos(theta)) - this.z*((float)Math.sin(theta));
		this.z =  this.x*((float)Math.sin(theta)) + this.z*((float)Math.cos(theta));
	}
	
	public Vec3D multiply(float value) {
		return new Vec3D(x*value,y*value,z*value);
	}

	public Vec3D multiply(float value[]) {
		float new_point [] = new float[4];
		for(int i=0;i<15;i+=4) {
			new_point[i/4] = value[i]*x + value[i+1]*y + value[i+2]*z + value[i+3]*w;
		}
		
		return new Vec3D(new_point[0],new_point[1],new_point[2],new_point[3]);
	}
	
	
	public Vec3D add(Vec3D vec) {
		return new Vec3D(this.x+vec.x,this.y+vec.y,this.z+vec.z);
	}
	
	public void translate(float delta_x,float delta_y,float delta_z) {
		this.x+=delta_x;
		this.y+=delta_y;
		this.z+=delta_z;
	}
	
 	public Vec3D crossProduct(Vec3D vec) {
		float new_x = (this.y*vec.z)-(this.z*vec.y);
		float new_y = (this.z*vec.x)-(this.x*vec.z);
		float new_z = (this.x*vec.y)-(this.y*vec.x);
		return new Vec3D(new_x,new_y,new_z);
	}
 	
 	public float innerProduct(Vec3D vec) {
 		return x*vec.x + y*vec.y + z*vec.z;
 	}
 	
	public Vec3D normalizedVector() {
		float magnitude = getMagnitude();
		return new Vec3D(x/magnitude,y/magnitude,z/magnitude);
	}
	
	public float getMagnitude() {
		return (float) Math.sqrt(x*x + y*y +z*z);
	}
	
	public void addGL(GL2 gl) {
		gl.glVertex3f(x, y, z);
	}
	
	public String toString() {
		return "v "+x+" "+y+" "+z;
	}
	
	public float getTheta() {
		return (float) Math.acos(z/x);
	}
	
	public float getPhi() {
		return (float) Math.atan(Math.sqrt(x*x + z*z)/y);
	}


}
