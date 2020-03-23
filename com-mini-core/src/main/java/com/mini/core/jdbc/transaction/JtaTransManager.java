package com.mini.core.jdbc.transaction;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.UserTransaction;

import static com.mini.core.jdbc.JdbcAccessor.transaction;
import static com.mini.core.util.ThrowsUtil.hidden;
import static java.util.Objects.requireNonNull;

/**
 * JTA 事务实现
 * @author xchao
 */
@Singleton
public final class JtaTransManager implements TransManager {
	private final UserTransaction transaction;
	
	@Inject
	public JtaTransManager(@Nullable UserTransaction transaction) {
		this.transaction = transaction;
	}
	
	@Override
	public <T> T open(TransManagerCallback<T> callback) {
		var userTrans = requireNonNull(transaction);
		return transaction(userTrans, trans -> {
			try {
				boolean commit = false;
				try {
					// 开始事务
					trans.startTransaction();
					// 调用目标方法
					T t = callback.apply();
					// 标记提交
					commit = true;
					// 返回
					return t;
				} finally {
					// 结束当前事务 (true-提交)
					trans.endTransaction(commit);
				}
			} catch (Throwable e) {
				throw hidden(e);
			}
		});
	}
}
