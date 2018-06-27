/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service;

import com.mycompany.carrental.entity.Vehicles;
import com.mycompany.carrental.repository.VehiclesRepository;
import java.util.List;

/**
 *
 * @author Bolek
 */
public interface VehiclesService {
    public Vehicles findOne(Integer id);
    public List<Vehicles> getAllVehicles();
    public List<Vehicles> findByModel(String name);
    public List<Vehicles> findByBrand(int brand);
    public List<Vehicles> findByModelAndBrand(String name, int brand);
    public List<Vehicles> searchVehicles(Vehicles vehiclesDTO);
    public List<Vehicles> searchVehicles(String model, int brands);
    public List<Vehicles> searchVehicles(String model, int brands, Integer offset, Integer maxResults);
    public void addVehicle(Vehicles vehicle);
    public void deleteVehicle(Integer id);
    public List<Vehicles> list(Integer offset, Integer maxResults);
    public Long pagesCount(Integer maxResults, List<Vehicles> result);
    
    public Long count();
}
