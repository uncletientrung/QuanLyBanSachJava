/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connectDB.JDBCUtil;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    public int getNextManv() {
    int nextManv = 1; // Mặc định nếu chưa có nhân viên nào
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT MAX(manv) FROM nhanvien"; // Lấy mã nhân viên lớn nhất
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            nextManv = rs.getInt(1); // Mã tiếp theo
        }
        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return nextManv;
}
    public int getSoLuongtkhientai() {
    int soluong = 1; // Mặc định nếu chưa có nhân viên nào
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT MAX(manv) FROM taikhoan"; // Lấy mã nhân viên lớn nhất
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            soluong = rs.getInt(1); // Mã tiếp theo
        }
        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return soluong;
}

    @Override
public int insert(TaiKhoanDTO tk) {
    int result = 0;
    try {
        Connection con = JDBCUtil.getConnection();
        int ma = getNextManv(); // Lấy mã nhân viên tiếp theo
        
        String sql = "INSERT INTO taikhoan(manv, tendangnhap, matkhau, manhomquyen, trangthai) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ma); // Sử dụng mã nhân viên mới
        pst.setString(2, tk.getUsername());
        pst.setString(3, tk.getMatkhau());
        pst.setInt(4, tk.getManhomquyen());
        pst.setInt(5, 1);

        result = pst.executeUpdate();
        JDBCUtil.closeConnection(con);
    } catch (Exception e) {
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
            pst.setInt(5, tk.getManv());
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(Exception e){
           Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }
    
//    @Override
//    public int delete(String manv){
//        int result=0;
//        try{
//            Connection con= (Connection) JDBCUtil.getConnection();
//            String sql="Update taikhoan Set trangthai = -1 Where manv= ? ";
//            PreparedStatement pst= (PreparedStatement) con.prepareStatement(sql);
//            pst.setInt(1, Integer.parseInt(manv));
//            result=pst.executeUpdate();
//            JDBCUtil.closeConnection(con);
//        }catch(Exception e){
//            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return  result;
//    }
    
    
    public int delete(int manv) {
    String query = "DELETE FROM taikhoan WHERE manv = ?";
    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement pst = conn.prepareStatement(query)) {
         pst.setInt(1, manv);
        return pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}



    @Override
public int delete(String t) {
    try {
        int manv = Integer.parseInt(t);  // Chuyển String → int
        return delete(manv);  // Gọi hàm delete(int)
    } catch (NumberFormatException e) {
        System.out.println("Lỗi chuyển đổi ID từ String sang int");
        return 0;
    }
}
    
    @Override
    public ArrayList<TaiKhoanDTO> selectAll(){
        ArrayList<TaiKhoanDTO> result= new ArrayList<>();
        try{
            Connection con = JDBCUtil.getConnection();
            String sql=" Select * From taikhoan";
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
    
    public TaiKhoanDTO kiemTraDangNhap(String tendangnhap, String matkhau) {
        TaiKhoanDTO tk = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE tendangnhap = ? AND matkhau = ? AND trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, tendangnhap);
            pst.setString(2, matkhau);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                int manv = rs.getInt("manv");
                String username = rs.getString("tendangnhap");
                String pass = rs.getString("matkhau");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                tk = new TaiKhoanDTO(manv, username, pass, manhomquyen, trangthai);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return tk;
    }
    
    public List<TaiKhoanDTO> getTaiKhoanTheoMaNhom(int maNhom) {
    List<TaiKhoanDTO> list = new ArrayList<>();
    String sql = "SELECT * FROM taikhoan WHERE manhomquyen = ?";
    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, maNhom);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            TaiKhoanDTO tk = new TaiKhoanDTO();
            tk.setManv(rs.getInt("manv"));
            tk.setUsername(rs.getString("tendangnhap"));
            tk.setMatkhau(rs.getString("matkhau"));
            list.add(tk);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
