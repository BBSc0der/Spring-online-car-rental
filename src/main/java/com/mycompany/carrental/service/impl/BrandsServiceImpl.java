/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service.impl;

import com.mycompany.carrental.entity.Brands;
import com.mycompany.carrental.repository.BrandsRepository;
import com.mycompany.carrental.service.BrandsService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bolek
 */
@Service
public class BrandsServiceImpl implements BrandsService {

    @Autowired
    private BrandsRepository brandsRepository;
    
    @Override
    public List<Brands> getAllBrands() {
        return brandsRepository.findAll();
    }

    @Override
    public Map<String, String> getBrandsForSelect() {
        //tworzenie zawartości dla selecta do wybierania marki
        Map<String, String> brSelect = new LinkedHashMap<String, String>();
        brSelect.put("0", "nie wybrano");
            for(Brands brand : brandsRepository.findAll()){
                
                brSelect.put(brand.getIdBrands().toString(), brand.getName());
            }
            
        return brSelect;
    }
    
}
