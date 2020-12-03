package model.states;

import model.gokstrategy.GokStrategy;

public interface RequestState {
    public boolean logIn(String login);

    public void selectStrategy(GokStrategy gokStrategy);

    public boolean startGame(int bet);

    public int throwDice();
}
