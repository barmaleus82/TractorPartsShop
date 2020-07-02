package com.TractorParts.dao.interfaces;

import com.TractorParts.dao.entity.Group;
import java.sql.SQLException;
import java.util.List;


public interface GroupDAO {
    public List<Group> getGroupList() throws SQLException;
}
