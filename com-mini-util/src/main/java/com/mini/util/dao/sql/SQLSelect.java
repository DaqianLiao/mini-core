package com.mini.util.dao.sql;

import com.mini.util.dao.IDao;
import com.mini.util.dao.IMapper;
import com.mini.util.dao.Paging;
import com.mini.util.dao.SQL;
import com.mini.util.dao.sql.fragment.*;
import com.mini.util.lang.StringUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLSelect implements SQL, SQLFragment, SQLFrom<SQLSelect>, SQLJoin<SQLSelect>, SQLWhere<SQLSelect>, SQLGroup<SQLSelect>, SQLHaving<SQLSelect>,
        SQLOrder<SQLSelect> {
    private final DefaultHaving having = new DefaultHaving();
    private final DefaultWhere where = new DefaultWhere();
    private final List<Object> params = new ArrayList<>();
    private final DefaultGroup group = new DefaultGroup();
    private final DefaultOrder order = new DefaultOrder();
    private final List<String> keys = new ArrayList<>();
    private final DefaultFrom from = new DefaultFrom();
    private final DefaultJoin join = new DefaultJoin();

    /**
     * 添加查询需要的字段
     * @param key 字段集合
     * @return 当前对象
     */
    public final SQLSelect addKeys(String... key) {
        Collections.addAll(keys, key);
        return this;
    }

    public final SQLSelect addKeyAs(String key, String alisa) {
        return addKeys(StringUtil.join("", key, AS, alisa));
    }

    /**
     * -添加参数列表
     * @param param 参数值
     * @return SQLInsert
     */
    public final SQLSelect params(Object... param) {
        params.addAll(Arrays.asList(param));
        return this;
    }

    @Override
    public Object[] params() {
        return params.toArray();
    }


    @Override
    public final SQLSelect getSelf() {
        return this;
    }

    @Override
    public final DefaultFrom getFrom() {
        return this.from;
    }

    @Override
    public final DefaultJoin getJoin() {
        return this.join;
    }

    @Override
    public final DefaultWhere getWhere() {
        return this.where;
    }

    @Override
    public DefaultGroup getGroup() {
        return this.group;
    }

    @Override
    public DefaultHaving getHaving() {
        return this.having;
    }

    @Override
    public DefaultOrder getOrder() {
        return this.order;
    }

    /**
     * 获取查询字段片断
     * @return 查询字段片断
     */
    public final String keys() {
        return StringUtil.join(", ", keys);
    }

    @Override
    public final String from() {
        return SQLFrom.super.from();
    }


    @Override
    public final String join() {
        return SQLJoin.super.join();
    }

    @Override
    public final String where() {
        return SQLWhere.super.where();
    }

    @Override
    public String content() {
        StringBuilder builder = new StringBuilder();
        builder.append(SELECT).append(keys());
        builder.append(FROM).append(from());
        // 联合表处理
        String join = SQLSelect.this.join();
        if (StringUtil.isNotBlank(join)) {
            builder.append(JOIN);
            builder.append(join);
        }
        // 条件处理
        String where = SQLSelect.this.where();
        if (StringUtil.isNotBlank(where)) {
            builder.append(WHERE);
            builder.append(where());
        }
        // 分组处理
        String group = SQLSelect.this.group();
        if (StringUtil.isNotBlank(group)) {
            builder.append(GROUP);
            builder.append(BY);
            builder.append(group);
        }
        // 结果过虑
        String having = SQLSelect.this.group();
        if (StringUtil.isNotBlank(having)) {
            builder.append(HAVING);
            builder.append(having);
        }
        // 处理排序
        String order = SQLSelect.this.order();
        if (StringUtil.isNotBlank(order)) {
            builder.append(ORDER);
            builder.append(BY);
            builder.append(order);
        }
        return builder.toString();
    }

    public final <T> List<T> query(IDao dao, IMapper<T> mapper) throws SQLException {
        return dao.query(this, mapper);
    }

    public final <T> List<T> query(Paging paging, IDao dao, IMapper<T> mapper) throws SQLException {
        return dao.query(paging, this, mapper);
    }

    public final <T> T queryOne(IDao dao, IMapper<T> mapper) throws SQLException {
        return dao.queryOne(this, mapper);
    }
}
