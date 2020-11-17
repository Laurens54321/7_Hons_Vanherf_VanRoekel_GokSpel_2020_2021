package model.database;

import model.Player;

import java.util.ArrayList;

public interface Reader {
    public ArrayList<Player> read();
    public void write(ArrayList<Player> players);
}
