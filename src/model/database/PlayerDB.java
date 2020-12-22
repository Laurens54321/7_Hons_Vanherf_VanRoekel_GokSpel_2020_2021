package model.database;

import model.DomainException;
import model.Player;
import model.database.filecontroller.ExcelPlayerLoadSavePlayer;
import model.database.filecontroller.PlayerLoadSaveStrategy;
import view.observer.PlayerObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class PlayerDB {
    private HashMap<String, Player> DB;
    private PlayerLoadSaveStrategy saveStrategy = new ExcelPlayerLoadSavePlayer();

    private Collection<PlayerObserver> observers = new ArrayList<>();

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
                p.setPlayerDB(this);
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

    public void addObserver(PlayerObserver obs){
        observers.add(obs);
    }

    public void updateMoneyObservers(){
        for (PlayerObserver obs : observers)
            obs.updatePlayers();
    }

    public void save(){
        saveStrategy.save(getPlayers());
    }

    public void setSaveStrategy(PlayerLoadSaveStrategy saveStrategy) {
        save();
        if (saveStrategy.getClass().equals(this.getClass())) return;
        this.saveStrategy = saveStrategy;
        save();
    }
}
