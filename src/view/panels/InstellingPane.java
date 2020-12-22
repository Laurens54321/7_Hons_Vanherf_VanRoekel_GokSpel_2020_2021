package view.panels;

import controller.InstellingController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.gokstrategy.GokStrategy;
import view.observer.EnabledGokStrategyObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InstellingPane extends GridPane {
    VBox layout;
    Label fileLabel;
    InstellingController instellingController;

    RadioButton excelFile;
    RadioButton textFile;
    RadioButton cvsFile;

    public HashMap<GokStrategy, CheckBox> gamblerStrategies = new HashMap<>();

    Button saveButton;

    String selectedSaveLoadController = null;


    public InstellingPane(InstellingController instellingController){

        layout = new VBox();
        layout.setSpacing(10);

        fileLabel = new Label();
        fileLabel.setText("Choose file type:");

        HBox fileChoice = new HBox();
        fileChoice.setSpacing(10);

        HBox strategiesBox = new HBox();
        strategiesBox.setSpacing(10);
        strategiesBox.setPadding(new Insets(10));


        VBox gamblerStrategiesButtonsBox = new VBox();
        gamblerStrategiesButtonsBox.setSpacing(16);

        VBox gamblerStrategiesTextfieldBox = new VBox();
        gamblerStrategiesTextfieldBox.setSpacing(10);

        this.instellingController = instellingController;

        ToggleGroup radioGroup = new ToggleGroup();

        excelFile = new RadioButton( "Data in Excel file");
        excelFile.setToggleGroup(radioGroup);
        excelFile.setOnAction(e -> setSaveLoadController("excel"));

        textFile = new RadioButton("Data in tekstfile");
        textFile.setToggleGroup(radioGroup);
        textFile.setOnAction(e -> setSaveLoadController("text"));

        cvsFile = new RadioButton( "Data in cvs file");
        cvsFile.setToggleGroup(radioGroup);
        cvsFile.setOnAction(event -> setSaveLoadController("cvs"));

        fileChoice.getChildren().addAll(excelFile,textFile,cvsFile);

        List<GokStrategy> gokStrategies = instellingController.getAllGokStrategies();
        List<TextField> strategyMultiplierFields = new ArrayList<>();


        for (int i = 0; i < gokStrategies.size(); i++) {
            gamblerStrategies.put(gokStrategies.get(i), createCheckBox(i));
            strategyMultiplierFields.add(new TextField(String.valueOf(gokStrategies.get(i).getMultiplier())));
        }
        Label strategyTitleLabel = new Label();
        strategyTitleLabel.setText("Strategies");
        gamblerStrategiesButtonsBox.getChildren().addAll(strategyTitleLabel);
        gamblerStrategiesButtonsBox.getChildren().addAll(gamblerStrategies.values());

        Label multiplierTitleLabel = new Label();
        multiplierTitleLabel.setText("Multiplier");
        gamblerStrategiesTextfieldBox.getChildren().addAll(multiplierTitleLabel);
        gamblerStrategiesTextfieldBox.getChildren().addAll(strategyMultiplierFields);

        strategiesBox.getChildren().addAll(gamblerStrategiesButtonsBox,gamblerStrategiesTextfieldBox);

        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setOnAction(event -> confirmSaveLoadController());

        layout.getChildren().addAll(fileLabel,fileChoice,strategiesBox,saveButton);
        this.getChildren().addAll(layout);

        for (GokStrategy g : instellingController.getAllGokStrategies()) {
            setCheckBox(g, g.isActive());
        }
    }

    public void setSaveLoadController(String s){
        selectedSaveLoadController = s;
    }

    public void confirmSaveLoadController(){
        instellingController.setLoader(selectedSaveLoadController);
        instellingController.saveSettings();
    }

    public void setGamblerStrategy(GokStrategy gokStrategy){
        gokStrategy.setActive(gamblerStrategies.get(gokStrategy).isSelected());
    }

    public CheckBox createCheckBox(int index){
        GokStrategy g =  instellingController.getAllGokStrategies().get(index);
        CheckBox checkBox = new CheckBox(g.getName());
        checkBox.setOnAction(event -> setGamblerStrategy(g));
        return checkBox;
    }

    private void setCheckBox(GokStrategy gokStrategy, boolean visible){
        gamblerStrategies.get(gokStrategy).setSelected(visible);
    }


}
