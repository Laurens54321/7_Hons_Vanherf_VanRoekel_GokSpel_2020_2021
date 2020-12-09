package controller;

import model.StrategyStat;
import model.gokstrategy.GokStrategy;
import view.observer.StrategyObserver;
import view.panels.StatistiekPane;

import java.util.ArrayList;
import java.util.HashMap;

public class StatistiekController implements StrategyObserver {

    private StatistiekPane statistiekPane;
    private ArrayList<StrategyStat> strategyStats;

    private GamblerController gamblerController;

    private HashMap<GokStrategy, ArrayList<Double>> stats;

    public StatistiekController(GamblerController gamblerController){
        initiateStrategyStats();                //Initiate this array before initialising the statisticspane cuz it will create NullPointerException
        statistiekPane = new StatistiekPane(this);

        this.gamblerController = gamblerController;
        gamblerController.addStrategyObserver(this);

        stats = new HashMap<>();
    }

    private void initiateStrategyStats() {
        strategyStats = new ArrayList<>();
        for (GokStrategy gokStrat : GokStrategy.values()) {
            StrategyStat strategyStat = new StrategyStat(gokStrat);
            strategyStats.add(strategyStat);
        }
    }

    public StatistiekPane getStatistiekPane() {
        return statistiekPane;
    }

    public ArrayList<StrategyStat> getStrategyStats() {
        return strategyStats;
    }

    @Override
    public void updateStrategy(GokStrategy gokStrategy, double bet, boolean won) {
        for (StrategyStat stat : strategyStats) {
            if (stat.getGokStrategy().equals(gokStrategy)){
                if (won) stat.addWin(bet);
                else stat.addLoss(bet);
                statistiekPane.refresh();
                return;
            }
        }


    }
}
