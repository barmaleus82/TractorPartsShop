package com.TractorParts.dao.implementations;

import com.TractorParts.dao.entity.*;
import com.TractorParts.dao.interfaces.GoodsDAO;
import com.TractorParts.hibernateFactory.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class GoodsDAOImpl implements GoodsDAO {

    @Override
    public void addGoods(Goods goods) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(goods);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null & session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void fillDataBase() throws RuntimeException {
        String str;
        try {
            String fileName = "d://Java/Projects/Товар ЛОСК.csv";
            BufferedReader buff = new BufferedReader(new FileReader(fileName));
            String line = buff.readLine();

            while (line != null) {
                String strArr[] = line.split(";");  // по пробелу
                if (strArr.length == 8){
                    try {
                        Factory.getInstance().getGoodsDAO().addGoods(
                            new Goods(
                                strArr[0], //vendorCode
                                strArr[1], //name
                                strArr[2], //description
                                Double.parseDouble(strArr[3]), //price
                                Integer.parseInt(strArr[4]), //primaryFotoId
                                Integer.parseInt(strArr[5]), //unitId
                                Integer.parseInt(strArr[6]), //countryId
                                Integer.parseInt(strArr[7]) //groupId
                            )
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                line = buff.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Goods getGoodsById(int id) {
        Goods goods = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Goods WHERE id =:paramId");
            query.setParameter("paramId", id);
            goods = (Goods) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public Country getCountryById(int countryId) {
        Country country = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Country WHERE countryId =:paramId");
            query.setParameter("paramId", countryId);
            country = (Country) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public Unit getUnitById(int unitId) {
        Unit unit = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Unit WHERE unitId=:paramId");
            query.setParameter("paramId", unitId);
            unit = (Unit) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit;
    }

    @Override
    public List<Goods> getGoodsListByStr(String findStr) {
        List goodsList = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Goods WHERE name LIKE :param ORDER BY name");
            query.setParameter("param", "%" + findStr.trim() + "%");
            goodsList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public List<Goods> getGoodsByGroupId(int groupId) {
        List goodsList = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Goods WHERE groupId =:paramId ORDER BY name");
            query.setParameter("paramId", groupId);
            goodsList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodsList;
    }
}
