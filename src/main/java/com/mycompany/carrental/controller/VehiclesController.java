/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.controller;

import com.mycompany.carrental.entity.Vehicles;
import com.mycompany.carrental.service.BrandsService;
import com.mycompany.carrental.service.VehiclesService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Bolek
 */
@Controller
public class VehiclesController {

    public VehiclesService vehiclesService;
    public BrandsService brandsService;

    @Autowired
    public VehiclesController(VehiclesService vehiclesService, BrandsService brandsService) {
        this.vehiclesService = vehiclesService;
        this.brandsService = brandsService;

    }

    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("vehiclesDTO", new Vehicles());
        // zawartosc selecta dla marek 
        model.addAttribute("brSelect", brandsService.getBrandsForSelect());
        model.addAttribute("brSelect2", brandsService.getAllBrands());

        return "vehicles";
    }

    @RequestMapping(value = "/vehicles/query", params = { "model", "brands", "offset", "maxResults" }, method = RequestMethod.GET)
    public String search(Model model,
     @RequestParam( "model" ) String mod, @RequestParam( "brands" ) int brands,
     @RequestParam( "offset" ) int offset, @RequestParam( "maxResults" ) int maxResults) {
        
        model.addAttribute("searchedVehicles", vehiclesService.searchVehicles(mod,brands,offset,maxResults));
        model.addAttribute("offset", offset);
        model.addAttribute("maxResults", maxResults);
        model.addAttribute("model", mod);
        model.addAttribute("brands", brands);
        model.addAttribute("pages", vehiclesService.pagesCount(maxResults,vehiclesService.searchVehicles(mod, brands)));
        
        return index(model);
    }

    @RequestMapping(value = "/vehicles/add", method = RequestMethod.GET)
    public String add(Model model) {
        Vehicles newVehicle = new Vehicles();
        model.addAttribute("newVehicle", newVehicle);
        model.addAttribute("allBrands", brandsService.getAllBrands());
        return "addVehicle";
    }

    @RequestMapping(value = "/vehicles/add", method = RequestMethod.POST)
    public String addValidate(@ModelAttribute("newVehicle") @Valid Vehicles newVehicle,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allBrands", brandsService.getAllBrands());
            return "addVehicle";
        } else {

            vehiclesService.addVehicle(newVehicle);
        }
        return "redirect:/vehicles";
    }

    @RequestMapping(value = "/vehicles/{id}", method = RequestMethod.DELETE)
    public String deleteVehicle(@PathVariable(value = "id") Integer id,
            Model model) {
        vehiclesService.deleteVehicle(id);
        return "redirect:/vehicles";
    }

    @RequestMapping(value = "/vehicles/edit/{id}", method = RequestMethod.GET)
    public String editVehicle(@PathVariable(value = "id") Integer id,
            Model model) {
        Vehicles editedVehicle = vehiclesService.findOne(id);
        model.addAttribute("editedVehicle", editedVehicle);
        model.addAttribute("allBrands", brandsService.getAllBrands());
        return "editVehicle";
    }

    @RequestMapping(value = "/vehicles/edit/{id}", method = RequestMethod.POST)
    public String editValidate(@PathVariable(value = "id") Integer id,
            @ModelAttribute("editedVehicle") @Valid Vehicles newVehicle,
            BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("allBrands", brandsService.getAllBrands());
            return "editVehicle";
        } else {
            newVehicle.setIdVehicles(id);
            vehiclesService.addVehicle(newVehicle);
        }
        return "redirect:/vehicles";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //ustawienie formatu daty
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
        
    }
}
