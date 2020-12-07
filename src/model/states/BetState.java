package model.states;

import controller.GamblerController;
import model.gokstrategy.GokStrategy;
import view.GamblerView;

public class BetState implements RequestState {

    GamblerController gamblerController;

    public BetState(GamblerController gamblerController){
        this.gamblerController = gamblerController;
        System.out.println("Bet State");
    }



    @Override
    public boolean logIn(String login) {
        gamblerController.setState(new BetState(gamblerController));
        return gamblerController.login(login);
    }

    @Override
    public void selectStrategy(GokStrategy gokStrategy) {

    }

    @Override
    public boolean startGame(int bet) {
        boolean success = gamblerController.setActiveBet(bet);
        if(success) gamblerController.setState(new ChooseState(gamblerController));
        return success;
    }

    @Override
    public int throwDice() {
        return -1;
    }
}
