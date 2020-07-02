package com.TractorParts.dao.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Formatter;

@Entity
@Table(name = "dbunit")
public class Unit {

    private int id;
    private String name ="";

    public Unit() { }
    public Unit(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")

    @Column(name = "unit_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "unit_id")
    public int getUnitId() {
        return id;
    }
    public void setUnitId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new Formatter().format("Unit: id=%d, name=%s",id,name).toString();
    }
}
