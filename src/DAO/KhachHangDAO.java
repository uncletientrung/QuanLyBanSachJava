
package DAO;

import  DTO.KhachHangDTO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class KhachHangDAO {
    
    public static int insert(KhachHangDTO a){
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
            System.out.println("LỖI SQL HÀM insert Ở CLASS KhachHangDAO");
        }        
        return result;
    }
    
    public static int update(KhachHangDTO a){
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
            System.out.println("LỖI SQL HÀM update Ở CLASS KhachHangDAO");
        }
        return  result;
    }
    
    public static int delete(String a){
        int result=0;
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql=" Delete From khachhang Where makh= ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(a));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
            System.out.println("LỖI SQL HÀM delete Ở CLASS KhachHangDAO");
        }
        return  result;
    }
    
    public static ArrayList<KhachHangDTO> selectAll(){
        try{
            ArrayList<KhachHangDTO> result= new ArrayList<>();
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
            return result;
        }catch(Exception e){
            System.out.println("LỖI SQL HÀM selectAll Ở CLASS KhachHangDAO");
            return null;
        }
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
    
    
}
