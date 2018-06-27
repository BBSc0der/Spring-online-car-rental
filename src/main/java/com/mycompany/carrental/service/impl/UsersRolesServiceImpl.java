/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service.impl;

import com.mycompany.carrental.entity.UsersRoles;
import com.mycompany.carrental.repository.UsersRolesRepository;
import com.mycompany.carrental.service.UsersRolesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bolek
 */
@Service
public class UsersRolesServiceImpl implements UsersRolesService {
    
    @Autowired
    UsersRolesRepository usersRolesRepository;
    
    @Override
    public List<UsersRoles> getAllUsersRoles() {
        return usersRolesRepository.findAll();
    }
    @Override
    public List<UsersRoles> findByUsersId(Integer id){
        return usersRolesRepository.findByUsersId(id);
    }

    @Override
    public void saveOrUpdate(UsersRoles usersRoles) {
        usersRolesRepository.saveOrUpdate(usersRoles);
    }
    
}
