/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.SQLUtil;
import java.math.BigInteger;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan
 */
public abstract class Post extends BaseModel{
    @Getter
    @Setter
    protected BigInteger id;
    
    @Getter
    @Setter
    protected BigInteger userId;
    
    @Getter
    @Setter
    protected String content;
    
    @Getter
    @Setter
    protected Date pubDate;
    
    @Getter
    @Setter
    protected double score;
    
    @Getter
    @Setter
    protected BigInteger animeId;
    
    @Getter
    @Setter
    protected boolean question;

    public Post(SQLUtil db) {
        super(db);
    }
}
