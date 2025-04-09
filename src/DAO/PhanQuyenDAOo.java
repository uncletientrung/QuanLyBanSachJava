/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhomQuyenDTO;
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
public class PhanQuyenDAOo implements DAOInterface<NhomQuyenDTO>{
    public static PhanQuyenDAOo getInstance(){
        return new PhanQuyenDAOo();
    }

    @Override
    public int insert(NhomQuyenDTO t) {
    int result = 0;
    try {
        Connection con = JDBCUtil.getConnection();

        // Kiểm tra xem nhóm quyền đã tồn tại chưa
        String checkSql = "SELECT COUNT(*) FROM nhomquyen WHERE tennhomquyen = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);
        checkStmt.setString(1, t.getTennhomquyen());
        ResultSet rs = checkStmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        
        if (count > 0) {
            System.out.println("Nhóm quyền đã tồn tại! Không thêm mới.");
            return 0; // Trả về 0 nếu nhóm quyền đã tồn tại
        }

        // Nếu không có trong database, tiến hành thêm mới
        String sql = "INSERT INTO nhomquyen (manhomquyen, tennhomquyen, trangthai) VALUES (?, ?, 1)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, t.getManhomquyen());
        pst.setString(2, t.getTennhomquyen());
        

        
        result = pst.executeUpdate(); // Thực thi câu lệnh INSERT

        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
        Logger.getLogger(PhanQuyenDAOo.class.getName()).log(Level.SEVERE, null, e);
    }
    return result; // Trả về số dòng bị ảnh hưởng (1 nếu thành công)
}


    @Override
    public int update(NhomQuyenDTO t) {
        int result=0;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql="UPDATE nhomquyen "+
                    "set tennhomquyen=? WHERE manhomquyen =?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennhomquyen());
            pst.setInt(2, t.getManhomquyen());
            result= pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(SQLException e){
            Logger.getLogger(PhanQuyenDAOo.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
        
       
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
            Logger.getLogger(PhanQuyenDAOo.class.getName()).log(Level.SEVERE,null,e);
        }
        return nhomQuyen;
        
    }

    @Override
    public NhomQuyenDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
       int result=-1;
        try{
            Connection con=(Connection) JDBCUtil.getConnection();
            String sql= "Select Auto_Increment From Information_Schema.tables Where Table_Schema = 'quanlibansach' and Table_name = 'nhomquyen'";
             PreparedStatement pst= (PreparedStatement) con.prepareStatement(sql);
             ResultSet rs=(ResultSet) pst.executeQuery();
             while(rs.next()){
                 result=rs.getInt("Auto_Increment");
             }
             if(result==-1){
                 System.out.println("No data, try again!");
             }
        }catch (SQLException e) {
            Logger.getLogger(PhanQuyenDAOo.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }

public int delete(int maNhomQuyen) {
    String query = "DELETE FROM nhomquyen WHERE manhomquyen = ?";
    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement pst = conn.prepareStatement(query)) {
         pst.setInt(1, maNhomQuyen);
        return pst.executeUpdate();
    } catch (SQLException e) {
            Logger.getLogger(PhanQuyenDAOo.class.getName()).log(Level.SEVERE, null, e);
        }
    return 0;
}



    @Override
public int delete(String t) {
    try {
        int maNhomQuyen = Integer.parseInt(t);  // Chuyển String → int
        return delete(maNhomQuyen);  // Gọi hàm delete(int)
    } catch (NumberFormatException e) {
        System.out.println("Lỗi chuyển đổi ID từ String sang int");
        return 0;
    }
}

public int getNextAvailableMaNhomQuyen() {
    int next = 1;
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT manhomquyen FROM nhomquyen ORDER BY manhomquyen ASC";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int current = rs.getInt("manhomquyen");
            if (current == next) {
                next++;
            } else {
                break; // đã tìm ra mã trống
            }
        }

        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
            Logger.getLogger(PhanQuyenDAOo.class.getName()).log(Level.SEVERE, null, e);
        }

    return next;
}

public String getTenNhomQuyen(int maNhom) {
    String ten = "";
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT tennhomquyen FROM nhomquyen WHERE manhomquyen = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maNhom);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            ten = rs.getString("tennhomquyen");
        }
        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
            Logger.getLogger(PhanQuyenDAOo.class.getName()).log(Level.SEVERE, null, e);
        }
    return ten;
}



  
    
    
}
 
