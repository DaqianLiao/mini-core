package com.mini.dao.row;

import com.mini.dao.IRow;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class BooleanRow implements IRow<Boolean> {
    public static final BooleanRow INSTANCE = new BooleanRow();

    private BooleanRow() {}

    @Override
    public Boolean execute(ResultSet rs, int index) throws SQLException {
        return rs.getBoolean(index);
    }
}