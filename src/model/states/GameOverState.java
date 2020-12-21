package model.states;

import controller.GamblerController;
import model.DomainException;
import model.gokstrategy.GokStrategy;
import model.gokstrategy.RequestGokStrategy;

public class GameOverState implements RequestState {
    GamblerController gamblerController;

    public GameOverState(GamblerController gamblerController){
        this.gamblerController = gamblerController;
    }

    @Override
    public boolean logIn(String login) {
        throw new DomainException("You must wait for the Admin to start a new game");
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
