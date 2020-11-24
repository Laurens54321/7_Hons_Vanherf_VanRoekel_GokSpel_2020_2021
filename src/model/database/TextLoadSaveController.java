package model.database;

import model.database.filecontroller.TextLoadSavePlayer;
import view.observer.PlayerObserver;


public class TextLoadSaveController implements PlayerObserver {
    private TextLoadSavePlayer reader;

    public TextLoadSaveController(){
        reader = new TextLoadSavePlayer();
    }

    @Override
    public void update(PlayerDB db) {
        db.setDB(reader.loadPlayers());
    }
}
