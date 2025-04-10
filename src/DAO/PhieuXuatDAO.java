/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhieuXuatDTO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class PhieuXuatDAO implements DAOInterface<PhieuXuatDTO>{
    public static PhieuXuatDAO getInstance(){
        return new PhieuXuatDAO();
    }

    @Override
    public int insert(PhieuXuatDTO px) {
        int result=0;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql="Insert into phieuxuat ( maphieuxuat,manv,makh, thoigiantao,tongtien, trangthai) values "
                                                        + " ( ? , ?, ? , ? , ? , ? )   ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, px.getMaphieu());
            pst.setInt(2, px.getManv());
            pst.setInt(3, px.getMakh());
            pst.setTimestamp(4, px.getThoigiantao());
            pst.setInt(5, (int) px.getTongTien());
            pst.setInt(6, px.getTrangthai());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;    
    }


    @Override
    public int update(PhieuXuatDTO px) {
        int result=0;
         try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql="Update phieuxuat Set manv = ? , makh= ? , thoigiantao = ? , tongtien = ? , trangthai = ?  Where maphieuxuat = ?   ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, px.getManv());
            pst.setInt(2, px.getMakh());
            pst.setTimestamp(3, px.getThoigiantao());
            pst.setInt(4, (int) px.getTongTien());
            pst.setInt(5, px.getTrangthai());
            pst.setInt(6, px.getMaphieu());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;    
    }

    @Override
    public int delete(String mapx) {
        int result=0;
         try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql="Update phieuxuat Set trangthai = 0  Where maphieuxuat = ?   ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(mapx));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;  
    }
    public int cancel(String mapx){
        int result=0;
         try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql="Delete From phieuxuat  Where maphieuxuat = ?   ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(mapx));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;  
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
        PhieuXuatDTO result=new PhieuXuatDTO();
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql="Select * From phieuxuat Where maphieuxuat = ?";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                int maphieuxuat=rs.getInt("maphieuxuat");
                int manv=rs.getInt("manv");
                int makh=rs.getInt("makh");
                Timestamp thoigiantao=rs.getTimestamp("thoigiantao");
                int tongtien=rs.getInt("tongtien");
                int trangthai=rs.getInt("trangthai");
                result= new PhieuXuatDTO(maphieuxuat, manv, makh, thoigiantao, tongtien, trangthai);
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlibansach' AND TABLE_NAME   = 'phieuxuat'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
   
    
    
}
