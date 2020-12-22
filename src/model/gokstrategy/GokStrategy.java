package model.gokstrategy;


import model.DomainException;

public enum GokStrategy {
    EASYSTRATEGY("Easy Strategy","EasyStrategy", 2),
    EVERYTHINGEVENSTRATEGY("Everything even","EverythingEvenStrategy" , 4),
    SUMIS21STRATEGY("Sum is 21","SumIs21Strategy", 5),
    HIGHERTHANPREVIOUSSTRATEGY("Higher Then Previous", "HigherThanPreviousStrategy", 10);

    private final String name;
    private String description;
    private final String strategyClass;
    private int multiplier;
    private boolean active;

    GokStrategy(String name, String strategyClass, int multiplier) {
        this.name = name;
        this.description = "Mogelijke winst is je inzet x " + multiplier ;
        this.strategyClass = strategyClass;
        this.multiplier = multiplier;
        setActive(true);
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

    public void setMultiplier(int multiplier){
        if (multiplier < 0) throw new DomainException("Multiplier cannot be lower then 1");
        description = "Mogelijke winst is je inzet x " + multiplier ;
        this.multiplier = multiplier;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
