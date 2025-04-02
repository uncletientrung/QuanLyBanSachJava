package DAO;

import DTO.TheLoaiDTO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TheLoaiDAO implements DAOInterface<TheLoaiDTO> {
    
    public static TheLoaiDAO getInstance() {
        return new TheLoaiDAO();
    }

    @Override
    public int insert(TheLoaiDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO theloai(matheloai, tentheloai) VALUES(?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMatheloai());
            pst.setString(2, t.getTentheloai());
            
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(TheLoaiDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE theloai SET tentheloai = ? WHERE matheloai = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTentheloai());
            pst.setInt(2, t.getMatheloai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(TheLoaiDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }
    
    public int delete(int maTheLoai) {
        String query = "DELETE FROM theloai WHERE matheloai = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, maTheLoai);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(String t) {
        try {
            int maTheLoai = Integer.parseInt(t);
            return delete(maTheLoai);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi chuyển đổi ID từ String sang int");
            return 0;
        }
    }

    @Override
    public ArrayList<TheLoaiDTO> selectAll() {
        ArrayList<TheLoaiDTO> theLoaiList = new ArrayList<>();
        
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM theloai";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maTl = rs.getInt("matheloai");
                String tenTl = rs.getString("tentheloai");
                
                TheLoaiDTO doi_tuong_tl = new TheLoaiDTO(maTl, tenTl);
                theLoaiList.add(doi_tuong_tl);
            }
        } catch (SQLException e) {
            Logger.getLogger(TheLoaiDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return theLoaiList;
    }

    @Override
    public TheLoaiDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}