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

import jogo.controller.CellController;
import jogo.view.customobject.Obj3D;


public class GameView implements GLEventListener {  
  
  
	private GLU glu = new GLU();
	private Camera camera;
	private RayPicker picker;
	private GLCanvas gc;
	private Obj3D obj;
	private UI ui;
	//private float rotation = 0.0f;
	public GameView(JFrame frame){
		final GLProfile gp = GLProfile.get( GLProfile.GL2 );  
	   	GLCapabilities cap = new GLCapabilities(gp );
	   	this.gc = new GLCanvas(cap);
	   	gc.setSize( frame.getWidth(),frame.getHeight());
	   	  
	   	final FPSAnimator animator = new FPSAnimator(gc, 200,true);  
	   	animator.start();
	   	
	   	
		float[] target = new float[] {0,0,0};
		float[] eye = new float[] {0,4,5};
		float[] up = new float[3];
		float [] right = new float[] {1,0,0};
		float [] aux = new float[3]; 
		VectorUtil.scaleVec3(aux, eye, -1);
		VectorUtil.addVec2(aux, aux, target);
		VectorUtil.crossVec3(up, right, aux);
		this.camera = new Camera(eye,target,up);
	   	this.obj = new Obj3D("castle");
	   	this.ui = new UI(14,0.2f,0.01f);
	   	ui.setDims(gc.getWidth(), gc.getHeight());
	   	
	   	gc.addGLEventListener( this );  
	   	
	   	
	   	CameraMover mover = new CameraMover(camera);
		gc.addMouseMotionListener(mover);
		gc.addKeyListener(ui);
	   	ui.setNextTurnListener(gc);
	   	
	   	this.picker = new RayPicker();
		gc.addMouseListener(picker);
		float[] min = obj.getAabbMin();
		float[] max = obj.getAabbMax();
		picker.setAabbMin(min);	
		picker.setAabbMax(max);
		picker.setActor(new CellController(-1,-1));
	   	
	   	frame.add(gc);

	   	frame.setVisible(true);
	}
	
  //glu.gluPerspective( camera.getFOV(), aspect_ratio, 1.0, 40.0 );  
   @Override  
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

	   obj.addGL(gl);
	   gl.glEnd();
	   
	   
	   
	   
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
	   
	   ui.draw(gl, 0.95f);

	   // Making sure we can render 3d again
	   gl.glMatrixMode(GL2.GL_PROJECTION);
	   gl.glPopMatrix();
	   enable3D(gl);
	   gl.glMatrixMode(GL2.GL_MODELVIEW);
	   	
	   gl.glFlush();
   }  
  
   @Override  
   public void dispose( GLAutoDrawable drawable ) {
   }  
  
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
   
   @Override  
   public void init( GLAutoDrawable drawable ) {  
	   final GL2 gl = drawable.getGL().getGL2();
	   gl.glShadeModel( GL2.GL_SMOOTH );
	   gl.glClearColor( 0f, 0f, 0f, 0f );
	   gl.glClearDepth( 1.0f );
	   gl.glDepthFunc( GL2.GL_LEQUAL );//depth so 3D can be used
	   gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
	   gl.glCullFace(GL2.GL_BACK);
	   gl.glEnable( GL2.GL_DEPTH_TEST );
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
  
   @Override  
   public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {  
      
	   final GL2 gl = drawable.getGL().getGL2();  
	   if( height <= 0 )  
		   height = 1;  
              
	   float aspect_ratio = ( float ) width / ( float ) height;  
	   gl.glViewport( 0, 0, width, height );  
	   gl.glMatrixMode( GL2.GL_PROJECTION );  
	   gl.glLoadIdentity();
	   glu.gluPerspective( 45.0f, aspect_ratio, 1.0, 40.0 );  
	   gl.glMatrixMode( GL2.GL_MODELVIEW );  
	   gl.glLoadIdentity();
	   
	   ui.setDims(width, height);
   }
   
  
}