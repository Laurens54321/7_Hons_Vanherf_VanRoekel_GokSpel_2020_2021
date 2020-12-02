package model.gokstrategy;


public enum GokStrategy {
    ALLESISEVENSTRATEGY("Mogelijke winst is je inzet x 4", "AllesIsEvenStrategy" , 4),
    SOMIS21STRATEGY("Mogelijke winst is je inzet x 5", "SomIs21Strategy", 5),
    HOGERDANVORIGESTRATEGY("Mogelijke winst is je inzet x 10", "HogerDanVorigeStrategy", 10);

    GokStrategy(String s, String hogerDanVorigeStrategy, int i) { }


}
