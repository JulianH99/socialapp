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
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan
 */
public class Anime extends BaseModel{
    
    
    @Getter
    @Setter
    private long id;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String imagePath;
    
    @Getter
    @Setter
    private ArrayList<AnimeGender> genders;

    

    public Anime(SQLUtil db) {
        super(db);
    }
    
    @Override
    public int save() {
        try {
            
            String saveQuery = "insert into animes (name, image_path)"
                    + " values(?, ?)";
            
            this.db.setQuery(saveQuery);
            
            PreparedStatement stmt = db.getStatement();
            
            stmt.setString(1, this.name);
            stmt.setString(2, this.imagePath);
            
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
            String query = "select * from animes";
            
            Anime tmpAnime = new Anime(this.db);
            AnimeGender tmpGender = new AnimeGender(this.db);
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Anime> animes = new ArrayList();
            
            while(rs.next()){
                tmpAnime.setId(rs.getLong("id"));
                tmpAnime.setName(rs.getString("name"));
                tmpAnime.setImagePath(rs.getString("image_path"));
                tmpAnime.setGenders(tmpGender.getForAnime(this.id));
                
                animes.add(tmpAnime);
            }
            
            return animes;
            
        }
        catch(SQLException ex) {
            return new ArrayList<>();
        }
        finally{
            this.db.close();
        }
    }

    @Override
    public Anime get(long id) {
        try {
            String getQuery = "select * from animes where id = ?";
            
            this.db.setQuery(getQuery);
            
            PreparedStatement stmt = this.db.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            // resultant user
            Anime anime = new Anime(this.db);
            
            if(rs.next()){
                anime.setId(id);
                anime.setName(rs.getString("name"));
                anime.setImagePath(rs.getString("image_path"));
                anime.setGenders((new AnimeGender(this.db)).getForAnime(this.id));
            }
            
            return anime;
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
            String query = "update animes set name = ?, image_path = ?"
                    + " where id = ?";
            
            this.db.setQuery(query);
            
            PreparedStatement stmt = this.db.getStatement();
            
            stmt.setString(1, this.name);
            stmt.setString(2, this.imagePath);
            stmt.setLong(3, this.id);
            
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
            String query = "delete from animes where id = ?";
            
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

    
}
