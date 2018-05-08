/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.managers;

import database.SQLUtil;

/**
 *
 * @author julian
 */
public abstract class AbstractManager {
    
    protected SQLUtil dataSource;
    
    public AbstractManager(SQLUtil dataSource) {
        this.dataSource = dataSource;
    }
    
}
