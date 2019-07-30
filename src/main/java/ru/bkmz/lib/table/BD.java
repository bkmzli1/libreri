package ru.bkmz.lib.table;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static ru.bkmz.lib.MainLibtreri.appdata;

public class BD {
    private static Map<Integer, Integer> languageMapId;
    private static Map<Integer, String> languageMapName;
    private static Map<Integer, String> languageMapComint;
    private static Map<Integer, String> languageMapUrl;

    private static Connection con;
    private static String url = "jdbc:sqlite:" + appdata + "res/BD/BD";

    public static String[] nameColumn;
    public int idAll;

    private void conection() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void sqlite() {
        languageMapId = new HashMap<Integer, Integer>();
        languageMapName = new HashMap<Integer, String>();
        languageMapComint = new HashMap<Integer, String>();
        languageMapUrl = new HashMap<Integer, String>();

        try {
            conection();
            Statement statement = con.createStatement();
            query(statement);

        } catch (Exception e) {

        }
        close();
    }

    static void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName(int i) {
        return languageMapName.get(i);
    }

    public String getComint(int i) {
        return languageMapComint.get(i);
    }

    public String getUrl(int i) {
        return languageMapUrl.get(i);
    }

    public int getId(int i) {
        return languageMapId.get(i);
    }

    public void sqliteAdd(String name, String comint, String url) {
        try {
            conection();
            Statement statement = con.createStatement();
            name = name.replace("'", "\"");
            comint = comint.replace("'", "\"");
            url = url.replace("'", "\"");
            statement.execute("INSERT INTO \"Data\" (\"id\",\"name\", \"comint\", \"url\") VALUES ('" + idAll + "', '" + name + "', '" + comint + "', '" + url + "')");

            query(statement);

        } catch (Exception e) {

        }
        close();
    }

    private void query(Statement statement) throws SQLException {

        ResultSet rs = statement.executeQuery("SELECT * FROM " + "Data");

        idAll = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String comint = rs.getString("comint");
            String url = rs.getString("url");
            if (id != idAll) {
                sqliteUp(id, "", "", "", idAll + "", statement);
                close();


                sqlite();


            }

            languageMapId.put(idAll, id + 1);
            languageMapName.put(idAll, name);
            languageMapComint.put(idAll, comint);
            languageMapUrl.put(idAll, url);

            idAll++;
        }
    }


    public void sqliteInfo() {
        try {
            conection();
            Statement statement = con.createStatement();
            String[] name;
            ResultSet rs = statement.executeQuery("pragma table_info(Data)");
            String s = "";
            while (rs.next()) {
                s += rs.getString("name") + "!";
            }
            name = s.split("!");
            nameColumn = name;
        } catch (Exception e) {

        }
        close();
    }

    public void sqliteDelete(int id) {
        try {
            conection();
            Statement statement = con.createStatement();
            statement.execute("DELETE FROM \"Data\" WHERE \"id\" = " + (id - 1));

        } catch (Exception e) {

        }
        close();
    }

    public void sqliteUp(int editid, String name, String comint, String url, String id) {
        String execute = "";
        int col = 0;
        try {
            conection();
            Statement statement = con.createStatement();
            edit(editid, name, comint, url, id, statement, execute, col);

        } catch (Exception e) {

        }
        close();
    }

    private void sqliteUp(int editid, String name, String comint, String url, String id, Statement statement) {
        String execute = "";
        int col = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            edit(editid, name, comint, url, id, statement, execute, col);

        } catch (Exception e) {

        }

    }

    private void edit(int editid, String name, String comint, String url, String id, Statement statement, String execute, int col) throws SQLException {
        if (!id.equals("")) {
            int idi = Integer.parseInt(id);
            execute += " \"id\" = " + idi;
            col++;
        }
        if (!name.equals("")) {
            if (col > 0) {
                execute += ",";
            }
            execute += " \"name\" = '" + name + "'";
            col++;
        }
        if (!comint.equals("")) {
            if (col > 0) {
                execute += ",";
            }
            execute += " \"comint\" = '" + comint + "'";
            col++;
        }
        if (!url.equals("")) {
            if (col > 0) {
                execute += ",";
            }
            execute += "\"url\" = '" + url + "'";
            col++;
        }
        if (col != 0) {
            statement.execute("UPDATE\"Data\" SET " + execute + " WHERE \"id\" = " + editid);
        }
        query(statement);
    }
}


