package hanvis.demo;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class BasicDAO {
    public int updata(String sql,Object... args) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        if(args!=null && args.length>0)
        {
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i+1,args[i]);
            }
        }
        int len = pst.executeUpdate();
        pst.close();
        JdbcUtil.free();
        return len;

    }

    public <T>ArrayList<T> getAll(Class<T> clazz, String sql, Object... args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        if(args!=null && args.length>0)
        {
            for(int i = 0; i < args.length; i++)
            {
                pst.setObject(i+1,args[i]);
            }
        }
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rsm = rs.getMetaData();

        int count = rsm.getColumnCount();
        ArrayList<T> list = new ArrayList<>();
        while(rs.next())
        {
            T obj = clazz.newInstance();
            for (int i = 0; i < count; i++) {
                String columnName = rsm.getColumnLabel(i + 1);
                Field filed = clazz.getDeclaredField(columnName);

            }
            list.add(obj);
        }
        pst.close();
        JdbcUtil.free();
        return list;
    }
}
