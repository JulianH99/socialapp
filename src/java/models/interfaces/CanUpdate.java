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
public interface CanUpdate<M> {
    
    /**
     * 
     * updates the model's data in database
     * 
     * @param model
     * @return whether the model could be updated or not 
     */
    public boolean update(M model);
}
