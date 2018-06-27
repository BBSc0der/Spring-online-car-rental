/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.controller;

import com.mycompany.carrental.entity.Users;
import com.mycompany.carrental.service.UsersService;
import com.mycompany.carrental.validator.UserRegistrationValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bolek
 */
@Controller
public class LoginController {
    
    public UserRegistrationValidator userRegistrationValidator;
    public UsersService usersService;

    @Autowired
    public LoginController(UserRegistrationValidator userRegistrationValidator, UsersService usersService) {
        this.userRegistrationValidator = userRegistrationValidator;
        this.usersService = usersService;
    }
    
    

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "redirect:/";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("newUser", new Users());
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerValidate(@ModelAttribute("newUser") @Valid Users newUser,
            BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            
            return "register";
        } else {
            usersService.saveUser(newUser);
        }
        return "redirect:/login";
    }
    @InitBinder("newUser")
    public void initBinder(WebDataBinder binder) {
       
        binder.setValidator(userRegistrationValidator);
        
    }
}
