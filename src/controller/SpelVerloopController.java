package controller;

import model.DomainException;
import model.gokstrategy.EverythingEvenStrategy;
import model.gokstrategy.GokStrategy;
import model.states.GameOverState;
import view.observer.ActionObserver;
import view.observer.GameObserver;
import view.observer.PlayerObserver;
import view.observer.StrategyObserver;
import view.panels.SpelVerloopPane;

import javax.swing.*;

import java.util.ArrayList;

import static model.gokstrategy.GokStrategy.EVERYTHINGEVENSTRATEGY;

public class SpelVerloopController implements ActionObserver, GameObserver {
    private SpelVerloopPane spelVerloopPane;
    private GamblerController gamblerController;

    private ArrayList<String> actionLog;

    private int gameCount;

    public SpelVerloopController(GamblerController gamblerController){
        spelVerloopPane = new SpelVerloopPane(this);
        actionLog = new ArrayList<>();

        this.gamblerController = gamblerController;
        gamblerController.addActionObserver(this);
        gamblerController.addGameObserver(this);
    }

    public SpelVerloopPane getSpelVerloopPane() {
        return spelVerloopPane;
    }

    public ArrayList<String> getActionLog(){
        return actionLog;
    }

    public String getGameCount(){
        return String.valueOf(gameCount);
    }

    public void startNewGame(){
        gamblerController.getState().startGame(0);
        spelVerloopPane.disableStartNewGameButton(true);
    }

    @Override
    public void updateAction(String actionString) {
        if (actionString == null || actionString.isEmpty()) throw new DomainException("ActionString cannot be empty");
        actionLog.add(actionString);
        spelVerloopPane.updateLog(actionLog);

        if (gamblerController.getState().getClass() == GameOverState.class) spelVerloopPane.disableStartNewGameButton(false);
    }

    @Override
    public void observerNewGame() {
        gameCount++;
        spelVerloopPane.updateGameCount(String.valueOf(gameCount));
    }
}
