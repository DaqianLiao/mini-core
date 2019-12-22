package com.mini.core.jdbc.transaction;

import com.mini.core.jdbc.JdbcTemplate;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Iterator;
import java.util.List;

/**
 * JDBC 事务实现
 * @author xchao
 */
@Singleton
public final class JdbcTransManager implements TransManager {
	private final List<JdbcTemplate> jdbcTemplateList;

	@Inject
	public JdbcTransManager(@Nonnull List<JdbcTemplate> jdbcTemplateList) {
		this.jdbcTemplateList = jdbcTemplateList;
	}

	@Override
	public <T> T open(TransManagerCallback<T> callback) throws Throwable {
		return transaction(jdbcTemplateList.iterator(), callback);
	}

	private <T> T transaction(Iterator<JdbcTemplate> iterator, TransManagerCallback<T> callback) throws Throwable {
		if (!iterator.hasNext()) return callback.apply();
		// 获取 JdbcTemplate
		JdbcTemplate jdbcTemplate = iterator.next();
		boolean commit = false;
		try {
			T result = transaction(iterator, callback);
			commit = true;
			return result;
		} finally {
			jdbcTemplate.endTransaction(commit);
		}
	}
}
