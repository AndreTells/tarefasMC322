package jogo.controller.gamecontroller;

import java.awt.event.MouseEvent;

import jogo.model.board.IBoardController;
import jogo.model.board.IPlayerController;
import jogo.model.events.IEventManager;
import jogo.view.board3d.IBoard3DManager;
import jogo.view.mouse.IActor;
import jogo.view.ui.IPopUpMenu;
import jogo.view.ui.IStats;

public class TurnController implements IActor{
	private IEventManager event_manager;
	protected IPlayerController player;
	protected IBoardController board;
	protected IStats stats_view;
	protected IBoard3DManager board_view_manager;
	
	
	public TurnController(IPlayerController player,IBoardController board,IStats stats_view,IBoard3DManager board_view_manager,IEventManager event_manager) {
		this.event_manager = event_manager;
		this.player = player;
		this.board = board;
		this.stats_view = stats_view;
		this.board_view_manager = board_view_manager;
	}
	
	public void act(MouseEvent e) {
		
		if(!gameOver()) {

			int [] modifier = board.getModifier();
			player.addModifier(modifier);
			
			if(gameOver()) {
				stats_view.setInfo("GAME OVER");
			}
			else {
				
				String description = event_manager.ExecuteRandomEvent(board);
				IPopUpMenu menu = stats_view.createSubMenu("_event-popup",-0.99f,0.8f,description,new String[]{"ok"});
				
				EventPopUpController pop_up_controller = new EventPopUpController(this);
				menu.setActionObservers(new IActor[] {pop_up_controller});
				
				updateStats();	
			}
			
		}
	}
	
	public boolean gameOver() {
		int population = player.getPopulationValue();
		int population_limit = player.getPopulationLimitValue();
		int [] modifier = board.getModifier();
		
		if(population > population_limit || population == 0) {
			return true;
		}
		if(modifier[0]<=0) {
			return true;
		}
		return false;
	}
	
	public void updataMap() {
		int map_length = board.getMapLength();
		int map_height = board.getMapHeight();
		
		for(int i=0;i<map_length;i++) {
			for(int j =0;j<map_height;j++) {
				board_view_manager.getCell(i, j).setObj(board.getCellHighestComponents(i, j));
			}
		}
	}
	
	public void updateStats() {
		int[] modifier =board.getModifier();
		stats_view.setPopulation("population: "+player.getPopulation()+(modifier[0]>=0?"+"+modifier[0]:modifier[0]));
		stats_view.setProduction("production: "+player.getProduction()+(modifier[1]>=0?"+"+modifier[1]:modifier[1]));
		stats_view.setFood("food: "+player.getFood()+(modifier[2]>=0?"+"+modifier[2]:modifier[2]));
	}

	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
		}
	}
	
}
