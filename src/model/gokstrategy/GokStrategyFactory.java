package model.gokstrategy;


public class GokStrategyFactory {

    private RequestGokStrategy gokStrategy = new EverythingEvenStrategy();
    private GokStrategy currentgokStrategy;
    public boolean isGameOver;

    public void GokStrategyFactory(){

    }

    public void rollDice(int roll){
        this.isGameOver = !gokStrategy.evalueerGok(roll);
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
