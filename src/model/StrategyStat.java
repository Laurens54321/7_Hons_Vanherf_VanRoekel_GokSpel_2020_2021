package model;

import model.*;
import model.gokstrategy.GokStrategy;

public class StrategyStat {
    private GokStrategy gokStrategy;
    private int count;
    private int won;
    private double betTotal;
    private double wonTotal;

    public StrategyStat(GokStrategy gokStrategy){
        setGokStrategy(gokStrategy);
    }

    private void setGokStrategy(GokStrategy gokStrategy) {
        this.gokStrategy = gokStrategy;
    }

    public GokStrategy getGokStrategy() {
        return gokStrategy;
    }

    public String getGokStrategyString(){
        return gokStrategy.getName();
    }

    public int getCount() {
        return count;
    }

    public int getWon() {
        return won;
    }

    public double getBetTotal() {
        return betTotal;
    }

    public double getWonTotal() {
        return wonTotal;
    }

    public void addLoss(double bet) {
        count++;
        betTotal += bet;
    }

    public void addWin(double bet) {
        count++;
        won++;
        betTotal += bet;
        wonTotal += bet * gokStrategy.getMultiplier();
    }


}
