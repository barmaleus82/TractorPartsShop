package com.TractorParts.dao.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Formatter;

@Entity
@Table(name = "dbcountry")
public class Country {

    private int id;
    private String name = "";

    public Country() {}
    public Country(String name) {
       this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")

    @Column(name = "country_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "country_id")
    public int getCountryId() {
        return id;
    }
    public void setCountryId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new Formatter().format("Country: id=%d, name=%s",getCountryId(),getName()).toString();
    }
}
