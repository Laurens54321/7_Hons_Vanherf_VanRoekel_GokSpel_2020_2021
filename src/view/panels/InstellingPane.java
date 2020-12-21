package view.panels;

import controller.InstellingController;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class InstellingPane extends GridPane {
    InstellingController instellingController;

    RadioButton excelFile;
    RadioButton textFile;
    Button saveButton;

    public InstellingPane(InstellingController instellingController){
        this.instellingController = instellingController;

        ToggleGroup radioGroup = new ToggleGroup();
        excelFile = new RadioButton( "Data in Excel file");
        excelFile.setToggleGroup(radioGroup);
        excelFile.setOnAction(event -> setSaveLoadController("excel"));

        textFile = new RadioButton("Data in tekstfile");
        textFile.setToggleGroup(radioGroup);
        textFile.setOnAction(event -> setSaveLoadController("text"));

        saveButton = new Button();
        saveButton.setText("Save");

        this.getChildren().addAll(excelFile, textFile, saveButton);
    }

    public void setSaveLoadController(String s){

    }
}
