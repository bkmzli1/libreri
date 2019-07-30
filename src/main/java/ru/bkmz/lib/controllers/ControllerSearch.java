package ru.bkmz.lib.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import ru.bkmz.lib.Processes;
import ru.bkmz.lib.table.Table;

import java.util.ArrayList;

import static ru.bkmz.lib.MainLibtreri.bd;

public class ControllerSearch {


    public Button menuButton;
    public Button search;
    public TextArea comintTex;
    public TableView table;
    private ArrayList<Integer> id;
    private ObservableList<Table> tables;

    @FXML
    public void initialize() {
        for (String name :
                bd.nameColumn) {
            Processes.column(table, name);
        }
    }

    public void menuButton(ActionEvent actionEvent) {
        menuButton.getScene().getWindow().hide();
        new StageStandart("fxml/lib.fxml", true);
    }


    public void onSearch(ActionEvent actionEvent) {
        id = new ArrayList<Integer>();
        tables = FXCollections.observableArrayList();
        String sr = comintTex.getText();
        int idAll = bd.idAll;
        for (int i = 0; i < idAll; i++) {
            String s = bd.getComint(i);
            int col = s.indexOf(sr);
            if (col != -1) {

                this.id.add(i);
            }
        }
        for (int i :
                this.id) {
            searchTable(i);
        }
        table.setItems(tables);


    }

    private ObservableList<Table> searchTable(int i) {
        tables.add(new Table(bd.getId(i), bd.getName(i), bd.getComint(i), bd.getUrl(i)));

        return tables;
    }
}
