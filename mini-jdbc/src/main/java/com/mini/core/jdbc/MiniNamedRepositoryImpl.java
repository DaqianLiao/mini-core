package com.mini.core.jdbc;

import com.mini.core.jdbc.builder.NamedSql;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.requireNonNullElse;
import static java.util.Optional.ofNullable;

public  class MiniNamedRepositoryImpl implements MiniNamedRepository{
    private final NamedParameterJdbcOperations jdbcOperations;
    private final Dialect jdbcDialect;

    public MiniNamedRepositoryImpl(NamedParameterJdbcOperations jdbcOperations, Dialect jdbcDialect) {
        this.jdbcOperations = jdbcOperations;
        this.jdbcDialect = jdbcDialect;
    }

    @Nonnull
    private <T> RowMapper<T> getSingleColumnRowMapper(@Nonnull Class<T> requiredType) {
        return new SingleColumnRowMapper<>(requiredType);
    }

    @Nonnull
    private <T> RowMapper<T> getBeanPropertyRowMapper(@Nonnull Class<T> requiredType) {
        return new BeanPropertyRowMapper<>(requiredType);
    }

    @Nonnull
    private RowMapper<Map<String, Object>> getColumnMapRowMapper() {
        return new ColumnMapRowMapper();
    }

    @Override
    public int execute(@Nonnull String sql, @Nullable Map<String, Object> params) {
        return jdbcOperations.update(sql, requireNonNullElse(params, Map.of()));
    }

    @Override
    public final int execute(@Nonnull NamedSql<?> sql) {
        return execute(sql.getSql(), sql.getArgs());
    }

    @Nonnull
    @Override
    public int[] executeBatch(@Nonnull String sql, Map<String, Object>[] paramsList) {
        return jdbcOperations.batchUpdate(sql, paramsList);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(@Nonnull String sql, Map<String, Object> params, RowMapper<T> mapper) {
        return jdbcOperations.query(sql, requireNonNullElse(params, Map.of()), mapper);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(@Nonnull NamedSql<?> sql, @Nonnull RowMapper<T> mapper) {
        return this.queryList(sql.getSql(), sql.getArgs(), mapper);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(@Nonnull String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryList(sql, params, getBeanPropertyRowMapper(type));
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(@Nonnull NamedSql<?> sql, @Nonnull Class<T> type) {
        return queryList(sql, getBeanPropertyRowMapper(type));
    }

    @Nonnull
    @Override
    public final List<Map<String, Object>> queryListMap(@Nonnull String sql, Map<String, Object> params) {
        return queryList(sql, params, getColumnMapRowMapper());
    }

    @Nonnull
    @Override
    public final List<Map<String, Object>> queryListMap(@Nonnull NamedSql<?> sql) {
        return queryList(sql, getColumnMapRowMapper());
    }

    @Nonnull
    @Override
    public final <T> List<T> queryListSingle(@Nonnull String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryList(sql, params, getSingleColumnRowMapper(type));
    }

    @Nonnull
    @Override
    public final <T> List<T> queryListSingle(@Nonnull NamedSql<?> sql, Class<T> type) {
        return queryList(sql, getSingleColumnRowMapper(type));
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(long offset, int limit, @Nonnull String sql, Map<String, Object> params, @Nonnull RowMapper<T> mapper) {
        sql = sql + this.jdbcDialect.limit().getLimitOffset(limit, offset);
        return queryList(sql, params, mapper);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(long offset, int limit, @Nonnull NamedSql<?> sql, RowMapper<T> mapper) {
        return queryList(offset, limit, sql.getSql(), sql.getArgs(), mapper);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(long offset, int limit, @Nonnull String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryList(offset, limit, sql, params, getBeanPropertyRowMapper(type));
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(long offset, int limit, @Nonnull NamedSql<?> sql, @Nonnull Class<T> type) {
        return queryList(offset, limit, sql, getBeanPropertyRowMapper(type));
    }

    @Nonnull
    @Override
    public final List<Map<String, Object>> queryListMap(long offset, int limit, @Nonnull String sql, Map<String, Object> params) {
        return queryList(offset, limit, sql, params, getColumnMapRowMapper());
    }

    @Nonnull
    @Override
    public final List<Map<String, Object>> queryListMap(long offset, int limit, @Nonnull NamedSql<?> sql) {
        return queryList(offset, limit, sql, getColumnMapRowMapper());
    }

    @Nonnull
    @Override
    public final <T> List<T> queryListSingle(long offset, int limit, @Nonnull String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryList(offset, limit, sql, params, getSingleColumnRowMapper(type));
    }

    @Nonnull
    @Override
    public final <T> List<T> queryListSingle(long offset, int limit, @Nonnull NamedSql<?> sql, @Nonnull Class<T> type) {
        return queryList(offset, limit, sql, getSingleColumnRowMapper(type));
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(int limit, @Nonnull String sql, Map<String, Object> params, @Nonnull RowMapper<T> mapper) {
        return queryList(0, limit, sql, params, mapper);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(int limit, @Nonnull NamedSql<?> sql, @Nonnull RowMapper<T> mapper) {
        return queryList(0, limit, sql, mapper);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(int limit, String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryList(0, limit, sql, params, type);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryList(int limit, @Nonnull NamedSql<?> sql, @Nonnull Class<T> type) {
        return queryList(0, limit, sql, type);
    }

    @Nonnull
    @Override
    public final List<Map<String, Object>> queryListMap(int limit, @Nonnull String sql, Map<String, Object> params) {
        return queryListMap(0, limit, sql, params);
    }

    @Nonnull
    @Override
    public final List<Map<String, Object>> queryListMap(int limit, @Nonnull NamedSql<?> sql) {
        return queryListMap(0, limit, sql);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryListSingle(int limit, @Nonnull String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryListSingle(0, limit, sql, params, type);
    }

    @Nonnull
    @Override
    public final <T> List<T> queryListSingle(int limit, @Nonnull NamedSql<?> sql, @Nonnull Class<T> type) {
        return queryListSingle(0, limit, sql, type);
    }

    @Nullable
    @Override
    public final <T> T queryObject(@Nonnull String sql, Map<String, Object> params, @Nonnull RowMapper<T> mapper) {
        return queryList(1, sql, params, mapper).stream().findFirst().orElse(null);
    }

    @Nullable
    @Override
    public final <T> T queryObject(@Nonnull NamedSql<?> sql, @Nonnull RowMapper<T> mapper) {
        return queryObject(sql.getSql(), sql.getArgs(), mapper);
    }

    @Nullable
    @Override
    public final <T> T queryObject(@Nonnull String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryObject(sql, params, getBeanPropertyRowMapper(type));
    }

    @Nullable
    @Override
    public final <T> T queryObject(@Nonnull NamedSql<?> sql, @Nonnull Class<T> type) {
        return queryObject(sql, getBeanPropertyRowMapper(type));
    }

    @Nullable
    @Override
    public final Map<String, Object> queryObjectMap(@Nonnull String sql, Map<String, Object> params) {
        return queryObject(sql, params, getColumnMapRowMapper());
    }

    @Nullable
    @Override
    public final Map<String, Object> queryObjectMap(@Nonnull NamedSql<?> sql) {
        return queryObject(sql, getColumnMapRowMapper());
    }

    @Nullable
    @Override
    public final <T> T queryObjectSingle(@Nonnull String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryObject(sql, params, getSingleColumnRowMapper(type));
    }

    @Nullable
    @Override
    public final <T> T queryObjectSingle(@Nonnull NamedSql<?> sql, @Nonnull Class<T> type) {
        return queryObject(sql, getSingleColumnRowMapper(type));
    }

    @Nullable
    @Override
    public final String queryString(String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, String.class);
    }

    @Nullable
    @Override
    public final String queryString(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, String.class);
    }

    @Nullable
    @Override
    public final Long queryLong(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Long.class);
    }

    @Nullable
    @Override
    public final Long queryLong(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Long.class);
    }

    @Nullable
    @Override
    public final Integer queryInt(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Integer.class);
    }

    @Nullable
    @Override
    public final Integer queryInt(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Integer.class);
    }

    @Nullable
    @Override
    public final Short queryShort(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Short.class);
    }

    @Nullable
    @Override
    public final Short queryShort(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Short.class);
    }

    @Nullable
    @Override
    public final Byte queryByte(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Byte.class);
    }

    @Nullable
    @Override
    public final Byte queryByte(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Byte.class);
    }

    @Nullable
    @Override
    public final Double queryDouble(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Double.class);
    }

    @Nullable
    @Override
    public final Double queryDouble(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Double.class);
    }

    @Nullable
    @Override
    public final Float queryFloat(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Float.class);
    }

    @Nullable
    @Override
    public final Float queryFloat(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Float.class);
    }

    @Nullable
    @Override
    public final Boolean queryBoolean(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Boolean.class);
    }

    @Nullable
    @Override
    public final Boolean queryBoolean(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Boolean.class);
    }

    @Nullable
    @Override
    public final Timestamp queryTimestamp(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Timestamp.class);
    }

    @Nullable
    @Override
    public final Timestamp queryTimestamp(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Timestamp.class);
    }

    @Nullable
    @Override
    public final Date queryDate(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Date.class);
    }

    @Nullable
    @Override
    public final Date queryDate(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Date.class);
    }

    @Nullable
    @Override
    public final Time queryTime(@Nonnull String sql, Map<String, Object> params) {
        return queryObjectSingle(sql, params, Time.class);
    }

    @Nullable
    @Override
    public final Time queryTime(@Nonnull NamedSql<?> sql) {
        return queryObjectSingle(sql, Time.class);
    }

    @Nonnull
    @Override
    public final <T> Page<T> queryPage(@Nonnull Pageable pageable, @Nonnull String sql, Map<String, Object> params, @Nonnull RowMapper<T> mapper) {
        final List<T> content = this.queryList(pageable.getOffset(), pageable.getPageSize(), sql, params, mapper);
        long total = ofNullable(queryLong(format("SELECT COUNT(*) FROM (%s) ", sql), params)).orElse(0L);
        return new PageImpl<>(content, pageable, total);
    }

    @Nonnull
    @Override
    public final <T> Page<T> queryPage(@Nonnull Pageable pageable, @Nonnull NamedSql<?> sql, @Nonnull RowMapper<T> mapper) {
        return queryPage(pageable, sql.getSql(), sql.getArgs(), mapper);
    }

    @Nonnull
    @Override
    public final <T> Page<T> queryPage(@Nonnull Pageable pageable, String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryPage(pageable, sql, params, getBeanPropertyRowMapper(type));
    }

    @Nonnull
    @Override
    public final <T> Page<T> queryPage(@Nonnull Pageable pageable, @Nonnull NamedSql<?> sql, Class<T> type) {
        return queryPage(pageable, sql, getBeanPropertyRowMapper(type));
    }

    @Nonnull
    @Override
    public final Page<Map<String, Object>> queryPageMap(@Nonnull Pageable pageable, @Nonnull String sql, Map<String, Object> params) {
        return queryPage(pageable, sql, params, getColumnMapRowMapper());
    }

    @Nonnull
    @Override
    public final Page<Map<String, Object>> queryPageMap(@Nonnull Pageable pageable, @Nonnull NamedSql<?> sql) {
        return queryPage(pageable, sql, getColumnMapRowMapper());
    }

    @Nonnull
    @Override
    public final <T> Page<T> queryPageSingle(@Nonnull Pageable pageable, @Nonnull String sql, Map<String, Object> params, @Nonnull Class<T> type) {
        return queryPage(pageable, sql, params, getSingleColumnRowMapper(type));
    }

    @Nonnull
    @Override
    public final <T> Page<T> queryPageSingle(@Nonnull Pageable pageable, @Nonnull NamedSql<?> sql, @Nonnull Class<T> type) {
        return queryPage(pageable, sql, getSingleColumnRowMapper(type));
    }
}
