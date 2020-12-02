package model.gokstrategy;


import model.DomainException;

import java.lang.reflect.InvocationTargetException;

public class GokStrategyFactory {

    private RequestGokStrategy gokStrategy = new AllesIsEvenStrategy();
    private GokStrategy currentgokStrategy;
    public boolean isGameOver;

    public void GokStrategyFactory(){

    }

    public void werpDobbelsteen(int worp){
        this.isGameOver = !gokStrategy.evalueerGok(worp);
    }

    public void setGokStrategy(GokStrategy gokStrategy){
        try {
            Class strategyClass = Class.forName("model.gokstrategy." + gokStrategy.getStrategyClass());
            Object strategyObject = strategyClass.getConstructor().newInstance();
            this.gokStrategy = (RequestGokStrategy) strategyObject;
            this.currentgokStrategy = gokStrategy;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GokStrategy getGokStrategy(){
        return currentgokStrategy;
    }


}
