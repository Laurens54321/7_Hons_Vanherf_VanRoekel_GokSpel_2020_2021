package view.panels;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.gokstrategy.GokStrategy;


public class StatistiekPane extends GridPane {

    TableView tableView;

    public StatistiekPane(){
        tableView = new TableView();
        TableColumn strategyColumn = new TableColumn("Strategie");
        TableColumn aantalKeerGekozen = new TableColumn("Aantal keer gekozen");
        TableColumn aantalKeerGewonnen = new TableColumn("Aantal keer gewonnen");
        TableColumn totaleInzet = new TableColumn("Totale inzet");
        TableColumn totaleWinst = new TableColumn("Totale winst");


        strategyColumn.setCellFactory(new PropertyValueFactory<>("strategie"));

        aantalKeerGekozen.setMinWidth(10);
        aantalKeerGekozen.setCellValueFactory(new PropertyValueFactory<>("aantalGekozen"));


        aantalKeerGewonnen.setMinWidth(10);
        aantalKeerGewonnen.setCellValueFactory(new PropertyValueFactory<>("aantalGewonnen"));

        totaleInzet.setMinWidth(10);
        totaleInzet.setCellValueFactory(new PropertyValueFactory<>("totalSpent"));

        totaleWinst.setMinWidth(10);
        totaleWinst.setCellValueFactory(new PropertyValueFactory<>("totalProfit"));

        tableView.getColumns().addAll(strategyColumn, aantalKeerGekozen, aantalKeerGewonnen, totaleInzet);

    }

    public void refresh(){
        //tableView.setItems(GokStrategy.getEnumConstants());
    }
}
