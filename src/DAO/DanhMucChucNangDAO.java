/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DTO.DanhMucChucNangDTO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hi
 */
public class DanhMucChucNangDAO implements DAOInterface<DanhMucChucNangDTO>{
    public static DanhMucChucNangDAO getInstance(){
        return new DanhMucChucNangDAO();
    }

    @Override
    public int insert(DanhMucChucNangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(DanhMucChucNangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DanhMucChucNangDTO> selectAll() {
         ArrayList<DanhMucChucNangDTO> chucNang= new ArrayList<DanhMucChucNangDTO>();
        try{
            Connection con = JDBCUtil.getConnection();
            String sql ="SELECT * FROM danhmucchucnang";
            PreparedStatement pst = con.prepareStatement(sql);
            
            
            //thiet lap result set
            ResultSet rs= pst.executeQuery(sql);
            //xu ly rs
            while(rs.next()){
                int maChucNang=rs.getInt("machucnang");
                String tenChucNang= rs.getString("tenchucnang");
                //tao 1 doi tuong nhom quyen de nhan du lieu da doc tu database
                DanhMucChucNangDTO doituong_chucnang= new DanhMucChucNangDTO(maChucNang, tenChucNang);
                //them doi tuong vo arraylist
                chucNang.add(doituong_chucnang);
                
            }
            JDBCUtil.closeConnection(con);
               
               
        }catch(SQLException e){
            Logger.getLogger(DanhMucChucNangDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return chucNang;
    }
    
    
    public String getTenChucNangByMa(int maChucNang) {
        String query = "SELECT tenchucnang FROM danhmucchucnang WHERE machucnang = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, maChucNang);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("tenchucnang");
            }

        } catch (SQLException e) {
            Logger.getLogger(DanhMucChucNangDAO.class.getName()).log(Level.SEVERE,null,e);
        }

        return null; // Không tìm thấy
    }

    @Override
    public DanhMucChucNangDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}
