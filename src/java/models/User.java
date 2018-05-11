/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Represents the authenticable and database-communicating user model
 *
 * @author Juan
 * @version 0.2
 * @see models.BaseModel
 */
public class User extends BaseModel{
 
    
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
    
    @Getter
    @Setter
    private String picturePath;
    
}
