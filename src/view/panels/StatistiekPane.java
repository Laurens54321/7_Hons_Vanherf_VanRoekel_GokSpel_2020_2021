package view.panels;

import controller.StatistiekController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Player;
import model.StrategyStat;
import model.gokstrategy.GokStrategy;


public class StatistiekPane extends GridPane {
    StatistiekController statistiekController;

    private ObservableList<StrategyStat> stats;
    private TableView<StrategyStat> table;

    TableView tableView;

    public StatistiekPane(StatistiekController statistiekController){
        this.statistiekController = statistiekController;

        Label lblHeading = new Label ("Player List: ");
        lblHeading.setFont(new Font("Arial", 20));

        table = new TableView<StrategyStat>();
        refresh();

        TableColumn<StrategyStat, String> colGokStrategy = new TableColumn<>("Gok Strategy");
        colGokStrategy.setMinWidth(120);
        colGokStrategy.setCellValueFactory(new PropertyValueFactory<>("gokStrategyString"));

        TableColumn<StrategyStat, Integer> colCount = new TableColumn<>("# of games");
        colCount.setMinWidth(100);
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));

        TableColumn<StrategyStat, Integer> colWon = new TableColumn<>("# of games won");
        colWon.setMinWidth(100);
        colWon.setCellValueFactory(new PropertyValueFactory<>("won"));

        TableColumn<StrategyStat, Double> colBetTotal = new TableColumn<>("€ bet");
        colBetTotal.setMinWidth(100);
        colBetTotal.setCellValueFactory(new PropertyValueFactory<>("betTotal"));

        TableColumn<StrategyStat, Double> colWonTotal = new TableColumn<>("€ won");
        colWonTotal.setMinWidth(100);
        colWonTotal.setCellValueFactory(new PropertyValueFactory<>("wonTotal"));

        table.getColumns().addAll(colGokStrategy, colCount, colWon, colBetTotal, colWonTotal);

        this.add(table, 5, 5, 5, 5);
    }

    public void refresh(){
        stats = FXCollections.observableArrayList(statistiekController.getStrategyStats());
        table.setItems(stats);
        table.refresh();
    }
}
