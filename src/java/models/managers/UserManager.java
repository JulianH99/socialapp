/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.managers;

import database.SQLUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import lib.BCrypt;
import models.User;
import models.interfaces.CanAuthenticate;
import models.interfaces.CanSave;
import models.interfaces.CanUpdate;
import models.interfaces.CanGetSingle;
import models.interfaces.CanCheckExistence;


/**
 *
 * @author julian
 */
public class UserManager extends AbstractManager
implements CanSave<User>, CanUpdate<User>, CanGetSingle<User>,
        CanCheckExistence<User>, CanAuthenticate<User>{
    
    public UserManager(SQLUtil dataSource) {
        super(dataSource);
    }

    @Override
    public boolean save(User model) {
        try {
            
            String saveQuery = "insert into users (name, password, email"
                    + ", register_date, gender, picture_path)"
                    + " values (?, ?, ?, ?, \'default.png\')";
            
            this.dataSource.setQuery(saveQuery);
            
            PreparedStatement stmt = dataSource.getStatement();
            
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getPassword());
            stmt.setString(3, model.getEmail());
            stmt.setDate(4, (java.sql.Date) new Date());
            stmt.setShort(5, model.getGender());
            
            return stmt.execute();
            
            
        }catch(SQLException ex) {
            
            return false;
        }
        finally{
            this.dataSource.close();
        }
    }

    @Override
    public boolean update(User model) {
        try {
            String query = "update users set name = ?, password = ?,"
                    + "email = ?, picture_path = ? where id = ?";
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            stmt.setString(1, model.getName());
            stmt.setString(2, this.hashPassword(model.getPassword()));
            stmt.setString(3, model.getEmail());
            stmt.setString(4, model.getPicturePath());
            stmt.setLong(5, model.getId());
            
            
            return stmt.execute();
        }
        catch(SQLException ex){
            return false;
        }
        finally{
            this.dataSource.close();
        }
    }

    @Override
    public User get(long id) {
        try {
            String getQuery = "select * from users where id = ?";
            
            this.dataSource.setQuery(getQuery);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            // resultant user
            User user = new User();
            
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
            this.dataSource.close();
        }
    }

    @Override
    public User exists(String column, Object value) {
        try{
            
            String query = "select * from users where " + column + 
                    " = ?";
            User newUser = new User();
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            stmt.setString(1, (String) value);
            
            ResultSet rs = stmt.executeQuery();
            
            
            
            if (rs.next()){
                newUser.setId(rs.getLong("id"));
                return newUser;
            }
            return null;
            
        }
        catch(SQLException ex) {
            System.out.println("Error checking existence: " + ex.getMessage());
            return null;
        }
        finally{
            this.dataSource.close();
        }
    }

    @Override
    public boolean login(User user) {
        
        System.out.println("Checking " + user.getName());
        User checkedUser = this.exists("name", user.getName());
        
        if(checkedUser != null) {
            System.out.println("User exists");
            checkedUser.setPassword(user.getPassword());
            return this.checkPassword(checkedUser);
        }
        System.out.println("User doesnt exist");
        return false;
        
    }
    
    
    @Override
    public boolean register() {
        //TODO : implementar metodo de register
        return false;
    }
    
    
    public boolean checkPassword(User user) {
        try{
            String query = "select password from users where id = ?";
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            stmt.setLong(1, user.getId());
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                String hashedPassword = rs.getString("password");
                
               
                System.out.println("Hola " + user.getPassword());
                return hashedPassword.equals(user.getPassword());
                //return BCrypt.checkpw(user.getPassword(), hashedPassword);
            }
            return false;
        }
        catch(SQLException ex){
            return false;
        }
    }
    
    
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    
    

    
}
