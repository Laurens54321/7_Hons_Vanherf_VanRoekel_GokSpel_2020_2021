package controller;

import model.Player;
import model.database.PlayerDB;
import model.gokstrategy.GokStrategy;
import model.gokstrategy.GokStrategyFactory;
import view.GamblerView;
import view.observer.MoneyObserver;

public class GamblerController implements MoneyObserver {

    private view.GamblerView gamblerView;
    public GokStrategyFactory gokStrategyFactory;

    private PlayerDB playerDB;
    private Player activePlayer;

    public GamblerController(PlayerDB playerDB) {
        gamblerView = new view.GamblerView(this);
        this.playerDB = playerDB;
        gokStrategyFactory = new GokStrategyFactory();

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

    public void udpateMoneyDisplays(){
        playerDB.updateMoneyObservers();
    }


    @Override
    public void updateMoney() {
        gamblerView.updateMoneyStatusLabel();
    }

    public void setGokStrategy(GokStrategy gokStrategy){
        gokStrategyFactory.setGokStrategy(gokStrategy);
    }


}
