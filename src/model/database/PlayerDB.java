package model.database;

import model.DomainException;
import model.Player;
import model.database.filecontroller.ExcelLoadSavePlayer;
import model.database.filecontroller.LoadSaveStrategy;
import model.database.filecontroller.TextLoadSavePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class PlayerDB {
    private HashMap<String, Player> DB;
    private LoadSaveStrategy saveStrategy = new ExcelLoadSavePlayer();

    public PlayerDB(){

        DB = new HashMap<>();
    }

    public void addPlayer(Player p){
        if (p == null) throw new DomainException("PLAYERDB: Player cannot be null");
        else {
            DB.put(p.getUserid(), p);
        }
    }

    public void setDB(ArrayList<Player> db){
        if (!this.DB.isEmpty()) System.out.println("You just tried to overwrite a non empty playerDB");
        else{
            for (Player p : db) {
                addPlayer(p);
            }
        }
    }

    public void removePlayer(Player p){
        if (p == null) throw new DomainException("PLAYERDB: Player cannot be null");
        else {
            DB.remove(p);
        }
    }

    public Player getPlayer(String username){
        return DB.get(username);
    }

    public ArrayList<Player> getPlayers(){
        ArrayList<Player> returnList = new ArrayList<>();

        Collection<Player> players =  DB.values();
        Iterator iterator = players.iterator();

        while (iterator.hasNext()){
            returnList.add((Player) iterator.next());
        }
        return returnList;
    }

    public void loadPlayers(){
        setDB(saveStrategy.load());
    }
}
