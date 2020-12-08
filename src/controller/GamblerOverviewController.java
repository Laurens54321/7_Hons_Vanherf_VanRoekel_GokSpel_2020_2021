package controller;

import model.Player;
import model.database.PlayerDB;
import view.observer.PlayerObserver;
import view.panels.GamblerOverviewPane;

import java.util.ArrayList;

public class GamblerOverviewController implements PlayerObserver {

    private GamblerOverviewPane gamblerOverviewPane;
    private PlayerDB playerDB;

    public GamblerOverviewController(PlayerDB playerDB){
        this.playerDB = playerDB;
        gamblerOverviewPane = new GamblerOverviewPane(this);
    }

    public GamblerOverviewPane getGamblerOverviewPane() {
        return gamblerOverviewPane;
    }

    public ArrayList<Player> getPlayers() {
        return playerDB.getPlayers();
    }

    @Override
    public void updatePlayers() {

    }
}
