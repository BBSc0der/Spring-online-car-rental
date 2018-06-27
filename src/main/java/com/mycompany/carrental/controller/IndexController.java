/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.controller;


import com.mycompany.carrental.service.UsersRolesService;
import com.mycompany.carrental.service.UsersService;
import com.mycompany.carrental.service.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Bolek
 */
@Controller
public class IndexController {
    
    public VehiclesService vehiclesService;
    public UsersService usersService;
    public UsersRolesService usersRolesService;
    
    @Autowired
    public IndexController(VehiclesService vehiclesService, UsersService usersService, UsersRolesService usersRolesService) {
        this.vehiclesService = vehiclesService;
        this.usersService = usersService;
        this.usersRolesService = usersRolesService;

    }
    
    @RequestMapping("/")
    public String index(Model model) {
       
        return "index";
    }
}
