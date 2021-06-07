package jogo.view;

import java.nio.FloatBuffer;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.math.VectorUtil;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import jogo.controller.CellController;
import jogo.controller.NextTurnController;
import jogo.view.glelements.GLMouse;
import jogo.view.glelements.graphics2d.GLElement;
import jogo.view.glelements.graphics2d.composite.GLUI;
import jogo.view.glelements.graphics2d.leaf.GLLabel;
import jogo.view.glelements.graphics2d.leaf.GLRectangle;
import jogo.view.glelements.graphics3d.Camera;
import jogo.view.glelements.graphics3d.CameraMover;
import jogo.view.glelements.graphics3d.RayPicker;

public class TEST implements GLEventListener,IViewBuilder{
	private GLU glu = new GLU();
	private GLCanvas gc;
	
	private Camera camera;
	private RayPicker picker;
	
	private CellView[][] cells;
	private final int map_size = 10;
	private GLUI ui;
	
	public TEST(JFrame frame){
		GameModels.loadModels();
		
		this.setUpCanvas(frame.getWidth(), frame.getHeight());
		
	   	this.setUpCamera();
		
	   setUpCellMatrix();
	   
	   GLMouse mouse = new GLMouse(gc);
	   GLElement.setMouse(mouse);
	   
	   this.ui = new GLUI("ui",frame.getHeight(),frame.getWidth());
	      
	   GLLabel l = new GLLabel("l",this.ui,0.99f,0,"yay",new float[] {1,1,1,1});
	   GLLabel b = new GLLabel("b",this.ui,-1f,0,"yay1\nbbbb",new float[] {0,0,0,1});
	   b.setActionObserver(new TestActor());

	   GLRectangle rect = new GLRectangle("rect",ui,
			   								0,0,
			   								0.1f,0.1f,
			   								0.02f,new float[] {1,1,1,1});
	   
	   gc.addGLEventListener( this );  
	   	
	   CameraMover mover = new CameraMover(camera);
	   gc.addMouseMotionListener(mover);
		
	   
	   this.setUpPicker();
	   
	   gc.addMouseListener(picker);
	   
	   frame.add(gc);
	   frame.setVisible(true);
	}
	
	private void setUpCamera() {
		float[] target = new float[] {0,0,0};
		float[] eye = new float[] {0,20,30};
		float[] up = new float[3];
		float [] right = new float[] {1,0,0};
		float [] aux = new float[3]; 
		VectorUtil.scaleVec3(aux, eye, -1);
		VectorUtil.addVec2(aux, aux, target);
		VectorUtil.crossVec3(up, right, aux);
		this.camera = new Camera(eye,target,up);
	}
	
	private void setUpCanvas(int width, int height) {
		final GLProfile gp = GLProfile.get( GLProfile.GL2 );  
	   	GLCapabilities cap = new GLCapabilities(gp );
	   	this.gc = new GLCanvas(cap);
	   	gc.setSize( width,height);
	   	  
	   	final FPSAnimator animator = new FPSAnimator(gc, 200,true);  
	   	animator.start();
	}
	
	private void setUpPicker() {
		this.picker = new RayPicker(map_size);
	}
	
	public void setCellListener(int i,int j,CellController controller) {
		float[] min = this.cells[i][j].getAabbMin();
		float[] max = this.cells[i][j].getAabbMax();
		picker.addClickableObject(i,j,min, max, controller);
	}
	
	private void setUpCellMatrix() {
		this.cells = new CellView[10][10];
		
		float dist = 4.0f;
		
		for(int i=0;i<map_size;i++) {
			for(int j=0;j<map_size;j++) {
				
				this.cells[i][j] = new CellView(new float[] {i*dist,0,j*dist}, GameModels.getModel("Forest"));
			}
		}
		
	}
	
	public void display( GLAutoDrawable drawable ) {
		
	   final GL2 gl = drawable.getGL().getGL2();
	   gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
	   gl.glLoadIdentity();
	   gl.glClearColor(0.08f, 0.13f, 0.17f, 0.5f);
	   //draw 3d objects
	   float[] eye = camera.getEye();
	   float[] target = camera.getTarget();
	   float[] up = camera.getUp();
	   
	   gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, FloatBuffer.wrap(new float[] {0+eye[0], 4f+eye[1], 3+eye[2],1}));
	   glu.gluLookAt(eye[0], eye[1], eye[2], target[0], target[1], target[2], up[0], up[1], up[2]);

	   for(int i=0;i<map_size;i++) {
			for(int j=0;j<map_size;j++) {
				cells[i][j].addGL(gl);
			}
	   }
	   
	   
	   //setting picker
	   Matrix4 i_view_matrix = getMatrix(GL2.GL_MODELVIEW_MATRIX,gl); 
	   Matrix4 i_projection_matrix = getMatrix(GL2.GL_PROJECTION_MATRIX,gl);
	   i_view_matrix.invert();
	   i_projection_matrix.invert();
	   
	   picker.setEye(eye);
	   picker.setInverseProjectionMatrix(i_projection_matrix);
	   picker.setInverseViewMatrix(i_view_matrix);
	   
	   //set up for 2d objects
	   gl.glMatrixMode(GL2.GL_PROJECTION);
	   gl.glPushMatrix();
	   gl.glLoadIdentity();
	   gl.glMatrixMode(GL2.GL_MODELVIEW);
	   gl.glLoadIdentity();
	   disable3D(gl);
	   gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);

	   //draw 2d objects
	   
	   ui.draw(gl);
	  

	   // Making sure we can render 3d again
	   gl.glMatrixMode(GL2.GL_PROJECTION);
	   gl.glPopMatrix();
	   enable3D(gl);
	   gl.glMatrixMode(GL2.GL_MODELVIEW);
	   	
	   gl.glFlush();
   }  

	public void dispose( GLAutoDrawable drawable ) {}  

	private void enable3D(GL2 gl) {
		gl.glEnable(GL2.GL_CULL_FACE);//not loading stuff that cant be seen
		gl.glEnable(GL2.GL_LIGHTING);//lighting
		gl.glEnable(GL2.GL_LIGHT0);
	}
	
	private void disable3D(GL2 gl) {
		gl.glDisable(GL2.GL_CULL_FACE);//not loading stuff that cant be seen
		gl.glDisable(GL2.GL_LIGHTING);//lighting
		gl.glDisable(GL2.GL_LIGHT0);
	}
	
	public void init( GLAutoDrawable drawable ) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glShadeModel( GL2.GL_SMOOTH );
		gl.glClearColor( 0f, 0f, 0f, 0f );
		gl.glClearDepth( 1.0f );
		gl.glDepthFunc( GL2.GL_LEQUAL );//depth so 3D can be used
		gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
		gl.glCullFace(GL2.GL_BACK);gl.glEnable( GL2.GL_DEPTH_TEST );
		gl.glEnable(GL2.GL_FRAMEBUFFER_SRGB);//basic gamma correction
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc (GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		enable3D(gl);
	}
	
	public Matrix4 getMatrix(int code,GL2 gl) {
		float[] f_matrix = new float[16];
		gl.glGetFloatv( code, f_matrix,0);
		Matrix4 matrix = new Matrix4();
		matrix.loadIdentity();
		matrix.multMatrix(f_matrix);
		return matrix;
	}
	
	public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {  
		final GL2 gl = drawable.getGL().getGL2();  
		if( height <= 0 )
			height = 1;  
              
		float aspect_ratio = ( float ) width / ( float ) height;  
		gl.glViewport( 0, 0, width, height );  
		gl.glMatrixMode( GL2.GL_PROJECTION );  
		gl.glLoadIdentity();
		glu.gluPerspective( 45.0f, aspect_ratio, 1.0, 60.0 );  
		gl.glMatrixMode( GL2.GL_MODELVIEW );  
		gl.glLoadIdentity();
	   
		ui.setDims(width, height);
   }

	//----------- setter methods
	
	public void setPopulation(String population) {
		//ui.setPopulation("population: "+population);
	}
	
	public void setProduction(String production) {
		//ui.setProduction("production: "+production);
	}
	
	public void setFood(String food) {
		//ui.setFood("food: "+food);
	}
	
	public void setNextTurnController(NextTurnController controller) {
		//ui.setNextTurnListener(gc,controller);
	}
	
	public ICellViewController getCell(int i,int j) {
			//String new_obj
		return this.cells[i][j];
	}

	@Override
	public void updateDetectionBox(int i, int j) {
		float[] min = this.cells[i][j].getAabbMin();
		float[] max = this.cells[i][j].getAabbMax();
		this.picker.setBoundsBox(i, j, min, max);
	
	
	}

	public void setInfo(String info_text) {
		//ui.setInfo("cell info: \n"+info_text);
	}

	public void createSubMenu(int pos_x, int pos_y,String[] items,IActor actor,IActor[] menu_item_actors) {
		/*
		SubMenu menu = ui.createSubMenu( (((float)pos_x*2.0f)/gc.getWidth()) -1.0f, (((float)pos_y*2.0f)/gc.getHeight()) -1.0f, items);
		SubMenuListener menu_listener = new SubMenuListener(actor,menu,ui);
		Button[] menu_item = menu.getMenuItems();
		for(int i=0;i<menu_item.length;i++) {
			gc.addMouseListener(new ButtonListener(menu_item[i],ui,menu_item_actors[i]));
		}
		gc.addMouseListener(menu_listener);*/
	}

	public void closeSubMenu() {
		//ui.closeSubMenu();
	}
}
