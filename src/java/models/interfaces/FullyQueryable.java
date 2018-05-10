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
public interface FullyQueryable<M>
extends CanDelete, CanGet<M>, CanSave<M>, CanUpdate<M>{
    
}
