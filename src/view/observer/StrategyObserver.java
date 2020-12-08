package view.observer;

import model.gokstrategy.GokStrategy;

public interface StrategyObserver {
    public void updateStrategy(GokStrategy gokStrategy, double bet, boolean won);
}
