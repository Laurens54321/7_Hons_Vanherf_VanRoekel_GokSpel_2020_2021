package controller;

import model.database.filecontroller.*;
import model.gokstrategy.GokStrategy;
import view.observer.EnabledGokStrategyObserver;
import view.panels.InstellingPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InstellingController {

    InstellingPane instellingPane;
    GamblerController gamblerController;

    ArrayList<EnabledGokStrategyObserver> gokStrategyObservers = new ArrayList<>();

    TextLoadSaveSetting textLoadSaveSetting;
    String saveStrategy = "excel";


    public InstellingController(GamblerController gamblerController){

        this.gamblerController = gamblerController;
        gamblerController.setInstellingController(this);

        textLoadSaveSetting = new TextLoadSaveSetting(this);
        gokStrategyObservers.add(gamblerController);
        updateEnabledGokStrategyObservers();

        instellingPane = new InstellingPane(this);

    }

    public InstellingPane getInstellingPane() {
        return instellingPane;
    }

    public void setLoader(String formaat){
        if (formaat == null || formaat.isEmpty()) return;
        switch (formaat){
            case "excel":
                gamblerController.getPlayerDB().setSaveStrategy(new ExcelPlayerLoadSavePlayer());
                break;
            case "text":
                gamblerController.getPlayerDB().setSaveStrategy(new TextPlayerLoadSavePlayer());
                break;
            case "csv":
                gamblerController.getPlayerDB().setSaveStrategy(new CSVPlayerLoadSavePlayer());
                break;
        }
    }

    public List<GokStrategy> getAllGokStrategies() {
        return Arrays.asList(GokStrategy.values());
    }

    public void updateEnabledGokStrategyObservers(){
        for (EnabledGokStrategyObserver obs : gokStrategyObservers) {
            obs.updateGokStrategies();
        }
        saveSettings();
    }

    public void setSaveStrategy(String saveStrategy){
        this.saveStrategy = saveStrategy;
    }

    public String getSaveStrategy(){
        return saveStrategy;
    }

    public void setGokStrategyMultipliers(HashMap<String, Integer> gokStrategyMultipliers){
        for (String s : gokStrategyMultipliers.keySet()) {
            for (GokStrategy gokStrategy : GokStrategy.values()) {
                if (gokStrategy.getName().equals(s)){
                    gokStrategy.setMultiplier(gokStrategyMultipliers.get(s));
                    System.out.println("Modified a multiplier from save");
                }

            }
        }
    }

    public HashMap<String, Integer> getGokStrategyMultipliers(){
        HashMap<String, Integer> gokStrategyMultipliers = new HashMap<>();
        for (GokStrategy gokStrategy : GokStrategy.values()) {
            gokStrategyMultipliers.put(gokStrategy.getName(), gokStrategy.getMultiplier());
        }
        return gokStrategyMultipliers;
    }

    public void saveSettings() {
        textLoadSaveSetting.save();
    }

    public void loadSettings(){
        textLoadSaveSetting.load();
    }
}
