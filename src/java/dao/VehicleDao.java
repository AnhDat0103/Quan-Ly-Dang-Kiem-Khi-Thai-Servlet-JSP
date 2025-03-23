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
import java.util.ArrayList;
import model.User;
import model.enums.vehicleEnums.vehicleEnums;

/**
 *
 * @author Lenovo
 */
public class VehicleDao implements Dao<Vehicles> {

    Connection connect = DBContext.getInstance().getConnection();
    UserDao ud = new UserDao();
//    InspectionRecordDao id = new InspectionRecordDao();

    @Override
    public int save(Vehicles t) {
        String sql = "INSERT INTO Vehicles (OwnerID, PlateNumber, Brand, Model, ManufactureYear, EngineNumber, Status)"
                + " VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, t.getOwner().getUserId());
            st.setString(2, t.getPlateNumber());
            st.setString(3, t.getBrand());
            st.setString(4, t.getModel());
            st.setInt(5, t.getManufactureYear());
            st.setString(6, t.getEngineNumber());
            st.setString(7, t.getStatus().toString());
            int result = st.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

    public boolean deleteByPlateNumber(String plateNumber) {
        String sql = "DELETE FROM Vehicles WHERE PlateNumber = ?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, plateNumber);
            int aftectedRows = st.executeUpdate();
            return aftectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String selectByPlateNumber(String plateNumber) {
        String sql = "SELECT vehicleID, ownerID, plateNumber, brand, model, manufactureYear, engineNumber "
                + "FROM Vehicles WHERE PlateNumber = ?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, plateNumber);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return "VehicleID: " + rs.getInt("vehicleID")
                        + ", OwnerID: " + rs.getInt("ownerID")
                        + ", PlateNumber: " + rs.getString("plateNumber")
                        + ", Brand: " + rs.getString("brand")
                        + ", Model: " + rs.getString("model")
                        + ", ManufactureYear: " + rs.getInt("manufactureYear")
                        + ", EngineNumber: " + rs.getString("engineNumber")
                        + ", Status: " + vehicleEnums.valueOf(rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean kiemtraphuongtien(String plateNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Vehicles WHERE PlateNumber = ?";
        try (PreparedStatement st = connect.prepareStatement(sql)) {
            st.setString(1, plateNumber);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
    
    public boolean kiemtrakhungphuongtien(String engineNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Vehicles WHERE EngineNumber = ?";
        try (PreparedStatement st = connect.prepareStatement(sql)) {
            st.setString(1, engineNumber);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public List<String> getPlateNumberByOwnerID(int ownerID) {
        List<String> plateNumbers = new ArrayList<>();
        String sql = "SELECT PlateNumber FROM Vehicles WHERE OwnerID = ?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, ownerID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                plateNumbers.add(rs.getString("PlateNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plateNumbers;
    }

    public int getVehicleIDByPlateNumber(String plateNumber) {
        int vehicleID = -1;
        String sql = "SELECT VehicleID FROM Vehicles WHERE PlateNumber = ?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, plateNumber);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                vehicleID = rs.getInt("VehicleID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleID;
    }

    public User getOwnerByPlateNumber(String plateNumber) {
        String sql = "select * from Vehicles where PlateNumber = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, plateNumber);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                int ownerId = rs.getInt("OwnerID");
                User owner = ud.findUserById(ownerId);
                return owner;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vehicles getVehiclesById(int id) {
        String sql = "select * from Vehicles where VehicleID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                Vehicles v = new Vehicles(rs.getInt("VehicleID"),
                        ud.findUserById(rs.getInt("OwnerID")),
                        rs.getString("PlateNumber"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getInt("ManufactureYear"),
                        rs.getString("EngineNumber"),
                        vehicleEnums.valueOf(rs.getString("Status"))
                );
                return v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vehicles> getAllVehiclesByUserID(int ownerID) {
        List<Vehicles> vehicleList = new ArrayList<>();
        String sql = "SELECT PlateNumber, Brand, Model, Status FROM Vehicles WHERE OwnerID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, ownerID);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Vehicles vehilce = new Vehicles(
                        rs.getString("PlateNumber"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        vehicleEnums.valueOf(rs.getString("Status"))
                );
                vehicleList.add(vehilce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    public boolean checkVehicleExistByOwnerId(int ownerID) {
        boolean exist = false;
        String sql = "SELECT COUNT(*) FROM Vehicles WHERE OwnerID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, ownerID);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                exist = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public Vehicles checkPlateNumAndOwner(String plateNum, String fullName, String tel) {
        User u = ud.getUserbyTelAndName(tel, fullName);
        if (u == null) {
            return null;
        }
        String sql = "select * from Vehicles where PlateNumber = ? and OwnerID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, plateNum);
            pt.setInt(2, u.getUserId());
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                Vehicles v = new Vehicles(rs.getInt("VehicleID"),
                        ud.findUserById(rs.getInt("OwnerID")),
                        rs.getString("PlateNumber"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getInt("ManufactureYear"),
                        rs.getString("EngineNumber"),
                        vehicleEnums.valueOf(rs.getString("Status")));
                return v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Vehicles> getFullVehiclesByUserID(int ownerID) {
        List<Vehicles> vehicleList = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE OwnerID = ?";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setInt(1, ownerID);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Vehicles vehilce = new Vehicles(
                        rs.getString("plateNumber"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("manufactureYear"),
                        rs.getString("engineNumber"),
                        vehicleEnums.valueOf(rs.getString("Status"))
                );
                vehicleList.add(vehilce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    public boolean banVehicleByPlateNumber(String plateNumber) {
        String sql = "UPDATE Vehicles SET Status = 'Ban' WHERE PlateNumber = ?";

        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, plateNumber);
            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countVehicles() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS VehicleCount FROM Vehicles";

        try (PreparedStatement ps = connect.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("VehicleCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void update(String result, String plateNumber) {
        String sql = "Update Vehicles SET Status = ? WHERE PlateNumber = ? ";
        try {
            PreparedStatement pt = connect.prepareStatement(sql);
            pt.setString(1, result);
            pt.setString(2, plateNumber);
            pt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getStatusVehicles(int vehicleID) {
        String sql = "select Status from Vehicles where VehicleID = ?";

        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, vehicleID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Status"); // Trả về kết quả mới nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Nếu không có dữ liệu thì trả về null
    }

    public boolean updateVehicle(Vehicles vehicles) {
        String sql = "UPDATE Vehicles SET Brand = ?, Model = ?, ManufactureYear = ?, EngineNumber = ?, Status = 'Fail' WHERE PlateNumber = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, vehicles.getBrand());
            ps.setString(2, vehicles.getModel());
            ps.setInt(3, vehicles.getManufactureYear());
            ps.setString(4, vehicles.getEngineNumber());
            ps.setString(5, vehicles.getPlateNumber());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Vehicles findByPlateNumber(String plateNumber) {
        String sql = "SELECT PlateNumber, Brand, Model, ManufactureYear, EngineNumber "
                + "FROM Vehicles WHERE PlateNumber = ?";
        try{
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, plateNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Vehicles vehicle = new Vehicles();
                vehicle.setPlateNumber(rs.getString("PlateNumber"));
                vehicle.setBrand(rs.getString("Brand"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setManufactureYear(rs.getInt("ManufactureYear"));
                vehicle.setEngineNumber(rs.getString("EngineNumber"));
                return vehicle;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
