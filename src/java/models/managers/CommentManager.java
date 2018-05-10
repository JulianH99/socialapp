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
import models.Comment;
import models.interfaces.CanSave;
import models.interfaces.CanUpdate;
import models.interfaces.CanDelete;

/**
 *
 * @author julian
 */
public class CommentManager extends AbstractManager
implements CanSave<Comment>, CanUpdate<Comment>, CanDelete{
    
    public CommentManager(SQLUtil dataSource) {
        super(dataSource);
    }

    @Override
    public boolean save(Comment model) {
        try {
            
            String saveQuery = "insert into comments (content, user_id,"
                    + "user_score, post_id) values(?,?,?,?)";
            
            this.dataSource.setQuery(saveQuery);
            
            PreparedStatement stmt = dataSource.getStatement();
            
            stmt.setString(1, model.getContent());
            stmt.setLong(2, model.getUserId());
            stmt.setLong(3, model.getUserScore());
            stmt.setLong(4, model.getPostId());
            
            
            return stmt.execute();
            
            
        }catch(SQLException ex) {
            
            return false;
        }
        finally{
            this.dataSource.close();
        }
    }

    @Override
    public boolean update(Comment model) {
        try {
            String query = "update comments set content = ?, user_score = ? "
                    + "where id = ?";
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            stmt.setString(1, model.getContent());
            stmt.setInt(2, model.getUserScore());
            stmt.setLong(3, model.getId());
            
            return stmt.execute();
        }
        catch(SQLException ex){
            return false;
        }
        finally{
            this.dataSource.close();
        }
    }

    @Override
    public boolean delete(long id) {
        try{
            String query = "delete from comments where id = ?";
            
            this.dataSource.setQuery(query);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            stmt.setLong(1, id);
            
            return stmt.execute();
        }
        catch(SQLException ex) {
            return false;
        }
        finally {
            this.dataSource.close();
        }
    }
    
    
    public List<Comment> getFor(int postId) {
        try {
            String sql = "{ call getCommentsFor(?) }";
            List<Comment> comments = new ArrayList<>();
            Comment tmpComment = new Comment();
            
            this.dataSource.setQuery(sql);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            stmt.setLong(1, postId);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                tmpComment.setContent(rs.getString("content"));
                tmpComment.setId(rs.getLong("id"));
                tmpComment.setPostId(rs.getLong("post_id"));
                tmpComment.setUserId(rs.getLong("user_id"));
                tmpComment.setUserScore(rs.getInt("user_score"));
                
                comments.add(tmpComment);
                
            }
            
            return comments;            
        }
        catch (SQLException ex) {
            return new ArrayList<>();
        }
        finally {
            this.dataSource.close();
        }
    } 
    
}
