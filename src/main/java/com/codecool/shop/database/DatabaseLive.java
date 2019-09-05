package com.codecool.shop.database;

public class DatabaseLive implements DataBaseDao {

    String url = "jdbc:postgresql://localhost:5432/";
    String dataBase = System.getenv("DATABASE");

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getDatabase() {
        return dataBase;
    }

    @Override
    public String getConnection() {
        return url + dataBase;

    }
}
