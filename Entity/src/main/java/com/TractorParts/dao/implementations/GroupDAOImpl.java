package com.TractorParts.dao.implementations;

import com.TractorParts.dao.entity.Group;
import com.TractorParts.dao.interfaces.GroupDAO;
import com.TractorParts.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class GroupDAOImpl implements GroupDAO {

    public List<Group> getGroupList() throws SQLException {
        List groupList = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Group");
            groupList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupList;
    }
}
