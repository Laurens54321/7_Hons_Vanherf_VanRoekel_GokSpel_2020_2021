package view.panels;

import controller.InstellingController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InstellingPane extends GridPane {
    VBox layout;
    Label fileLabel;
    InstellingController instellingController;

    RadioButton excelFile;
    RadioButton textFile;
    Button saveButton;

    String selectedSaveLoadController = null;


    public InstellingPane(InstellingController instellingController){
        layout = new VBox();
        layout.setSpacing(10);

        fileLabel = new Label();
        fileLabel.setText("Choose file type:");

        HBox fileChoice = new HBox();

        this.instellingController = instellingController;

        ToggleGroup radioGroup = new ToggleGroup();

        excelFile = new RadioButton( "Data in Excel file");
        excelFile.setToggleGroup(radioGroup);
        excelFile.setOnAction(e -> setSaveLoadController("excel"));

        textFile = new RadioButton("Data in tekstfile");
        textFile.setToggleGroup(radioGroup);
        textFile.setOnAction(e -> setSaveLoadController("text"));

        fileChoice.getChildren().addAll(excelFile,textFile);

        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setOnAction(event -> confirmSaveLoadController());

        layout.getChildren().addAll(fileLabel,fileChoice,saveButton);
        this.getChildren().addAll(layout);
    }

    public void setSaveLoadController(String s){
        selectedSaveLoadController = s;
    }

    public void confirmSaveLoadController(){
        instellingController.setLoader(selectedSaveLoadController);
    }
}
