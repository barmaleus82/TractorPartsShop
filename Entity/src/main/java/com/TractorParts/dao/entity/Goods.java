package com.TractorParts.dao.entity;

import com.TractorParts.hibernateFactory.Factory;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.SQLException;
import java.util.Formatter;

@Entity
@Table (name = "dbgoods")
public class Goods {

    private int     id,
                    unitId,
                    countryId,
                    groupId,
                    primaryFotoId;
    private String  vendorCode,
                    name,
                    description;
    private Double  price;

    public Goods() {
    }
    public Goods(String vendorCode, String name, String description, double price, int primaryFotoId, int unitId,
                 int countryId, int groupId) {
        this.unitId = unitId;
        this.countryId = countryId;
        this.groupId = groupId;
        this.primaryFotoId = primaryFotoId;
        this.vendorCode = vendorCode;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "goods_id")
    public int getGoodsId() {
        return id;
    }
    public void setGoodsId(int id) {
        this.id = id;
    }

    @Column(name = "goods_vendorcode")
    public String getVendorCode() {
        return vendorCode;
    }
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    @Column(name = "goods_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "goods_description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "goods_primaryfoto")
    public int getPrimaryFotoId() {
        return primaryFotoId;
    }
    public void setPrimaryFotoId(int primaryFotoId) {
        this.primaryFotoId= primaryFotoId;
    }

    @Column(name = "goods_price")
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price= price;
    }

    @Column(name = "goods_unitId")
    public int getUnitId() {
        return unitId;
    }
    public void setUnitId(int unitId) {
        this.unitId= unitId;
    }

    @Column(name = "goods_countryId")
    public int getCountryId() {
        return countryId;
    }
    public void setCountryId(int countryId) {
        this.countryId= countryId;
    }

    @Column(name = "goods_groupId")
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId= groupId;
    }

    @Override
    public String toString(){
        try{
            return new Formatter().format("%d;%d;%s;%s;%.2f;%s;%s; unitId=%d, countryId=%d",
                    id,
                    vendorCode,
                    name,
                    description,
                    price,
                    Factory.getInstance().getGoodsDAO().getUnitById(getUnitId()).getName(),
                    Factory.getInstance().getGoodsDAO().getCountryById(getCountryId()).getName(),
                    groupId,
                    primaryFotoId
            ).toString();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
