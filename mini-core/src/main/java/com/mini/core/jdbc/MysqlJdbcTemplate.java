package com.mini.core.jdbc;

import javax.sql.DataSource;

import static java.lang.String.format;

public class MysqlJdbcTemplate extends JdbcTemplate {

    public MysqlJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    protected String paging(int start, int limit, String sql) {
        return format("%s LIMIT %d, %d", sql, start, limit);
    }

    @Override
    protected String totals(String sql) {
        return format("SELECT COUNT(*) FROM (%s) TB", sql);
    }
}