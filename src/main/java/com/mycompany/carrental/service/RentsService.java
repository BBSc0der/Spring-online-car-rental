/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service;

import com.mycompany.carrental.entity.Rents;
import com.mycompany.carrental.entity.Users;
import com.mycompany.carrental.entity.Vehicles;
import java.util.List;

/**
 *
 * @author Bolek
 */
public interface RentsService {
    public List<Rents> getAllRents();
    public Rents findOne(Integer id);
    public void addRent(Rents rent, Users user, Vehicles vehicle);
    public List<Rents> getUsersRents(Users user);
    public List<Rents> getActiveRents(Vehicles vehicle);
    public List<Rents> findActiveVehicleRents(Vehicles vehicle);
}
