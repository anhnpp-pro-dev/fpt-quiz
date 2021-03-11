package anhnpp.dto;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class CarDTO implements Serializable {

    private String carId, carName, carColor;
    private int carYear;
    private float carPrice;
    private String carImage;
    private int carQuantity;

    public CarDTO(String carId, String carName, String carColor, int carYear, float carPrice, String carImage, int carQuantity) {
        this.carId = carId;
        this.carName = carName;
        this.carColor = carColor;
        this.carYear = carYear;
        this.carPrice = carPrice;
        this.carImage = carImage;
        this.carQuantity = carQuantity;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public float getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(float carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public int getCarQuantity() {
        return carQuantity;
    }

    public void setCarQuantity(int carQuantity) {
        this.carQuantity = carQuantity;
    }

    

}
