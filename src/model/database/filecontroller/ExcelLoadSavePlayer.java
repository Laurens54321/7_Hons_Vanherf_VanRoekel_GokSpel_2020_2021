package model.database.filecontroller;

import excel.ExcelPlugin;
import jdk.nashorn.internal.runtime.regexp.joni.ApplyCaseFoldArg;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelLoadSavePlayer extends ExcelPlugin implements LoadSaveStrategy {

    File personenFile = new File("src/bestanden/speler.xls");

    @Override
    public ArrayList<Player> load() {
        ArrayList<Player> returnList = new ArrayList<>();
        try{
            ArrayList<ArrayList<String>> args = read(personenFile);
            for (ArrayList<String> arr : args) {
                 Player p = new Player(arr.get(0), arr.get(1), arr.get(2), Integer.parseInt(arr.get(3)));
                 returnList.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return returnList;
    }

    @Override
    public void save(ArrayList list) {
        ArrayList<Player> players = new ArrayList<>(list);
        ArrayList<ArrayList<String>> args = new ArrayList<>();
        for (Player p : players) {
            ArrayList<String> playerProperties = new ArrayList<String>();
            playerProperties.add(p.getFirstName());
            playerProperties.add(p.getLastName());
            playerProperties.add(p.getUserid());
            playerProperties.add(String.valueOf(p.getMoney()));
            args.add(playerProperties);
        }
        try {
            write(personenFile, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
