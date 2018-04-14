package maoxin;

import java.sql.*;

/**
 * Created by Maoxin on 2018/04/14
 */
public final class JDBCUtils {
    private static String url = "jdbc:mysql://localhost:3306/test1?useSSL=false";
    private static String user = "root";
    private static String pw = "1998lmx521lq";
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new ExceptionInInitializerError(e);
        }
    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void freeConnection(ResultSet rs, Statement statement,Connection conn){
        try{
            if (rs!=null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (statement!=null){
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try{
                    if(conn!=null){
                        conn.close();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
