/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.SQLUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import models.interfaces.Authenticable;
import models.interfaces.HasExistence;
import lombok.Getter;
import lombok.Setter;
import lib.BCrypt;

/**
 * 
 * Represents the authenticable and database-communicating user model
 *
 * @author Juan
 * @version 0.2
 * @see models.BaseModel
 * @see models.interfaces.Authenticable
 */
public class User extends BaseModel implements Authenticable, HasExistence{
 
    
    @Getter
    @Setter
    private long id;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String password;
    
    @Getter
    @Setter
    private String email;    
    
    @Getter
    @Setter
    private short gender;
    
    @Getter
    @Setter
    private Date registerDate;

    public User(SQLUtil db) {
        super(db);
    }
    

    /**
     * saves the user to the database
     * @return number of affected rows. If user was saved should be greater
     * than 0
     */
    @Override
    public int save() {
        try {
            
            String saveQuery = "insert into users (name, password, email"
                    + ", register_date, gender) values (?, ?, ?, ?)";
            
            this.db.setQuery(saveQuery);
            
            PreparedStatement stmt = db.getStatement();
            
            stmt.setString(1, this.name);
            stmt.setString(2, this.password);
            stmt.setString(3, this.email);
            stmt.setDate(4, (java.sql.Date) new Date());
            stmt.setShort(5, this.gender);
            
            return stmt.executeUpdate();
            
            
        }catch(SQLException ex) {
            
            return -1;
        }
        finally{
            this.db.close();
        }
    }

    @Override
    public ArrayList all() {
        try{
            String query = "select * from users";
            
            User tmpUser = new User(null);
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList users = new ArrayList();
            
            while(rs.next()){
                tmpUser.setId(rs.getLong("id"));
                tmpUser.setName(rs.getString("name"));
                tmpUser.setPassword("*");
                tmpUser.setEmail("");
                tmpUser.setGender(rs.getShort("gender"));
                
                users.add(tmpUser);
            }
            
            return users;
            
        }
        catch(SQLException ex) {
            return new ArrayList<>();
        }
        finally{
            this.db.close();
        }
    }
    
    @Override
    public boolean exists(String columnName, String value) {
        try{
            
            String query = "select * from users where " + columnName + 
                    " = ?";
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            stmt.setString(1, value);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                this.setId(rs.getLong("id"));
                return true;
            }
            return false;
            
        }
        catch(SQLException ex) {
            return false;
        }
        finally{
            this.db.close();
        }
    }

    @Override
    public User get(long id) {
        try {
            String getQuery = "select * from users where id = ?";
            
            this.db.setQuery(getQuery);
            
            PreparedStatement stmt = this.db.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            // resultant user
            User user = new User(this.db);
            
            if(rs.next()){
                user.setId(id);
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRegisterDate(rs.getDate("register_date"));
                user.setGender(rs.getShort("gender"));
            }
            
            return user;
        }
        catch (SQLException ex) {
            return null;
        }
        finally {
            this.db.close();
        }
    }

    @Override
    public boolean update() {
        try {
            String query = "update users set name = ?, password = ?,"
                    + "email = ? where id = ?";
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            stmt.setString(1, this.name);
            stmt.setString(2, this.hashPassword(this.password));
            stmt.setString(3, this.email);
            stmt.setLong(4, this.id);
            
            return stmt.execute();
        }
        catch(SQLException ex){
            return false;
        }
        finally{
            this.db.close();
        }
    }

    @Override
    public boolean delete() {
        try{
            String query = "delete from users where id = ?";
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            stmt.setLong(1, this.id);
            
            return stmt.execute();
        }
        catch(SQLException ex) {
            return false;
        }
        finally{
            this.db.close();
        }
    }

    @Override
    public boolean login(String username, String password) {
        this.setName(username);
        
        return this.exists("username", this.name) && 
                this.checkPassword(password);
    }
    
    @Override
    public boolean register() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String hashPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkPassword(String password) {
        try{
            String query = "select password from users where id = ?";
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            stmt.setLong(1, this.id);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                String hashedPassword = rs.getString("password");
                return BCrypt.checkpw(this.password, hashedPassword);
            }
            return false;
        }
        catch(SQLException ex){
            return false;
        }
    }

    
}
