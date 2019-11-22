package com.mini.core.jdbc;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class JdbcTemplateMysql extends JdbcTemplate {

    public JdbcTemplateMysql(@Nonnull DataSource dataSource) {
        super(dataSource);
    }

    @Nonnull
    @Override
    public final String totals(String str) {
        return StringUtils.join("select count(*) from (", str, ") t");
    }

    @Nonnull
    @Override
    protected String paging(int start, int limit, String str) {
        return StringUtils.join(str, " limit ", start, ", ", limit);
    }
}
