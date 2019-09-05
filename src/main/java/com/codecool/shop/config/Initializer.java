package com.codecool.shop.config;

import com.codecool.shop.database.DataBaseDao;
import com.codecool.shop.database.DatabaseConnection;
import com.codecool.shop.database.DatabaseLive;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseConnection dbConnect = new DatabaseConnection();
        dbConnect.setDatabase(new DatabaseLive());
    }

}
