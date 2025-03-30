/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
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
public class NhaCungCapDAO implements DAOInterface<NhaCungCapDTO>{
    
    public static NhaCungCapDAO getInstance(){
        return new NhaCungCapDAO();
    }

    @Override
    public int insert(NhaCungCapDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(NhaCungCapDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<NhaCungCapDTO> selectAll() {
        ArrayList<NhaCungCapDTO> nhaCungCap= new ArrayList<NhaCungCapDTO>();
        try{
            Connection con = JDBCUtil.getConnection();
            String sql ="SELECT * FROM nhacungcap";
            PreparedStatement pst = con.prepareStatement(sql);
            
            
            //thiet lap result set
            ResultSet rs= pst.executeQuery(sql);
            //xu ly rs
            while(rs.next()){
                int maNhaCungCap=rs.getInt("mancc");
                String tenNhaCungCap= rs.getString("tenncc");
                String diaChiNhaCungCap = rs.getString("diachincc");
                String sdtNCC = rs.getString("sdt");
                String email = rs.getString("email");
                //tao 1 doi tuong nhom quyen de nhan du lieu da doc tu database
                NhaCungCapDTO doituong_nhacungcap= new NhaCungCapDTO(maNhaCungCap, tenNhaCungCap,diaChiNhaCungCap, sdtNCC, email);
                //them doi tuong vo arraylist
                nhaCungCap.add(doituong_nhacungcap);
                
            }
            JDBCUtil.closeConnection(con);
               
               
        }catch(SQLException e){
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return nhaCungCap;
    }

    @Override
    public NhaCungCapDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
