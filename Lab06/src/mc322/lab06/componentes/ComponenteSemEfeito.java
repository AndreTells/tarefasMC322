package mc322.lab06.componentes;

public abstract class ComponenteSemEfeito extends Componente{

	ComponenteSemEfeito(int x, int y) {
		super(x, y);
	}

	public void addEffects() {}
	public void removeEffects() {}	
}
