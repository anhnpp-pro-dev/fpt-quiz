package anhnpp.dao;

import anhnpp.db.MyConnection;
import anhnpp.dto.CarDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class CarDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public CarDAO() {
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

    public List<CarDTO> getCars(String cate, String name, String rentalDate, String returnDate, String amount) throws Exception {
        List<CarDTO> listCar = new ArrayList<>();
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT carId, carName, carColor, carYear, carPrice, carImage, carQuantity FROM dbo.Cars WHERE cateId = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, cate);
                rs = preStm.executeQuery();
                while (rs.next()) {
                    String carId = rs.getString("carId");
                    String carName = rs.getString("carName");
                    String carColor = rs.getString("carColor");
                    int carYear = rs.getInt("carYear");
                    float carPrice = rs.getFloat("carPrice") / 200;
                    String carImage = rs.getString("carImage");
                    int carQuantity = rs.getInt("carQuantity");
                    CarDTO car = new CarDTO(carId, carName, carColor, carYear, carPrice, carImage, carQuantity);
                    listCar.add(car);
                }
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }
}
