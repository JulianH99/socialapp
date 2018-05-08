/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.SQLUtil;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan
 */
public class AnimeList extends BaseModel{
    
    @Getter
    @Setter
    private BigInteger id;
    
    @Getter
    @Setter
    private List animes;
    
    @Getter
    @Setter
    private long userId;

    @Getter
    @Setter
    private long animeId;
   
    
}
