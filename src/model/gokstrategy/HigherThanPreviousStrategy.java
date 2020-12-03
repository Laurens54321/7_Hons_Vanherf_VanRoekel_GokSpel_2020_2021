package model.gokstrategy;


public class HigherThanPreviousStrategy implements RequestGokStrategy {
    private int vorige = 0;

    @Override
    public boolean evalueerGok(int worp) {
        if (worp > vorige){
            vorige = worp;
            return true;
        }
        else return false;
    }
}
