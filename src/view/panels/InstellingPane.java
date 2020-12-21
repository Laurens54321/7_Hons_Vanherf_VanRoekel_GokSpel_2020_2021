package view.panels;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class InstellingPane extends GridPane {

    RadioButton excelFile;
    RadioButton textFile;
    Button saveButton;

    public InstellingPane(){
        ToggleGroup radioGroup = new ToggleGroup();
        excelFile = new RadioButton( "Data in Excel file");
        excelFile.setToggleGroup(radioGroup);

        textFile = new RadioButton("Data in tekstfile");
        textFile.setToggleGroup(radioGroup);

        saveButton.setText("Save");


    }
}
