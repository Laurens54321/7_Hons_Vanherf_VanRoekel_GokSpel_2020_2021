package controller;

import model.Player;
import model.database.PlayerDB;
import model.gokstrategy.GokStrategy;
import model.gokstrategy.GokStrategyFactory;
import model.states.BetState;
import model.states.LogInState;
import model.states.RequestState;
import model.states.SecondThrowState;
import view.observer.MoneyObserver;

public class GamblerController implements MoneyObserver {

    private RequestState state;

    private view.GamblerView gamblerView;
    public GokStrategyFactory gokStrategyFactory;

    private PlayerDB playerDB;
    private Player activePlayer;

    private double activeBet;

    private int rollCount = 0;

    public GamblerController(PlayerDB playerDB) {
        gamblerView = new view.GamblerView(this);
        this.playerDB = playerDB;
        gokStrategyFactory = new GokStrategyFactory();
        state = new LogInState(this);

    }

    public void setState(RequestState state){
        this.state = state;
    }

    public RequestState getState(){
        return state;
    }

    public Player getActivePlayer(){
        return activePlayer;
    }

    public boolean login(String userid){
        Player p = playerDB.getPlayer(userid);
        if (p != null){
            activePlayer = p;
            System.out.println("Logged in as " + p.getUserid());
            playerDB.updateMoneyObservers();
            gamblerView.disableStartGameButton(false);
            return true;
        }
        return false;
    }

    public boolean setActiveBet(int bet){
        if (activePlayer.getMoney() <  bet) return false;
        else {
            this.activeBet = bet;
            rollCount = 0;
            gamblerView.disableBetField(true);
            gamblerView.disableStartGameButton(true);
            gamblerView.disableStrategyButtons(false);
            gamblerView.setLoseMessage(false);
            return true;
        }
    }

    public boolean raiseBet(int bet){
        if (activePlayer.getMoney() < bet + activeBet) return false;
        else {
            this.activeBet = bet;
            gamblerView.disableBetField(true);
            gamblerView.disableStartGameButton(true);
            return false;
        }
    }

    public boolean isGameOver(){
        return gokStrategyFactory.isGameOver;
    }

    public void gameOver(){
        gamblerView.disableBetField(false);
        gamblerView.disableStartGameButton(true);
        gamblerView.disableStrategyButtons(true);
        gamblerView.disableThrowDiceButton(true);
        activePlayer.addMoney(-activeBet);
        gamblerView.setLoseMessage(true);
        setState(new BetState(this));
    }

    public void winGame(){
        gamblerView.disableBetField(false);
        gamblerView.disableStartGameButton(true);
        gamblerView.disableStrategyButtons(true);
        gamblerView.disableThrowDiceButton(true);
        activePlayer.addMoney(activeBet * gokStrategyFactory.getGokStrategy().getMultiplier());
        gamblerView.setWinMessage();
        setState(new BetState(this));
    }

    public int getRollCount(){
        return rollCount;
    }

    public void setGokStrategy(GokStrategy gokStrategy){
        gamblerView.disableStrategyButtons(true);
        gokStrategyFactory.setGokStrategy(gokStrategy);
        gamblerView.disableThrowDiceButton(false);
    }

    public int rolldice(){
        int roll = (int) (Math.random()*6);
        if (gokStrategyFactory.rollDice(roll)){
            gameOver();
        }
        else rollCount += 1;

        if (isGameOver()) return -1;
        if (getRollCount() == 1) setState(new SecondThrowState(this));
        if (getRollCount() == 4) winGame();
        System.out.println(rollCount);
        return roll;
    }

    public void udpateMoneyDisplays(){
        playerDB.updateMoneyObservers();
    }


    @Override
    public void updateMoney() {
        gamblerView.updateActiveBalance();
    }




}
