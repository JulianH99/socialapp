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
public interface HasExistence {
    
    /**
     *
     * @param columnName
     * @param value
     * @return
     */
    public boolean exists(String columnName, String value);
    
}
