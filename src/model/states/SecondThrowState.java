package model.states;

import controller.GamblerController;
import model.gokstrategy.GokStrategy;

public class SecondThrowState implements RequestState{

    GamblerController gamblerController;

    public SecondThrowState(GamblerController gamblerController){
        this.gamblerController = gamblerController;
        System.out.println("Second Throw State");
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
        return gamblerController.raiseBet(bet);
    }

    @Override
    public int throwDice() {
        gamblerController.setState(new PlayState(gamblerController));
        return gamblerController.getState().throwDice();
    }
}
