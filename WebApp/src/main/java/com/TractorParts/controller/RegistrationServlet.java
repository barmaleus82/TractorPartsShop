package com.TractorParts.controller;

import com.TractorParts.managers.RegistrationManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RegistrationServlet extends APIHandlerServlet.APIRequestHandler {
    public static final RegistrationServlet instance = new RegistrationServlet();

    public static RegistrationServlet getInstance() {
        return instance;
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String[]> map = request.getParameterMap();
            RegistrationManager registrationManager = new RegistrationManager();
            String[] param= registrationManager.registrationController(map);

            jsonObject.put("name", param[0]);
            if (map.get("flag")[0].equals("registration")) {
                jsonObject.put("result", param[1]);
            }else if(map.get("flag")[0].equals("getRegistrationData")){
                jsonObject.put("user_login", param[1]);
                jsonObject.put("user_pass", param[2]);
                jsonObject.put("user_name", param[3]);
                jsonObject.put("user_phone", param[4]);
            }
        } catch (Exception e) {
            jsonObject.put("name", "error");
            jsonObject.put("result", "error");
        }
        return jsonObject;

    }
}