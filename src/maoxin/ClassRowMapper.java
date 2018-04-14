package maoxin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Maoxin on 2018/04/14
 */
public class ClassRowMapper<T> implements RowMapper<T>{

    private Class<T> clazz = null;
    public ClassRowMapper(Class clazz){
        if(clazz==null)throw new NullPointerException();
        this.clazz = clazz;
    }
    @Override
    public T mapping(ResultSet rs) throws SQLException {
        T instance = null;
        ResultSetMetaData rsmd = null;
        try{
            instance = clazz.newInstance();
            rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            String[] fields = new String[count];
            Method[] methods = clazz.getMethods();
            for(int i = 0;i<count;i++){
                fields[i] = rsmd.getColumnLabel(i+1);
            }
            for (String methodName:fields){
                String realname = "set"+methodName;
                for(Method method:methods){
                    if(realname.equals(method.getName())){
                        method.invoke(instance,rs.getObject(methodName));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return  instance;
    }
}
