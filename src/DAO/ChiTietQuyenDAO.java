/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietQuyenDTO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hi
 */
public class ChiTietQuyenDAO implements DAOInterface<ChiTietQuyenDTO>{
    
     public static ChiTietQuyenDAO getInstance(){
        return new ChiTietQuyenDAO();
    }

    @Override
    public int insert(ChiTietQuyenDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            // Nếu không có trong database, tiến hành thêm mới
            String sql = "INSERT INTO ctquyen (manhomquyen,machucnang,hanhdong) VALUES (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getManhomquyen());
            pst.setInt(2, t.getMachucnang());
            pst.setString(3, t.getHanhdong());
            
            
            result = pst.executeUpdate(); // Thực thi câu lệnh INSERT

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ChiTietQuyenDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result; 
    }
    
    public List<Integer> getDanhSachChucNang(int maNhomQuyen) {
        List<Integer> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT machucnang FROM ctquyen WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maNhomQuyen);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("machucnang"));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
}
    
public int deleteByMaNhom(int maNhomQuyen) {
    String sql = "DELETE FROM ctquyen WHERE manhomquyen = ?";
    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, maNhomQuyen);
        return pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}






    @Override
    public int update(ChiTietQuyenDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChiTietQuyenDTO> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietQuyenDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
