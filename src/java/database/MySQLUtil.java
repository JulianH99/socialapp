/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author julian
 */
public class MySQLUtil extends SQLUtil{
    
    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String USER = "root";
    private final String PASSWORD = "sabertooth99";
    private final String DB = "socialapp";
    private final String URL = "jdbc:mysql://localhost/%1$s";

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String connectionString = String.format(URL, DB);
            
            Connection connection = DriverManager.getConnection(connectionString, 
                                                                USER, PASSWORD);
            
            return connection;
            
            
            
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error instantiating connection");
            return null;
        }
        
        
    }

    @Override
    public void freeConnection() throws SQLException {
    }
    
}
