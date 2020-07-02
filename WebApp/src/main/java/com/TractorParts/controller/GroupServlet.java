package com.TractorParts.controller;

import com.TractorParts.managers.GroupManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public class GroupServlet extends APIHandlerServlet.APIRequestHandler {
    public static final GroupServlet instance = new GroupServlet();

    public static GroupServlet getInstance() {
        return instance;
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String[]> map = request.getParameterMap();
            GroupManager groupManager = new GroupManager();
            jsonObject.put("result",groupManager.groupController(map));
        } catch (SQLException e) {
            jsonObject.put("result", "SQL_error");
        }
        return jsonObject;

    }


}
