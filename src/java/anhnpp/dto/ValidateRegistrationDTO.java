package anhnpp.dto;

/**
 *
 * @author nguye
 */
public class ValidateRegistrationDTO {

    private String errorEmail, errorFullname, errorPhone, errorAddress, errorPassword, errorConfirm;

    public ValidateRegistrationDTO(String errorEmail, String errorPassword) {
        this.errorEmail = errorEmail;
        this.errorPassword = errorPassword;
    }

    public ValidateRegistrationDTO(String errorEmail, String errorFullname, String errorPhone, String errorAddress, String errorPassword, String errorConfirm) {
        this.errorEmail = errorEmail;
        this.errorFullname = errorFullname;
        this.errorPhone = errorPhone;
        this.errorAddress = errorAddress;
        this.errorPassword = errorPassword;
        this.errorConfirm = errorConfirm;
    }

    public String getErrorEmail() {
        return errorEmail;
    }

    public void setErrorEmail(String errorEmail) {
        this.errorEmail = errorEmail;
    }

    public String getErrorFullname() {
        return errorFullname;
    }

    public void setErrorFullname(String errorFullname) {
        this.errorFullname = errorFullname;
    }

    public String getErrorPhone() {
        return errorPhone;
    }

    public void setErrorPhone(String errorPhone) {
        this.errorPhone = errorPhone;
    }

    public String getErrorAddress() {
        return errorAddress;
    }

    public void setErrorAddress(String errorAddress) {
        this.errorAddress = errorAddress;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword = errorPassword;
    }

    public String getErrorConfirm() {
        return errorConfirm;
    }

    public void setErrorConfirm(String errorConfirm) {
        this.errorConfirm = errorConfirm;
    }

}
