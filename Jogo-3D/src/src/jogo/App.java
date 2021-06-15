package jogo;

import jogo.view.boardview3d.BoardViewManager;
import jogo.view.mouse.GLMouse;
import jogo.view.screen.GameFrame;
import jogo.view.screen.ScreenManager;
import jogo.view.ui.UIManager;
import jogo.view.ui.leaf.GLRectangle;
import jogo.controller.Builder;
import jogo.model.boardmodel.BoardManager;
import jogo.model.boardmodel.BoardModel;
import jogo.model.events.EventManager;

public class App {
	public static void main( String[] args ) {
		
		Builder builder = new Builder();
		builder.buildGame(
				new BoardManager(),
				new ScreenManager(),
				new UIManager(),
				new GLMouse(),
				new BoardViewManager(),
				new EventManager());
				
				
				
				
				//new BoardManager(),
				//new ScreenManager(),
				//new UIManager(),
				//new GLMouse(),
				//new BoardViewManager()
				//);

	}
}
