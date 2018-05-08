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
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan
 */
public class Comment extends BaseModel{
    @Getter
    @Setter
    private long id;
    
    @Getter
    @Setter
    private long postId;
    
    @Getter
    @Setter
    private long userId;
    
    @Getter
    @Setter
    private int userScore;

    @Getter
    @Setter
    private String content;


    
    public ArrayList all() {
        try {
            
            String saveQuery = "select * from comments where post_id = ?";
            
            Comment tmpComment = new Comment(this.db);
            
            ArrayList<Comment> comments = new ArrayList<>();
            
            this.db.setQuery(saveQuery);
            
            
            PreparedStatement stmt = db.getStatement();
            
            stmt.setLong(1, this.postId);
            
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                
                tmpComment.setId(rs.getLong("id"));
                tmpComment.setContent(rs.getString("content"));
                tmpComment.setPostId(this.postId);
                tmpComment.setUserId(rs.getLong("user_id"));
                tmpComment.setUserScore(rs.getInt("user_score"));
                
                comments.add(tmpComment);
            }
            
            return comments;
            
        }catch(SQLException ex) {
            
            return new ArrayList<>();
        }
        finally{
            this.db.close();
        }
    }

    
    
    
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public BaseModel get(long id) {
        return null;
    }
}
