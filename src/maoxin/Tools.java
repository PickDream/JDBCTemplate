package maoxin;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现一个简单的jdbc工具通过传入Domain的Class来返回数据对象
 * Created by Maoxin on 2018/04/14
 */
public class Tools {
    //查询单个对象的工具函数
    public static <T> T find(String sql,RowMapper<T> rowMapper,Object... argms){
        T ret = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            int count = ps.getParameterMetaData().getParameterCount();
            int argmCount = argms==null?0:argms.length;
            if(count!=argmCount){
                throw new SQLException("参数信息不匹配");
            }
            for (int i = 0;i<count;i++){
                ps.setObject(i+1,argms[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                ret = rowMapper.mapping(rs);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            JDBCUtils.freeConnection(rs,ps,conn);
        }
    }
    public static <T> T find(String sql,Object[] argms,Class clazz){
        return null;
    }
    //测试
    public static void main(String[] args) {
        User user = Tools.find("SELECT ID,Location,Password,Name FROM User Where ID=?",
                 new ClassRowMapper<User>(User.class),
                20165919);
    }
}
