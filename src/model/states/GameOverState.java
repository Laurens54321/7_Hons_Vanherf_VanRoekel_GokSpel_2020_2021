package model.states;

import controller.GamblerController;
import model.gokstrategy.GokStrategy;
import model.gokstrategy.RequestGokStrategy;

public class GameOverState implements RequestState {
    GamblerController gamblerController;

    public GameOverState(GamblerController gamblerController){
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
        gamblerController.setState(new LogInState(gamblerController));
        return false;
    }

    @Override
    public int throwDice() {
        return 0;
    }
}
