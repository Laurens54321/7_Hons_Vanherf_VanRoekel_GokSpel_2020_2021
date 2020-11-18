package model.database;

import controller.filecontroller.TxtReader;
import view.observer.PlayerObserver;


public class TxtController implements PlayerObserver {
    private TxtReader reader;

    public TxtController(){
        reader = new TxtReader();
    }

    @Override
    public void update(PlayerDB db) {
        db.setDB(reader.read());
    }
}
