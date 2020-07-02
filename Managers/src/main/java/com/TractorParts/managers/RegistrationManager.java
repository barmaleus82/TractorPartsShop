package com.TractorParts.managers;

import com.TractorParts.dao.entity.User;
import com.TractorParts.hibernateFactory.Factory;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Map;

public class RegistrationManager {

    public String[] registrationController(Map<String, String[]> paramMap) throws Exception {

        String[] resultData = new String[5];

        String flag = paramMap.get("flag")[0];
        String user_id = paramMap.get("user_id")[0].trim();

        if (flag.equals("registration")) {
            String login = paramMap.get("login_reg")[0].trim();
            String pass1 = paramMap.get("pass1_reg")[0].trim();
            String pass2 = paramMap.get("pass2_reg")[0].trim();
            String name =  paramMap.get("name_reg")[0].trim();
            String phone = paramMap.get("phone_reg")[0].trim();

            if (!pass1.equals(pass2)) {
                resultData[0] = "error";
                resultData[1] = "passErr";
            } else if ((!login.contains("@")) || (!login.contains("."))) {
                resultData[0] = "error";
                resultData[1] = "loginErr";
            } else if (name == null || name.trim().length() == 0) {
                resultData[0] = "error";
                resultData[1] = "nameErr";
            } else if (phone == null || phone.trim().length() == 0) {
                resultData[0] = "error";
                resultData[1] = "phoneErr";
            } else if ((login.length() > 64) || (pass1.length() > 32) || (name.length() > 128) || (phone.length() > 128)) {
                resultData[0] = "error";
                resultData[1] = "paramLength";
            } else {
                try {
                    if (user_id.length() == 0) { // create new user
                        User currentUser = Factory.getInstance().getUserDAO().getUserByLogin(login);

                        if (currentUser != null) {
                            resultData[0] = "error";
                            resultData[1] = "userExist";
                        } else {
                            //not exist
                            currentUser = new User(login, pass1, name, phone);
                            Factory.getInstance().getUserDAO().addUser(currentUser);
                            resultData[0] = "create";
                            resultData[1] = login;
                        }
                    } else { //update current user data
                        User currentUser = Factory.getInstance().getUserDAO().getUserById(Integer.parseInt(user_id));

                        if (currentUser == null) {
                            resultData[0] = "error";
                            resultData[1] = "notFound";
                        } else {
                            currentUser.setLogin(login);
                            currentUser.setPassword(pass1);
                            currentUser.setName(name);
                            currentUser.setPhone(phone);

                            Factory.getInstance().getUserDAO().updateUser(currentUser);
                            resultData[0] = "update";
                            resultData[1] = name + " / " + login + " / " + phone;
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Enable to connect", JOptionPane.OK_OPTION);
                    e.printStackTrace();
                    throw new Exception();
                }
            }

        } else if (flag.equals("getRegistrationData")) {
            try{
                User currentUser = Factory.getInstance().getUserDAO().getUserById(Integer.parseInt(user_id));
                if (currentUser != null) {
                    resultData[0] = "data";
                    resultData[1] = currentUser.getLogin();
                    resultData[2] = currentUser.getPassword();
                    resultData[3] = currentUser.getName();
                    resultData[4] = currentUser.getPhone();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Enable to connect", JOptionPane.OK_OPTION);
                e.printStackTrace();
                throw new Exception();
            }
        }

        return resultData;
    }
}
