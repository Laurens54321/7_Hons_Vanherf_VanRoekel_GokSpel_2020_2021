package view;

import controller.GamblerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.gokstrategy.GokStrategy;

import java.util.ArrayList;

public class GamblerView {
	GamblerController gamblerController;
	int activeBalance;


	private Stage stage = new Stage();
	Pane root;

	Label moneyStatusLabel;
	TextField betField;

	Button startGameButton;

	GokStrategy gokStrategy;
	RadioButton easyStrategyButton;
	RadioButton allesEvenButton;
	RadioButton somIs21Button;
	RadioButton hogerDanVorigeButton;

	Button confirmChoiceButton;

	Button rollDiceButton;
	ArrayList<Label> dicerollLabels;
	Label endOfGameLabel;
		
	public GamblerView(GamblerController gamblerController){
		this.gamblerController = gamblerController;
		stage.setTitle("GAMBLER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);

		root = new Pane();

		Scene scene = new Scene(root, 600, 600);

		VBox layout = new VBox();

		VBox loginVbox = new VBox();
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
		betField = new TextField();


		startGameButton = new Button();
		startGameButton.setDisable(true);

		startGameButton.setText("Start gokspel");
		startGameButton.setOnAction(e -> startGame(Integer.parseInt(betField.getText())));

		userId.getChildren().addAll(userIdLabel,userIdField, moneyStatusLabel); //je goksaldo label met var
		userId.setSpacing(10);

		bet.getChildren().addAll(betLabel,betField);
		bet.setSpacing(30);

		loginVbox.getChildren().addAll(userId,bet, startGameButton);
		loginVbox.setAlignment(Pos.TOP_LEFT);
		loginVbox.setSpacing(10);
		loginVbox.setPadding(new Insets(10));


		///GOKSTRATEGY SELECTOR

		VBox strategyVbox = new VBox();
		Label strategyTitle = new Label();
		strategyTitle.setText("Kies je gok strategie uit onderstaande lijst");
		ToggleGroup radioGroup = new ToggleGroup();
		HBox strategies = new HBox();

		easyStrategyButton = new RadioButton( "ez katka");
		easyStrategyButton.setToggleGroup(radioGroup);
		easyStrategyButton.setOnAction(e -> setEasyStrategy());
		easyStrategyButton.setDisable(true);

		allesEvenButton = new RadioButton("het aantal ogen bij elke worp is een even getal");
		allesEvenButton.setToggleGroup(radioGroup);
		allesEvenButton.setOnAction(e -> setAllesIsEvenStrategy());
		allesEvenButton.setDisable(true);


		somIs21Button = new RadioButton("de som van de ogen van alle worpen samen is 21");
		somIs21Button.setToggleGroup(radioGroup);
		somIs21Button.setOnAction(e -> setSomIs21Strategy());
		somIs21Button.setDisable(true);

		hogerDanVorigeButton = new RadioButton("het aantal ogen is bij elke worp hoger dan bij de vorige worp");
		hogerDanVorigeButton.setToggleGroup(radioGroup);
		hogerDanVorigeButton.setOnAction(e -> setHogerDanVorigeStrategy());
		hogerDanVorigeButton.setDisable(true);

		VBox rbtnsVbox = new VBox();
		VBox rblabels = new VBox();

		Label allesEvenLabel = new Label();
		allesEvenLabel.setText("mogelijke winst is 4x je inzet");
		Label somIs21Label = new Label();
		somIs21Label.setText("mogelijke winst is 5x je inzet");
		Label hogerDanVorigeLabel = new Label();
		hogerDanVorigeLabel.setText("mogelijke winst is 10x je inzet");

		rbtnsVbox.getChildren().addAll(allesEvenButton, somIs21Button, hogerDanVorigeButton, easyStrategyButton);
		rbtnsVbox.setSpacing(10);
		rblabels.getChildren().addAll(allesEvenLabel,somIs21Label,hogerDanVorigeLabel);
		rblabels.setSpacing(10);

		strategies.getChildren().addAll(rbtnsVbox,rblabels);
		strategies.setSpacing(20);
		strategies.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
		strategies.setPadding(new Insets(10));


		confirmChoiceButton = new Button();
		confirmChoiceButton.setText("Bevestig je keuze");
		confirmChoiceButton.setOnAction(event -> confirmStrategyChoice());
		confirmChoiceButton.setDisable(true);

		strategyVbox.getChildren().addAll(strategyTitle,strategies, confirmChoiceButton);
		strategyVbox.setAlignment(Pos.CENTER_LEFT);
		strategyVbox.setSpacing(10);




		//

		VBox gameVbox = new VBox();

		rollDiceButton = new Button();
		rollDiceButton.setText("RollDice");
		rollDiceButton.setOnAction(event -> rollDice());
		rollDiceButton.setDisable(true);

		gameVbox.getChildren().add(rollDiceButton);

		dicerollLabels = new ArrayList<>();

		for (int i = 1; i < 5; i++) {
			Label worp = new Label();
			dicerollLabels.add(worp);
		}

		endOfGameLabel = new Label();


		gameVbox.getChildren().addAll(dicerollLabels);
		gameVbox.getChildren().add(endOfGameLabel);
		gameVbox.setSpacing(10);
		gameVbox.setPadding(new Insets(10));

		layout.getChildren().addAll(loginVbox,strategyVbox,gameVbox);
		layout.setPadding(new Insets(10));

		root.getChildren().addAll(layout);


		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}



	private void startGame(int bet){
		boolean success = gamblerController.getState().startGame(bet);
		if (!success) moneyStatusLabel.setText("You do not have enough money to start that game");
	}

	private void login(String userid){
		boolean success = gamblerController.getState().logIn(userid);

		if (success){
			startGameButton.setDisable(false);
			gamblerController.udpateMoneyDisplays();
			moneyStatusLabel.setTextFill(Color.web("#000000"));
		}
		else{
			startGameButton.setDisable(true);
			moneyStatusLabel.setText(userid + " not found in database");
			moneyStatusLabel.setTextFill(Color.web("#ff0000"));
		}

	}

	private void rollDice(){
		int roll = gamblerController.getState().throwDice();
		if (roll < 0) return;
		int rollCount = gamblerController.getRollCount();
		dicerollLabels.get(rollCount-1).setText("Roll " + rollCount + ": " + roll);
	}

	public void updateActiveBalance(){
		activeBalance = gamblerController.getActivePlayer().getMoney();
		moneyStatusLabel.setText("Je goksaldo is " + activeBalance + " €");
	}

	public void disableBetField(boolean disable){
		betField.setDisable(disable);
	}

	public void disableStartGameButton(Boolean disable){
		startGameButton.setDisable(disable);
	}

	public void setLoseMessage(boolean enable){
		if (enable) {
			gamblerController.updatePlayers();
			endOfGameLabel.setText("HELAAS, JE HEBT NIET GEWONNEN SUKKEL \nJe nieuwe goksaldo bedraagt " + activeBalance );
			endOfGameLabel.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));

		}
		else {
			endOfGameLabel.setText("");
		}
	}

	public void setWinMessage(){
		gamblerController.updatePlayers();
		endOfGameLabel.setText("HOERA, JE HEBT GEWONNEN SUKKEL \nJe nieuwe goksaldo bedraagt " + activeBalance );
		endOfGameLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,null,null)));
	}

	private void setEasyStrategy() {
		gokStrategy = GokStrategy.EASYSTRATEGY;
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
		gamblerController.getState().selectStrategy(gokStrategy);
	}

	public void disableStrategyButtons(Boolean disable) {
		easyStrategyButton.setDisable(disable);
		allesEvenButton.setDisable(disable);
		somIs21Button.setDisable(disable);
		hogerDanVorigeButton.setDisable(disable);
		confirmChoiceButton.setDisable(disable);

	}



	public void disableThrowDiceButton(Boolean disable){
		rollDiceButton.setDisable(disable);
	}
}
