/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DAOInterface;
import DTO.TaiKhoanDTO;
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
public class TaiKhoanDAO implements  DAOInterface<TaiKhoanDTO>{
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }
    
    @Override
    public int insert(TaiKhoanDTO tk){
        int result=0;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql=" Insert into taikhoan(manv, tendangnhap, matkhau, manhomquyen, trangthai) values ( ? , ? , ? , ?,  ?)";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, tk.getManv());
            pst.setString(2, tk.getUsername());
            pst.setString(3, tk.getMatkhau());
            pst.setInt(4,tk.getManhomquyen());
            pst.setInt(5, tk.getTrangthai());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
           Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    @Override
    public int update(TaiKhoanDTO tk){
        int result=0;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql="Update taikhoan Set tendangnhap= ? , matkhau= ? , manhomquyen= ? , trangthai= ? Where manv = ?  ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, tk.getUsername());
            pst.setNString(2, tk.getMatkhau());
            pst.setInt(3, tk.getManhomquyen());
            pst.setInt(4, tk.getTrangthai());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
           Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }
    
    @Override
    public int delete(String manv){
        int result=0;
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql="Update taikhoan Set trangthai = -1 Where manv= ? ";
            PreparedStatement pst= (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(manv));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }
    
    @Override
    public ArrayList<TaiKhoanDTO> selectAll(){
        ArrayList<TaiKhoanDTO> result= new ArrayList<>();
        try{
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql=" Select * From taikhoan Where trangthai=1";
            PreparedStatement pst= (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs= (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manv= rs.getInt("manv");
                String tendangnhap=rs.getString("tendangnhap");
                String matkhau=rs.getString("matkhau");
                int manhomquyen=rs.getInt("manhomquyen");
                int trangthai=rs.getInt("trangthai");
                TaiKhoanDTO tk=new TaiKhoanDTO(manv,tendangnhap,matkhau,manhomquyen,trangthai);
                result.add(tk);
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }
    @Override
    public TaiKhoanDTO selectById(String manv){
        TaiKhoanDTO tk=new TaiKhoanDTO();
        try {
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql=" Select * From taikhoan Where manv= ? ";
            PreparedStatement pst= (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(manv));
            ResultSet rs=(ResultSet) pst.executeQuery();
            while(rs.next()){
                int matk= rs.getInt("manv");
                String tendangnhap=rs.getString("tendangnhap");
                String matkhau=rs.getString("matkhau");
                int manhomquyen=rs.getInt("manhomquyen");
                int trangthai=rs.getInt("trangthai");
                tk.setManv(matk); tk.setUsername(tendangnhap); tk.setMatkhau(matkhau);
                tk.setManhomquyen(manhomquyen); tk.setTrangthai(trangthai);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return tk;
    }
    
    @Override
    public int getAutoIncrement(){
        int result=-1;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql= "Select Auto_Increment From Information_Schema.tables Where Table_Schema = 'quanlibansach' and Table_name = 'taikhoan'";
             PreparedStatement pst= (PreparedStatement) con.prepareStatement(sql);
             ResultSet rs=(ResultSet) pst.executeQuery();
             while(rs.next()){
                 result=rs.getInt("Auto_Increment");
             }
             if(result==-1){
                 System.out.println("No data, try again!");
             }
        }catch (Exception e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
        // Thừa
    }
}
