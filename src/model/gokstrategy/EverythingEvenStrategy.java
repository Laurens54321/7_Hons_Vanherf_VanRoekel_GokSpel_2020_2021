package model.gokstrategy;

public class EverythingEvenStrategy implements RequestGokStrategy {
    public boolean evalueerGok(int worp){
        if (worp%2 == 0) return true;
        else return false;
    }
}
