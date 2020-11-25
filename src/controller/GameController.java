package controller;

import model.Player;
import model.database.PlayerDB;

import java.util.ArrayList;

public class GameController {
    PlayerDB playerDB;
    Player currentPlayer;


    public GameController(){ }

    public PlayerDB getPlayerDB(){
        return playerDB;
    }

    public void login(String userid){
        Player p = findPlayer(userid);
        if (p != null){
            currentPlayer = p;
            System.out.println("LOGGED IN | currentPlayer = " + p.getUserid());
        }
    }

    public void setPlayerDB(PlayerDB playerDB){
        this.playerDB = playerDB;
    }

    public void logout(){
        currentPlayer = null;
        System.out.println("LOGGED OUT | currentPlayer = null" );
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public ArrayList<Player> getPlayers(){
        return playerDB.getPlayers();
    }



    private Player findPlayer(String userid){
        for (Player p : playerDB.getPlayers()) {
            if (p == null) break;
            if (p.getUserid().equals(userid)) return p;
        }
        return null;
    }
}
