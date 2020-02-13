package hanvis.demo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {


    private static DataSource ds;
    private static ThreadLocal<Connection> local;
    static{
        try {
            Properties pro = new Properties();
            pro.load(Hello.class.getClassLoader().getResourceAsStream("druid.properties"));
            local = new ThreadLocal<>();
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDs() {
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = local.get();
        if(conn==null)
        {
            conn = ds.getConnection();
            local.set(conn);
        }
        return conn;
    }

    public static void free()
    {
        Connection conn = local.get();
        if(conn!=null)
        {
            try {
                local.remove();
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
