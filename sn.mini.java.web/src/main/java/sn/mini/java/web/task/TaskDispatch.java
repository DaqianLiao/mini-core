package sn.mini.java.web.task;

import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import sn.mini.java.util.lang.ClassUtil;
import sn.mini.java.util.lang.StringUtil;
import sn.mini.java.util.logger.Log;

/**
 * 定时任务高度器
 * @author XChao
 */
public class TaskDispatch implements ServletContextListener {

	private final Map<TaskRunnable, ScheduledFuture<?>> futures = new ConcurrentHashMap<>();
	private ScheduledExecutorService scheduler;

	public final void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext context = event.getServletContext();
			if (StringUtil.isNotBlank(context.getInitParameter("task-classes"))) {
				String[] classes = context.getInitParameter("task-classes").split("[,][\\s]+");
				scheduler = Executors.newScheduledThreadPool(classes.length);
				for (String clazz : classes) {
					TaskRunnable runnable = new TaskRunnable(ClassUtil.forName(clazz, ITask.class), context.getInitParameter(clazz));
					futures.put(runnable, scheduler.scheduleAtFixedRate(runnable, 1, 1, MINUTES));
				}
			}
		} catch (Throwable throwable) {
			Log.error("Task start error.", throwable);
		}
	}

	public final void contextDestroyed(ServletContextEvent event) {
		for (Entry<TaskRunnable, ScheduledFuture<?>> entry : futures.entrySet()) {
			if (entry.getValue() != null && !entry.getValue().isCancelled()) {
				entry.getValue().cancel(true);
				entry.getKey().destroy();
			}
		}
		if (scheduler != null && !scheduler.isShutdown()) {
			scheduler.shutdown();
		}
	}
}
