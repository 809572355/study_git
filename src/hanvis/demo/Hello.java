package hanvis.demo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Hello {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入部门名称");
        String name = input.nextLine();

        System.out.println("请输入部门简介");
        String desc = input.nextLine();

        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into t_department values(null,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,name);
        pst.setObject(2,desc);

        int len = pst.executeUpdate();
        System.out.println(len>0?"添加成功":"添加失败");

        pst.close();
        JdbcUtil.free();
        input.close();

    }

}
