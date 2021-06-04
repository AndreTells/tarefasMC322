package jogo.view.customobject;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4;


public class Obj3D {
	private List<Face3D> faces;
	private Vec3D aabb_min;
	private Vec3D aabb_max;
	private String name;
	private String path;
	
	public Obj3D(String name) {
		this.faces = new LinkedList<Face3D>();
		this.name = name;
		this.path =  System.getProperty("user.dir") + "\\data\\";
		getObj();
	}
	
	private List<Vec3D> getVectors(BufferedReader file){
		try {
			float x_min = Float.MAX_VALUE;
			float y_min = Float.MAX_VALUE;
			float z_min = Float.MAX_VALUE;
			
			float x_max = Float.MIN_VALUE;
			float y_max = Float.MIN_VALUE;
			float z_max = Float.MIN_VALUE;
			List<Vec3D> vectors =  new LinkedList<Vec3D>();
			String line = file.readLine();
			while(!line.equals("")) {
				String vertex_s[] = line.split(" ");
				Vec3D vector =new Vec3D(Float.parseFloat(vertex_s[1]),Float.parseFloat(vertex_s[2]),Float.parseFloat(vertex_s[3])); 
				vectors.add(vector);
				if(vector.x < x_min) {
					x_min = vector.x;
				}
				else if(vector.x >x_max) {
					x_max = vector.x;
				}
				
				if(vector.y < y_min) {
					y_min = vector.y;
				}
				else if(vector.y >y_max) {
					y_max = vector.y;
				}
				
				if(vector.z < z_min) {
					z_min = vector.z;
				}
				else if(vector.z >z_max) {
					z_max = vector.z;
				}
				line = file.readLine();
			}
			this.aabb_max = new Vec3D(x_max,y_max,z_max);
			this.aabb_min = new Vec3D(x_min,y_min,z_min);
			
			return vectors;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private List<Color> getColors(BufferedReader file){
		try {
			List<Color> colors =  new LinkedList<Color>();
			BufferedImage image = ImageIO.read(new File(path+name+".png"));
			int width = image.getWidth();
			
			String line = file.readLine();
			while(!line.equals("")) {
				String color_s[] = line.split(" ");
				int color_int = image.getRGB((int)(roundToHalf((image.getWidth()-1)*Float.parseFloat(color_s[1]))),0);
				int  red = (color_int & 0x00ff0000) >> 16;
		   		int  green = (color_int & 0x0000ff00) >> 8;
		   		int  blue = color_int & 0x000000ff;
		   		colors.add(new Color(red,green,blue));
				line = file.readLine();
			}
			return colors;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private void getFaces(List<Vec3D> vertices,List<Vec3D> normals, List<Color> colors,BufferedReader file) {
		try {
			String line = file.readLine();
			while(line!=null) {
				String faces_s[] = line.split(" ");
				Vec3D[] face_vertices = new Vec3D[3];
				int color_index = 0;
				int normal_index = 0;
				for(int i=1;i<4;i++) {
					String vertex_s[] = faces_s[i].split("/");//regex reduces 4 backslashes into 2 and java reduces 2 backslashes into 1, wich is what the string checks
					//System.out.println(vertex_s[0]+" "+vertex_s[1]+" "+vertex_s[2]);
					face_vertices[i-1] = vertices.get((Integer.parseInt(vertex_s[0]) - 1));
					color_index = Integer.parseInt(vertex_s[1]);
					normal_index = Integer.parseInt(vertex_s[2]);
				}
				Color color = colors.get(color_index-1);
				Vec3D normal = normals.get(normal_index-1);
				//System.out.println(color);
				//System.out.println(color.getRed()/256f+" "+color.getGreen()/256f+" "+color.getBlue()/256f);
				faces.add(new Face3D(face_vertices[0],face_vertices[1],face_vertices[2],color,normal));
				
				line = file.readLine();
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getObj() {
		try {
			BufferedReader file = new BufferedReader(new FileReader(path+name+".obj"));
			String line = file.readLine();
			//jumping the header of the file
			while(!line.equals("# normals")) {
				line = file.readLine();
			}
			List<Vec3D> normals = getVectors(file);//#normals
			line = file.readLine();//skip an empty line
			List<Color> colors = getColors(file);//#texcoords
			line = file.readLine();//skip an empty line
			List<Vec3D> vertices = getVectors(file);//#verts
			line = file.readLine();//skip an empty line
			getFaces(vertices,normals,colors,file);//#faces;
			
			
			file.close();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}
	
	private double roundToHalf(double d) {
	    return Math.round(d * 2) / 2.0;
	}
      

	public void addGL(GL2 gl) {
		for(Face3D face:faces) {
			face.addGL(gl);
		}
	}
	
	public void printFaces() {
		for(Face3D face: faces) {
			System.out.println(face);
		}
	}
	
	public float[] getAabbMin() {
		return new float[] {aabb_min.x,aabb_min.y,aabb_min.z};
	}
	
	public float[] getAabbMax() {
		return new float[] {aabb_max.x,aabb_max.y,aabb_max.z};
	}
}
