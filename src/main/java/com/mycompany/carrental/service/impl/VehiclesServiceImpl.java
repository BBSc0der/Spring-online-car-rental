/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service.impl;

import com.mycompany.carrental.entity.Vehicles;
import com.mycompany.carrental.repository.BrandsRepository;
import com.mycompany.carrental.repository.VehiclesRepository;
import com.mycompany.carrental.service.VehiclesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Bolek
 */
@Service
public class VehiclesServiceImpl implements VehiclesService {
    @Autowired
    private VehiclesRepository vehiclesRepository;
    @Autowired
    private BrandsRepository brandsRepository;
    
    @Override
    public List<Vehicles> getAllVehicles(){
        return vehiclesRepository.findAll();
    }

    @Override
    public List<Vehicles> findByModel(String name) {
        return vehiclesRepository.findByModel(name);
    }

    @Override
    public List<Vehicles> findByBrand(int brand) {
        return vehiclesRepository.findByBrand(brand);
    }

    @Override
    public List<Vehicles> findByModelAndBrand(String name, int brand) {
        return vehiclesRepository.findByModelAndBrand(name, brand);
    }

    @Override
    public List<Vehicles> searchVehicles(Vehicles vehiclesDTO) {
        
        // algorytm wyszukiwania pojazdow w bazie
        if(vehiclesDTO.getModel().equals("")){  // nie podano modelu
            if(vehiclesDTO.getBrands().getName().equals("0")){ // nie podano marki 
                return getAllVehicles();
            }else{ // podano marke 
                return findByBrand(Integer.parseInt(vehiclesDTO.getBrands().getName()));
            }
        }else{  //podano model
            if(vehiclesDTO.getBrands().getName().equals("0")){
                return findByModel(vehiclesDTO.getModel());
            }else{
                return findByModelAndBrand(vehiclesDTO.getModel(), Integer.parseInt(vehiclesDTO.getBrands().getName()));
            }
        }
    }
    @Override
    public List<Vehicles> searchVehicles(String model, int brands) {
        // algorytm wyszukiwania pojazdow w bazie
        if(model.equals("")){  // nie podano modelu
            if(brands == 0){ // nie podano marki 
                return getAllVehicles();
            }else{ // podano marke 
                return findByBrand(brands);
            }
        }else{  //podano model
            if(brands == 0){
                return findByModel(model);
            }else{
                return findByModelAndBrand(model, brands);
            }
        }
    }
    @Override
    public List<Vehicles> searchVehicles(String model, int brands, Integer offset, Integer maxResults) {
        // algorytm wyszukiwania pojazdow w bazie
        if(model.equals("")){  // nie podano modelu
            if(brands == 0){ // nie podano marki 
                return vehiclesRepository.list(offset, maxResults);
            }else{ // podano marke 
                return vehiclesRepository.findByBrand(brands, offset, maxResults);
            }
        }else{  //podano model
            if(brands == 0){
                return vehiclesRepository.findByModel(model, offset, maxResults);
            }else{
                return vehiclesRepository.findByModelAndBrand(model, brands, offset, maxResults);
            }
        }
    }

    @Override
    public void addVehicle(Vehicles vehicle) {
        vehiclesRepository.saveOrUpdate(vehicle);
    }

    @Override
    public void deleteVehicle(Integer id) {
        vehiclesRepository.deleteOne(id);
    }

    @Override
    public Vehicles findOne(Integer id) {
        return vehiclesRepository.findOne(id);
    }

    @Override
    public List<Vehicles> list(Integer offset, Integer maxResults) {
    return vehiclesRepository.list(offset, maxResults);
    }

    @Override
    public Long count() {
    return vehiclesRepository.count();
    }
    @Override
    public Long pagesCount(Integer maxResults, List<Vehicles> result){
        return (long) Math.ceil((float)result.size() / maxResults);
    }

    
}
