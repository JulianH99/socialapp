/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.interfaces.Queryable;
import database.SQLUtil;

/**
 *
 * Base class used by classes that interact with database
 * 
 * @version 0.1
 * @see models.interfaces.Queryable
 * @author julian
 */
abstract public class BaseModel implements Queryable<BaseModel>{
    
    /**
     * database connection
     * @see database.SQLUtil
     */
    protected SQLUtil db;
    
}
