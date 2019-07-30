package ru.bkmz.lib.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import ru.bkmz.lib.Processes;
import ru.bkmz.lib.table.BD;
import ru.bkmz.lib.table.Table;

import static ru.bkmz.lib.MainLibtreri.bd;
import static ru.bkmz.lib.MainLibtreri.getTable;


public class Controller {
    @FXML
    public Button add;
    @FXML
    public Button delete;
    @FXML
    public Button edition;
    @FXML
    public TableView<Table> table;
    public Button search;
    public Button get;

    @FXML
    public void initialize() {
        upTable();
    }

    @FXML
    public void onAdd(ActionEvent actionEvent) {

        add.getScene().getWindow().hide();

        StageStandart stageStandart = new StageStandart("fxml/libAdd.fxml", false);


    }


    @FXML
    public void onDelete(ActionEvent actionEvent) {
        delete.getScene().getWindow().hide();

        StageStandart stageStandart = new StageStandart("fxml/libDelete.fxml", false);
    }

    @FXML
    public void onEdition(ActionEvent actionEvent) {
        edition.getScene().getWindow().hide();

        StageStandart stageStandart = new StageStandart("fxml/libEdit.fxml", false);
    }

    void upTable() {
        bd = new BD();
        bd.sqliteInfo();
        bd.sqlite();
        for (String name :
                bd.nameColumn) {
            Processes.column(table, name);
        }
        table.setItems(getTable());
        bd = new BD();
        bd.sqliteInfo();
        bd.sqlite();
        table.setItems(getTable());


    }


    public void onSearch(ActionEvent actionEvent) {
        search.getScene().getWindow().hide();
        StageStandart stageStandart = new StageStandart("fxml/libSearch.fxml", false);
    }

    public void onGet(ActionEvent actionEvent) {
        get.getScene().getWindow().hide();
        StageStandart stageStandart = new StageStandart("fxml/libGet.fxml", false);
    }
}
