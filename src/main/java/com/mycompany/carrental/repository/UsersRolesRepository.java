/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.repository;

import com.mycompany.carrental.entity.UsersRoles;
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
public class UsersRolesRepository extends HibernateDaoSupport {
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
    public UsersRoles saveOrUpdate(UsersRoles toSave){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(toSave);
        tx.commit();
        s.close();
        return toSave;
    }
    public List<UsersRoles> findAll() {
        Session s = getSessionFactory().openSession();
        List<UsersRoles> usersRoles = s.createQuery("from UsersRoles").list();
        s.close();
        return usersRoles;
    }
    public List<UsersRoles> findByUsersId(Integer id){
        
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM UsersRoles WHERE idusers= :param");
        query.setParameter("param", id);
        @SuppressWarnings("unchecked")
        
        List<UsersRoles> usersRoles = query.list();
        
        s.close();
        return usersRoles;
    }
}
