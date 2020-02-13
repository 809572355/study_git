package hanvis.demo;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class BasicDAO2 {
    private QueryRunner qr = new QueryRunner(JdbcUtil.getDs());

    public int upadata(String sql, Object... args) throws SQLException {
        return qr.update(sql, args);
    }

}
