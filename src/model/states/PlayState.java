package model.states;

import controller.GamblerController;
import model.gokstrategy.GokStrategy;

public class PlayState implements RequestState {

    GamblerController gamblerController;

    public PlayState(GamblerController gamblerController){
        this.gamblerController = gamblerController;
    }

    @Override
    public boolean logIn(String login) {
        return false;
    }

    @Override
    public void selectStrategy(GokStrategy gokStrategy) {

    }

    @Override
    public boolean startGame(int bet) {
        return false;
    }

    @Override
    public int throwDice() {
        if (gamblerController.isGameOver()) return -1;
        if (gamblerController.getRollCount() == 0) gamblerController.setState(new SecondThrowState(gamblerController));
        return gamblerController.rolldice();
    }
}
