/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.interfaces;

import java.util.ArrayList;

/**
 *
 * @author julian
 * @param <M> 
 */
public interface Queryable<M> {
    
    public int save();
    
    public ArrayList<M> all();
    
    /**
     *
     * @param columnName
     * @param value
     * @return
     */
    public boolean exists(String columnName, String value);
    
    public M get(long id);
    
    public boolean update();
    
    public boolean delete();
    
    
    
}
