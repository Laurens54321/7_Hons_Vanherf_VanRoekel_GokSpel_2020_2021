package controller;

import model.Player;
import model.database.PlayerDB;
import model.gokstrategy.GokStrategy;
import model.gokstrategy.GokStrategyFactory;
import model.gokstrategy.RequestGokStrategy;
import model.states.*;
import view.observer.PlayerObserver;
import view.observer.StrategyObserver;

import javax.security.auth.login.LoginContext;
import java.util.ArrayList;

public class GamblerController implements PlayerObserver {

    private RequestState state;

    private view.GamblerView gamblerView;
    public GokStrategyFactory gokStrategyFactory;

    private ArrayList<StrategyObserver> strategyObserverArrayList;

    private PlayerDB playerDB;
    private Player activePlayer;

    private double activeBet;

    private int rollCount = 0;

    public GamblerController(PlayerDB playerDB) {
        gamblerView = new view.GamblerView(this);
        this.playerDB = playerDB;
        gokStrategyFactory = new GokStrategyFactory();
        strategyObserverArrayList = new ArrayList<>();
        setState(new LogInState(this));

    }

    public void setState(RequestState state){

        if (state.getClass() == LogInState.class){
            playerDB.updateMoneyObservers();
            gamblerView.disableBetField(true);
            gamblerView.disableStartGameButton(true);
            gamblerView.disableStrategyButtons(true);
            gamblerView.disableThrowDiceButton(true);
        }
        if (state.getClass() == BetState.class){
            playerDB.updateMoneyObservers();
            gamblerView.disableBetField(false);
            gamblerView.disableStartGameButton(false);
            gamblerView.disableStrategyButtons(true);
            gamblerView.disableThrowDiceButton(true);
            gamblerView.disableErrorMessage();
        }
        if (state.getClass() == ChooseState.class){
            gamblerView.disableBetField(true);
            gamblerView.disableStartGameButton(true);
            gamblerView.disableStrategyButtons(false);
            gamblerView.setLoseMessage(false);
            gamblerView.disableErrorMessage();
        }
        if (state.getClass() == PlayState.class){
            gamblerView.disableBetField(true);
            gamblerView.disableStartGameButton(true);
            gamblerView.disableStrategyButtons(true);
            gamblerView.disableThrowDiceButton(false);
            gamblerView.updateBetfield();
        }
        if (state.getClass() == SecondThrowState.class){
            gamblerView.disableBetField(false);
            gamblerView.disableStartGameButton(false);
            gamblerView.disableStrategyButtons(true);
            gamblerView.disableThrowDiceButton(false);
        }
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

            return true;
        }
        return false;
    }

    public boolean setActiveBet(int bet){
        if (activePlayer.getMoney() <  bet) return false;
        else {
            this.activeBet = bet;
            rollCount = 0;
            return true;
        }
    }

    public double getActiveBet() {
        return activeBet;
    }

    public boolean raiseBet(int bet){
        if (activePlayer.getMoney() < bet) return false;
        else {
            this.activeBet = bet;
            setState(new PlayState(this));
            System.out.println("raised acitvebet");
            return true;
        }
    }

    public boolean isGameOver(){
        return gokStrategyFactory.isGameOver;
    }

    public void gameOver(){
        updateStrategyObservers(gokStrategyFactory.getGokStrategy(), activeBet, false);
        gamblerView.disableBetField(false);
        gamblerView.disableStartGameButton(true);
        gamblerView.disableStrategyButtons(true);
        gamblerView.disableThrowDiceButton(true);
        activePlayer.addMoney(-activeBet);
        gamblerView.setLoseMessage(true);
        setState(new BetState(this));

    }

    public void winGame(){
        updateStrategyObservers(gokStrategyFactory.getGokStrategy(), activeBet, true);
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
        gokStrategyFactory.setGokStrategy(gokStrategy);

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

    public void addStrategyObserver(StrategyObserver strategyObserver){
        strategyObserverArrayList.add(strategyObserver);
    }

    public void updateStrategyObservers(GokStrategy gokStrategy, double bet, boolean won){
        for (StrategyObserver so : strategyObserverArrayList) {
            so.updateStrategy(gokStrategy, bet, won);
            System.out.println("updated this first ofc");
        }
    }


    @Override
    public void updatePlayers() {
        gamblerView.updateActiveBalance();
    }




}
