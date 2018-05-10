/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.managers;

import database.SQLUtil;
import models.User;
import models.interfaces.CanSave;
import models.interfaces.CanUpdate;
import models.interfaces.CanGetSingle;

/**
 *
 * @author julian
 */
public class UserManager extends AbstractManager
implements CanSave<User>, CanUpdate<User>, CanGetSingle<User>{
    
    public UserManager(SQLUtil dataSource) {
        super(dataSource);
    }

    @Override
    public boolean save(User model) {
        return false;
    }

    @Override
    public boolean update(User model) {
        return false;
    }

    @Override
    public User get(long id) {
        return null;
    }
    
}
