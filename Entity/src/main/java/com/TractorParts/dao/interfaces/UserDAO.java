package com.TractorParts.dao.interfaces;

import com.TractorParts.dao.entity.User;
import java.sql.SQLException;

public interface UserDAO {
    public void addUser(User user) throws SQLException;
    public void updateUser(User user) throws SQLException;
    public User getUserByLogin(String userLogin) throws SQLException;
    public User getUserById(int id)throws SQLException;
}
