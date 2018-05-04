/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.SQLUtil;
import java.math.BigInteger;
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
    
    public AnimeList(SQLUtil db) {
        super(db);
    }

    @Override
    public int save() {
        return 0;
    }

    @Override
    public ArrayList<BaseModel> all() {
        return null;
    }

    @Override
    public BaseModel get(long id) {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    

}
