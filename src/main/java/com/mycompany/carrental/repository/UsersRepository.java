/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.repository;

import com.mycompany.carrental.entity.Users;
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
public class UsersRepository  extends HibernateDaoSupport {
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
    public Users saveOrUpdate(Users toSave){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(toSave);
        tx.commit();
        s.close();
        return toSave;
    }
    public Users findOne(Long id){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Users WHERE idusers= :param");
        query.setParameter("param", id);
        @SuppressWarnings("unchecked")
        
        Users user = null;
        if(query.list().size() > 0)
            user = (Users) query.list().get(0);
        
        s.close();
        return user;
     }
     public Users findOneByUserName(String username){
         
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Users WHERE name= :param");
        query.setParameter("param", username);
        @SuppressWarnings("unchecked")
        
        Users user = null;
        if(query.list().size() > 0)
            user = (Users) query.list().get(0);
        
        s.close();
        return user;
     }
     public Users findOneByUserNickname(String username){
         
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Users WHERE nickname= :param");
        query.setParameter("param", username);
        @SuppressWarnings("unchecked")
        
        Users user = null;
        if(query.list().size() > 0)
            user = (Users) query.list().get(0);
        
        s.close();
        return user;
     }
     public Users findOneByEmail(String email){
         
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("FROM Users WHERE email= :param");
        query.setParameter("param", email);
        @SuppressWarnings("unchecked")
        
        Users user = null;
        if(query.list().size() > 0)
            user = (Users) query.list().get(0);
        
        s.close();
        return user;
     }
    
    public List<Users> findAll() {
        Session s = getSessionFactory().openSession();
        List<Users> users = s.createQuery("from Users").list();
        s.close();
        return users;
    }
}
