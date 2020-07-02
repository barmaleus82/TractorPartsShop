package com.TractorParts.controller;

import com.TractorParts.managers.LoginManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LoginServlet extends APIHandlerServlet.APIRequestHandler {
    public static final LoginServlet instance = new LoginServlet();

    public static LoginServlet getInstance() {
        return instance;
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String[]> map = request.getParameterMap();
            LoginManager loginManager = new LoginManager();
            String[] param = loginManager.loginController(map);
            jsonObject.put("name", param[0]);
            jsonObject.put("result", param[1]);
        } catch (Exception e) {
            jsonObject.put("name", "error");
            jsonObject.put("result", "error");
        }
        return jsonObject;
    }
}
