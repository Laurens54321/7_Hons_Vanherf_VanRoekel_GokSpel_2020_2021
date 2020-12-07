package controller;

import model.Player;
import model.database.PlayerDB;
import model.gokstrategy.GokStrategy;
import model.gokstrategy.GokStrategyFactory;
import model.gokstrategy.RequestGokStrategy;
import model.states.LogInState;
import model.states.RequestState;
import view.GamblerView;
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
            gamblerView.disableStartGameButton(true);
            gamblerView.disableStrategyButtons(false);
            return true;
        }
    }

    public boolean raiseBet(int bet){
        if (activePlayer.getMoney() < bet + activeBet) return false;
        else {
            this.activeBet = bet;
            gamblerView.disableStartGameButton(true);
            return false;
        }
    }

    public boolean isGameOver(){
        return gokStrategyFactory.isGameOver;
    }

    public int getRollCount(){
        return rollCount;
    }

    public void setGokStrategy(GokStrategy gokStrategy){
        gamblerView.disableStrategyButtons(true);
        gokStrategyFactory.setGokStrategy(gokStrategy);
    }

    public int rolldice(){
        int roll = (int) (Math.random()*6);
        gokStrategyFactory.rollDice(roll);
        rollCount += 1;
        return roll;
    }

    public void udpateMoneyDisplays(){
        playerDB.updateMoneyObservers();
    }


    @Override
    public void updateMoney() {
        gamblerView.updateMoneyStatusLabel();
    }




}
