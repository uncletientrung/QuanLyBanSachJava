/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhieuNhapDTO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Minnie
 */
public class PhieuNhapDAO implements DAOInterface<PhieuNhapDTO>{
    public static PhieuNhapDAO getInstance(){
        return new PhieuNhapDAO();
    }
    
    @Override
    public int insert(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Insert into phieunhap (maphieunhap,manv,mancc,thoigiantao,tongtien,trangthai) values"
                    + " ( ? , ? , ? , ? , ? , ?) ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieu());
            pst.setInt(2, t.getManv());
            pst.setInt(3, t.getMancc());
            pst.setTimestamp(4, t.getThoigiantao());
            pst.setInt(5, (int) t.getTongTien());
            pst.setInt(6, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public int update(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update phieunhap Set maphieunhap = ? , manv = ? , mancc = ? , thoigiantao = ? , tongtien = ? , trangthai = ? Where maphieunhap = ? "; 
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieu());
            pst.setInt(2, t.getManv());
            pst.setInt(3, t.getMancc());
            pst.setTimestamp(4, t.getThoigiantao());
            pst.setInt(5, (int) t.getTongTien());
            pst.setInt(6, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result=0;
         try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql=" Delete From phieunhap Where maphieunhap= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }

    public int cancel(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql=" Delete From phieunhap Where maphieunhap= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql="Select * From phieunhap";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                int mapn = rs.getInt("maphieunhap");
                int manv = rs.getInt("manv");
                int mancc = rs.getInt("mancc");
                Timestamp thoigiantao = rs.getTimestamp("thoigiantao");
                int tongtien = rs.getInt("tongtien");
                int trangthai = rs.getInt("trangthai");
                PhieuNhapDTO pnnew= new PhieuNhapDTO(mapn, manv, mancc, thoigiantao, tongtien, trangthai);
                result.add(pnnew);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public PhieuNhapDTO selectById(String t) {
        PhieuNhapDTO result = new PhieuNhapDTO();
        try {
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql = "Select * From phieunhap Where maphieunhap = ?";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                int mapn = rs.getInt("maphieunhap");
                int manv = rs.getInt("manv");
                int mancc = rs.getInt("mancc");
                Timestamp thoigiantao = rs.getTimestamp("thoigiantao");
                int tongtien = rs.getInt("tongtien");
                int trangthai = rs.getInt("trangthai");
                result = new PhieuNhapDTO(mapn, manv, mancc, thoigiantao, tongtien, trangthai);
            }
        } catch (Exception e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlibansach' AND TABLE_NAME   = 'phieunhap'";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            if(!rs.isBeforeFirst()){
                System.err.println("No data");
            } else {
                while (rs.next()) {
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
}
