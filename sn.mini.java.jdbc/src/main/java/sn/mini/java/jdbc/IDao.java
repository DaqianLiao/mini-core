/**
 * Created the sn.mini.jdbc.IDao.java
 * @created 2016年9月22日 下午3:28:42
 * @version 1.0.0
 */
package sn.mini.java.jdbc;

import sn.mini.java.jdbc.mapper.row.IRow;
import sn.mini.java.util.json.JSONArray;
import sn.mini.java.util.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

/**
 * sn.mini.jdbc.IDao.java
 * @author XChao
 */
public interface IDao extends Connection {

	/**
	 * 填充预处理sql语句
	 * @param statement 预处理sql 对象
	 * @param params    statement 对象中 sql对应的参数值
	 */
	void fullPreparedStatement(PreparedStatement statement, Object... params);

	/**
	 * 批量SQL操作实现方法<br/>
	 * 实现时, 填充 PreparedStatement参数可以调用 IDB.fullPreparedStatement
	 * @param sql    预编译SQL语句
	 * @param length 数据长度
	 * @param batch  回调接口
	 */
	int[] batch(String sql, int length, Batch batch);

	/**
	 * 批量SQL操作实现方法
	 * @param sql
	 * @param params
	 * @return
	 */
	int[] batch(String sql, Object[][] params);

	/**
	 * 批量SQL操作实现方法<br/>
	 * 实现时, 填充 PreparedStatement参数可以调用 IDB.fullPreparedStatement
	 * @param sql        预编译SQL语句
	 * @param dataLength 数据长度
	 * @param batch      回调接口
	 */
	int[] batch(Sql sql, int dataLength, Batch batch);

	/**
	 * 批量SQL操作实现方法
	 * @param sql
	 * @param params
	 * @return
	 */
	int[] batch(Sql sql, Object[][] params);

	/**
	 * 执行insert,replace,update,delete语句,返回影响数据库记录的行数
	 * @param sql
	 * @param params
	 * @return
	 */
	int execute(String sql, Object... params);

	/**
	 * 执行insert,replace语句, 返回数据库自增ID
	 * @param sql
	 * @param params
	 * @return 返回自增长ID值
	 */
	long executeGen(String sql, Object... params);

	/**
	 * 执行insert,replace,update,delete语句,返回影响数据库记录的行数
	 * @param sql
	 * @return
	 */
	int execute(Sql sql);

	/**
	 * 执行insert,replace语句, 返回数据库自增ID
	 * @param sql
	 * @return 返回自增长ID值
	 */
	long executeGen(Sql sql);

	/**
	 * 生成SQL结果: insert into user_info(coumn1, column2, column3) values(?, ?, ?)
	 * @param tname  表名称 比如: user_info
	 * @param keys   比如: coumn1, column2, column3
	 * @param params 比如: va1, va2, va3 分别对应三个字段的值
	 * @return
	 */
	int insert(String tname, String[] keys, Object... params);

	/**
	 * 生成SQL结果: insert into user_info(coumn1, column2, column3) values(?, ?, ?)
	 * @param tname  表名称 比如: user_info
	 * @param keys   比如: coumn1, column2, column3
	 * @param params 比如: va1, va2, va3 分别对应三个字段的值
	 * @return 返回自增长ID值
	 */
	long insertGen(String tname, String[] keys, Object... params);

	/**
	 * 生成SQL结果: replace into user_info(coumn1, column2, column3) values(?, ?, ?)
	 * @param tname  表名称 比如: user_info
	 * @param keys   比如: coumn1, column2, column3
	 * @param params 比如: va1, va2, va3 分别对应三个字段的值
	 * @return
	 */
	int replace(String tname, String[] keys, Object... params);

	/**
	 * 生成SQL结果: replace into user_info(coumn1, column2, column3) values(?, ?, ?)
	 * @param tname  表名称 比如: user_info
	 * @param keys   比如: coumn1, column2, column3
	 * @param params 比如: va1, va2, va3 分别对应三个字段的值
	 * @return 返回自增长ID值
	 */
	long replaceGen(String tname, String[] keys, Object... params);

	/**
	 * 生成SQL结果: update coumn1 = ?, column2 = ?, column3 = ? where coumn4 = ? and column5 = ?
	 * @param tname  表名称 比如: user_info
	 * @param keys   比如: coumn1, column2, column3
	 * @param wheres 比如: coumn4, column5 默认用and方式连接
	 * @param params 比如: va1, va2, va3, va4, va5, 其中前三个对应keys的值, 后两个对应wheres的值
	 * @return
	 */
	int update(String tname, String[] keys, String[] wheres, Object... params);

	/**
	 * 生成SQL结果: delete user_info where coumn1 = ? and column2 = ? and column3 = ?
	 * @param tname  表名称 比如: user_info
	 * @param wheres 比如: coumn1, column2, column3 默认用and方式连接
	 * @param params 比如: va1, va2, va3 分别对应三个字段值
	 * @return 影响数据库的条数
	 */
	int delete(String tname, String[] wheres, Object... params);

	/**
	 * 查询记录, 根据sql中所有带条查询数据库所有匹配的记录<br/>
	 * 调用该方法时, 一定要手动关闭ResultSet结果集,否则导致无法关闭连接
	 * @param table  回调解析过程
	 * @param sql    sql语句
	 * @param params sql语句是的参数
	 * @return
	 */
	<T> T executeQuery(IRow<T> row, String sql, Object... params);

	/**
	 * 查询记录, 根据sql所所带条件查询数据库所有匹配的记录<br/>
	 * 调用该方法时, 一定要手动关闭ResultSet结果集,否则导致无法关闭连接
	 * @param table 回调解析过程
	 * @param sql   sql封装器
	 * @return
	 */
	<T> T executeQuery(IRow<T> row, Sql sql);

	/**
	 * 查询记录, 根据sql所所带条件查询数据库记录按分页工具分页<br/>
	 * 调用该方法时, 一定要手动关闭ResultSet结果集,否则导致无法关闭连接
	 * @param table  回调解析过程
	 * @param paging 分页工具
	 * @param sql    sql语句
	 * @param params sql语句是的参数
	 * @return
	 */
	<T> T executeQuery(IRow<T> row, Paging paging, String sql, Object... params);

	/**
	 * 查询记录, 根据sql所所带条件查询数据库记录按分页工具分页<br/>
	 * 调用该方法时, 一定要手动关闭ResultSet结果集,否则导致无法关闭连接
	 * @param table  回调解析过程
	 * @param paging 分页工具
	 * @param sql    sql封装器
	 * @return
	 */
	<T> T executeQuery(IRow<T> row, Paging paging, Sql sql);

	/**
	 * 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回
	 * @param sql sql封装工具
	 * @return
	 */
	JSONArray query(Sql sql);

	/**
	 * 根据 查询所有记录
	 * @param sql
	 * @param params
	 * @return
	 */
	JSONArray query(String sql, Object... params);

	/**
	 * -查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回
	 * @param 实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回
	 * @return 查询结果
	 */
	<T> List<T> query(Class<T> clazz, Sql sql);

	/**
	 * -查询所有记录
	 * @param clazz  实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql    查询sql语句
	 * @param params
	 * @return
	 */
	<T> List<T> query(Class<T> clazz, String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回, 该方法中sql中的cellMapper无效
	 * @param xRowMapper
	 * @param sql
	 * @return
	 */
	<T> List<T> query(IRow<T> mapper, Sql sql);

	/**
	 * 查询所有数据
	 * @param mapper
	 * @param sql
	 * @param params
	 * @return
	 */
	<T> List<T> query(IRow<T> mapper, String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件与Paging工具分页查询数据库
	 * @param paging 分页工具
	 * @param sql    sql封装工具
	 * @return 查询结果 @see com.ms.mvc.util.JSONArrays
	 */
	JSONArray query(Paging paging, Sql sql);

	/**
	 * 分页查询数据
	 * @param paging
	 * @param sql
	 * @param objects
	 * @return
	 */
	JSONArray query(Paging paging, String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件与Paging工具分页查询数据库
	 * @param paging sql 分页工具
	 * @param clazz  实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql
	 * @return
	 */
	<T> List<T> query(Paging paging, Class<T> clazz, Sql sql);

	/**
	 * 查询所有记录
	 * @param paging
	 * @param clazz   实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql
	 * @param objects
	 * @return
	 */
	<T> List<T> query(Paging paging, Class<T> clazz, String sql, Object... params);

	/**
	 * 查询记录， 根据sql中所带的条件查询数据库所有匹配的记录全部返回, 该方法中sql中的cellMapper无效
	 * @param paging
	 * @param mapper
	 * @param sql
	 * @return
	 */
	<T> List<T> query(Paging paging, IRow<T> mapper, Sql sql);

	/**
	 * 分页查询数据
	 * @param paging
	 * @param mapper
	 * @param sql
	 * @param params
	 * @return
	 */
	<T> List<T> query(Paging paging, IRow<T> mapper, String sql, Object... params);

	/**
	 * 查询单条记录
	 * @param sql sql封装工具
	 * @return 查询结果 @see com.ms.mvc.util.JSONArrays
	 */
	JSONObject queryOne(Sql sql);

	/**
	 * 查询单条记录
	 * @param sql
	 * @param params
	 * @return
	 */
	JSONObject queryOne(String sql, Object... params);

	/**
	 * 查询单条记录
	 * @param clazz 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql   根据sql中所带的条件查询数据库所有匹配的记录全部返回
	 * @return 查询结果
	 */
	<T> T queryOne(Class<T> clazz, Sql sql);

	/**
	 * 查询单条记录
	 * @param clazz  实体对象class. 为了节约性能 ，实体对象属性中不能有复合对象，只有能简单数据 类型，否则组装失败
	 * @param sql
	 * @param params
	 * @return
	 */
	<T> T queryOne(Class<T> clazz, String sql, Object... params);

	/**
	 * 查询单条记录 该方法中sql中的cellMapper无效
	 * @param mapper
	 * @param sql
	 * @return
	 */
	<T> T queryOne(IRow<T> mapper, Sql sql);

	/**
	 * 查询单条记录
	 * @param mapper
	 * @param sql
	 * @param params
	 * @return
	 */
	<T> T queryOne(IRow<T> mapper, String sql, Object... params);

	/**
	 * 查询单一 String 数据值
	 * @param sql
	 */
	String queryString(Sql sql);

	/**
	 * 查询单一 String 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	String queryString(String sql, Object... params);

	/**
	 * 查询单一 Long 数据值
	 * @param sql
	 */
	long queryLong(Sql sql);

	/**
	 * 查询单一 Long 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	long queryLong(String sql, Object... params);

	/**
	 * 查询单一 int 数据值
	 * @param sql
	 */
	int queryInt(Sql sql);

	/**
	 * 查询单一 int 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	int queryInt(String sql, Object... params);

	/**
	 * 查询单一 short 数据值
	 * @param sql
	 */
	short queryShort(Sql sql);

	/**
	 * 查询单一 short 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	short queryShort(String sql, Object... params);

	/**
	 * 查询单一 byte 数据值
	 * @param sql
	 */
	byte queryByte(Sql sql);

	/**
	 * 查询单一 short 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	byte queryByte(String sql, Object... params);

	/**
	 * 查询单一 double 数据值
	 * @param sql
	 */
	double queryDouble(Sql sql);

	/**
	 * 查询单一 double 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	double queryDouble(String sql, Object... params);

	/**
	 * 查询单一 float 数据值
	 * @param sql
	 */
	float queryFloat(Sql sql);

	/**
	 * 查询单一 float 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	float queryFloat(String sql, Object... params);

	/**
	 * 查询单一 date 数据值
	 * @param sql
	 */
	Date queryDate(Sql sql);

	/**
	 * 查询单一 date 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	Date queryDate(String sql, Object... params);

	/**
	 * 查询单一 boolean 数据值
	 * @param sql
	 */
	boolean queryBoolean(Sql sql);

	/**
	 * 查询单一 boolean 数据值
	 * @param sql
	 * @param params
	 * @return
	 */
	boolean queryBoolean(String sql, Object... params);

	/**
	 * 事件执行方法
	 * @param transaction
	 * @return true: 事务执行成功(最后提交事务), false: 事务执行失败, 最后回滚
	 */
	boolean transaction(Transaction transaction);

	/**
	 * 事件执行方法
	 * @param transLevel  事务级别
	 * @param transaction
	 * @return true: 事务执行成功(最后提交事务), false: 事务执行失败, 最后回滚
	 */
	boolean transaction(int transLevel, Transaction transaction);

	/**
	 * Batch 批处理回调
	 * @author XChao
	 */
	@FunctionalInterface
	interface Batch {
		Object[] values(int index);
	}

	/**
	 * Transaction 事务回调
	 * @author XChao
	 */
	@FunctionalInterface
	interface Transaction {
		boolean transaction();
	}
}
