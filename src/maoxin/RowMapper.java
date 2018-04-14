package maoxin;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Maoxin on 2018/04/14
 */
public interface  RowMapper<T> {
    public T mapping(ResultSet rs) throws SQLException;
}
