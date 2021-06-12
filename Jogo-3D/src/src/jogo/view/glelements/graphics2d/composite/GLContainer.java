package jogo.view.glelements.graphics2d.composite;

import java.util.LinkedList;
import java.util.List;

import jogo.view.glelements.graphics2d.GLElementComponent;
import jogo.view.glelements.graphics2d.GLElementComposite;
import jogo.view.glelements.graphics2d.IComponent2DGraphics;
import jogo.view.glelements.graphics2d.IComposite2DGraphics;
import jogo.view.glelements.graphics2d.ILeaf2DGraphics;

public class GLContainer extends GLElementComposite{
	
	public GLContainer(String id, float width,float height) {
		super(id,null,0,0,width,height);
	}

}
