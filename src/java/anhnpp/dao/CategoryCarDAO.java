package anhnpp.dao;

import anhnpp.db.MyConnection;
import anhnpp.dto.CategoryCarDTO;
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
public class CategoryCarDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public CategoryCarDAO() {
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

    public List<CategoryCarDTO> getAllCategoryCar() throws Exception {
        List<CategoryCarDTO> listCate = new ArrayList<>();
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT cateId, cateName FROM dbo.CategoryCar";
                preStm = conn.prepareStatement(sql);
                rs = preStm.executeQuery();
                while (rs.next()) {
                    int cateId = rs.getInt("cateId");
                    String cateName = rs.getString("cateName");
                    CategoryCarDTO cateCar = new CategoryCarDTO(cateId, cateName);
                    listCate.add(cateCar);
                }
            }
        } finally {
            closeConnection();
        }
        return listCate;
    }

    public CategoryCarDTO getCategoryCarByCateId(String cateId) throws Exception {
        CategoryCarDTO cate = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT cateId, cateName FROM dbo.CategoryCar WHERE cateId = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, cateId);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    int cateIdi = rs.getInt("cateId");
                    String cateName = rs.getString("cateName");
                    cate = new CategoryCarDTO(cateIdi, cateName);
                }
            }
        } finally {
            closeConnection();
        }
        return cate;
    }
}
