/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import connectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minnie
 */
public class ChiTietPhieuNhapDAO implements ChiTietInterface<ChiTietPhieuNhapDTO>{
    public static ChiTietPhieuNhapDAO getInstance(){
        return new ChiTietPhieuNhapDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuNhapDTO> t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Insert into ctphieunhap (maphieunhap , masach , soluong , dongia)  values ( ? , ? , ? , ? )";
            PreparedStatement pst = con.prepareStatement(sql);
            for(ChiTietPhieuNhapDTO chitiet : t){
                pst.setInt(1, chitiet.getMaphieu());
                pst.setString(2, chitiet.getMasach());
                pst.setInt(3, chitiet.getSoluong());
                pst.setInt(4, chitiet.getDongia());


                result += pst.executeUpdate();
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e){Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);}
        return result;
    }

    @Override
    public int delete(String t) {
        int result=0;
        try{
            Connection con= (Connection) JDBCUtil.getConnection();
            String sql="Delete  From ctphieunhap Where maphieunhap =  ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            result=pst.executeUpdate();
            JDBCUtil.closeConnection(con); 
        }catch(Exception e){
           Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return  result;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuNhapDTO> t, String pk) {
        int result= this.delete(pk);
        if(result !=0){
            result=this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuNhapDTO> result= new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql="Select * From ctphieunhap Where maphieunhap = ? ";
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                int maphieuxuat=rs.getInt("maphieunhap");
                String masach=rs.getString("masach");
                int soluong=rs.getInt("soluong");
                int dongia=rs.getInt("dongia");
                ChiTietPhieuNhapDTO ctPhieuNhap = new ChiTietPhieuNhapDTO(maphieuxuat, masach, soluong, dongia);
                result.add(ctPhieuNhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;    
    }    
}
