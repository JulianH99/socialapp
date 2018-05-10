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
import models.Anime;
import models.interfaces.CanGet;

/**
 *
 * @author julian
 */
public class AnimeManager extends AbstractManager
    implements CanGet<Anime>{
    
    public AnimeManager(SQLUtil dataSource) {
        super(dataSource);
    }
    
    @Override
    public List<Anime> all() {
        try{
            String query = "select * from animes";
            
            Anime tmpAnime = new Anime();
            AnimeGenderManager agm = new AnimeGenderManager(this.dataSource);
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Anime> animes = new ArrayList();
            
            while(rs.next()){
                tmpAnime.setId(rs.getLong("id"));
                tmpAnime.setName(rs.getString("name"));
                tmpAnime.setImagePath(rs.getString("image_path"));
                tmpAnime.setGenders((ArrayList) agm.getFor(tmpAnime.getId()));
                
                animes.add(tmpAnime);
            }
            
            return animes;
            
        }
        catch(SQLException ex) {
            return new ArrayList<>();
        }
        finally{
            this.dataSource.close();
        }
    }

    @Override
    public Anime get(long id) {
        try {
            String getQuery = "select * from animes where id = ?";
            
            this.dataSource.setQuery(getQuery);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            AnimeGenderManager agm = new AnimeGenderManager(this.dataSource);
            
            ResultSet rs = stmt.executeQuery();
            
            // resultant user
            Anime anime = new Anime();
            
            if(rs.next()){
                anime.setId(id);
                anime.setName(rs.getString("name"));
                anime.setImagePath(rs.getString("image_path"));
                anime.setGenders((ArrayList) agm.getFor(anime.getId()));
            }
            
            return anime;
        }
        catch (SQLException ex) {
            return null;
        }
        finally {
            this.dataSource.close();
        }
    }
    
    
}
