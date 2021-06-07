package jogo.view.glelements.graphics2d;

import jogo.view.glelements.GLMouse;

public abstract class GLElement implements IComponent2DGraphics{
	private static GLMouse mouse;

	public static GLMouse getMouse() {
		return mouse;
	}
	
	public static void setMouse(GLMouse mouse_i) {
		mouse = mouse_i;
	}

}

