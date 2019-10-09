package com.mini.web.test.service;

import java.time.LocalDate;
import java.util.List;

import com.google.inject.ImplementedBy;
import com.mini.jdbc.util.Paging;
import com.mini.web.test.entity.UserExtend;
import com.mini.web.test.service.base.BaseUserService;
import com.mini.web.test.service.impl.UserServiceImpl;

/**
 * UserService.java
 * @author xchao
 */
@ImplementedBy(UserServiceImpl.class)
public interface UserService extends BaseUserService {

	/**
	 * 用户后台搜索方法
	 * @param paging      分页工具
	 * @param search      搜索字符串
	 * @param sortType    排序类型
	 * @param phoneAuto   手机号是否已认证
	 * @param emailAuto   邮箱是否已认证
	 * @param regionIdUri 所属地区ID Uri
	 * @return 用户信息 + 扩展
	 */
	default List<UserExtend> search(Paging paging, String search, int sortType, int phoneAuto, int emailAuto, String regionIdUri,
			LocalDate startTime, LocalDate endTime) {
		return getUserDao().search(paging, search, sortType, phoneAuto, emailAuto, regionIdUri, startTime, endTime);
	}

	default int delete(long[] idList) {
		return getUserDao().delete(idList);
	}
}
