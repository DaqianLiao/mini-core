package com.mini.dao.row;

import com.mini.dao.IRow;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class IntRow implements IRow<Integer> {
    public static final IntRow INSTANCE = new IntRow();

    private IntRow() {}

    @Override
    public Integer execute(ResultSet rs, int index) throws SQLException {
        return rs.getInt(index);
    }
}
