package controller;

import model.gokstrategy.EverythingEvenStrategy;
import model.gokstrategy.GokStrategy;
import view.observer.PlayerObserver;
import view.observer.StrategyObserver;
import view.panels.SpelVerloopPane;

import static model.gokstrategy.GokStrategy.EVERYTHINGEVENSTRATEGY;

public class SpelVerloopController implements PlayerObserver {
    private SpelVerloopPane spelVerloopPane;
    private GamblerController gamblerController;





    public SpelVerloopController(GamblerController gamblerController){

    }

    public SpelVerloopPane getSpelVerloopPane() {
        return spelVerloopPane;
    }

    @Override
    public void updatePlayers() {

    }


}
