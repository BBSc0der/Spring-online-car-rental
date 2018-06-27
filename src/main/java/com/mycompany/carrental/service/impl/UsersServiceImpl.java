/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service.impl;

import com.mycompany.carrental.entity.Rents;
import com.mycompany.carrental.entity.Users;
import com.mycompany.carrental.entity.UsersRoles;
import com.mycompany.carrental.repository.UsersRepository;
import com.mycompany.carrental.service.UsersRolesService;
import com.mycompany.carrental.service.UsersService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bolek
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    UsersRolesService usersRolesService;

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users findOneByUserNickname(String nickname) {
        return usersRepository.findOneByUserNickname(nickname);
    }

    @Override
    public Users findOneByEmail(String email) {
        return usersRepository.findOneByEmail(email);
    }

    @Override
    public void saveUser(Users user) {
        // haszowanie hasla uzytkownika
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        String hashedPass= encoder.encode(user.getPassword());
        user.setPassword(hashedPass);
        
        usersRepository.saveOrUpdate(user);
        // utworzenie roli dla uzytkownika
        UsersRoles newRole = new UsersRoles(user, "ROLE_USER");
        usersRolesService.saveOrUpdate(newRole);
        
    }
    
    
}
