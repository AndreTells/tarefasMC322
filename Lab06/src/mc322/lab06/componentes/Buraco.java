package mc322.lab06.componentes;

import mc322.lab06.Caverna;
import mc322.lab06.Sala;

public class Buraco extends Componente{
	public Buraco(int x,int y){
		super(x,y);
		this.icon = 'B';
	}
	
	@Override
	public void addEffects() {
		Sala left = this.sala.getLeft();
		Sala right = this.sala.getRight();
		Sala up = this.sala.getUp();
		Sala down = this.sala.getDown();
		
		Sala adjacent[] = {up,down,left,right};
		int delta_x []= {-1,1,0,0};
		int delta_y []= {0,0,-1,1};
		
		for(int i=0;i<4;i++) {
			if(adjacent[i]!=null) {
				Brisa brisa = new Brisa(this.x+delta_x[i],this.y+delta_y[i]);
				Caverna.connectComponente(brisa);
			}
		}
	}

	@Override
	public void removeEffects() {
		Sala left = this.sala.getLeft();
		Sala right = this.sala.getRight();
		Sala up = this.sala.getUp();
		Sala down = this.sala.getDown();
		
		
		Sala adjacent[] = {up,down,left,right};
		int delta_x []= {-1,1,0,0};
		int delta_y []= {0,0,-1,1};
		
		for(int i=0;i<4;i++) {
			if(adjacent[i]!=null) {
				Brisa brisa = new Brisa(this.x+delta_x[i],this.y+delta_y[i]);
				adjacent[i].removeComponente(brisa);
			}
		}
	}
}
