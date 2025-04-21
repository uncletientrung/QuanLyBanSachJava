/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuXuatDTO;
import connectDB.JDBCUtil;
import java.lang.reflect.Array;
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
public class ChiTietPhieuXuatDAO implements ChiTietInterface<ChiTietPhieuXuatDTO>{
    public static ChiTietPhieuXuatDAO getInstance() {
        return new ChiTietPhieuXuatDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuXuatDTO> t) {
    int result = 0;
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "Insert into ctphieuxuat (maphieuxuat, masach, soluong, dongia) values ( ? , ? , ? , ? )";
        PreparedStatement pst = con.prepareStatement(sql);
        
        for (ChiTietPhieuXuatDTO chiTiet : t) {
            pst.setInt(1, chiTiet.getMaphieu());
            pst.setString(2, chiTiet.getMasach());
            pst.setInt(3, chiTiet.getSoluong());
            pst.setInt(4, chiTiet.getDongia());
            result += pst.executeUpdate();
        }
        JDBCUtil.closeConnection(con);
    } catch (Exception e) {
        Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
    }
    return result;
}

    @Override
    public int delete(String mapx) {
        int result=0;
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql="Delete  From ctphieuxuat Where maphieuxuat =  ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(mapx));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con); 
        }catch(Exception e){
           Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuXuatDTO> dsChiTiet, String maPhieuXuat) {
        int result= this.delete(maPhieuXuat);
        if(result !=0){
            result=this.insert(dsChiTiet);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuXuatDTO> selectAll(String mapx) {
        ArrayList<ChiTietPhieuXuatDTO> result= new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql="Select * From ctphieuxuat Where maphieuxuat = ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(mapx));
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                int maphieuxuat=rs.getInt("maphieuxuat");
                String masach=rs.getString("masach");
                int soluong=rs.getInt("soluong");
                int dongia=rs.getInt("dongia");
                ChiTietPhieuXuatDTO ctPhieuXuat=new ChiTietPhieuXuatDTO(maphieuxuat, masach, soluong, dongia);
                result.add(ctPhieuXuat);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

     public ArrayList<ChiTietPhieuXuatDTO> selectAll2() {
        ArrayList<ChiTietPhieuXuatDTO> result= new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql="Select * From ctphieuxuat";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                int maphieuxuat=rs.getInt("maphieuxuat");
                String masach=rs.getString("masach");
                int soluong=rs.getInt("soluong");
                int dongia=rs.getInt("dongia");
                ChiTietPhieuXuatDTO ctPhieuXuat=new ChiTietPhieuXuatDTO(maphieuxuat, masach, soluong, dongia);
                result.add(ctPhieuXuat);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    
}
