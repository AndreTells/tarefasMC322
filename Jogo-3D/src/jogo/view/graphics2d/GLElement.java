package jogo.view.graphics2d;

import jogo.view.GLMouse;

public abstract class GLElement implements IComponent2DGraphics{
	private static GLMouse mouse;

	public GLMouse getMouse() {
		return mouse;
	}
	
	public void setMouse(GLMouse mouse) {
		this.mouse = mouse;
	}

}

