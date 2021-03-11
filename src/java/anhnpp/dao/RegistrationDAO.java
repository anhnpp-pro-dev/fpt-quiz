package anhnpp.dao;

import anhnpp.db.MyConnection;
import anhnpp.dto.RegistrationDTO;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nguye
 */
public class RegistrationDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public RegistrationDAO() {
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

    public boolean register(String email, String fullname, String phone, String address, String password, String codeVerification) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT dbo.Users\n"
                        + "        ( email ,\n"
                        + "          fullname ,\n"
                        + "          phone ,\n"
                        + "          password ,\n"
                        + "          createDate ,\n"
                        + "          codeVerification ,\n"
                        + "          sttUsId ,\n"
                        + "          roleId\n"
                        + "        )\n"
                        + "VALUES  ( ? , -- email - varchar(100)\n"
                        + "          ? , -- fullname - nvarchar(100)\n"
                        + "          ? , -- phone - int\n"
                        + "          ? , -- password - varchar(100)\n"
                        + "          GETDATE() , -- createDate - datetime\n"
                        + "          ? , -- codeVerification - varchar\n"
                        + "          'new' , -- sttUsId - varchar(10)\n"
                        + "          2  -- roleId - int\n"
                        + "        )";
                preStm = conn.prepareStatement(sql);

                preStm.setString(1, email);
                preStm.setNString(2, fullname);
                preStm.setString(3, phone);
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                String hashPassword = String.format("%064x", new BigInteger(1, hash));
                preStm.setString(4, hashPassword);
                preStm.setString(5, codeVerification);

                AddressUserDAO addUsDao = new AddressUserDAO();
                result = preStm.executeUpdate() > 0;
                result = result && addUsDao.register(address, email);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public RegistrationDTO getRegistration(String email, String password) throws Exception {
        RegistrationDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT email, fullname, phone, codeVerification, createDate, sttUsId, roleId FROM dbo.Users WHERE email = ? AND password = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);

                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                String hashPassword = String.format("%064x", new BigInteger(1, hash));
                preStm.setString(2, hashPassword);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email");
                    String fullName = rs.getNString("fullName");
                    String phone = rs.getString("phone");
                    String codeVerification = rs.getString("codeVerification");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("sttUsId");
                    int role = rs.getInt("roleId");
                    dto = new RegistrationDTO(email, fullName, phone, codeVerification, createDate, status, role);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public String getVerificationCode(String email) throws Exception {
        String codeVerification = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT codeVerification FROM dbo.Users WHERE email = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    codeVerification = rs.getString("codeVerification");
                }
            }
        } finally {
            closeConnection();
        }
        return codeVerification;
    }

    public boolean setVerificationCode(String email, String codeVerification) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "UPDATE dbo.Users SET codeVerification = ? WHERE email = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, codeVerification);
                preStm.setString(2, email);

                result = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean setSttUser(String email) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "UPDATE dbo.Users SET sttUsId = 'act' WHERE email = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);

                result = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
