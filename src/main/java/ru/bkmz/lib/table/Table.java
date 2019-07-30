package ru.bkmz.lib.table;

public class Table {
    private int id;
    private String name;
    private String comint;
    private String url;

    public Table(int id, String name, String comint, String url) {
        this.id = id;
        this.name = name;
        this.comint = comint;
        this.url = url;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComint() {
        return comint;
    }

    public void setComint(String comint) {
        this.comint = comint;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
