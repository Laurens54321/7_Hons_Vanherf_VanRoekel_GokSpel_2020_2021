package view;

import controller.GameController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.database.PlayerDB;

public class AdminView {
	BorderPane borderPane;
	Group root;
	Scene scene;

	private Stage stage = new Stage();		
		
	public AdminView(){
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(630);
		stage.setY(20);
		root = new Group();
		scene = new Scene(root, 600, 600);

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void setBorderPane(GameController gameController){
		borderPane = new AdminMainPane(gameController);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
	}
}
