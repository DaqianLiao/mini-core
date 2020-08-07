package com.mini.plugin.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intellij.database.psi.DbTable;
import com.mini.plugin.util.StringUtil;
import com.squareup.javapoet.ClassName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.LinkedHashMap;

import static com.squareup.javapoet.ClassName.get;

/**
 * 数据库连接信息-表
 *
 * @author xchao
 */
@SuppressWarnings("ALL")
public final class TableInfo extends AbstractClone<TableInfo> implements Serializable {
    private LinkedHashMap<String, ColumnInfo> columnMap;
    private String packageName;
    private String entityName;
    private String namePrefix;
    private String tableName;
    private String comment;
    @JsonIgnore
    private DbTable table;

    public TableInfo setColumnMap(LinkedHashMap<String, ColumnInfo> columnMap) {
        this.columnMap = columnMap;
        return this;
    }

    public TableInfo setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public TableInfo setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public TableInfo setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
        return this;
    }

    public TableInfo setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public TableInfo setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public TableInfo setTable(DbTable table) {
        this.table = table;
        return this;
    }

    @NotNull
    public final LinkedHashMap<String, ColumnInfo> getColumnMap() {
        if (TableInfo.this.columnMap == null) {
            columnMap = new LinkedHashMap<>();
        }
        return columnMap;
    }

    @NotNull
    public synchronized String getPackageName() {
        if (StringUtil.isEmpty(packageName)) {
            packageName = "";
        }
        return packageName;
    }

    @NotNull
    public synchronized String getEntityName() {
        if (StringUtil.isEmpty(entityName)) {
            entityName = "";
        }
        return entityName;
    }

    @NotNull
    public synchronized String getNamePrefix() {
        if (StringUtil.isEmpty(namePrefix)) {
            namePrefix = "";
        }
        return namePrefix;
    }

    @NotNull
    public synchronized String getTableName() {
        if (StringUtil.isEmpty(tableName)) {
            tableName = "";
        }
        return tableName;
    }

    @NotNull
    public synchronized String getComment() {
        if (StringUtil.isEmpty(comment)) {
            comment = "Comment";
        }
        return comment;
    }

    @Nullable
    public final DbTable getTable() {
        return table;
    }

    public TableInfo addColumnInfo(@NotNull ColumnInfo columnInfo) {
        getColumnMap().put(columnInfo.getColumnName(), columnInfo);
        return this;
    }

    @Nullable
    public final ColumnInfo getColumnInfo(String columnName) {
        if (columnMap == null) return null;
        return columnMap.get(columnName);
    }

    @NotNull
    @Override
    public final synchronized TableInfo clone() {
        final TableInfo info = new TableInfo();
        info.setColumnMap(new LinkedHashMap<>(getColumnMap()));
        info.setPackageName(packageName);
        info.setNamePrefix(namePrefix);
        info.setEntityName(entityName);
        info.setTableName(tableName);
        info.setComment(comment);
        info.setTable(table);
        return info;
    }


    public String beanName() {
        return getEntityName();
    }

    public String beanPackage() {
        return getPackageName() + ".entity";
    }

    public ClassName beanClass() {
        return get(beanPackage(), beanName());
    }

//	public  String builderName() {
//		return getEntityName() + "Builder";
//	}
//
//	public  ClassName builderClass() {
//		return get(beanPackage(), beanName(), builderName());
//	}

    public String daoBaseName() {
        return getEntityName() + "BaseDao";
    }

    public String daoBasePackage() {
        return getPackageName() + ".dao.base";
    }

    public ClassName daoBaseClass() {
        return get(daoBasePackage(), daoBaseName());
    }

    public String daoName() {
        return getEntityName() + "Dao";
    }

    public String daoPackage() {
        return getPackageName() + ".dao";
    }

    public ClassName daoClass() {
        return get(daoPackage(), daoName());
    }

    public String daoImplName() {
        return getEntityName() + "DaoImpl";
    }

    public String daoImplPackage() {
        return getPackageName() + ".dao.impl";
    }

    public ClassName daoImplClass() {
        return get(daoImplPackage(), daoImplName());
    }
}
