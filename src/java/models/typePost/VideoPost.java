/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.typePost;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.Post;

/**
 *
 * @author Juan
 */
@NoArgsConstructor
public class VideoPost extends Post{
    
    @Getter
    @Setter
    private String url;
    
    @Getter
    @Setter
    private String miniature;


    public VideoPost(Post copy) {
        super(copy);
    }

}
