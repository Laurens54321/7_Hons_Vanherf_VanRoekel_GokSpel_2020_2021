package model.database.filecontroller;

import model.DomainException;
import model.GeneriekeList;
import model.Player;

import java.io.File;
import java.util.ArrayList;

public class TextLoadSavePlayer extends TextLoadSaveTemplate {
    File personenFile = new File("src/bestanden/speler.txt");

    public TextLoadSavePlayer() {
        super();
        setFile(personenFile);
    }

    public ArrayList<Player> loadPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        TextLoadSaveTemplate textLoader = new TextLoadSaveTemplate(personenFile);

        while(textLoader.hasNextLine()){
            ArrayList<String> var = (ArrayList<String>) textLoader.readLine().getAll();

            Player p = new Player(var.get(0), var.get(1), var.get(2), Integer.parseInt(var.get(3)));
            players.add(p);
        }
        return players;
    }

    public void savePlayers(ArrayList<Player> players){
        if (players.isEmpty()) throw new DomainException("TEXTLOADSAVEPLAYER: players cannot be null when saving");
        for (Player p : players) {
            GeneriekeList l = new GeneriekeList();
            l.voegToe(p.getFirstName());
            l.voegToe(p.getLastName());
            l.voegToe(p.getUserid());
            l.voegToe(p.getMoney());
            writeNextLine(l);
            closeWriter();
        }
    }
}
