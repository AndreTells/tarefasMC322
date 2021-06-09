package jogo.view.glelements.graphics2d;

import jogo.view.glelements.GLMouse;

public abstract class GLElement implements IComponent2DGraphics{
	protected static GLMouse mouse;

	public static void setMouse(GLMouse mouse_i) {
		mouse = mouse_i;
	}

}

