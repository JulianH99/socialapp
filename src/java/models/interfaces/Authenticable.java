/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.interfaces;

/**
 *
 * @author julian
 */
public interface Authenticable {
    
    /**
     * 
     * Authenticates the user with username and password given
     * 
     * @param username
     * @param password
     * @return true if the user was successfully authenticated 
     */
    public boolean login(String username, String password);
    
    /**
     * Registers the user in the social network database
     * 
     * @return true if the user was successfully registered 
     */
    public boolean register();
    
    /**
     * logs out the user, cleaning session variables
     */
    public void logout();
    
    /**
     * Hashes the given password with Bcrypt algorithm
     * 
     * @param password
     * @return hashed password 
     */
    public String hashPassword(String password);
    
    /**
     *
     * @param password
     * @return
     */
    public boolean checkPassword(String password);
    
}
