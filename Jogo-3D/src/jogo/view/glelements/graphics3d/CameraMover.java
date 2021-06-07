package jogo.view.glelements.graphics3d;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CameraMover implements MouseMotionListener {
	private Camera camera;
	private float old_mouse_x;
	private float old_mouse_y;
	
	public CameraMover(Camera camera) {
		this.camera = camera;
	}

	public void mouseMoved(MouseEvent e) {
		this.old_mouse_x = e.getX();
		this.old_mouse_y = e.getY();
	}
	public void mouseDragged(MouseEvent e) {
		camera.move( new float[] {(old_mouse_x-e.getX())/50 , 0,  (old_mouse_y-e.getY())/50});
		this.old_mouse_x = e.getX();
		this.old_mouse_y = e.getY();
	}

	    
	
}