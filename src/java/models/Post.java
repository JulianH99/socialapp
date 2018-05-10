/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan
 */
public class Post extends BaseModel{
    @Getter
    @Setter
    protected long id;
    
    @Getter
    @Setter
    protected long userId;
    
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
    protected long animeId;
   
    @Getter
    @Setter
    protected boolean question;
    
    /**
     * 
     */
    public static final int TEXT = 1;
    
    public static final int IMAGE = 2;
    
    public static final int VIDEO = 3;
    
    public Post() {
        
    }
    
    public Post(Post copy) {
        this.animeId = copy.getAnimeId();
        this.id = copy.getId();
        this.userId = copy.getUserId();
        this.content = copy.getContent();
        this.pubDate = copy.getPubDate();
        this.question = copy.isQuestion();
        this.score = copy.getScore();
    }
}
