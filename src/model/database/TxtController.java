package model.database;

import controller.filecontroller.TxtReader;


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
