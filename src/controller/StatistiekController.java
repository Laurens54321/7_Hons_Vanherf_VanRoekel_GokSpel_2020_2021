package controller;

import model.gokstrategy.GokStrategy;
import view.observer.StrategyObserver;
import view.panels.StatistiekPane;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatistiekController implements StrategyObserver {

    private StatistiekPane statistiekPane;
    private GamblerController gamblerController;

    private HashMap<GokStrategy, ArrayList<Double>> stats;

    public StatistiekController(GamblerController gamblerController){
        statistiekPane = new StatistiekPane();
        this.gamblerController = gamblerController;
        stats = new HashMap<>();
    }

    public StatistiekPane getStatistiekPane() {
        return statistiekPane;
    }

    @Override
    public void updateStrategy(GokStrategy gokStrategy, double bet, boolean won) {
        ArrayList<Double> strategystats = stats.get(gokStrategy);
        strategystats.add(strategystats.get(0) + 1);
        if (won) strategystats.add(strategystats.get(1) + 1);
        strategystats.add(strategystats.get(2) + bet);
        if (won) strategystats.add(strategystats.get(3) + bet * gokStrategy.getMultiplier());
        stats.put(gokStrategy, strategystats);
    }
}
