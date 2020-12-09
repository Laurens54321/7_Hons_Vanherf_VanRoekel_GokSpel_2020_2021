package model;

import model.*;
import model.gokstrategy.GokStrategy;

public class StrategyStat {
    private GokStrategy gokStrategy;
    private String gokStrategyString;
    private int count;
    private int won;
    private double betTotal;
    private double wonTotal;

    public StrategyStat(GokStrategy gokStrategy){
        setGokStrategy(gokStrategy);
    }

    private void setGokStrategy(GokStrategy gokStrategy) {
        this.gokStrategy = gokStrategy;
        this.gokStrategyString = gokStrategy.getName();
        if (gokStrategy == GokStrategy.EASYSTRATEGY) wonTotal = 69;
    }

    public GokStrategy getGokStrategy() {
        return gokStrategy;
    }

    public String getGokStrategyString(){
        System.out.println("pussy");
        return gokStrategy.getName();
    }

    public int getCount() {
        System.out.println("penis");
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
