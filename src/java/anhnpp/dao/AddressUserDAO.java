package anhnpp.dao;

import anhnpp.db.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nguye
 */
public class AddressUserDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public AddressUserDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean register(String address, String email) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT dbo.AddressUser\n"
                        + "        ( addUs, email )\n"
                        + "VALUES  ( ? , -- addUs - nvarchar(300)\n"
                        + "          ?  -- email - varchar(100)\n"
                        + "          )";
                preStm = conn.prepareStatement(sql);
                preStm.setNString(1, address);
                preStm.setString(2, email);
                result = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
