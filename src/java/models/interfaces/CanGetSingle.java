/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.interfaces;

/**
 *
 * @author julian
 * @param <M>
 */
public interface CanGetSingle<M> {
    
    /**
     * 
     * @param id
     * @return 
     */
    public M get(long id);
}
