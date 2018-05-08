/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.interfaces;

import java.util.List;

/**
 *
 * @author julian
 * @param <M>
 */
public interface CanGet<M> {
    
    public List<M> all();
    
    public M get(long id);
    
}
