package com.TractorParts.managers;

import com.TractorParts.dao.entity.User;
import com.TractorParts.hibernateFactory.Factory;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Map;

public class LoginManager {

    public String[] loginController(Map<String, String[]> paramMap) throws Exception {
        String[] resultData = new String[2];

        String login = paramMap.get("login")[0];
        String password = paramMap.get("password")[0];

        try {
            User currentUser = Factory.getInstance().getUserDAO().getUserByLogin(login);
            if (currentUser != null) {
                if (password.equals(currentUser.getPassword())) {
                    resultData[0] = currentUser.getName() + " / " + currentUser.getLogin() + " / " + currentUser.getPhone();
                    resultData[1] = String.valueOf(currentUser.getIdUser());
                } else {
                    resultData[0] = "error";
                    resultData[1] = "pass";
                }
            } else {
                resultData[0] = "error";
                resultData[1] = "login";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Enable to connect", JOptionPane.OK_OPTION);
            e.printStackTrace();
        }
        return resultData;
    }
}
