/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhuyenMaiDTO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hi
 */
public class KhuyenMaiDAO implements DAOInterface<KhuyenMaiDTO>{
    
    public static KhuyenMaiDAO getInstance(){
        return new KhuyenMaiDAO();
    }

    
    @Override
    public int insert(KhuyenMaiDTO t) {
        int result =0;
        try{
            
            Connection con = JDBCUtil.getConnection();
            
              // Kiểm tra xem nhóm quyền đã tồn tại chưa
            String checkSql = "SELECT COUNT(*) FROM khuyenmai WHERE tenkm = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, t.getTenChuongTrinh());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                return 0; // Trả về 0 nếu nhóm quyền đã tồn tại
            }
            String sql ="INSERT INTO khuyenmai (tenkm,ngaybatdau,ngayketthuc,dieukientoithieu,phantramgiam) values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenChuongTrinh());
            pst.setDate(2, new java.sql.Date(t.getNgayBatDau().getTime()));
            pst.setDate(3, new java.sql.Date(t.getNgayKetThuc().getTime()));
            pst.setDouble(4, t.getDieuKienToiThieu());
            pst.setDouble(5, t.getPhanTramGiam());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(SQLException e){
            Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

   @Override
    public int update(KhuyenMaiDTO t) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE khuyenmai SET tenkm = ?, ngaybatdau = ?, ngayketthuc = ?, dieukientoithieu = ?, phantramgiam = ? WHERE makm = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, t.getTenChuongTrinh());
            pst.setDate(2, new java.sql.Date(t.getNgayBatDau().getTime()));
            pst.setDate(3, new java.sql.Date(t.getNgayKetThuc().getTime()));
            pst.setDouble(4, t.getDieuKienToiThieu());
            pst.setDouble(5, t.getPhanTramGiam());
            pst.setInt(6, t.getMaKM());

            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public int delete(String t) {
        try {
            int maKhuyenMai = Integer.parseInt(t);  // Chuyển từ String sang int
            return delete(maKhuyenMai);             // Gọi hàm delete(int)
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int delete(int maKhuyenMai) {
        String query = "DELETE FROM khuyenmai WHERE makm = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, maKhuyenMai);
            return pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public ArrayList<KhuyenMaiDTO> selectAll() {
        ArrayList<KhuyenMaiDTO> danhSachKM = new ArrayList<>();

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khuyenmai";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maKM = rs.getInt("makm");
                String TenChuongTrinh = rs.getString("tenkm");
                Date NgayBatDau = rs.getDate("ngaybatdau");
                Date NgayKetThuc = rs.getDate("ngayketthuc");
                double dieuKienToiThieu = rs.getDouble("dieukientoithieu");
                double phanTramGiam = rs.getDouble("phantramgiam");

                KhuyenMaiDTO doituongKM = new KhuyenMaiDTO(maKM, TenChuongTrinh, NgayBatDau, NgayKetThuc, dieuKienToiThieu, phanTramGiam);
                danhSachKM.add(doituongKM);
            }
            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return danhSachKM;
    }


    @Override
    public KhuyenMaiDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
