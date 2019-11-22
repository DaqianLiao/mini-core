package com.mini.core.jdbc.mapper;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mini.core.jdbc.util.JdbcUtil.getObject;
import static org.apache.commons.lang3.Validate.isTrue;

public class IMapperSingle<T> implements IMapper<T> {
    private final Class<T> type;

    public IMapperSingle(Class<T> type) {
        this.type = type;
    }

    @Nonnull
    @Override
    public T get(ResultSet rs, int number) throws SQLException {
        isTrue(rs.getMetaData().getColumnCount() == 1);
        return type.cast(getObject(rs, 1, type));
    }
}
