/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import connectDB.JDBCUtil;
import java.util.ArrayList;
import  DTO.KhachHangDTO;
import connectDB.JDBCUtil;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DELL
 */

public class KhachHangDAO {
    public static KhachHangDAO getInstance(){
        return new KhachHangDAO();
    }
    public int insert(KhachHangDTO a){
        int result=0;
        try{
             Connection con= (Connection) JDBCUtil.getConnection();
             String sql= "Insert into khachhang(makh,hokhachhang,tenkhachhang,email,ngaysinh,sdt,trangthai)  values ( ? , ? , ? ,  ? ,  ? ,  ? ,  ?  )";
             PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
             pst.setInt(1, a.getMakh());
             pst.setString(2, a.getHokh());
             pst.setString(3, a.getTenkh());
             pst.setString(4, a.getemail());
             pst.setDate(5, new java.sql.Date( a.getNgaysinh().getTime()));
             pst.setString(6, a.getSdt());
             pst.setInt(7, 1);
             result=pst.executeUpdate();
             JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }        
        return result;
    }
    public int update(KhachHangDTO a){
        int result=0;
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql="Update khachhang Set hokhachhang= ? , tenkhachhang= ? ,  email= ? , ngaysinh = ? , sdt= ? Where makh= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
             pst.setString(1, a.getHokh());
             pst.setString(2, a.getTenkh());
             pst.setString(3, a.getemail());
             pst.setDate(4, new java.sql.Date( a.getNgaysinh().getTime()));
             pst.setString(5, a.getSdt());
             pst.setInt(6, a.getMakh());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }
    
    public int delete(String a){
        int result=0;
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql=" Delete From khachhang Where makh= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(a));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return  result;
    }
    
    public ArrayList<KhachHangDTO> selectAll(){
        ArrayList<KhachHangDTO> result= new ArrayList<>();
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql=" SELECT *  FROM khachhang";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=(ResultSet) pst.executeQuery();
            while(rs.next()){
                int ma=rs.getInt(1);
                String ho=rs.getString(2);
                String ten=rs.getString(3);
                String email=rs.getString(4);
                Date ngaysinh=rs.getDate(5);
                String sdt=rs.getString(6);
                
              
                KhachHangDTO a=new KhachHangDTO(ma, ho, ten, email, ngaysinh, sdt);
                result.add(a);
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public KhachHangDTO selectById(String ma){
        KhachHangDTO result=new KhachHangDTO();
        try {
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql=" SELECT *  FROM khachhang  Where makh=  ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(ma));
            ResultSet rs=(ResultSet) pst.executeQuery();
            while(rs.next()){
                int makh=rs.getInt(1);
                String ho=rs.getString(2);
                String ten=rs.getString(3);
                String email=rs.getString(4);
                Date ngaysinh=rs.getDate(5);
                String sdt=rs.getString(6);
                
                
                result.setMakh(makh);
                result.setHokh(ho);
                result.setTenkh(ten);
                result.setemail(email);
                result.setNgaysinh(ngaysinh);
                result.setSdt(sdt);
                return result;
            }
        } catch (Exception e) {
        }
        return result;
    }
    
    public int getAutoIncrement(){
        int result=-1;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql= "Select Auto_Increment From Information_Schema.tables Where Table_Schema = 'quanlibansach' and Table_name = 'khachhang'";
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
    }
    
}
