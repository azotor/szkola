package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class App {

    String host, user, password;
    int port;

    JFrame loginFrame, mainFrame;

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
                Database db = new Database();
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
                    mainFrame.setVisible( true );   
                    loginFrame.setVisible( false );

                } else logLabel.setText( "Nie udało się połączyć z bazą danych!!!" );
            }
        });

    }

    public void createMainFrame() {

        mainFrame = new JFrame();
        mainFrame.setTitle( "Host: " + host + ":" + String.valueOf( port ) + " | Użytkownik: " + user );
        mainFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        mainFrame.setSize( new Dimension( 800, 400 ) );
        mainFrame.setLocationRelativeTo( null );
        mainFrame.setResizable( false );
        //mainFrame.setUndecorated( true );

    }

}