/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import model.Vehicles;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class VehicleDao implements Dao<Vehicles> {

    @Override
    public int save(Vehicles t) {
        int ketQua = 0;

        // Chuẩn bị câu lệnh SQL
        String sql = "INSERT INTO Vehicles (OwnerID, PlateNumber, Brand, Model, ManufactureYear, EngineNumber)" +
        " VALUES (?,?,?,?,?,?)";

        Connection connection = null;
        try {
            connection = DBContext.getInstance().getConnection();
            if (connection == null || connection.isClosed()) {
                throw new SQLException("Không thể kết nối đến cơ sở dữ liệu.");
            }
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setInt(1, t.getOwnerID());
                st.setString(2, t.getPlateNumber());
                st.setString(3, t.getBrand());
                st.setString(4, t.getModel());
                st.setInt(5, t.getManufactureYear());
                st.setString(6, t.getEngineNumber());
                 
                ketQua = st.executeUpdate();
                connection.commit();
                System.out.println("Thêm phương tiện thành công, số dòng bị ảnh hưởng: " + ketQua);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm dữ liệu vào Vehicles: " + e.getMessage());
        }
        return ketQua;
    }

    @Override
    public List<Vehicles> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Vehicles t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean deleteByPlateNumber(String plateNumber){
        String xe = "";
        String sql = "DELETE FROM Vehicles WHERE PlateNumber = ?";
        
        try(Connection connection = DBContext.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(sql)){
            
            st.setString(1, plateNumber);
            int aftectedRows = st.executeUpdate();
            
            return aftectedRows > 0;
        }catch(SQLException e){
            System.err.println("Xoá phương tiện không thành công: " + e.getMessage());
        }
        return false;
    }
    
    public String selectByPlateNumber(String plateNumber) {
        String xe = null;

        // Chuẩn bị câu lệnh SQL
        String sql = "SELECT * FROM Vehicles WHERE PlateNumber = ?";

        try (
                // Tự động quản lý tài nguyên với try-with-resources
                Connection connection = DBContext.getInstance().getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            // Gán giá trị tham số
            st.setString(1, plateNumber);

            // Thực thi câu lệnh SQL
            try (ResultSet rs = st.executeQuery()) {
                // Kiểm tra nếu có kết quả trả về
                if (rs.next()) {
                    // Lấy dữ liệu từ ResultSet và khởi tạo đối tượng Vehicles
                    xe = "VehicleID: " + rs.getInt("vehicleID")
                            + ", OwnerID: " + rs.getInt("ownerID")
                            + ", PlateNumber: " + rs.getString("plateNumber")
                            + ", Brand: " + rs.getString("brand")
                            + ", Model: " + rs.getString("model")
                            + ", ManufactureYear: " + rs.getInt("manufactureYear")
                            + ", EngineNumber: " + rs.getString("engineNumber");
                }
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ và in thông báo lỗi
            System.err.println("Lỗi khi truy vấn Vehicles: " + e.getMessage());
        }

        return xe;
    }
    
    public boolean kiemtraphuongtien(String plateNumber) throws SQLException {
        String sql = "SELECT * FROM Vehicles WHERE PlateNumber = ?";
        try (
                Connection db = DBContext.getInstance().getConnection(); PreparedStatement st = db.prepareStatement(sql);) {
            st.setString(1, plateNumber);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}