/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhieuXuatDTO;
import connectDB.JDBCUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author DELL
 */
public class PhieuXuatDAO implements DAOInterface<PhieuXuatDTO>{
    public static PhieuXuatDAO getInstance(){
        return new PhieuXuatDAO();
    }

    @Override
    public int insert(PhieuXuatDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(PhieuXuatDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PhieuXuatDTO> selectAll() {
        ArrayList<PhieuXuatDTO> result=new ArrayList<>();
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql="Select * From phieuxuat";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                int maphieuxuat=rs.getInt("maphieuxuat");
                int manv=rs.getInt("manv");
                int makh=rs.getInt("makh");
                Timestamp thoigiantao=rs.getTimestamp("thoigiantao");
                int tongtien=rs.getInt("tongtien");
                int trangthai=rs.getInt("trangthai");
                PhieuXuatDTO pxnew= new PhieuXuatDTO(maphieuxuat, manv, makh, thoigiantao, tongtien, trangthai);
                result.add(pxnew);
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return result;
     }

    @Override
    public PhieuXuatDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
    
}
