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
import java.util.ArrayList;
import java.util.List;
import models.AnimeGender;
import models.interfaces.CanGet;

/**
 *
 * @author julian
 */
public class AnimeGenderManager extends AbstractManager
implements CanGet<AnimeGender>{
    
    public AnimeGenderManager(SQLUtil dataSource) {
        super(dataSource);
    }

    @Override
    public List<AnimeGender> all() {
        try{
            String query = "select * from anime_genders";
            
            AnimeGender tmpGender = new AnimeGender();
            
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
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
            this.dataSource.close();
        }
    }

    @Override
    public AnimeGender get(long id) {
        try {
            String getQuery = "select * from anime_genders where id = ?";
            
            this.dataSource.setQuery(getQuery);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            // resultant user
            AnimeGender gender = new AnimeGender();
            
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
            this.dataSource.close();
        }
    }
    
    public List<AnimeGender> getFor(long animeId) {
        try{
            
            String query = "{call getGendersForAnime(?)}";
            
            ArrayList<AnimeGender> genders = new ArrayList<>();
            
            AnimeGender tmpGender = new AnimeGender();
            
            this.dataSource.setMode(SQLUtil.CALLABLE);
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
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
    }
    
}
