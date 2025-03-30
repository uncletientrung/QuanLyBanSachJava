/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import DTO.NhaXuatBanDTO;
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
public class NhaXuatBanDAO implements DAOInterface<NhaXuatBanDTO>{
    public static NhaXuatBanDAO getInstance(){
        return new NhaXuatBanDAO();
        
    }

    @Override
    public int insert(NhaXuatBanDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(NhaXuatBanDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<NhaXuatBanDTO> selectAll() {
        ArrayList<NhaXuatBanDTO> nhaXuatBan= new ArrayList<NhaXuatBanDTO>();
        try{
            Connection con = JDBCUtil.getConnection();
            String sql ="SELECT * FROM nhaxuatban";
            PreparedStatement pst = con.prepareStatement(sql);
            
            
            //thiet lap result set
            ResultSet rs= pst.executeQuery(sql);
            //xu ly rs
            while(rs.next()){
                int maNhaXuatBan=rs.getInt("manxb");
                String tenNhaXuatBan= rs.getString("tennxb");
                String diachiNhaXuatBan = rs.getString("diachinxb");
                String sdtNXB = rs.getString("sdt");
                String email = rs.getString("email");
                //tao 1 doi tuong nhom quyen de nhan du lieu da doc tu database
                NhaXuatBanDTO doituong_nhacungcap= new NhaXuatBanDTO(maNhaXuatBan, tenNhaXuatBan, diachiNhaXuatBan, sdtNXB, email);
                //them doi tuong vo arraylist
                nhaXuatBan.add(doituong_nhacungcap);
                
            }
            JDBCUtil.closeConnection(con);
               
               
        }catch(SQLException e){
            Logger.getLogger(NhaXuatBanDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return nhaXuatBan;
    }

    @Override
    public NhaXuatBanDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
