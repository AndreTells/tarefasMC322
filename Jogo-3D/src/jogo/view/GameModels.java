package jogo.view;

import java.util.Dictionary;
import java.util.Hashtable;

import jogo.view.glelements.graphics3d.customobject.Obj3D;

public class GameModels {
	private static Dictionary<String,Obj3D> models;
	public static void loadModels() {
		models = new Hashtable<String,Obj3D>();
		
		models.put("City", new Obj3D("City_1"));
		models.put("Forest", new Obj3D("Forest_1"));
		models.put("Mountain",  new Obj3D("Mountain_1"));
		models.put("Water",  new Obj3D("Water_1"));
		models.put("Grass",  new Obj3D("Grass_1"));
		models.put("LumberMill", new Obj3D("LumberMill_1"));
	}
	
	public static Obj3D getModel(String model) {
		return models.get(model);
	}
}
	