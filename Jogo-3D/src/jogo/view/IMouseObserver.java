package jogo.view;

public interface IMouseObserver {
	public boolean conditonIsMet(float pos_x,float pos_y );
	
	public void performAction();

	public int getRank();
}
