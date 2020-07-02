package com.TractorParts.managers;

import com.TractorParts.dao.entity.Group;
import com.TractorParts.hibernateFactory.Factory;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class GroupManager {

    public String groupController(Map<String, String[]> paramMap) throws Exception {
        String resultData;

        try {
            resultData = "";
            for (Group currGr : Factory.getInstance().getGroupDAO().getGroupList()){
                resultData += currGr.toString()+";";   //last element ? "": ";");
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Enable to connect", JOptionPane.OK_OPTION);
            e.printStackTrace();
            resultData = "error";
        }
        return resultData;

    }
}
