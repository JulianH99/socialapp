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
import models.AnimeList;
import models.interfaces.CanDelete;
import models.interfaces.CanSave;

/**
 *
 * @author julian
 */
public class AnimeListManager extends AbstractManager
implements CanSave<AnimeList>, CanDelete{
    
    public AnimeListManager(SQLUtil dataSource) {
        super(dataSource);
    }

    @Override
    public boolean save(AnimeList model) {
        try {
            
            String saveQuery = "insert into anime_lists (user_id, anime_id)"
                    + " values(?, ?)";
            
            this.dataSource.setQuery(saveQuery);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            stmt.setLong(1, model.getUserId());
            stmt.setLong(2, model.getAnimeId());
            
            return stmt.execute();
            
            
        }catch(SQLException ex) {
            
            return true;
        }
        finally{
            this.dataSource.close();
        }
    }

    @Override
    public boolean delete(long id) {
        try{
            String query = "delete from anime_lists id = ?";
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            stmt.setLong(1, id);
            
            return stmt.execute();
        }
        catch(SQLException ex) {
            return false;
        }
        finally{
            this.dataSource.close();
        }
    }
    
    public List<Anime> getFor(long userId) {
        try{
            String query = "{call getAnimesFor(?)}";
            ArrayList<Anime> animes = new ArrayList<>();
            Anime tmpAnime = new Anime();
            
            this.dataSource.setMode(SQLUtil.CALLABLE);
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            stmt.setLong(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                tmpAnime.setId(rs.getLong("id"));
                tmpAnime.setName(rs.getString("name"));
                tmpAnime.setImagePath(rs.getString("image_path"));
                
                animes.add(tmpAnime);
            }
            
            return animes;
        }
        catch(SQLException ex) {
            return new ArrayList<>();
        }
        
    }
    
}
