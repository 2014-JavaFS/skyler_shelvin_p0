package Util;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Stack;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory = new ConnectionFactory(); //eagerly instantiating the object

    private Properties properties = new Properties();

    //connecting to the database
    private ConnectionFactory(){
        try{
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //static block to CHECK that you have your intended driver

    static{
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getConnectionFactory(){
        return connectionFactory;
    }

    //obfuscate info from db.properties
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","password");
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }





}
