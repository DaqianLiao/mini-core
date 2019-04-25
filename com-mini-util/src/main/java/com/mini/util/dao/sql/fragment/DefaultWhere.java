package com.mini.util.dao.sql.fragment;

import com.mini.util.dao.SQL;
import com.mini.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.List;

public final class DefaultWhere implements SQLFragment {
    private final List<SQLFragment> wheres = new ArrayList<>();
    private String connector = SQL.AND;

    /**
     * 设置多个条件的连接符
     * @param connector 连接符
     * @return 当前对象
     */
    public DefaultWhere connector(String connector) {
        this.connector = connector;
        return this;
    }

    /**
     * 添加一个SQL片断
     * @param fragment 片断内容
     * @return 当前对象
     */
    public DefaultWhere where(SQLFragment fragment) {
        this.wheres.add(fragment);
        return this;
    }

    /**
     * 添加一个条件
     * @param name  字段名称
     * @param c     连接符号
     * @param value 字段值
     * @return 当前对象
     */
    public DefaultWhere where(String name, String c, String value) {
        return where(new DefaultFragment(name, c, value));
    }

    /**
     * 添加一个条件
     * @param name  字段名称
     * @param value 字段值
     * @return 当前对象
     */
    public DefaultWhere where(String name, String value) {
        return where(name, "=", value);
    }

    /**
     * 添加一个条件
     * @param name 字段名称
     * @return 当前对象
     */
    public DefaultWhere where(String name) {
        return where(name, "?");
    }

    @Override
    public String content() {
        return "(" + StringUtil.join(connector, wheres.stream().map(SQLFragment::content).toArray()) + ")";
    }
}
