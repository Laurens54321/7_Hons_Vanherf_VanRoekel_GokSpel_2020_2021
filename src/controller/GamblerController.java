package controller;

import model.Player;
import model.database.PlayerDB;
import view.GamblerView;
import view.observer.MoneyObserver;

public class GamblerController implements MoneyObserver {

    private GamblerView gamblerView;

    private PlayerDB playerDB;
    private Player activePlayer;

    public GamblerController(PlayerDB playerDB) {
        gamblerView = new GamblerView(this);
        this.playerDB = playerDB;


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
}
