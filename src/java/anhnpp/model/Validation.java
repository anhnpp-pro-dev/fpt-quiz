package anhnpp.model;

import anhnpp.dto.ValidateRegistrationDTO;
import anhnpp.dto.ValidateSearchCarDTO;

/**
 *
 * @author nguye
 */
public class Validation {

    private static boolean isEmptyString(String s) {
        if (s == null || s.equals("") || s.length() == 0 || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static ValidateRegistrationDTO checkLogin(String email, String password) {

        boolean isInvalid = false;
        String errorEmail = null;
        String errorPassword = null;

        if (isEmptyString(email)) {
            errorEmail = "Email is blank";
            isInvalid = true;
        } else if (email.length() < 9) {
            errorEmail = "Email must not be less than 9 characters";
            isInvalid = true;
        } else if (!email.matches("^[a-z][a-z0-9_\\.]{3,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")) {
            errorEmail = "Email wrong form (abcd@gmail.com.vn)";
            isInvalid = true;
        }
        if (isEmptyString(password)) {
            errorPassword = "Password is blank";
            isInvalid = true;
        } else if (password.length() < 6) {
            errorPassword = "Password must not be less than 6 characters";
            isInvalid = true;
        } else if (password.length() > 18) {
            errorPassword = "Password must not be more than 18 characters";
            isInvalid = true;
        }

        if (isInvalid) {
            ValidateRegistrationDTO validate = new ValidateRegistrationDTO(errorEmail, errorPassword);
            return validate;
        } else {
            return null;
        }
    }

    public static ValidateRegistrationDTO checkRegister(String email, String fullname, String phone, String address, String password, String confirm) {

        boolean isInvalid = false;
        String errorEmail = null;
        String errorFullName = null;
        String errorPhone = null;
        String errorAddress = null;
        String errorPassword = null;
        String errorConfirm = null;

        if (isEmptyString(email)) {
            errorEmail = "Email is blank";
            isInvalid = true;
        } else if (email.length() < 9) {
            errorEmail = "Email must not be less than 9 characters";
            isInvalid = true;
        } else if (!email.matches("^[a-z][a-z0-9_\\.]{3,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")) {
            errorEmail = "Email wrong form (abcd@gmail.com.vn)";
            isInvalid = true;
        }
        if (Validation.isEmptyString(fullname)) {
            errorFullName = "Full name is blank";
            isInvalid = true;
        }
        if (isEmptyString(phone)) {
            errorPhone = "Phone is blank";
            isInvalid = true;
        } else if (phone.length() < 10) {
            errorPhone = "Phone must not be less than 10 characters";
            isInvalid = true;
        } else if (!phone.matches("^[0-9]{1,}$")) {
            errorPhone = "Phone must be number";
            isInvalid = true;
        }
        if (isEmptyString(address)) {
            errorAddress = "Address is blank";
            isInvalid = true;
        }
        if (isEmptyString(password)) {
            errorPassword = "Password is blank";
            isInvalid = true;
        } else if (password.length() < 6) {
            errorPassword = "Password must not be less than 6 characters";
            isInvalid = true;
        } else if (password.length() > 18) {
            errorPassword = "Password must not be more than 18 characters";
            isInvalid = true;
        }
        if (Validation.isEmptyString(confirm)) {
            errorConfirm = "Confirm is blank";
            isInvalid = true;
        } else if (confirm.length() < 6) {
            errorConfirm = "Confirm must not be less than 6 characters";
            isInvalid = true;
        } else if (confirm.length() > 18) {
            errorConfirm = "Confirm must not be more than 18 characters";
            isInvalid = true;
        } else if (!password.equals(confirm)) {
            errorConfirm = "Confirm not match password";
            isInvalid = true;
        }
        if (isInvalid) {
            ValidateRegistrationDTO validate = new ValidateRegistrationDTO(errorEmail, errorFullName, errorPhone, errorAddress, errorPassword, errorConfirm);
            return validate;
        } else {
            return null;
        }
    }

    public static ValidateSearchCarDTO checkSearchCar(String name, String search, String rentalDate, String returnDate, String amount) {

        boolean isInvalid = false;
        String errorName = null;
        String errorSearch = null;
        String errorRentalDate = null;
        String errorReturnDate = null;
        String errorAmount = null;

        if (search == null) {
            errorSearch = "Choose a search option";
            isInvalid = true;
        } else if (search.equals("name")) {
            if (Validation.isEmptyString(name)) {
                errorName = "Name is blank";
                isInvalid = true;
            }
        }
        if (Validation.isEmptyString(rentalDate)) {
            errorRentalDate = "Rental date is blank";
            isInvalid = true;
        }
        if (Validation.isEmptyString(returnDate)) {
            errorReturnDate = "Renturn date is blank";
            isInvalid = true;
        }
        if (Validation.isEmptyString(amount)) {
            errorAmount = "Amount date is blank";
            isInvalid = true;
        }

        if (isInvalid) {
            ValidateSearchCarDTO validate = new ValidateSearchCarDTO(errorName, errorSearch, errorRentalDate, errorReturnDate, errorAmount);
            return validate;
        } else {
            return null;
        }
    }
}
