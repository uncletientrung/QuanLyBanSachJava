/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import connectDB.JDBCUtil;
import java.util.ArrayList;
import  DTO.SachDTO;
import connectDB.JDBCUtil;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DELL
 */

public class SachDAO implements  DAOInterface<SachDTO>{
    public static SachDAO getInstance(){
        return new SachDAO();
    }
    @Override 
    public int insert(SachDTO sach){
        int result=0;
        try{
             Connection con= (Connection) JDBCUtil.getConnection();
             String sql= "Insert into sach(masach,tensach,manxb,matacgia,matheloai,soluongton,namxuatban,dongia)  values ( ? , ? , ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
             PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
             pst.setString(1, sach.getMasach());
             pst.setString(2, sach.getTensach());
             pst.setInt(3, sach.getManxb());
             pst.setInt(4, sach.getMatacgia());
             pst.setInt(5, sach.getMatheloai());
             pst.setInt(6, sach.getSoluongton());
             pst.setString(7, sach.getNamxuatban());
             pst.setInt(8,sach.getDongia());
             result=pst.executeUpdate();
             JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, e);
        }        
        return result;
    }
    @Override
    public int update(SachDTO sach){
        int result=0;
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql="Update sach Set tensach= ? , manxb= ? ,  matacgia= ? , matheloai = ? , namxuatban= ? , dongia=? Where masach= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, sach.getTensach());
            pst.setInt(2, sach.getManxb());
            pst.setInt(3, sach.getMatacgia());
            pst.setInt(4, sach.getMatheloai());
            pst.setString(5, sach.getNamxuatban());
            pst.setInt(6, sach.getDongia());
            pst.setString(7, sach.getMasach());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }
    
    @Override
    public int delete(String sach){
        int result=0;
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql=" Delete From sach Where masach= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setString(1,sach);
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return  result;
    }
    @Override
    public ArrayList<SachDTO> selectAll(){
        ArrayList<SachDTO> result= new ArrayList<>();
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql=" SELECT *  FROM sach";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=(ResultSet) pst.executeQuery();
            while(rs.next()){
                String masach=rs.getString("masach");
                String tensach=rs.getString("tensach");
                int manxb=rs.getInt("manxb");
                int matacgia=rs.getInt("matacgia");
                int matheloai=rs.getInt("matheloai");
                int soluong=rs.getInt("soluongton");
                String namxuatban=rs.getString("namxuatban");
                int dongia=rs.getInt("dongia");
                SachDTO sach=new SachDTO(masach, tensach, manxb, matacgia, matheloai, soluong, namxuatban, dongia);
                result.add(sach);
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    @Override
    public SachDTO selectById(String maSach){
        SachDTO result=new SachDTO();
        try {
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql=" SELECT *  FROM sach  Where masach=  ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, maSach);
            ResultSet rs=(ResultSet) pst.executeQuery();
            while(rs.next()){
                String masach=rs.getString("masach");
                String tensach=rs.getString("tensach");
                int manxb=rs.getInt("manxb");
                int matacgia=rs.getInt("matacgia");
                int matheloai=rs.getInt("matheloai");
                int soluong=rs.getInt("soluongton");
                String namxuatban=rs.getString("namxuatban");
                int dongia=rs.getInt("dongia");
                result.setMasach(masach);
                result.setTensach(tensach);
                result.setManxb(manxb);
                result.setMatacgia(matacgia);
                result.setMatheloai(matheloai);
                result.setSoluongton(soluong);
                result.setNamxuatban(namxuatban);
                result.setDongia(dongia);
                return result;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    @Override
    public int getAutoIncrement(){
        int result=-1;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql= "Select Auto_Increment From Information_Schema.tables Where Table_Schema = 'quanlibansach' and Table_name = 'sach'";
             PreparedStatement pst= (PreparedStatement) con.prepareStatement(sql);
             ResultSet rs=(ResultSet) pst.executeQuery();
             while(rs.next()){
                 result=rs.getInt("Auto_Increment");
             }
             if(result==-1){
                 System.out.println("No data, try again!");
             }
             JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }
    public int getSoLuongById(String masach){ // Hàm lấy số lượng sách từ mã sách 
        int result=0;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql="Select soluongton From sach Where masach= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, masach);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=rs.getInt("soluongton");
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    public int UpdateSoLuong(String masach, int soluong){ // Update số lượng sách từ các chi tiết phiếu xuất và nhập
        int result=0;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql="Update sach Set soluongton = soluongton + ? Where masach= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, soluong);
            pst.setString(2, masach);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }
    
}
