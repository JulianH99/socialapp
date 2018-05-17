/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author julian
 * 
 */
abstract public class SQLUtil {
    
    /**
     * 
     */
    public static boolean PREPARED = true;
    
    public static boolean CALLABLE = false;
    
    
    private Connection connection = null;
    
    private PreparedStatement stmt = null;
    
    private ResultSet rset = null;
    
    private boolean mode = PREPARED;
    
    
    abstract public Connection getConnection() throws SQLException;
    
    abstract public void freeConnection() throws SQLException;
    
    
    public void setQuery(String query) throws SQLException {
        
        if (this.connection == null) {
            System.out.println("Connection is null");
            this.connection = this.getConnection();
        }
        
        if (this.mode == PREPARED) {
            System.out.println("Getting prepared statement");
            this.stmt = this.connection.prepareStatement(query);
        } else {
            this.stmt = this.connection.prepareCall(query);
        }
        
    }
    
    
    public void setMode(boolean mode) {
        this.mode = mode;
    }
    
    public PreparedStatement getStatement() {
        return this.stmt;
    }
    
    public void close() {
        
        if (this.connection != null) {
            try {
                this.freeConnection();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            this.connection = null;
        }
        
    }
    
    
    
    
    
}
