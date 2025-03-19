/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.TaiKhoanDAO;
import  DTO.TaiKhoanDTO;
import java.util.ArrayList;
/**
 *
 * @author DELL
 */
public class TaiKhoanBUS {
    private final ArrayList<TaiKhoanDTO> listTaiKhoan;
    public TaiKhoanBUS(){
        this.listTaiKhoan =TaiKhoanDAO.getInstance().selectAll();
    }
    public ArrayList<TaiKhoanDTO> getTaiKhoanAll(){
        return listTaiKhoan;
    }
    
}
