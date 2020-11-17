package model.database;

import model.DomainException;
import model.Player;

import java.util.ArrayList;

public class PlayerDB {
    private ArrayList<Player> DB;

    public PlayerDB(){
        DB = new ArrayList<>();
    }

    public void addPlayer(Player p){
        if (p == null) throw new DomainException("PLAYERDB: Player cannot be null");
        else {
            DB.add(p);
        }
    }

    public void setDB(ArrayList<Player> db){
        if (this.DB == null || this.DB.isEmpty()) System.out.println("You just tried to overwrite a non empty playerDB");
        else{
            this.DB = db;
        }
    }

    public void removePlayer(Player p){
        if (p == null) throw new DomainException("PLAYERDB: Player cannot be null");
        else {
            DB.remove(p);
        }
    }

    public Player getPlayer(String username){
        for (Player p : DB) {
            if (p.getUserid().equals(username)) return p;
        }
        return null;
    }
}
