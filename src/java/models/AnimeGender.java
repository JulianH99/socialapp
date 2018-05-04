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

    public AnimeGender(SQLUtil db) {
        super(db);
    }
    

    @Override
    public int save() {
        try {
            
            String saveQuery = "insert into anime_genders (name)"
                    + " values(?)";
            
            this.db.setQuery(saveQuery);
            
            PreparedStatement stmt = db.getStatement();
            
            stmt.setString(1, this.name);
            
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
            String query = "select * from anime_genders";
            
            AnimeGender tmpGender = new AnimeGender(this.db);
            
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<AnimeGender> genders = new ArrayList();
            
            while(rs.next()){
                tmpGender.setId(rs.getLong("id"));
                tmpGender.setName(rs.getString("name"));
                
                genders.add(tmpGender);
            }
            
            return genders;
            
        }
        catch(SQLException ex) {
            return new ArrayList<>();
        }
        finally{
            this.db.close();
        }
    }

    @Override
    public AnimeGender get(long id) {
        try {
            String getQuery = "select * from anime_genders where id = ?";
            
            this.db.setQuery(getQuery);
            
            PreparedStatement stmt = this.db.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            // resultant user
            AnimeGender gender = new AnimeGender(this.db);
            
            if(rs.next()){
                gender.setId(id);
                gender.setName(rs.getString("name"));
                
            }
            
            return gender;
        }
        catch (SQLException ex) {
            return null;
        }
        finally {
            this.db.close();
        }
    }
    
    public ArrayList<AnimeGender> getForAnime(long animeId){
        try{
            
            String query = "call getGendersForAnime(?)";
            
            ArrayList<AnimeGender> genders = new ArrayList<>();
            
            AnimeGender tmpGender = new AnimeGender(this.db);
            
            this.db.setMode(SQLUtil.CALLABLE);
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            stmt.setLong(1, animeId);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                tmpGender.setId(rs.getLong("id"));
                tmpGender.setName(rs.getString("name"));
                
                genders.add(tmpGender);
            }
            
            return genders;
            
            
        }
        catch(SQLException ex) {
            return new ArrayList<>();
        }
        finally{
            this.db.close();
        }
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
