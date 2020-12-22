package model.gokstrategy;

public class SumIs21Strategy implements RequestGokStrategy {
    private int throwCount = 0;
    private int sum = 0;

    @Override
    public boolean evalueerGok(int worp) {
        throwCount++;
        if (throwCount >= 4) return false;
        sum += worp;
        if (sum <= 21) return true;
        else return false;
    }
}
