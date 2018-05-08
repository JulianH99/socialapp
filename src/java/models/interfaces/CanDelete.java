/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.interfaces;

/**
 *
 * @author julian
 */
public interface CanDelete {
    
    /**
     * Deletes a row in database containing the id provided
     * 
     * @param id
     * @return whether the row with id provided could be deleted
     */
    public boolean delete(long id);
    
}
