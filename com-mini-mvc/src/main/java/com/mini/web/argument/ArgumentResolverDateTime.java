package com.mini.web.argument;

import com.mini.util.DateUtil;
import com.mini.util.LocalDateTimeUtil;
import com.mini.web.config.Configure;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static com.mini.util.StringUtil.def;

@Named
@Singleton
public final class ArgumentResolverDateTime extends ArgumentResolverBase {
    private Configure configure;

    @Inject
    public void setConfigure(Configure configure) {
        this.configure = configure;
    }

    @Override
    protected Object parse(String text, @Nonnull Class<?> type, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) {
        String format = def(configure.getDateTimeFormat(), "yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTimeUtil.parse(text, format);

        // java.time.LocalDateTime 类型的参数
        if (LocalDateTime.class.isAssignableFrom(type)) {
            return dateTime;
        }

        // java.sql.Timestamp 类型的参数
        if (Timestamp.class.isAssignableFrom(type)) {
            return Timestamp.valueOf(dateTime);
        }

        // java.util.Date
        if (Date.class.isAssignableFrom(type)) {
            return DateUtil.parse(text, format);
        }

        //  其它类型的参数
        return null;
    }
}
