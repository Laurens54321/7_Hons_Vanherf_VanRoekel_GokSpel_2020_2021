package model.states;

import controller.GamblerController;
import model.gokstrategy.GokStrategy;

public class PlayState implements RequestState {

    GamblerController gamblerController;

    public PlayState(GamblerController gamblerController){
        this.gamblerController = gamblerController;
        System.out.println("Play State");
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
        return gamblerController.rolldice();
    }
}
