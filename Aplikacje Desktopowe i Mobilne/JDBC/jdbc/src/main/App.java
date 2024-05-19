package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class App {

    String host, user, password, base, selectedTable;
    int port;
    ArrayList< String > databases;
    ArrayList< String > tables;

    Database db;

    JFrame loginFrame, mainFrame;
    JPanel databasesPanel, tablesPanel, queryPanel;

    public App() {

        createLoginFrame();
        createMainFrame();

        loginFrame.setVisible( true );

    }

    public void createLoginFrame() {

        loginFrame = new JFrame();
        loginFrame.setTitle( "Logowanie" );
        loginFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        loginFrame.setSize( new Dimension( 400, 400 ) );
        loginFrame.setLocationRelativeTo( null );
        loginFrame.setResizable( false );
        loginFrame.setUndecorated( true );

        JPanel topPanel = new JPanel();
        topPanel.setBackground( Color.decode( "#3498db" ) );
        loginFrame.add( topPanel, BorderLayout.NORTH );

        JLabel titleLabel = new JLabel( "Logowanie" );
        titleLabel.setPreferredSize( new Dimension( 350, 20 ) );
        titleLabel.setForeground( Color.WHITE );
        topPanel.add( titleLabel );

        JButton closeButton = new JButton( "X" );
        closeButton.setBackground( null );
        closeButton.setForeground( Color.WHITE );
        closeButton.setFocusPainted( false );
        closeButton.setBorderPainted( false );
        closeButton.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
        topPanel.add( closeButton );
        closeButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                loginFrame.dispose();
                System.exit( 0 );
            }
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout( new FlowLayout( FlowLayout.CENTER, 10, 20 ) );
        loginFrame.add( centerPanel, BorderLayout.CENTER );

        int labelWidth = 150;
        int labelHeight = 20;
        int fieldColumns = 15;
        Insets fieldMargin = new Insets( 3, 5, 3, 5 );

        JLabel hostLabel = new JLabel( "Adres serwera:" );
        hostLabel.setPreferredSize( new Dimension( labelWidth, labelHeight ) );
        hostLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        centerPanel.add( hostLabel );
        JTextField hostField = new JTextField( "localhost" );
        hostField.setColumns( fieldColumns );
        hostField.setMargin( fieldMargin );
        centerPanel.add( hostField );

        JLabel portLabel = new JLabel( "Port serwera:" );
        portLabel.setPreferredSize( new Dimension( labelWidth, labelHeight ) );
        portLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        centerPanel.add( portLabel );
        JTextField portField = new JTextField( "3306" );
        portField.setColumns( fieldColumns );
        portField.setMargin( fieldMargin );
        centerPanel.add( portField );

        JLabel userLabel = new JLabel( "Użytkownik:" );
        userLabel.setPreferredSize( new Dimension( labelWidth, labelHeight ) );
        userLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        centerPanel.add( userLabel );
        JTextField userField = new JTextField( "root" );
        userField.setColumns( fieldColumns );
        userField.setMargin( fieldMargin );
        centerPanel.add( userField );

        JLabel passLabel = new JLabel( "Hasło:" );
        passLabel.setPreferredSize( new Dimension( labelWidth, labelHeight ) );
        passLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        centerPanel.add( passLabel );
        JPasswordField passField = new JPasswordField();
        passField.setColumns( fieldColumns );
        passField.setMargin( fieldMargin );
        centerPanel.add( passField );

        JButton loginButton = new JButton( "Zaloguj" );
        centerPanel.add( loginButton );

        JLabel logLabel = new JLabel( "Status..." );
        logLabel.setPreferredSize( new Dimension( 350, 50 ) );
        logLabel.setForeground( Color.RED );
        centerPanel.add( logLabel );

        loginButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                db = new Database();
                if(
                    db.connect(
                        hostField.getText(),
                        Integer.parseInt( portField.getText() ),
                        userField.getText(),
                        new String( passField.getPassword() )
                    )
                ) {
                    logLabel.setText( "Połączono z bazą danych..." );
                    host = hostField.getText();
                    port = Integer.parseInt( portField.getText() );
                    user = userField.getText();
                    password = new String( passField.getPassword() );
                    //loginFrame.setVisible( false );
                    loginFrame.dispose();
                    runMainFrame();

                } else logLabel.setText( "Nie udało się połączyć z bazą danych!!!" );
            }
        });

    }

    public void createMainFrame() {

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        mainFrame.setSize( new Dimension( 800, 400 ) );
        mainFrame.setLocationRelativeTo( null );
        mainFrame.setResizable( false );
        //mainFrame.setUndecorated( true );

    }

    public void runMainFrame() {
        mainFrame.setTitle( "Host: " + host + ":" + String.valueOf( port ) + " | Użytkownik: " + user );
        
        databasesPanel = new JPanel();
        databasesPanel.setPreferredSize( new Dimension( 200, 400 ) );
        mainFrame.add( databasesPanel, BorderLayout.WEST );

        JLabel databasesLabel = new JLabel( "Bazy danych", JLabel.CENTER );
        databasesLabel.setOpaque(true);
        databasesLabel.setPreferredSize( new Dimension( 200, 40 ) );
        databasesLabel.setBackground( Color.decode( "#3498db" ) );
        databasesLabel.setForeground( Color.WHITE );
        databasesPanel.add( databasesLabel );

        tablesPanel = new JPanel();
        tablesPanel.setPreferredSize( new Dimension( 200, 400 ) );
        mainFrame.add( tablesPanel, BorderLayout.CENTER );

        JLabel tablesLabel = new JLabel( "Tabele", JLabel.CENTER );
        tablesLabel.setOpaque(true);
        tablesLabel.setPreferredSize( new Dimension( 200, 40 ) );
        tablesLabel.setBackground( Color.decode( "#3498db" ) );
        tablesLabel.setForeground( Color.WHITE );
        tablesPanel.add( tablesLabel );

        queryPanel = new JPanel();
        queryPanel.setPreferredSize( new Dimension( 380, 400 ) );
        mainFrame.add( queryPanel, BorderLayout.EAST );

        JLabel queryLabel = new JLabel( "Rekordy", JLabel.CENTER );
        queryLabel.setOpaque(true);
        queryLabel.setPreferredSize( new Dimension( 380, 40 ) );
        queryLabel.setBackground( Color.decode( "#3498db" ) );
        queryLabel.setForeground( Color.WHITE );
        queryPanel.add( queryLabel );

        databases = db.getDatabases();
        for( String database : databases ) {
            JButton databaseButton = new JButton( database );
            databaseButton.setPreferredSize( new Dimension( 180, 30 ) );
            databasesPanel.add( databaseButton );
            databaseButton.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e ) {
                    base = e.getActionCommand();
                    db.use( base );
                    showTables();
                }
            });
        }

        mainFrame.setVisible( true );
    }

    public void showTables() {

        tablesPanel.removeAll();
        tablesPanel.revalidate();
        tablesPanel.repaint();

        JLabel tablesLabel = new JLabel( "Tabele", JLabel.CENTER );
        tablesLabel.setOpaque(true);
        tablesLabel.setPreferredSize( new Dimension( 200, 40 ) );
        tablesLabel.setBackground( Color.decode( "#3498db" ) );
        tablesLabel.setForeground( Color.WHITE );
        tablesPanel.add( tablesLabel );

        tables = db.getTables();
        for( String table : tables ) {
            JButton tableButton = new JButton( table );
            tableButton.setPreferredSize( new Dimension( 180, 30 ) );
            tablesPanel.add( tableButton );
            tableButton.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e ) {
                    selectedTable = e.getActionCommand();
                    showTable();

                }
            });
        }

        mainFrame.setVisible( true );

    }

    public void showTable() {

        queryPanel.removeAll();
        queryPanel.revalidate();
        queryPanel.repaint();

        ArrayList< ArrayList< String > > rows = db.selectAll( selectedTable );

        String[] header = new String[ rows.get( 0 ).size() ];

        for( int i = 0; i < rows.get( 0 ).size(); i++ ) {

            header[ i ] = rows.get( 0 ).get( i );

        }

        rows.remove( 0 );

        String[][] datas = new String[ rows.size() ][ rows.get( 0 ).size() ];

        for( int i = 0; i < rows.size(); i++ ) {

            ArrayList< String > row = rows.get( i );

            for( int j = 0; j < row.size(); j++ ) {

                datas[ i ][ j ] = row.get( j );

            }

        }

        JLabel queryLabel = new JLabel( "Rekordy", JLabel.CENTER );
        queryLabel.setOpaque(true);
        queryLabel.setPreferredSize( new Dimension( 380, 40 ) );
        queryLabel.setBackground( Color.decode( "#3498db" ) );
        queryLabel.setForeground( Color.WHITE );
        queryPanel.add( queryLabel );

        JTable table = new JTable( datas, header );
        table.setPreferredScrollableViewportSize( table.getPreferredSize() );
        table.setFillsViewportHeight( true );
        table.setDefaultEditor( Object.class, null );
        JScrollPane scroll = new JScrollPane(table);
        //scroll.setSize( new Dimension( 380, 300 ) );

        queryPanel.add( scroll );
    }

}