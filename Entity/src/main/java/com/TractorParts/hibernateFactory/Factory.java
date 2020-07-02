package com.TractorParts.hibernateFactory;

import com.TractorParts.dao.implementations.*;
import com.TractorParts.dao.interfaces.*;

public class Factory {

    private static UserDAO  userDAO;
    private static GoodsDAO goodsDAO;
    private static GroupDAO groupDAO;
    private static Factory  instance;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    public GoodsDAO getGoodsDAO() {
        if (goodsDAO == null) {
            goodsDAO = new GoodsDAOImpl();
        }
        return goodsDAO;
    }

    public GroupDAO getGroupDAO() {
        if (groupDAO == null) {
            groupDAO = new GroupDAOImpl();
        }
        return groupDAO;
    }
}