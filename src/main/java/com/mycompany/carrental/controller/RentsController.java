/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.controller;

import com.mycompany.carrental.entity.Rents;
import com.mycompany.carrental.entity.Vehicles;
import com.mycompany.carrental.service.RentsService;
import com.mycompany.carrental.service.UsersService;
import com.mycompany.carrental.service.VehiclesService;
import com.mycompany.carrental.validator.RentalDateValidator;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bolek
 */
@Controller
public class RentsController {
    
    public VehiclesService vehiclesService;
    public UsersService usersService;
    public RentsService rentsService;
    public RentalDateValidator rendalDateValidator;

    @Autowired
    public RentsController(VehiclesService vehiclesService,UsersService usersService, RentsService rentsService, RentalDateValidator rendalDateValidator) {
        this.vehiclesService = vehiclesService;
        this.usersService = usersService;
        this.rentsService = rentsService;
        this.rendalDateValidator = rendalDateValidator;
    }
    
    @RequestMapping(value="/rent/{id}", method = RequestMethod.GET)
    public String rent(@PathVariable(value = "id") Integer id,
            Model model, Principal principal){
        Vehicles veh = vehiclesService.findOne(id);
        
        model.addAttribute("vehicle", veh);
        model.addAttribute("activeRents", rentsService.findActiveVehicleRents(veh));
        model.addAttribute("user", usersService.findOneByUserNickname(principal.getName()));
        Rents newRent = new Rents();
        newRent.setVehicles(veh);
        model.addAttribute("newRent", newRent);
        
        return "rent";
    }
    @RequestMapping(value="/rent/{id}", method = RequestMethod.POST)
    public String rentValidate(@PathVariable(value = "id") Integer id,
            Model model, Principal principal,@ModelAttribute("newRent") @Valid Rents newRent,  BindingResult result){
        
        //rendalDateValidator.validate(newRent, result);
        
        if (result.hasErrors()) {
            model.addAttribute("vehicle", vehiclesService.findOne(id));
            model.addAttribute("activeRents", rentsService.getActiveRents(vehiclesService.findOne(id)));
            model.addAttribute("user", usersService.findOneByUserNickname(principal.getName()));
            model.addAttribute("newRent", newRent);
            return "rent";
        } else {
            
            rentsService.addRent(newRent,
                    usersService.findOneByUserNickname(principal.getName()),
                    vehiclesService.findOne(id));
                    
        }
        return "redirect:/rent/my-rents";
    }
    
    @RequestMapping(value="/rent/my-rents", method = RequestMethod.GET)
    public String myRents(Model model, Principal principal){
        // pobranie nazwy zalogowane użytkownika i przesłanie do modelu listy jego wypożyczen
        model.addAttribute("rents", rentsService.getUsersRents(usersService.findOneByUserNickname(principal.getName())));
        return "my-rents";
    }
    @InitBinder("newRent")
    public void initBinder(WebDataBinder binder) {
        // edytor ustawiajacy format daty 
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
        
        binder.setValidator(rendalDateValidator);
        
    }
}
