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
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

public class InstellingPane extends GridPane {
    VBox layout;
    Label fileLabel;
    InstellingController instellingController;

    RadioButton excelFile;
    RadioButton textFile;
    RadioButton cvsFile;

    VBox gamblerStrategiesButtonsBox;
    VBox gamblerStrategiesTextfieldBox;

    public EnumMap<GokStrategy, CheckBox> gamblerStrategies = new EnumMap<>(GokStrategy.class);

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


        gamblerStrategiesButtonsBox = new VBox();
        gamblerStrategiesButtonsBox.setSpacing(16);

        gamblerStrategiesTextfieldBox = new VBox();
        gamblerStrategiesTextfieldBox.setSpacing(10);

        this.instellingController = instellingController;

        ToggleGroup radioGroup = new ToggleGroup();

        excelFile = new RadioButton( "Data in Excel file");
        excelFile.setToggleGroup(radioGroup);
        excelFile.setOnAction(e -> setSaveLoadController("excel"));
        excelFile.setSelected(true);

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
            strategyMultiplierFields.add(createTextField(i));
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
        layout.setPadding(new Insets(10));

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

    public void setMultiplier(GokStrategy gokStrategy, String multiplier){
        System.out.println("changed multiplier");
        gokStrategy.setMultiplier(Integer.parseInt(multiplier));
    }

    public CheckBox createCheckBox(int index){
        GokStrategy g =  instellingController.getAllGokStrategies().get(index);
        CheckBox checkBox = new CheckBox(g.getName());
        checkBox.setOnAction(event -> setGamblerStrategy(g));
        return checkBox;
    }

    public TextField createTextField(int index){
        GokStrategy g =  instellingController.getAllGokStrategies().get(index);
        TextField textField = new TextField(String.valueOf(g.getMultiplier()));
        textField.setOnAction(event -> setMultiplier(g, textField.getText()));
        return textField;
    }

    private void setCheckBox(GokStrategy gokStrategy, boolean visible){
        gamblerStrategies.get(gokStrategy).setSelected(visible);
    }


}
