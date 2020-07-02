package com.TractorParts.controller;

import com.TractorParts.managers.GoodsManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GoodsServlet extends APIHandlerServlet.APIRequestHandler {
    public static final GoodsServlet instance = new GoodsServlet();

    public static GoodsServlet getInstance() {
        return instance;
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String[]> map = request.getParameterMap();
            GoodsManager goodsManager = new GoodsManager();
            jsonObject.put("result",goodsManager.goodsController(map)); //[0]
        } catch (Exception e) {
            jsonObject.put("result", "error");
        }
        return jsonObject;
    }


}
