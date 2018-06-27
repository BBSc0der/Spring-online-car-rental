/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service;

import com.mycompany.carrental.entity.Rents;
import com.mycompany.carrental.entity.Users;
import java.util.List;

/**
 *
 * @author Bolek
 */
public interface UsersService {
    public List<Users> getAllUsers();
    public Users findOneByUserNickname(String nickname);
    public Users findOneByEmail(String email);
    public void saveUser(Users user);
}
