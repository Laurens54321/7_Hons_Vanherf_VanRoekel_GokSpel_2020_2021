package view;

import controller.AdminViewController;
import controller.GameController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.panels.AdminMainPane;

public class AdminView {
	AdminViewController adminViewController;

	BorderPane borderPane;
	Group root;
	Scene scene;

	private Stage stage = new Stage();		
		
	public AdminView(AdminViewController adminViewController){
		this.adminViewController = adminViewController;

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

	public void setAdminPane(AdminMainPane adminMainPane){
		borderPane = adminMainPane;
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		System.out.println("this should be last");

	}
}
