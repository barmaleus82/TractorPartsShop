package com.TractorParts.managers;

import com.TractorParts.dao.entity.Goods;
import com.TractorParts.hibernateFactory.Factory;

import javax.swing.*;
import java.util.*;

public class GoodsManager {

    private String goodsListToString(List<Goods> goodsList) throws Exception{
        String bufStr = "";
        for(Goods currGoods : goodsList){
            bufStr += currGoods.toString()+";";
        }
        return  bufStr;
    }

    public String goodsController(Map<String, String[]> paramMap) throws Exception {
        String paramName = paramMap.get("name")[0];
        String resultData = null;
        try {
            if(paramName.equals("fillDB")) {
                resultData="notActiv";
            }else if(paramName.equals("getGoodsByGroupId")){
                resultData = goodsListToString(
                        Factory.getInstance().getGoodsDAO().getGoodsByGroupId(Integer.parseInt(paramMap.get("groupId")[0]))
                );
            }else if(paramName.equals("findByStr")){
                resultData = goodsListToString(
                        Factory.getInstance().getGoodsDAO().getGoodsListByStr(paramMap.get("findStr")[0])
                );
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Enable to connect", JOptionPane.OK_OPTION);
            e.printStackTrace();
            resultData="error";
        }
       return resultData;
    }
}
