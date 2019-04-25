package com.mini.util.dao.sql.fragment;

import com.mini.util.dao.SQL;
import com.mini.util.dao.sql.SQLSelect;
import com.mini.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.List;

public final class DefaultJoin implements SQLFragment {
    private final List<SQLFragment> ones = new ArrayList<>();
    private String connector = SQL.AND;
    private SQLFragment content;

    /**
     * 设置 FROM 对象
     * @param fragment FROM 对象
     * @return 当前对象
     */
    public DefaultJoin join(SQLFragment fragment) {
        this.content = fragment;
        return this;
    }

    /**
     * 设置 FROM 对象内容
     * @param content FROM 对象
     * @param alias   别名
     * @return 当前对象
     */
    public DefaultJoin join(String content, String alias) {
        return join(new DefaultFragment(content, alias));
    }

    /**
     * 设置 FROM 对象内容
     * @param content FROM 对象
     * @return 当前对象
     */
    public DefaultJoin join(String content) {
        return join(new DefaultFragment(content));
    }

    /**
     * 设置 FROM 对象
     * @param select FROM 对象
     * @return 当前对象
     */
    public DefaultJoin join(SQLSelect select, String alias) {
        return join(select.content(), alias);
    }

    /**
     * 设置 FROM 对象
     * @param select FROM 对象
     * @return 当前对象
     */
    public DefaultJoin join(SQLSelect select) {
        return join(select.content());
    }

    /**
     * 设置 ON 的连接符
     * @param connector 连接符
     * @return 当前对象
     */
    public DefaultJoin connector(String connector) {
        this.connector = connector;
        return this;
    }

    /**
     * 添加一个SQL片断
     * @param fragment 片断内容
     * @return 当前对象
     */
    public DefaultJoin on(SQLFragment fragment) {
        this.ones.add(fragment);
        return this;
    }

    /**
     * 添加一个条件
     * @param name  字段名称
     * @param c     连接符号
     * @param value 字段值
     * @return 当前对象
     */
    public DefaultJoin on(String name, String c, String value) {
        return on(new DefaultFragment(name, c, value));
    }

    /**
     * 添加一个条件
     * @param name  字段名称
     * @param value 字段值
     * @return 当前对象
     */
    public DefaultJoin on(String name, String value) {
        return on(name, "=", value);
    }

    /**
     * 添加一个条件
     * @param name 字段名称
     * @return 当前对象
     */
    public DefaultJoin on(String name) {
        return on(name, "?");
    }

    @Override
    public  String content() {
        if (content == null) return null;
        return StringUtil.join(" ", content.content(), "(", StringUtil.join(connector, ones.stream().map(SQLFragment::content).toArray()), ")");
    }
}
