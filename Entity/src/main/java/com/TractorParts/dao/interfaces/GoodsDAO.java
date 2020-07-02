package com.TractorParts.dao.interfaces;

import com.TractorParts.dao.entity.*;

import java.sql.SQLException;
import java.util.List;


public interface GoodsDAO {
    public void addGoods(Goods goods) throws SQLException;
    public void fillDataBase() throws SQLException;
    public Goods getGoodsById(int goodsId) throws SQLException;
    public Country getCountryById(int countryId) throws SQLException;
    public Unit getUnitById(int unitId) throws SQLException;
    public List<Goods> getGoodsByGroupId(int groupId);
    public List<Goods> getGoodsListByStr(String findStr);
}
