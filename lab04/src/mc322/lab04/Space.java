package mc322.lab04;

public class Space {
	int x;
	int y;
	char icon;
	Space under;
	Space above;
	Space left;
	Space right;
	
	void setRelativePieces(Space above, Space left) {
		this.under = null;
		this.above = above;
		this.left = left;
		this.right = null;
		
		if(right != null) {
			right.left = this;
		}
		if(above != null) {
			above.under = this;
		}
	}

	void copy(Space s) {
		this.x = s.x;
		this.y = s.y;
		this.above = s.above;
		this.under = s.under;
		this.left = s.left;
		this.right = s.right;
	}
}
