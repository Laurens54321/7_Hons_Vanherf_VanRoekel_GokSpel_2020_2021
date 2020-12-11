package model.gokstrategy;


public enum GokStrategy {
    EASYSTRATEGY("Easy Strategy","winst is 2x inzet", "EasyStrategy", 2),
    EVERYTHINGEVENSTRATEGY("Everything even","Mogelijke winst is je inzet x 4", "EverythingEvenStrategy" , 4),
    SUMIS21STRATEGY("Sum is 21","Mogelijke winst is je inzet x 5", "SumIs21Strategy", 5),
    HIGHERTHANPREVIOUSSTRATEGY("Higher Then Previous","Mogelijke winst is je inzet x 10", "HigherThanPreviousStrategy", 10);

    private final String name;
    private final String description;
    private final String strategyClass;
    private final int multiplier;

    GokStrategy(String name, String s, String strategyClass, int multiplier) {
        this.name = name;
        this.description = s;
        this.strategyClass = strategyClass;
        this.multiplier = multiplier;
    }

    public String getName(){
        return name;
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
