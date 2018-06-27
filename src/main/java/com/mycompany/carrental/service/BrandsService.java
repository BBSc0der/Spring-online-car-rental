/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service;

import com.mycompany.carrental.entity.Brands;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bolek
 */
public interface BrandsService {
    public List<Brands> getAllBrands();
    public Map<String,String> getBrandsForSelect();
}
