/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhomQuyenDTO;
import connectDB.JDBCUtil;
import java.awt.List;
import java.util.ArrayList;
import connectDB.JDBCUtil;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BUS.PhanQuyenBUS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hi
 */
public class PhanQuyenDao implements DAOInterface<NhomQuyenDTO>{
    public static PhanQuyenDao getInstance(){
        return new PhanQuyenDao();
    }

    @Override
    public int insert(NhomQuyenDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(NhomQuyenDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<NhomQuyenDTO> selectAll() {
        ArrayList<NhomQuyenDTO> nhomQuyen= new ArrayList<NhomQuyenDTO>();
        try{
            Connection con = JDBCUtil.getConnection();
            String sql ="SELECT * FROM nhomquyen";
            PreparedStatement pst = con.prepareStatement(sql);
            
            
            //thiet lap result set
            ResultSet rs= pst.executeQuery(sql);
            //xu ly rs
            while(rs.next()){
                int maNhomQuyen=rs.getInt("manhomquyen");
                String tenNhomQuyen= rs.getString("tennhomquyen");
                //tao 1 doi tuong nhom quyen de nhan du lieu da doc tu database
                NhomQuyenDTO doituong_nhomquyen= new NhomQuyenDTO(maNhomQuyen, tenNhomQuyen);
                //them doi tuong vo arraylist
                nhomQuyen.add(doituong_nhomquyen);
                
            }
            JDBCUtil.closeConnection(con);
               
               
        }catch(SQLException e){
            Logger.getLogger(PhanQuyenDao.class.getName()).log(Level.SEVERE,null,e);
        }
        return nhomQuyen;
        
    }

    @Override
    public NhomQuyenDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
    
}
