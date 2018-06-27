/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.repository;

import com.mycompany.carrental.entity.Rents;
import com.mycompany.carrental.entity.Users;
import com.mycompany.carrental.entity.Vehicles;
import java.util.ArrayList;
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
public class RentsRepository extends HibernateDaoSupport {
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
    public Rents saveOrUpdate(Rents toSave){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(toSave);
        tx.commit();
        s.close();
        return toSave;
    }
    public Rents findOne(Integer id){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Rents WHERE idRents= :param");
        query.setParameter("param", id);
        @SuppressWarnings("unchecked")
        
        Rents rent = null;
        if(query.list().size() > 0)
            rent = (Rents) query.list().get(0);
        
        s.close();
        return rent;
     }
    public List<Rents> findAll() {
        Session s = getSessionFactory().openSession();
        List<Rents> rents = s.createQuery("from Rents").list();
        s.close();
        return rents;
    }
    public List<Rents> findAllUsersRents(Users user){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Rents WHERE users_idusers= :param ORDER BY dateOfRental DESC");
        query.setParameter("param", user.getIdusers());
        @SuppressWarnings("unchecked")
        
        List<Rents> rents = new ArrayList<>();
        if(query.list().size() > 0)
            rents =  query.list();
        
        s.close();
        return rents;
     }
    public List<Rents> findActiveVehicleRents(Vehicles vehicle){
        
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Rents WHERE vehicles= :param AND dateOfReturn >= CURRENT_DATE() ORDER BY dateOfRental DESC");
        
        
        
        query.setParameter("param", vehicle);
        @SuppressWarnings("unchecked")
        
        List<Rents> rents = new ArrayList<>();
        if(query.list().size() > 0)
            rents =  query.list();
        
        s.close();
        return rents;
    }
}
