package controller;

import javafx.scene.Node;
import model.database.filecontroller.ExcelLoadSavePlayer;
import model.database.filecontroller.TextLoadSavePlayer;
import view.panels.InstellingPane;

public class InstellingController {

    InstellingPane instellingPane;
    GamblerController gamblerController;


    public InstellingController(GamblerController gamblerController){
        instellingPane = new InstellingPane(this);
        this.gamblerController = gamblerController;
    }

    public InstellingPane getInstellingPane() {
        return instellingPane;
    }

    public void setLoader(String formaat){
        switch (formaat){
            case "excel":
                gamblerController.getPlayerDB().setSaveStrategy(new ExcelLoadSavePlayer());
                break;
            case "text":
                gamblerController.getPlayerDB().setSaveStrategy(new TextLoadSavePlayer());
                break;
        }
    }
}
