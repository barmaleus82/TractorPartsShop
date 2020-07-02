package com.TractorParts.dao.implementations;

import com.TractorParts.dao.entity.User;
import com.TractorParts.dao.interfaces.UserDAO;
import com.TractorParts.hibernateFactory.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(User user) throws SQLException{
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        }finally {
            if (session != null & session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByLogin(String userLogin) {
        User user = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login =:paramName");
            query.setParameter("paramName", userLogin);
            user = (User) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE idUser =:paramId");
            query.setParameter("paramId", id);
            user = (User) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
