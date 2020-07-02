package com.TractorParts.dao.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Formatter;

@Entity
@Table(name = "dbgroup")
public class Group {

    private int     id,
                    parentId;
    private String  name;

    public Group() {}
    public Group(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")

    @Column(name = "group_id")
    public int getGroupId() {
        return id;
    }
    public void setGroupId(int id) {
        this.id = id;
    }

    @Column(name = "group_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "group_parentId")
    public int getParentId() {
        return parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return new Formatter().format("%d;%d;%s",
                (getParentId()==0) ? 1 : 2,
                parentId,
                name.replace (';', ',')
        ).toString();
    }
}
