package model.database.filecontroller;

import controller.InstellingController;
import model.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class TextLoadSaveSetting extends TextLoadSaveTemplate {
    File settingFile = new File("src/bestanden/settings.csv");
    InstellingController instellingController;

    public TextLoadSaveSetting(InstellingController instellingController){
        super();
        setFile(settingFile);
        this.instellingController = instellingController;
    }

    public void load(){
        TextLoadSaveTemplate textLoader = new TextLoadSaveTemplate(settingFile);
        try {

            String loadSaveStrategy = ((ArrayList<String>) textLoader.readLine()).get(1);
            if (loadSaveStrategy == null || loadSaveStrategy.isEmpty())
                System.out.println("No loadSaveStrategy found, using default excelLoader");
            else instellingController.setLoader(loadSaveStrategy);
        } catch (NullPointerException e){
            System.out.println("No loadSaveStrategy found, using default excelLoader");
        }

        try{
            HashMap<String, Integer> gokStrategyMultipliers = new HashMap<>();
            while(textLoader.hasNextLine()){
                ArrayList<String> var = (ArrayList<String>) textLoader.readLine();
                if (var.get(1) != null && !var.get(1).isEmpty()) gokStrategyMultipliers.put(var.get(0), Integer.valueOf(var.get(1)));
            }
            instellingController.setGokStrategyMultipliers(gokStrategyMultipliers);
        } catch (NumberFormatException e){
            System.out.println("Error while parsing settings");
        }

    }

    public void save(){
        ArrayList l = new ArrayList();
        l.add("LoadSaveStrategy:");
        l.add( instellingController.getSaveStrategy());
        writeNextLine(l);

        for (String s : instellingController.getGokStrategyMultipliers().keySet()) {
            ArrayList l2 = new ArrayList();
            l2.add(s);
            l2.add(instellingController.getGokStrategyMultipliers().get(s));
            writeNextLine(l2);
        }
        closeWriter();
    }
}
