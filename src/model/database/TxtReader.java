package model.database;

import model.DomainException;
import model.Player;

import java.io.*;
import java.util.*;


public class TxtReader implements Reader{

    File personenFile = new File("src/bestanden/speler.txt");

    @Override
    public ArrayList<Player> read() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            Scanner scannerFile = new Scanner(personenFile);
            while (scannerFile.hasNextLine()) {
                String s = scannerFile.nextLine();
                System.out.println(s);

                String[] var = s.split(",");
                Player p = new Player(var[0], var[1], var[2], Integer.parseInt(var[3]));
                players.add(p);
                scannerFile.close();
            }
        }  catch (FileNotFoundException e) {
            throw new DomainException("TXTREADER: Error reading player file", e);
        }
        return players;
    }

    @Override
    public void write(ArrayList<Player> players) {
        try {
            Writer writerFile = new PrintWriter(personenFile);
            for (Player p : players) {
                writerFile.write(p.getFirstName() + "," + p.getLastName() + "," + p.getUserid() + "," + p.getMoney() + "\n");
            }

            writerFile.close();
        } catch (FileNotFoundException e) {
            throw new DomainException("TXTREADER: Error reading player file", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
