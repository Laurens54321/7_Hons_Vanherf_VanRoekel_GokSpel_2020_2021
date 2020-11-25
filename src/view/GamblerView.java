package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GamblerView {
	private Stage stage = new Stage();
	TextField userIdField;
	TextField betText;
	Label betLabel;
	Label userIdLabel;
	Button gameButton;
		
	public GamblerView(){
		stage.setTitle("GAMBLER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		VBox login = new VBox();
		HBox userId = new HBox();
		HBox bet = new HBox();

		Scene scene = new Scene(root, 600, 600);

		userIdField = new TextField();
		userIdLabel = new Label();
		userIdLabel.setText("Wat is je spelernaam?");

		betLabel = new Label();
		betLabel.setText("Wat is je inzet?");
		betText = new TextField();

		gameButton = new Button();
		gameButton.setText("Start gokspel");

		userId.getChildren().addAll(userIdLabel,userIdField); //je goksaldo label met var
		userId.setSpacing(10);

		bet.getChildren().addAll(betLabel,betText);
		bet.setSpacing(30);

		login.setPadding(new Insets(5));
		login.getChildren().addAll(userId,bet,gameButton);
		login.setAlignment(Pos.TOP_LEFT);
		login.setSpacing(10);

		root.getChildren().addAll(login);

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();




	}
}
