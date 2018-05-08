/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.SQLUtil;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author julian
 */
public class AnimeGender extends BaseModel{
    
    @Getter
    @Setter
    private long id;
    
    @Getter
    @Setter
    private String name;

    
}
