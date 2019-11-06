package com.mini.code;

import com.mini.code.impl.*;
import com.mini.jdbc.JdbcTemplate;
import com.mini.jdbc.JdbcTemplateMysql;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.SQLException;
import java.util.EventListener;

import static com.mini.code.impl.CodeDaoImpl.generator;
import static java.lang.String.format;

public class ConfigureDefault extends Configure implements EventListener {
    String PATH = "D:/workspace-git/mini-core/com-mini-mvc-test";

    @Override
    public String getClassPath() {
        return format("%s/src/main/java", PATH);
    }

    @Override
    public String getDocumentPath() {
        return format("%s/document", PATH);
    }

    @Override
    public String getPackageName() {
        return "com.mini.web.test";
    }

    @Override
    public String getDatabaseName() {
        return "mini_test";
    }

    @Override
    public JdbcTemplate getJdbcTemplate() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(getDatabaseName());
        dataSource.setServerName(SERVER_NAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setUseSSL(false);
        dataSource.setUser("root");
        return new JdbcTemplateMysql(dataSource);
    }

    @Override
    public BeanItem[] getDatabaseBeans() {
        return new BeanItem[]{
                new BeanItem("Region", "common_region", "region_"),
                new BeanItem("User", "user_info", "user_"),
        };
    }

    public static void main(String[] args) throws Exception {
        Configure configure = new ConfigureDefault();
        for (BeanItem bean : configure.getDatabaseBeans()) {
            ClassInfo info = new ClassInfo(configure, bean);
            // 生成 Bean Mapper Base 代码生成
            CodeBean.generator(configure, info, bean, true);
            CodeDaoBase.generator(configure, info, bean, true);

            // 生成 DAO  与Dao Impl代码
            CodeDao.generator(configure, info, bean, false);
            generator(configure, info, bean, false);
        }
        // 生成数据库文档
        Creations.generator(configure);
        Dictionaries.generator(configure);

    }
}
