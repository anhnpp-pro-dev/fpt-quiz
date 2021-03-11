package anhnpp.dto;

/**
 *
 * @author nguye
 */
public class ValidateSearchCarDTO {

    private String errorName, errorSearch, errorRentalDate, errorReturnDate, errorAmount;

    public ValidateSearchCarDTO(String errorName, String errorSearch, String errorRentalDate, String errorReturnDate, String errorAmount) {
        this.errorName = errorName;
        this.errorSearch = errorSearch;
        this.errorRentalDate = errorRentalDate;
        this.errorReturnDate = errorReturnDate;
        this.errorAmount = errorAmount;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorSearch() {
        return errorSearch;
    }

    public void setErrorSearch(String errorSearch) {
        this.errorSearch = errorSearch;
    }

    public String getErrorRentalDate() {
        return errorRentalDate;
    }

    public void setErrorRentalDate(String errorRentalDate) {
        this.errorRentalDate = errorRentalDate;
    }

    public String getErrorReturnDate() {
        return errorReturnDate;
    }

    public void setErrorReturnDate(String errorReturnDate) {
        this.errorReturnDate = errorReturnDate;
    }

    public String getErrorAmount() {
        return errorAmount;
    }

    public void setErrorAmount(String errorAmount) {
        this.errorAmount = errorAmount;
    }

}
