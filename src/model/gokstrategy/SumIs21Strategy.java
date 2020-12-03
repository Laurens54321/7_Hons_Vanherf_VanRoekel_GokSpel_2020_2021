package model.gokstrategy;

public class SumIs21Strategy implements RequestGokStrategy {
    private int sum = 0;

    @Override
    public boolean evalueerGok(int worp) {
        sum += worp;
        if (sum <= 21) return true;
        else return false;
    }
}
