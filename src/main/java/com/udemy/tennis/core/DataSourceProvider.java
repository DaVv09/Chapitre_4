package com.udemy.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static BasicDataSource singleDataSource; //declaration de la variable connection
    public static DataSource getSingleDataSourceInstance(){
        if(singleDataSource==null){
            singleDataSource = new BasicDataSource(); //affectation de l'instance au la variable singleDataSource
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            singleDataSource.setInitialSize(5);
            singleDataSource.setUsername("davv");
            singleDataSource.setPassword("davv");
        }
        return singleDataSource;
    }
}




