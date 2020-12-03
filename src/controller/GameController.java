package controller;

import model.Player;
import model.database.PlayerDB;

import java.util.ArrayList;

public class GameController {
    PlayerDB playerDB;
    public GameController(){ }
        public void setPlayerDB(PlayerDB playerDB){
        this.playerDB = playerDB;
    }



    public ArrayList<Player> getPlayers(){
        return playerDB.getPlayers();
    }



}
