/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.TacGiaDTO;
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
public class TacGiaDAO implements DAOInterface<TacGiaDTO>{
    
    public static TacGiaDAO getInstance(){
        return new TacGiaDAO();
    }

    @Override
    public int insert(TacGiaDTO t) {
        int result=0;
        try{
            Connection con =JDBCUtil.getConnection();
            String sql="INSERT INTO tacgia(matacgia,tentacgia) values(?,?)";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setInt(1, t.getMatacgia());
            pst.setString(2, t.getHotentacgia());
            
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(TacGiaDTO t) {
        int result=0;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql="UPDATE tacgia "+
                    "set tentacgia=? WHERE matacgia =?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getHotentacgia());
            pst.setInt(2, t.getMatacgia());
            result= pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(SQLException e){
            Logger.getLogger(TacGiaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }
    
    public int delete(int maTacGia) {
    String query = "DELETE FROM tacgia WHERE matacgia = ?";
    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement pst = conn.prepareStatement(query)) {
         pst.setInt(1, maTacGia);
        return pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

    @Override
    public int delete(String t) {
        try {
        int maTacGia = Integer.parseInt(t);  // Chuyển String → int
        return delete(maTacGia);  // Gọi hàm delete(int)
    } catch (NumberFormatException e) {
        System.out.println("Lỗi chuyển đổi ID từ String sang int");
        return 0;
    }
    }

    @Override
    public ArrayList<TacGiaDTO> selectAll() {
        ArrayList<TacGiaDTO> tacGia = new ArrayList<TacGiaDTO>();
        
        try{
            Connection con = JDBCUtil.getConnection();
            String sql ="SELECT * from tacgia";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int maTg= rs.getInt("matacgia");
                String tenTg = rs.getString("tentacgia");
                //tao doi tuong
                TacGiaDTO doi_tuong_tg = new TacGiaDTO(maTg, tenTg);
                tacGia.add(doi_tuong_tg);
            }
            
        }catch(SQLException e){
            Logger.getLogger(TacGiaDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return tacGia;
    }

    @Override
    public TacGiaDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
