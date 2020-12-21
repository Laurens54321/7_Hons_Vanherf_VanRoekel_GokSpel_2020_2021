package view.panels;

import controller.InstellingController;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class InstellingPane extends GridPane {
    VBox layout;
    Label fileLabel;
    InstellingController instellingController;

    RadioButton excelFile;
    RadioButton textFile;
    RadioButton cvsFile;

    ArrayList<CheckBox> gamblerStrategies = new ArrayList<>();

    Button saveButton;

    String selectedSaveLoadController = null;


    public InstellingPane(InstellingController instellingController){

        layout = new VBox();
        layout.setSpacing(10);

        fileLabel = new Label();
        fileLabel.setText("Choose file type:");

            HBox fileChoice = new HBox();
        fileChoice.setSpacing(10);

        HBox gamblerStrategiesBox = new HBox();
        gamblerStrategiesBox.setSpacing(10);

        this.instellingController = instellingController;

        ToggleGroup radioGroup = new ToggleGroup();

        excelFile = new RadioButton( "Data in Excel file");
        excelFile.setToggleGroup(radioGroup);
        excelFile.setOnAction(e -> setSaveLoadController("excel"));

        textFile = new RadioButton("Data in tekstfile");
        textFile.setToggleGroup(radioGroup);
        textFile.setOnAction(e -> setSaveLoadController("text"));

        cvsFile = new RadioButton( "Data in Excel file");
        cvsFile.setToggleGroup(radioGroup);
        cvsFile.setOnAction(event -> setSaveLoadController("cvs"));

        fileChoice.getChildren().addAll(excelFile,textFile,cvsFile);

        for (int i = 0; i < instellingController.getAllGokStrategies().size(); i++) {
            gamblerStrategies.add(new CheckBox(instellingController.getAllGokStrategies().get(i)));
        }
        gamblerStrategiesBox.getChildren().addAll(gamblerStrategies);

        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setOnAction(event -> confirmSaveLoadController());

        layout.getChildren().addAll(fileLabel,fileChoice,gamblerStrategiesBox,saveButton);
        this.getChildren().addAll(layout);
    }

    public void setSaveLoadController(String s){
        selectedSaveLoadController = s;
    }

    public void confirmSaveLoadController(){
        instellingController.setLoader(selectedSaveLoadController);
    }
}
