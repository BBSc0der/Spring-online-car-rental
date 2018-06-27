/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service.impl;

import com.mycompany.carrental.entity.Rents;
import com.mycompany.carrental.entity.Users;
import com.mycompany.carrental.entity.Vehicles;
import com.mycompany.carrental.repository.RentsRepository;
import com.mycompany.carrental.repository.VehiclesRepository;
import com.mycompany.carrental.service.RentsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bolek
 */
@Service
public class RentsServiceImpl implements RentsService {

    @Autowired
    private RentsRepository rentsRepository;
    @Autowired
    private VehiclesRepository vehiclesRepository;
    
    @Override
    public List<Rents> getAllRents() {
        return rentsRepository.findAll();
    }

    @Override
    public Rents findOne(Integer id) {
        return rentsRepository.findOne(id);
    }

    @Override
    public void addRent(Rents rent, Users user, Vehicles vehicle) {
        // ustawianie pol niedostepnych w formularzu 
        rent.setPricePerDay(100);
        rent.setUsers(user);
        rent.setVehicles(vehicle);
        rentsRepository.saveOrUpdate(rent);
        
        // ustawienie statusu pojazdu na wypozyczony
        Vehicles thisVehicle = vehiclesRepository.findOne(rent.getVehicles().getIdVehicles());
        thisVehicle.setStatus("wypożyczony");
        vehiclesRepository.saveOrUpdate(thisVehicle);
        
    }

    @Override
    public List<Rents> getUsersRents(Users user) {
        return rentsRepository.findAllUsersRents(user);
    }

    @Override
    public List<Rents> getActiveRents(Vehicles vehicle) {
        List<Rents> activeRents = new ArrayList<>();
        for(Rents rent : vehicle.getRentses()){
            if(rent.getDateOfReturn().after(new Date())){
                activeRents.add(rent);
            }
        }
        return activeRents;
    }

    @Override
    public List<Rents> findActiveVehicleRents(Vehicles vehicle) {
        return rentsRepository.findActiveVehicleRents(vehicle);
    }
    
}
