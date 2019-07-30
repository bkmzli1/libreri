package ru.bkmz.lib;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import ru.bkmz.lib.controllers.StageStandart;
import ru.bkmz.lib.table.BD;
import ru.bkmz.lib.table.Table;

public class MainLibtreri extends Application {

    public static final String nameStage = "libs";
    public static final String appdata = System.getenv("APPDATA") + "\\.libs\\";
    private static final String[] filesAll = new String[]{""};


    public static BD bd;

    @Override
    public void start(Stage primaryStage) throws Exception {

        new StageStandart("fxml/lib.fxml", false);
    }

    @Override
    public void init() throws Exception {
        System.out.println("start");
        Processes.file(filesAll);
        CopyFiles.failCopi("BD/", "BD");

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static ObservableList<Table> getTable() {

        ObservableList<Table> tables = FXCollections.observableArrayList();
        for (int i = 0; i < bd.idAll; i++) {
            tables.add(new Table(bd.getId(i), bd.getName(i), bd.getComint(i), bd.getUrl(i)));
        }
        return tables;
    }
}
