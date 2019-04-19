package com.mini.util.dao.row;

import com.mini.util.dao.IRow;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StringRow implements IRow<String> {
    public static final StringRow INSTANCE = new StringRow();

    private StringRow() {}

    @Override
    public String execute(ResultSet rs, int index) throws SQLException {
        return rs.getString(index);
    }
}
