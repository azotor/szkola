package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
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
            stmt.executeUpdate("use " + database + ";");
            //connection.setCatalog( database );
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

    public ArrayList< ArrayList< String > > selectAll( String table ) {

        String query = "SELECT * FROM `" + table + "`";
        ArrayList< ArrayList< String > > out = new ArrayList< ArrayList< String > >();

        try {

            ResultSet result = stmt.executeQuery( query );
            ResultSetMetaData meta = result.getMetaData();

            ArrayList< String > header = new ArrayList< String >();

            for( int i = 1; i <= meta.getColumnCount(); i++ ) {
                header.add( meta.getColumnName( i ) );
            }

            out.add( header );

            while( result.next() ) {

                ArrayList< String > row = new ArrayList< String >();
                
                for( int i = 1; i <= meta.getColumnCount(); i++ ) {

                    row.add( result.getString( i ) );

                }

                out.add( row );

            }

        } catch( SQLException e ) { e.printStackTrace(); }

        return out;

    }

}
