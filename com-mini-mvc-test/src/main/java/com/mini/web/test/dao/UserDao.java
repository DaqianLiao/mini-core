package com.mini.web.test.dao;

import com.google.inject.ImplementedBy;
import com.mini.core.jdbc.builder.SQLBuilder;
import com.mini.core.jdbc.mapper.BeanMapper;
import com.mini.core.jdbc.model.Paging;
import com.mini.core.util.DateFormatUtil;
import com.mini.web.test.dao.base.UserBaseDao;
import com.mini.web.test.dao.impl.UserDaoImpl;
import com.mini.web.test.entity.extend.UserExt;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

import static com.mini.web.test.entity.Region.REGION_ID_URI;
import static com.mini.web.test.entity.User.*;

/**
 * UserDao.java
 * @author xchao
 */
@ImplementedBy(UserDaoImpl.class)
public interface UserDao extends UserBaseDao {
	
	default Paging<UserExt> search(int page, int limit, String search, int sortType, int phoneAuto, int emailAuto, String regionIdUri,
		LocalDate startTime, LocalDate endTime) {
		return queryPaging(page, limit, new SQLBuilder(UserExt.class) {{
			// 搜索关键字条件
			if (!StringUtils.isBlank(search)) {
				where("(%s LIKE ? OR %s LIKE ? OR %s LIKE ?)", USER_NAME, USER_FULL_NAME, USER_PHONE);
				params(search + "%", search + "%", search + "%");
			}
			
			// 手机号已认证
			if (phoneAuto == 1) {
				where("%s = ?", USER_PHONE_AUTH);
				params(phoneAuto);
			}
			
			// 油箱已认证
			if (emailAuto == 1) {
				where("%s = ?", USER_EMAIL_AUTH);
				params(emailAuto);
			}
			
			// 地区条件
			if (!StringUtils.isBlank(regionIdUri)) {
				where("%s LIKE ?", REGION_ID_URI);
				params(regionIdUri + "%");
			}
			
			// 开始时间
			if (startTime != null) {
				where("%s >= ? ", USER_CREATE_TIME);
				params(DateFormatUtil.formatDate(startTime));
			}
			
			// 结束时间
			if (endTime != null) {
				where("%s < ? ", USER_CREATE_TIME);
				params(DateFormatUtil.formatDate(endTime.plusDays(1)));
			}
			// 排序
			orderBy("%s %s", USER_CREATE_TIME, (sortType == 1 ? "DESC" : "ASC"));
		}}, BeanMapper.create(UserExt.class));
	}
	
	default int delete(long[] idList) {
		return execute(new SQLBuilder() {{
			delete().from(TABLE);
			for (long id : idList) {
				or();
				where("%s = ?", USER_ID);
				params(id);
			}
		}});
	}
}
