package com.mini.jdbc;

import com.mini.jdbc.mapper.IMapper;
import com.mini.jdbc.util.Paging;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface BasicsDao {
    /**
     * 获取数据写入 DaoTemplate 对象
     * @return DaoTemplate 对象
     */
    JdbcTemplate writeTemplate();

    /**
     * 获取数据读取 DaoTemplate 对象
     * @return DaoTemplate 对象
     */
    JdbcTemplate readTemplate();

    /**
     * 执行SQL
     * @param str    SQL
     * @param params 参数
     * @return 执行结果
     */
    default int execute(String str, Object... params) {
        return writeTemplate().execute(str, params);
    }

    /**
     * 执行SQL
     * @param builder SQLBuilder
     * @return 执行结果
     */
    default int execute(SQLBuilder builder) {
        return writeTemplate().execute(builder);
    }

    /**
     * 查询结果
     * @param str    SQL
     * @param m      映射器
     * @param params 参数
     * @return 查询结果
     */
    @Nonnull
    default <T> List<T> query(String str, IMapper<T> m, Object... params) {
        return readTemplate().query(str, m, params);
    }

    /**
     * 查询结果
     * @param builder SQLBuilder
     * @param m       映射器
     * @return 查询结果
     */
    @Nonnull
    default <T> List<T> query(SQLBuilder builder, IMapper<T> m) {
        return readTemplate().query(builder, m);
    }

    /**
     * 查询结果
     * @param start  查询起始位置
     * @param limit  查询条数
     * @param str    SQL
     * @param m      解析器
     * @param params 参数
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @Nonnull
    default <T> List<T> query(int start, int limit, String str, IMapper<T> m, Object... params) {
        return readTemplate().query(start, limit, str, m, params);
    }

    /**
     * 查询结果
     * @param start   查询起始位置
     * @param limit   查询条数
     * @param m       解析器
     * @param builder SQL
     * @param <T>     解析器类型
     * @return 查询结果
     */
    @Nonnull
    default <T> List<T> query(int start, int limit, SQLBuilder builder, IMapper<T> m) {
        return readTemplate().query(start, limit, builder, m);
    }

    /**
     * 查询结果
     * @param limit  查询条数
     * @param str    SQL
     * @param m      解析器
     * @param params 参数
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @Nonnull
    default <T> List<T> query(int limit, String str, IMapper<T> m, Object... params) {
        return readTemplate().query(limit, str, m, params);
    }

    /**
     * 查询结果
     * @param limit   查询条数
     * @param m       解析器
     * @param builder SQL
     * @param <T>     解析器类型
     * @return 查询结果
     */
    @Nonnull
    default <T> List<T> query(int limit, SQLBuilder builder, IMapper<T> m) {
        return readTemplate().query(limit, builder, m);
    }

    /**
     * 查询结果
     * @param paging paging 分页器
     * @param str    SQL
     * @param m      映射器
     * @param params 参数
     * @return 查询结果
     */
    @Nonnull
    default <T> List<T> query(Paging paging, String str, IMapper<T> m, Object... params) {
        return readTemplate().query(paging, str, m, params);
    }

    /**
     * 查询结果
     * @param paging  分页器
     * @param builder SQLBuilder
     * @param m       映射器
     * @return 查询结果
     */
    @Nonnull
    default <T> List<T> query(Paging paging, SQLBuilder builder, IMapper<T> m) {
        return readTemplate().query(paging, builder, m);
    }

    /**
     * 查询结果
     * @param str    SQL
     * @param m      映射器
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default <T> T queryOne(String str, IMapper<T> m, Object... params) {
        return readTemplate().queryOne(str, m, params);
    }

    /**
     * 查询结果
     * @param builder SQLBuilder
     * @param m       映射器
     * @return 查询结果
     */
    @Nullable
    default <T> T queryOne(SQLBuilder builder, IMapper<T> m) {
        return readTemplate().queryOne(builder, m);
    }

    /**
     * 查询String
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default String queryString(String str, Object... params) {
        return readTemplate().queryString(str, params);
    }

    /**
     * 查询String
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default String queryString(SQLBuilder builder) {
        return readTemplate().queryString(builder);
    }

    /**
     * 查询Long
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Long queryLong(String str, Object... params) {
        return readTemplate().queryLong(str, params);
    }

    /**
     * 查询Long
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Long queryLong(SQLBuilder builder) {
        return readTemplate().queryLong(builder);
    }

    /**
     * 查询Integer
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Integer queryInt(String str, Object... params) {
        return readTemplate().queryInt(str, params);
    }

    /**
     * 查询Integer
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Integer queryInt(SQLBuilder builder) {
        return readTemplate().queryInt(builder);
    }

    /**
     * 查询Short
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Short queryShort(String str, Object... params) {
        return readTemplate().queryShort(str, params);
    }

    /**
     * 查询Short
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Short queryShort(SQLBuilder builder) {
        return readTemplate().queryShort(builder);
    }

    /**
     * 查询Byte
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Byte queryByte(String str, Object... params) {
        return readTemplate().queryByte(str, params);
    }

    /**
     * 查询Byte
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Byte queryByte(SQLBuilder builder) {
        return readTemplate().queryByte(builder);
    }

    /**
     * 查询Double
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Double queryDouble(String str, Object... params) {
        return readTemplate().queryDouble(str, params);
    }

    /**
     * 查询Double
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Double queryDouble(SQLBuilder builder) {
        return readTemplate().queryDouble(builder);
    }

    /**
     * 查询Float
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Float queryFloat(String str, Object... params) {
        return readTemplate().queryFloat(str, params);
    }

    /**
     * 查询Float
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Float queryFloat(SQLBuilder builder) {
        return readTemplate().queryFloat(builder);
    }

    /**
     * 查询Boolean
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Boolean queryBoolean(String str, Object... params) {
        return readTemplate().queryBoolean(str, params);
    }

    /**
     * 查询Boolean
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Boolean queryBoolean(SQLBuilder builder) {
        return readTemplate().queryBoolean(builder);
    }

    /**
     * 查询Timestamp
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Timestamp queryTimestamp(String str, Object... params) {
        return readTemplate().queryTimestamp(str, params);
    }

    /**
     * 查询Timestamp
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Timestamp queryTimestamp(SQLBuilder builder) {
        return readTemplate().queryTimestamp(builder);
    }

    /**
     * 查询Date
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Date queryDate(String str, Object... params) {
        return readTemplate().queryDate(str, params);
    }

    /**
     * 查询Date
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Date queryDate(SQLBuilder builder) {
        return readTemplate().queryDate(builder);
    }

    /**
     * 查询Time
     * @param str    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    default Time queryTime(String str, Object... params) {
        return readTemplate().queryTime(str, params);
    }

    /**
     * 查询Time
     * @param builder SQLBuilder
     * @return 查询结果
     */
    @Nullable
    default Time queryTime(SQLBuilder builder) {
        return readTemplate().queryTime(builder);
    }

}
