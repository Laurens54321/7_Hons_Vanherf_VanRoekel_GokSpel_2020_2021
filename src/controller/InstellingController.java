package controller;

import javafx.scene.Node;
import model.database.filecontroller.ExcelLoadSavePlayer;
import model.database.filecontroller.TextLoadSavePlayer;
import model.gokstrategy.GokStrategy;
import view.observer.EnabledGokStrategyObserver;
import view.panels.InstellingPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InstellingController {

    InstellingPane instellingPane;
    GamblerController gamblerController;

    ArrayList<EnabledGokStrategyObserver> gokStrategyObservers = new ArrayList<>();


    public InstellingController(GamblerController gamblerController){
        instellingPane = new InstellingPane(this);
        this.gamblerController = gamblerController;

        gokStrategyObservers.add(gamblerController.getGamblerView());
    }

    public InstellingPane getInstellingPane() {
        return instellingPane;
    }

    public void setLoader(String formaat){
        if (formaat == null || formaat.isEmpty()) return;
        switch (formaat){
            case "excel":
                gamblerController.getPlayerDB().setSaveStrategy(new ExcelLoadSavePlayer());
                break;
            case "text":
                gamblerController.getPlayerDB().setSaveStrategy(new TextLoadSavePlayer());
                break;
        }
    }

    public List<GokStrategy> getAllGokStrategies() {
        return Arrays.asList(GokStrategy.values());
    }

    public void updateEnabledGokStrategyObservers(GokStrategy gokStrategy, boolean active){
        for (EnabledGokStrategyObserver obs : gokStrategyObservers) {
            gokStrategy.setActive(active);
            obs.updateGokStrategies(gokStrategy);
        }
    }
}
