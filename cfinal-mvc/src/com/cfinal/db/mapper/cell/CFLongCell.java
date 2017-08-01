/**   
 * Created the com.cfinal.db.mapper.cell.CFLongCell.java
 * @created 2016年10月9日 下午5:37:01 
 * @version 1.0.0 
 */
package com.cfinal.db.mapper.cell;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cfinal.db.CFDB;

/**   
 * com.cfinal.db.mapper.cell.CFLongCell.java 
 * @author XChao  
 */
public class CFLongCell implements CFCell<Long>{

	@Override
	public Long getCell(CFDB db, ResultSet resultSet, String columnLabel) throws SQLException {
		return resultSet.getLong(columnLabel);
	}

}
