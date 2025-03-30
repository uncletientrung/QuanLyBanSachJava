/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author Hi
 */
public class TheLoaiDAO implements DAOInterface<TheLoaiDTO>{
    public static TheLoaiDAO getInstance(){
        return new TheLoaiDAO();
    }

    @Override
    public int insert(TheLoaiDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(TheLoaiDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<TheLoaiDTO> selectAll() {
    ArrayList<TheLoaiDTO> theLoai = new ArrayList<TheLoaiDTO>();
    
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM theloai"; 
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int maTl = rs.getInt("matheloai"); 
            String tenTl = rs.getString("tentheloai"); 
            // Tạo đối tượng
            TheLoaiDTO doi_tuong_tl = new TheLoaiDTO(maTl, tenTl);
            theLoai.add(doi_tuong_tl);
        }
        
    } catch (SQLException e) {
        Logger.getLogger(TheLoaiDAO.class.getName()).log(Level.SEVERE, null, e); 
    }
    return theLoai;
}

    @Override
    public TheLoaiDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
