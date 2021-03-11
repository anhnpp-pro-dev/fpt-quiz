package anhnpp.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author nguye
 */
public class MyConnection implements Serializable {

    public static Connection getConnection() throws NamingException, SQLException {
        Connection conn = null;
        Context ctx = new InitialContext();
        Context tomcat = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcat.lookup("DBDataSource");
        conn = ds.getConnection();
        return conn;
    }
}
