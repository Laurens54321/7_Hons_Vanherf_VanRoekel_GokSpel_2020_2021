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
import model.gokstrategy.GokStrategy;

public class GamblerView {
	GamblerController gamblerController;

	private Stage stage = new Stage();
	Group root;

	Label moneyStatusLabel;

	Button startGameButton;

	GokStrategy gokStrategy;
	RadioButton allesEvenButton;
	RadioButton somIs21Button;
	RadioButton hogerDanVorigeButton;

	Button confirmChoiceButton;
		
	public GamblerView(GamblerController gamblerController){
		this.gamblerController = gamblerController;
		stage.setTitle("GAMBLER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);

		root = new Group();

		Scene scene = new Scene(root, 600, 600);

		VBox layout = new VBox();

		VBox login = new VBox();
		HBox userId = new HBox();
		HBox bet = new HBox();

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

		startGameButton = new Button();
		startGameButton.setDisable(true);

		startGameButton.setText("Start gokspel");
		startGameButton.setOnAction(e -> startGame());

		userId.getChildren().addAll(userIdLabel,userIdField, moneyStatusLabel); //je goksaldo label met var
		userId.setSpacing(10);

		bet.getChildren().addAll(betLabel,betText);
		bet.setSpacing(30);

		login.getChildren().addAll(userId,bet, startGameButton);
		login.setAlignment(Pos.TOP_LEFT);
		login.setSpacing(10);
		login.setPadding(new Insets(10));


		///GOKSTRATEGY SELECTOR

		VBox strategyChoice = new VBox();
		Label strategyTitle = new Label();
		strategyTitle.setText("Kies je gok strategie uit onderstaande lijst");
		ToggleGroup radioGroup = new ToggleGroup();
		HBox strategies = new HBox();

		allesEvenButton = new RadioButton("het aantal ogen bij elke worp is een even getal");
		allesEvenButton.setToggleGroup(radioGroup);
		allesEvenButton.setOnAction(e -> setAllesIsEvenStrategy());


		somIs21Button = new RadioButton("de som van de ogen van alle worpen samen is 21");
		somIs21Button.setToggleGroup(radioGroup);
		somIs21Button.setOnAction(e -> setSomIs21Strategy());

		hogerDanVorigeButton = new RadioButton("het aantal ogen is bij elke worp hoger dan bij de vorige worp");
		hogerDanVorigeButton.setToggleGroup(radioGroup);
		hogerDanVorigeButton.setOnAction(e -> setHogerDanVorigeStrategy());

		VBox rbtns = new VBox();
		VBox rblabels = new VBox();

		Label allesEvenLabel = new Label();
		allesEvenLabel.setText("mogelijke winst is 4x je inzet");
		Label somIs21Label = new Label();
		somIs21Label.setText("mogelijke winst is 5x je inzet");
		Label hogerDanVorigeLabel = new Label();
		hogerDanVorigeLabel.setText("mogelijke winst is 10x je inzet");

		rbtns.getChildren().addAll(allesEvenButton, somIs21Button, hogerDanVorigeButton);
		rbtns.setSpacing(10);
		rblabels.getChildren().addAll(allesEvenLabel,somIs21Label,hogerDanVorigeLabel);
		rblabels.setSpacing(10);

		strategies.getChildren().addAll(rbtns,rblabels);
		strategies.setSpacing(20);

		confirmChoiceButton = new Button();
		confirmChoiceButton.setText("Bevestig je keuze");
		confirmChoiceButton.setOnAction(event -> confirmStrategyChoice());

		strategyChoice.getChildren().addAll(strategyTitle,strategies, confirmChoiceButton);
		strategyChoice.setAlignment(Pos.CENTER_LEFT);
		strategyChoice.setSpacing(10);


		layout.getChildren().addAll(login,strategyChoice);
		layout.setPadding(new Insets(10));

		root.getChildren().addAll(layout);

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
			startGameButton.setDisable(false);
			gamblerController.udpateMoneyDisplays();
		}
		else{
			startGameButton.setDisable(true);
			moneyStatusLabel.setText(userid + " not found in database");
		}

	}

	public void disableStartGameButton(Boolean disable){
		startGameButton.setDisable(disable);
	}

	private void setAllesIsEvenStrategy(){
		gokStrategy = GokStrategy.ALLESISEVENSTRATEGY;
	}

	private void setSomIs21Strategy(){
		gokStrategy = GokStrategy.SOMIS21STRATEGY;
	}

	private void setHogerDanVorigeStrategy(){
		gokStrategy = GokStrategy.HOGERDANVORIGESTRATEGY;
	}

	private void confirmStrategyChoice(){
		gamblerController.setGokStrategy(gokStrategy);
		if (gokStrategy == GokStrategy.ALLESISEVENSTRATEGY) allesEvenButton.setSelected(true);
		else allesEvenButton.setSelected(false);
		if (gokStrategy == GokStrategy.SOMIS21STRATEGY) somIs21Button.setSelected(true);
		else somIs21Button.setSelected(false);
		if (gokStrategy == GokStrategy.HOGERDANVORIGESTRATEGY) hogerDanVorigeButton.setSelected(true);
		else hogerDanVorigeButton.setSelected(false);
	}

	public void disableAllesiIsEvenButton(Boolean disable){
		allesEvenButton.setDisable(disable);
	}

	public void disableSomIs21Button(Boolean disable){
		somIs21Button.setDisable(disable);
	}

	public void disableHogerDanVorigeButton(Boolean disable){
		hogerDanVorigeButton.setDisable(disable);
	}



	public void disableConfirmChoiceButton(Boolean disable){
		confirmChoiceButton.setDisable(disable);
	}
}
