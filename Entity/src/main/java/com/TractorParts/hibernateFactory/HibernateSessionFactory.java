package com.TractorParts.hibernateFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    HibernateSessionFactory(){
        try{
            sessionFactory =  new Configuration().configure().buildSessionFactory();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
