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
        int result = 0;
    try {
        Connection con = JDBCUtil.getConnection();

        // Kiểm tra xem nhóm quyền đã tồn tại chưa
        String checkSql = "SELECT COUNT(*) FROM nhacungcap WHERE tenncc = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);
        checkStmt.setString(1, t.getTenncc());
        ResultSet rs = checkStmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        
        if (count > 0) {
            return 0; // Trả về 0 nếu nhóm quyền đã tồn tại
        }

        // Nếu không có trong database, tiến hành thêm mới
        String sql = "INSERT INTO nhacungcap (tenncc,diachincc,sdt,email,trangthai) VALUES (?,?,?,?,1)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, t.getTenncc());
        pst.setString(2, t.getDiachincc());
        pst.setString(3, t.getSdt());
        pst.setString(4, t.getEmail());
        
        result = pst.executeUpdate(); // Thực thi câu lệnh INSERT

        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
        Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, e);
    }
    return result; // Trả về số dòng bị ảnh hưởng (1 nếu thành công)
    }

    @Override
    public int update(NhaCungCapDTO t) {
        int result=0;
        
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE nhacungcap set tenncc=?,diachincc=?,sdt=?,email=? where mancc=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenncc());
            pst.setString(2, t.getDiachincc());
            pst.setString(3, t.getSdt());
            pst.setString(4, t.getEmail());
            pst.setInt(5, t.getMancc());
            
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(String t) {
        try {
        int maNhaCungCap = Integer.parseInt(t);  // Chuyển String → int
        return delete(maNhaCungCap);  // Gọi hàm delete(int)
    } catch (NumberFormatException e) {
        return 0;
    }
    }
    
    
    public int delete(int maNhaCungCap) {
    String query = "DELETE FROM nhacungcap WHERE mancc = ?";
    try (Connection conn = JDBCUtil.getConnection();
         PreparedStatement pst = conn.prepareStatement(query)) {
         pst.setInt(1, maNhaCungCap);
        return pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
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
