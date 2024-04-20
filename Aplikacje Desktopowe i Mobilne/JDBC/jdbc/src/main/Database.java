package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    protected Connection connection;

    public boolean connect(
        String host,
        int port,
        String user,
        String password
    ) {

        try {

            connection = DriverManager.getConnection( "jdbc:mysql://" + host + ":" + String.valueOf( port ), user, password );

        } catch ( Exception e ) { return false; }

        return true;

    }

    public String[] getDatabases() {
        return new String[] {};
    }

    public String[] getTables() {
        return new String[] {};
    }

}
