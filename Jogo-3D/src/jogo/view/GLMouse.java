package jogo.view;

import com.jogamp.opengl.awt.GLCanvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Enumeration;
import java.util.Dictionary;
import java.util.Hashtable;

public class GLMouse implements MouseListener,MouseMotionListener{

	private Dictionary<String,IMouseObserver> dic_action_observers;
	private Dictionary<String,IMouseObserver> dic_motion_observers;
	
	//implement try catch for this
	public GLMouse(GLCanvas gc) {
		this.dic_action_observers = new Hashtable<String,IMouseObserver>();
		this.dic_motion_observers = new Hashtable<String,IMouseObserver>();
		gc.addMouseListener(this);
		gc.addMouseListener(this);
	}
	
	//do add method for action and motion observers
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		float formated_x = ((2.0f*e.getX())/e.getComponent().getWidth()) - 1.0f;
		float formated_y = 1.0f - (2.0f * e.getY())/e.getComponent().getHeight();
		
		
		Enumeration<IMouseObserver> observers = dic_action_observers.elements();
		IMouseObserver selected_observer = null;
		int selected_observer_rank = 0;
		
		while(observers.hasMoreElements()) {
			IMouseObserver observer = observers.nextElement();
			if(observer.conditonIsMet(formated_x,formated_y)) {
				int rank = observer.getRank();
				
				if(rank>selected_observer_rank) {
					selected_observer = observer;
					selected_observer_rank = observer.getRank();
				}
				else if(rank == selected_observer_rank) {
					//throw exception
				}
			}
		}
		
		if(selected_observer!= null) {
			selected_observer.performAction();
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
