package model.states;

import controller.GamblerController;
import model.gokstrategy.GokStrategy;

public class ChooseState implements RequestState{

    GamblerController gamblerController;

    public ChooseState(GamblerController gamblerController){
        this.gamblerController = gamblerController;
        System.out.println("Choose State");
    }

    @Override
    public boolean logIn(String login) {
        return false;
    }


    @Override
    public void selectStrategy(GokStrategy gokStrategy) {
        gamblerController.setGokStrategy(gokStrategy);
        gamblerController.setState(new PlayState(gamblerController));
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
