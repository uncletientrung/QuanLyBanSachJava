/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DAO.*;
import DTO.*;
import connectDB.*;
import BUS.*;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Minnie
 */
public class NhanVienDAO implements DAOInterface<NhanVienDTO> {
    
    public static NhanVienDAO getInstance(){
        return new NhanVienDAO();
    }
    
    @Override
    public int insert(NhanVienDTO nv) {
        int result = 0;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql= "Insert into nhanvien(manv, honv, tennv, gioittinh, sdt, ngaysinh, trang thai) values (?,?,?,?,?,?,?)";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, nv.getManv());
            pst.setString(2, nv.getHonv());
            pst.setString(3, nv.getTennv());
            pst.setInt(4, nv.getGioitinh());
            pst.setString(5, nv.getSdt());
            pst.setDate(6, (Date) nv.getNgaysinh());
            pst.setInt(7, nv.getTrangthai());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }
        catch(Exception e){
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }

    @Override
    public int update(NhanVienDTO nv) {
        int result = 0;
        try{
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update nhanvien Set honv= ? , tennv= ? , gioittinh= ? , sdt= ? , ngaysinh= ? , trangthai= ?  ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, nv.getHonv());
            pst.setString(2, nv.getTennv());
            pst.setInt(3, nv.getGioitinh());
            pst.setString(4, nv.getSdt());
            pst.setDate(5, (Date) nv.getNgaysinh());
            pst.setInt(6, nv.getTrangthai());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }

    @Override
    public int delete(String nv) {
        int result=0;
        try{
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Delete From nhanvien Where manv= ?";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(nv));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    @Override
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=(ResultSet) pst.executeQuery();
            while(rs.next()){
                int ma = rs.getInt("manv");
                String ho = rs.getString("honv");
                String ten = rs.getString("tennv");
                int gioitinh = rs.getInt("gioittinh");
                String sdt = rs.getString("sdt");
                Date ngaysinh = rs.getDate("ngaysinh");
                int trangthai = rs.getInt("trangthai");
                NhanVienDTO nv = new NhanVienDTO(ma,ho,ten,gioitinh,sdt,trangthai,ngaysinh);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;              
    }

    @Override
    public NhanVienDTO selectById(String ma) {
        return new NhanVienDTO();
    }

    @Override
    public int getAutoIncrement() {
        return 0;
    }
}
