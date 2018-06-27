/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.repository;

import com.mycompany.carrental.entity.Brands;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bolek
 */
@Repository
public class BrandsRepository extends HibernateDaoSupport {
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
    public Brands saveOrUpdate(Brands toSave){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(toSave);
        tx.commit();
        s.close();
        return toSave;
    }
    public Brands findOne(Long id){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Brands WHERE idBrands= :param");
        query.setParameter("param", id.intValue());
        @SuppressWarnings("unchecked")
        
        Brands brand = null;
        if(query.list().size() > 0)
            brand = (Brands) query.list().get(0);
        
        s.close();
        return brand;
     }
    public List<Brands> findAll() {
        Session s = getSessionFactory().openSession();
        List<Brands> brands = s.createQuery("from Brands").list();
        s.close();
        return brands;
    }
}
