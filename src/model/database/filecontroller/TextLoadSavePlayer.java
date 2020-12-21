package model.database.filecontroller;

import model.DomainException;
import model.Player;

import java.io.File;
import java.util.ArrayList;

public class TextLoadSavePlayer extends TextLoadSaveTemplate implements LoadSaveStrategy<Player> {
    File personenFile = new File("src/bestanden/speler.txt");

    public TextLoadSavePlayer() {
        super();
        setFile(personenFile);
    }

    public ArrayList<Player> load(){
        ArrayList<Player> players = new ArrayList<>();
        TextLoadSaveTemplate textLoader = new TextLoadSaveTemplate(personenFile);

        while(textLoader.hasNextLine()){
            ArrayList<String> var = (ArrayList<String>) textLoader.readLine();

            Player p = new Player(var.get(0), var.get(1), var.get(2), Integer.parseInt(var.get(3)));
            players.add(p);
        }
        return players;
    }

    public void save(ArrayList<Player> players){
        if (players.isEmpty()) throw new DomainException("TEXTLOADSAVEPLAYER: players cannot be null when saving");
        for (Player p : players) {
            ArrayList l = new ArrayList();
            l.add(p.getFirstName());
            l.add(p.getLastName());
            l.add(p.getUserid());
            l.add(p.getMoney());
            writeNextLine(l);

        }
        closeWriter();
    }
}
