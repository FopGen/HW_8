package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Database {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/migration";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1";
    private static Connection connection;

    Database(){
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static Connection getConnection() throws SQLException{
        if(Objects.isNull(connection)){
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
