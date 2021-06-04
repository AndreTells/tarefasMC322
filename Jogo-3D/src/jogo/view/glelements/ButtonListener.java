package jogo.view.glelements;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonListener implements MouseListener{
	private Button btn;
	private IContainer ui;
	private IActor actor;
	
	public ButtonListener(Button btn,IContainer ui,IActor actor) {
		this.btn = btn;
		this.ui = ui;
		this.actor = actor;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() != e.BUTTON1 || !ui.isActive()) {
			return;
		}
		
		
		int [] screen_dims = ui.getDims();
		int [] pos = btn.getPos(screen_dims[0], screen_dims[1]);
		int [] btn_dims = btn.getDims(screen_dims[0], screen_dims[1]);
		
		int min_x = pos[0]-btn_dims[0];
		int max_x = pos[0]+btn_dims[0];
		
		int min_y = pos[1]-btn_dims[1];
		int max_y = pos[1]+btn_dims[1];
		
		int x = e.getX();
		int y = screen_dims [1] - e.getY();
		//System.out.println("x: "+pos[0]+" y: "+pos[1]);
		//System.out.println(x+" min:"+min_x+" max:"+max_x+" "+y+" min:"+min_y+" max:"+max_y);
		
		if((min_x < x && max_x >x) && (min_y< y && max_y >y)) {
			if(actor!=null) {
				actor.act(e);
			}
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
