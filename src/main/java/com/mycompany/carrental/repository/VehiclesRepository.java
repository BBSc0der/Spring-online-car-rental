/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.repository;

import com.mycompany.carrental.entity.Vehicles;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bolek
 */
@Repository
public class VehiclesRepository extends HibernateDaoSupport {

    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }

    public Vehicles saveOrUpdate(Vehicles toSave) {
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(toSave);
        tx.commit();
        s.close();
        return toSave;
    }

    public Vehicles findOne(Integer id) {
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Vehicles WHERE idVehicles= :param");
        query.setParameter("param", id);
        @SuppressWarnings("unchecked")

        Vehicles vehicle = new Vehicles();
        if (query.list().size() > 0) {
            vehicle = (Vehicles) query.list().get(0);
        }

        s.close();
        return vehicle;
    }

    public List<Vehicles> findAll() {
        Session s = getSessionFactory().openSession();
        List<Vehicles> vehicles = s.createQuery("from Vehicles").list();
        s.close();
        return vehicles;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Vehicles> list(Integer offset, Integer maxResults) {
        SessionFactory sf = getSessionFactory();
        return sf.openSession()
                .createQuery("FROM Vehicles")
                .setFirstResult(offset != null ? offset*maxResults : 0)
                .setMaxResults(maxResults != null ? maxResults : 10)
                .list();
    }

    public Long count() {
        SessionFactory sf = getSessionFactory();
        return (Long) sf.openSession()
                .createCriteria(Vehicles.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public List<Vehicles> findByModel(String name) {

        Session s = getSessionFactory().openSession();
        Query query = s.createQuery("FROM Vehicles WHERE model like concat('%',:param,'%')");
        query.setParameter("param", name);
        @SuppressWarnings("unchecked")

        List<Vehicles> vehicles = new ArrayList<>();

        if (query.list().size() > 0) {
            vehicles = query.list();
        }
        s.close();
        return vehicles;
    }

    public List<Vehicles> findByBrand(int brand) {
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery("FROM Vehicles WHERE idBrands = :param");
        query.setParameter("param", brand);
        @SuppressWarnings("unchecked")

        List<Vehicles> vehicles = new ArrayList<>();
        if (query.list().size() > 0) {
            vehicles = query.list();
        }

        s.close();
        return vehicles;
    }

    public List<Vehicles> findByModelAndBrand(String name, int brand) {
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery("FROM Vehicles WHERE model like concat('%',:param,'%') and idBrands = :param2");
        query.setParameter("param", name);
        query.setParameter("param2", brand);
        @SuppressWarnings("unchecked")

        List<Vehicles> vehicles = new ArrayList<>();
        if (query.list().size() > 0) {
            vehicles = query.list();
        }

        s.close();
        return vehicles;
    }
    //////
    // nowe zapytanaka 
    /////
    
    
    public List<Vehicles> findByModel(String name, Integer offset, Integer maxResults) {

        Session s = getSessionFactory().openSession();
        Query query = s.createQuery("FROM Vehicles WHERE model like concat('%',:param,'%')");
        query.setParameter("param", name)
            .setFirstResult(offset != null ? offset*maxResults : 0)
                .setMaxResults(maxResults != null ? maxResults : 10);
        @SuppressWarnings("unchecked")

        List<Vehicles> vehicles = new ArrayList<>();

        if (query.list().size() > 0) {
            vehicles = query.list();
        }
        s.close();
        return vehicles;
    }

    public List<Vehicles> findByBrand(int brand, Integer offset, Integer maxResults) {
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery("FROM Vehicles WHERE idBrands = :param");
        query.setParameter("param", brand)
            .setFirstResult(offset != null ? offset*maxResults : 0)
                .setMaxResults(maxResults != null ? maxResults : 10);
        @SuppressWarnings("unchecked")

        List<Vehicles> vehicles = new ArrayList<>();
        if (query.list().size() > 0) {
            vehicles = query.list();
        }

        s.close();
        return vehicles;
    }

    public List<Vehicles> findByModelAndBrand(String name, int brand, Integer offset, Integer maxResults) {
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery("FROM Vehicles WHERE model like concat('%',:param,'%') and idBrands = :param2");
        query.setParameter("param", name)
             .setParameter("param2", brand)
                .setFirstResult(offset != null ? offset*maxResults : 0)
                    .setMaxResults(maxResults != null ? maxResults : 10);
        @SuppressWarnings("unchecked")

        List<Vehicles> vehicles = new ArrayList<>();
        if (query.list().size() > 0) {
            vehicles = query.list();
        }

        s.close();
        return vehicles;
    }

    public boolean deleteOne(Integer id) {

        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        Vehicles persistentInstance = (Vehicles) s.load(Vehicles.class, id);
        if (persistentInstance != null) {
            s.delete(persistentInstance);
        }
        tx.commit();
        s.close();
        return true;
    }
}
