/**
 * Created the com.cfinal.util.logger.CFLog.java
 * @created 2017年8月15日 上午11:03:54
 * @version 1.0.0
 */
package com.cfinal.util.logger;

/**
 * com.cfinal.util.logger.CFLog.java
 * @author XChao
 */
public abstract class CFLog {
	protected abstract boolean isFatalEnabled();

	protected abstract boolean isErrorEnabled();

	protected abstract boolean isWarnEnabled();

	protected abstract boolean isInfoEnabled();

	protected abstract boolean isDebugEnabled();

	protected abstract boolean isTraceEnabled();

	protected abstract void doFatal(String message);

	protected abstract void doFatal(String message, Throwable throwable);

	protected abstract void doError(String message);

	protected abstract void doError(String message, Throwable throwable);

	protected abstract void doWarn(String message);

	protected abstract void doWarn(String message, Throwable throwable);

	protected abstract void doInfo(String message);

	protected abstract void doInfo(String message, Throwable throwable);

	protected abstract void doDebug(String message);

	protected abstract void doDebug(String message, Throwable throwable);

	protected abstract void doTrace(String message);

	protected abstract void doTrace(String message, Throwable throwable);

	private static CFLog instance;

	/**
	 * 获取当前类的实例
	 * @return
	 */
	private final static CFLog getInstance() {
		if(instance == null) {
			synchronized (CFLog.class) {
				if(instance == null) {
					instance = new CFLogger();
				}
			}
		}
		return instance;
	}

	/**
	 * 设置输出日志实现类
	 * @param logger
	 */
	public static void setInstance(Class<? extends CFLog> logger) {
		try {
			CFLog.instance = logger.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 输出日志
	 * @param message
	 */
	public static void fatal(String message) {
		if(getInstance().isFatalEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doFatal(getMessageByElement(element, message));
		}
	}

	/**
	 * 输出日志
	 * @param message
	 * @param throwable
	 */
	public static void fatal(String message, Throwable throwable) {
		if(getInstance().isFatalEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doFatal(getMessageByElement(element, message), throwable);
		}
	}

	/**
	 * 输出日志
	 * @param message
	 */
	public static void error(String message) {
		if(getInstance().isErrorEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doError(getMessageByElement(element, message));
		}
	}

	/**
	 * 输出日志
	 * @param message
	 * @param throwable
	 */
	public static void error(String message, Throwable throwable) {
		if(getInstance().isErrorEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doError(getMessageByElement(element, message), throwable);
		}
	}

	/**
	 * 输出日志
	 * @param message
	 */
	public static void warn(String message) {
		if(getInstance().isWarnEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doWarn(getMessageByElement(element, message));
		}
	}

	/**
	 * 输出日志
	 * @param message
	 * @param throwable
	 */
	public static void warn(String message, Throwable throwable) {
		if(getInstance().isWarnEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doWarn(getMessageByElement(element, message), throwable);
		}
	}

	/**
	 * 输出日志
	 * @param message
	 */
	public static void info(String message) {
		if(getInstance().isInfoEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doInfo(getMessageByElement(element, message));
		}
	}

	/**
	 * 输出日志
	 * @param message
	 * @param throwable
	 */
	public static void info(String message, Throwable throwable) {
		if(getInstance().isInfoEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doInfo(getMessageByElement(element, message), throwable);
		}
	}

	/**
	 * 输出日志
	 * @param message
	 */
	public static void debug(String message) {
		if(getInstance().isDebugEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doDebug(getMessageByElement(element, message));
		}
	}

	/**
	 * 输出日志
	 * @param message
	 * @param throwable
	 */
	public static void debug(String message, Throwable throwable) {
		if(getInstance().isDebugEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doDebug(getMessageByElement(element, message), throwable);
		}
	}

	/**
	 * 输出日志
	 * @param message
	 */
	public static void trace(String message) {
		if(getInstance().isTraceEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doTrace(getMessageByElement(element, message));
		}
	}

	/**
	 * 输出日志
	 * @param message
	 * @param throwable
	 */
	public static void trace(String message, Throwable throwable) {
		if(getInstance().isTraceEnabled()) {
			StackTraceElement element = getStackTraceElement(new Throwable());
			getInstance().doTrace(getMessageByElement(element, message), throwable);
		}
	}

	private static StackTraceElement getStackTraceElement(Throwable throwable) {
		StackTraceElement[] elements = throwable.getStackTrace();
		return elements.length > 0 ? elements[1] : elements[0];
	}

	private static String getMessageByElement(StackTraceElement element, String message) {
		StringBuilder builer = new StringBuilder().append(element.getClassName()).append(".");
		builer.append(element.getMethodName()).append("[Line:").append(element.getLineNumber());
		return builer.append("] - ").append(message).toString();
	}
}
