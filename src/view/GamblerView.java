package view;

import controller.GamblerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Player;

import javax.xml.soap.Text;

public class GamblerView {
	GamblerController gamblerController;

	private Stage stage = new Stage();
	Group root;

	Label moneyStatusLabel;

	Button gameButton;
		
	public GamblerView(GamblerController gamblerController){
		this.gamblerController = gamblerController;
		stage.setTitle("GAMBLER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		root = new Group();

		Scene scene = new Scene(root, 600, 600);

		VBox login = new VBox();
		HBox userId = new HBox();
		HBox bet = new HBox();

		VBox strategy = new VBox();
		Label strategyTitle = new Label();
		strategyTitle.setText("Kies je gok strategie uit onderstaande lijst");
		ToggleGroup radioGroup = new ToggleGroup();

		RadioButton rb1 = new RadioButton("het aantal ogen bij elke worp is een even getal");
		rb1.setToggleGroup(radioGroup);
		rb1.setSelected(true);

		RadioButton rb2 = new RadioButton("de som van de ogen van alle worpen samen is 21");
		rb2.setToggleGroup(radioGroup);

		RadioButton rb3 = new RadioButton("het aantal ogen is bij elke worp hoger dan bij de vorige worp");
		rb3.setToggleGroup(radioGroup);

		Button confirmChoice = new Button();

		TextField userIdField = new TextField();
		userIdField.setOnAction(e -> login(userIdField.getText()));
		Label userIdLabel = new Label();
		userIdLabel.setText("Wat is je spelernaam?");

		moneyStatusLabel = new Label();
		if (gamblerController.getActivePlayer() != null){
			moneyStatusLabel.setText("Je goksaldo is " + gamblerController.getActivePlayer().getMoney() + " €");
		}

		Label betLabel = new Label();
		betLabel.setText("Wat is je inzet?");
		TextField betText = new TextField();

		gameButton = new Button();
		if (gamblerController.getActivePlayer() == null) gameButton.setDisable(true);
		else gameButton.setDisable(false);

		gameButton.setText("Start gokspel");
		gameButton.setOnAction(e -> startGame());

		userId.getChildren().addAll(userIdLabel,userIdField, moneyStatusLabel); //je goksaldo label met var
		userId.setSpacing(10);

		bet.getChildren().addAll(betLabel,betText);
		bet.setSpacing(30);

		login.setPadding(new Insets(5));
		login.getChildren().addAll(userId,bet,gameButton);
		login.setAlignment(Pos.TOP_LEFT);
		login.setSpacing(10);

		strategy.setPadding(new Insets(5));
		strategy.getChildren().addAll(strategyTitle,radioGroup,confirmChoice);

		root.getChildren().addAll(login,strategy);

		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void startGame(){

	}

	public void updateMoneyStatusLabel(){
		moneyStatusLabel.setText("Je goksaldo is " + gamblerController.getActivePlayer().getMoney() + " €");
	}

	public void login(String userid){
		gamblerController.login(userid);
		if (gamblerController.getActivePlayer() != null){
			gameButton.setDisable(false);
			gamblerController.udpateMoneyDisplays();
		}
		else{
			gameButton.setDisable(true);
			moneyStatusLabel.setText(userid + " not found in database");
		}

	}
}
