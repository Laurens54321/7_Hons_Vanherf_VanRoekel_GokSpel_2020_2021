package model.database.filecontroller;

import java.util.ArrayList;

public interface PlayerLoadSaveStrategy<Player> {

    public ArrayList<Player> load();
    public void save(ArrayList<Player> list);

}
