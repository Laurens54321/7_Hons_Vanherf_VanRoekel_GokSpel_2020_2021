package model.states;

import controller.GamblerController;
import model.gokstrategy.GokStrategy;

public class LogInState implements RequestState {

    GamblerController gamblerController;

    public LogInState(GamblerController gamblerController){
        this.gamblerController = gamblerController;
        System.out.println("Login State");
    }

    @Override
    public boolean logIn(String login) {
        boolean success = gamblerController.login(login);
        if (success) gamblerController.setState(new BetState(gamblerController));
        return success;
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
        return -1;
    }
}
