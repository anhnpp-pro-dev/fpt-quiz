package anhnpp.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author KING (Nguyễn Phan Phước Anh)
 */
public class RegistrationDTO implements Serializable {

    private String email, fullname, phone, codeVerification;
    private Date createDate;
    private String status;
    private int role;

    public RegistrationDTO(String email, String fullname, String phone, String codeVerification, Date createDate, String status, int role) {
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.codeVerification = codeVerification;
        this.createDate = createDate;
        this.status = status;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCodeVerification() {
        return codeVerification;
    }

    public void setCodeVerification(String codeVerification) {
        this.codeVerification = codeVerification;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
