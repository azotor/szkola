package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    
    protected Connection connection;
    protected Statement stmt;

    public boolean connect(
        String host,
        int port,
        String user,
        String password
    ) {

        try {

            connection = DriverManager.getConnection( "jdbc:mysql://" + host + ":" + String.valueOf( port ), user, password );
            stmt = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );

        } catch ( SQLException e ) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean use( String database ) {

        try {

            connection.setCatalog( database );
            
        } catch ( SQLException e ) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public ArrayList< String > getDatabases() {

        String query = "SHOW DATABASES";
        ArrayList< String > out = new ArrayList< String >();

        try {

            ResultSet result = stmt.executeQuery( query );
            while( result.next() ) {
                out.add( result.getString( 1 ) );
            }

        } catch( SQLException e ) { e.printStackTrace(); }

        return out;

    }

    public ArrayList< String > getTables() {

        String query = "SHOW TABLES";
        ArrayList< String > out = new ArrayList< String >();

        try {

            ResultSet result = stmt.executeQuery( query );
            while( result.next() ) {
                out.add( result.getString( 1 ) );
            }

        } catch( SQLException e ) { e.printStackTrace(); }

        return out;

    }

}
