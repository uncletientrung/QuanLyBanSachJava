/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaXuatBanDTO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hi
 */
public class NhaXuatBanDAO implements DAOInterface<NhaXuatBanDTO> {
    
    public static NhaXuatBanDAO getInstance() {
        return new NhaXuatBanDAO();
    }

    @Override
    public int insert(NhaXuatBanDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            // Kiểm tra xem nhà xuất bản đã tồn tại chưa
            String checkSql = "SELECT COUNT(*) FROM nhaxuatban WHERE tennxb = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, t.getTennxb());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            
            if (count > 0) {
                return 0; // Trả về 0 nếu nhà xuất bản đã tồn tại
            }

            // Nếu không có trong database, tiến hành thêm mới
            String sql = "INSERT INTO nhaxuatban (tennxb, diachinxb, sdt, email, trangthai) VALUES (?,?,?,?,1)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennxb());
            pst.setString(2, t.getDiachinxb());
            pst.setString(3, t.getSdt());
            pst.setString(4, t.getEmail());
            
            result = pst.executeUpdate(); // Thực thi câu lệnh INSERT

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhaXuatBanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result; // Trả về số dòng bị ảnh hưởng (1 nếu thành công)
    }

    @Override
    public int update(NhaXuatBanDTO t) {
        int result = 0;
        
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE nhaxuatban SET tennxb=?, diachinxb=?, sdt=?, email=? WHERE manxb=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennxb());
            pst.setString(2, t.getDiachinxb());
            pst.setString(3, t.getSdt());
            pst.setString(4, t.getEmail());
            pst.setInt(5, t.getManxb());
            
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
            int maNhaXuatBan = Integer.parseInt(t);  // Chuyển String → int
            return delete(maNhaXuatBan);  // Gọi hàm delete(int)
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public int delete(int maNhaXuatBan) {
        String query = "DELETE FROM nhaxuatban WHERE manxb = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, maNhaXuatBan);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<NhaXuatBanDTO> selectAll() {
        ArrayList<NhaXuatBanDTO> nhaXuatBan = new ArrayList<NhaXuatBanDTO>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhaxuatban";
            PreparedStatement pst = con.prepareStatement(sql);
            
            // Thiết lập result set
            ResultSet rs = pst.executeQuery(sql);
            // Xử lý rs
            while (rs.next()) {
                int maNhaXuatBan = rs.getInt("manxb");
                String tenNhaXuatBan = rs.getString("tennxb");
                String diaChiNhaXuatBan = rs.getString("diachinxb");
                String sdtNXB = rs.getString("sdt");
                String email = rs.getString("email");
                // Tạo 1 đối tượng nhà xuất bản để nhận dữ liệu đã đọc từ database
                NhaXuatBanDTO doituong_nhaxuatban = new NhaXuatBanDTO(maNhaXuatBan, tenNhaXuatBan, diaChiNhaXuatBan, sdtNXB, email);
                // Thêm đối tượng vào arraylist
                nhaXuatBan.add(doituong_nhaxuatban);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(NhaXuatBanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return nhaXuatBan;
    }

    @Override
    public NhaXuatBanDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}