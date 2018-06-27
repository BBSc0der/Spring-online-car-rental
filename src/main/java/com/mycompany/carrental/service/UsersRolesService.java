/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service;

import com.mycompany.carrental.entity.UsersRoles;
import java.util.List;

/**
 *
 * @author Bolek
 */
public interface UsersRolesService {
    public void saveOrUpdate(UsersRoles usersRoles);
    public List<UsersRoles> getAllUsersRoles();
    public List<UsersRoles> findByUsersId(Integer id);
    
}
