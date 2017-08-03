/**
 * Created the com.cfinal.gen.GENBeanEntity.java
 * @created 2017年8月3日 上午10:08:01
 * @version 1.0.0
 */
package com.cfinal.gen;

import java.sql.SQLException;

import com.cfinal.db.CFDB;
import com.cfinal.db.CFDBFactory;
import com.cfinal.db.model.mapping.CFDBTables;

/**
 * com.cfinal.gen.GENBeanEntity.java
 * @author XChao
 */
public class GENBeanDB extends GENAll {
	public static void main(String[] args) throws SQLException, Exception {
		CFDB db = null;
		try {
			db = GENConfig.getDB(); // 创建数据库连接
			// 查询当前表的所有字段
			GENAll.genDB(CFDBTables.getColumns(GENConfig.getDB(), GENConfig.TABLE_DB_NAME),
				CFDBTables.getPrimaryKey(db, GENConfig.TABLE_DB_NAME));
		} finally {
			CFDBFactory.close(db);
		}
	}
}
