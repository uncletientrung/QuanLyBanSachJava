/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DAO.*;
import DTO.*;
import connectDB.*;
import BUS.*;
import java.util.ArrayList;

/**
 *
 * @author Minnie
 */
public class NhanVienDAO implements DAOInterface<NhanVienDTO> {
    
    public static NhanVienDAO getInstance(){
        return new NhanVienDAO();
    }
    
    @Override
    public int insert(NhanVienDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int update(NhanVienDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public ArrayList<NhanVienDTO> selectAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NhanVienDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
