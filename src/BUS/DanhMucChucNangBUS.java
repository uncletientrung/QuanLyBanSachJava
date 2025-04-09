/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DanhMucChucNangDAO;
import DTO.DanhMucChucNangDTO;
import java.util.ArrayList;

/**
 *
 * @author Hi
 */
public class DanhMucChucNangBUS {
    private final ArrayList<DanhMucChucNangDTO> listChucNang;
    private final DanhMucChucNangDAO cnDAO;
    
    
    public DanhMucChucNangBUS(){
        cnDAO=DanhMucChucNangDAO.getInstance();
        listChucNang=cnDAO.selectAll();
    }
    
    public ArrayList<DanhMucChucNangDTO> getAllchucnang(){
        return DanhMucChucNangDAO.getInstance().selectAll();
    }
}
