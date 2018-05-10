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
import models.Post;
import models.interfaces.FullyQueryable;
import models.typePost.ImagePost;
import models.typePost.VideoPost;

/**
 *
 * @author julian
 */
public class PostManager extends AbstractManager
        implements FullyQueryable<Post> {

    public PostManager(SQLUtil dataSource) {
        super(dataSource);
    }

    @Override
    public boolean delete(long id) {
        try {
            String query = "delete from posts where id = ?";

            this.dataSource.setQuery(query);

            PreparedStatement stmt = this.dataSource.getStatement();

            stmt.setLong(1, id);

            return stmt.execute();
        } catch (SQLException ex) {
            return false;
        } finally {
            this.dataSource.close();
        }
    }

    @Override
    public List<Post> all() {
        try {
            String query = "select * from posts order by pub_date";

            Post tmpPost;
            List<Post> posts = new ArrayList<>();
            int postType;

            this.dataSource.setQuery(query);

            PreparedStatement stmt = this.dataSource.getStatement();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                postType = rs.getInt("post_type");
                
                tmpPost = new Post();
                tmpPost.setId(rs.getLong("id"));
                tmpPost.setContent(rs.getString("content"));
                tmpPost.setPubDate(rs.getDate("pub_date"));
                tmpPost.setAnimeId(rs.getLong("anime_id"));
                tmpPost.setUserId(rs.getLong("user_id"));
                tmpPost.setQuestion(rs.getBoolean("is_question"));
                tmpPost.setScore(rs.getDouble("score"));
                switch (postType) {
                    case Post.IMAGE:
                        ImagePost tmpImgPost = new ImagePost(tmpPost);
                        tmpImgPost.setImagePath(rs.getString("image_path"));
                        
                        posts.add(tmpImgPost);
                        break;
                    case Post.VIDEO:
                        VideoPost tmpVidPost = new VideoPost(tmpPost);
                        tmpVidPost.setUrl(rs.getString("url_video"));
                        tmpVidPost.setMiniature(rs.getString("image_path"));
                        posts.add(tmpVidPost);
                        break;
                    default:
                        posts.add(tmpPost);
                        break;
                }
            }
            
            return posts;

        } catch (SQLException ex) {
            return new ArrayList<>();
        } finally {
            this.dataSource.close();
        }
    }

    @Override
    public Post get(long id) {
        try {
            String getQuery = "select * from posts where id = ?";
            
            this.dataSource.setQuery(getQuery);
            
            PreparedStatement stmt = this.dataSource.getStatement();
            
            ResultSet rs = stmt.executeQuery();
            
            // resultant user
            Post post = new Post();
            
            if(rs.next()){
                post.setId(id);
                post.setAnimeId(rs.getLong("anime_id"));
                post.setContent(rs.getString("content"));
                post.setPubDate(rs.getDate("pub_date"));
                post.setQuestion(rs.getBoolean("is_question"));
                post.setScore(rs.getDouble("score"));
                post.setUserId(rs.getLong("user_id"));
                
                int postType = rs.getInt("post_type");
                
                if (postType == Post.IMAGE){
                    ImagePost imagePost = new ImagePost(post);
                    imagePost.setImagePath(rs.getString("img_path"));
                    
                    return imagePost;
                } 
                else if (postType == Post.VIDEO) {
                    VideoPost videoPost = new VideoPost(post);
                    videoPost.setMiniature(rs.getString("img_path"));
                    videoPost.setUrl(rs.getString("url_vide"));
                    
                    return videoPost;
                }
            }
            
            return post;
            
            
        }
        catch (SQLException ex) {
            return null;
        }
        finally {
            this.dataSource.close();
        }
    }

    @Override
    public boolean save(Post model) {
        try {
            
            String saveQuery = "insert into posts (user_id, anime_id, content,"
                    + "pub_date, score, is_question, post_type, url_video, "
                    + "img_path) values (?, ?, ? ,? ,? ,? ,? ,?, ?)";
            
            this.dataSource.setQuery(saveQuery);
            
            PreparedStatement stmt = dataSource.getStatement();
            
            stmt.setLong(1, model.getUserId());
            stmt.setLong(2, model.getAnimeId());
            stmt.setString(3, model.getContent());
            stmt.setDate(4, (java.sql.Date) (new java.util.Date()));
            stmt.setDouble(5, model.getScore());
            stmt.setBoolean(6, model.isQuestion());
            
            if(model instanceof ImagePost) {
                
                stmt.setInt(7, Post.IMAGE);
                stmt.setString(8, "");
                stmt.setString(9, new ImagePost(model).getImagePath());
            }
            else if (model instanceof VideoPost) {
                VideoPost tmpVp = new VideoPost(model);
                
                stmt.setInt(7, Post.VIDEO);
                
                stmt.setString(8, tmpVp.getUrl());
                stmt.setString(9, tmpVp.getMiniature());
            }
            else{
                stmt.setInt(7, Post.TEXT);
                stmt.setString(8, "");
                stmt.setString(9, "");
            }
            
            return stmt.execute();
            
            
        }catch(SQLException ex) {
            
            return false;
        }
        finally{
            this.dataSource.close();
        }
    }

    @Override
    public boolean update(Post model) {
        throw new IllegalAccessError("cant access now");
    }

}
