package mc322.lab04;

public class Piece extends Space{	
	Piece(int x, int y){
		this.x =x;
		this.y =y;
		this.icon = 'P';
	}

	Piece move(Hole h) {
		Space transfer = new Space();
		transfer.copy(this);
		
		this.copy(h);
		h.copy(transfer);
		
		return this;
	}
}
