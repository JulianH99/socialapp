/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
    
}
