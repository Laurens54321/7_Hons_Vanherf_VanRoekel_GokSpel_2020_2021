package view.panels;

import controller.SpelVerloopController;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.awt.*;
import java.util.ArrayList;


public class SpelVerloopPane extends BorderPane {
    private SpelVerloopController spelVerloopController;
    VBox layout;

    Label gameCount;

    VBox actionLogVbox;
    ArrayList<Label> actionLogLabels;

    Button startNewGameButton;

    public SpelVerloopPane(SpelVerloopController spelVerloopController){
        this.spelVerloopController = spelVerloopController;
        layout = new VBox();
        layout.setSpacing(10);
        startNewGameButton = new Button();
        startNewGameButton.setText("New game");
        startNewGameButton.setOnAction(e -> startNewGame());
        startNewGameButton.setDisable(true);


        gameCount = new Label();
        gameCount.setText("Game Count: 0");
        gameCount.setFont(new Font("Arial", 15));
        gameCount.setPadding(new Insets(5));

        layout.getChildren().add(gameCount);

        actionLogVbox = new VBox();
        actionLogVbox.setMinSize(200, 200);
        actionLogVbox.setBorder(new Border(new BorderStroke(Color.valueOf("#000000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        actionLogVbox.setSpacing(5);
        actionLogVbox.setPadding(new Insets(5));
        actionLogLabels = new ArrayList<>();

        layout.getChildren().add(actionLogVbox);

        layout.getChildren().add(startNewGameButton);
        layout.setPadding(new Insets(10));
        setCenter(layout);
    }

    public void updateLog(ArrayList<String> logList){
        actionLogVbox.getChildren().clear();
        for (String s : logList) {
            actionLogLabels.clear();
            actionLogLabels.add(new Label(s));
            actionLogVbox.getChildren().addAll(actionLogLabels);
        }
    }

    public void updateGameCount(String string){
        gameCount.setText("Game Count: " + string);
    }

    public void startNewGame(){
        spelVerloopController.startNewGame();
    }

    public void disableStartNewGameButton(Boolean disable){
        startNewGameButton.setDisable(disable);
    }
}
