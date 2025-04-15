/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhuyenMaiDTO;
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
public class KhuyenMaiDAO implements DAOInterface<KhuyenMaiDTO>{
    
    public static KhuyenMaiDAO getInstance(){
        return new KhuyenMaiDAO();
    }

    
    @Override
    public int insert(KhuyenMaiDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(KhuyenMaiDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<KhuyenMaiDTO> selectAll() {
        ArrayList<KhuyenMaiDTO> danhSachKM= new ArrayList<KhuyenMaiDTO>();
        
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * from khuyenmai";
            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int maKM = rs.getInt("makm");
                String TenChuongTrinh=rs.getString("tenkm");
                String NgayBatDau=rs.getString("ngaybatdau");
                String NgayKetThuc= rs.getString("ngayketthuc");
                double dieuKienToiThieu= rs.getDouble("dieukientoithieu");
                double phanTramGiam=rs.getDouble("phantramgiam");
                KhuyenMaiDTO doituongKM = new KhuyenMaiDTO(maKM, TenChuongTrinh, NgayBatDau, NgayKetThuc, dieuKienToiThieu, phanTramGiam);
                danhSachKM.add(doituongKM);
            }
            JDBCUtil.closeConnection(con);
            
            
        }catch(SQLException e){
            Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE,null,e);
            
        }
        return danhSachKM;
    }

    @Override
    public KhuyenMaiDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
