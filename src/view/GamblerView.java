package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;	

public class GamblerView {
	private Stage stage = new Stage();		
		
	public GamblerView(){			
		stage.setTitle("GAMBLER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
