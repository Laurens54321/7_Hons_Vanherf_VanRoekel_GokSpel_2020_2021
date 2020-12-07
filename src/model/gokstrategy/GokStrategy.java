package model.gokstrategy;


public enum GokStrategy {
    EASYSTRATEGY("winst is 2x inzet", "EasyStrategy", 2),
    ALLESISEVENSTRATEGY("Mogelijke winst is je inzet x 4", "AllesIsEvenStrategy" , 4),
    SOMIS21STRATEGY("Mogelijke winst is je inzet x 5", "SomIs21Strategy", 5),
    HOGERDANVORIGESTRATEGY("Mogelijke winst is je inzet x 10", "HogerDanVorigeStrategy", 10);


    private final String description;
    private final String strategyClass;
    private final int multiplier;

    GokStrategy(String s, String strategyClass, int multiplier) {
        this.description = s;
        this.strategyClass = strategyClass;
        this.multiplier = multiplier;
    }

    public String getDescription() {
        return description;
    }

    public String getStrategyClass() {
        return strategyClass;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
