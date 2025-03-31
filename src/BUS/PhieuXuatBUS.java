/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.PhieuXuatDAO;
import DTO.PhieuXuatDTO;
import java.util.ArrayList;
/**
 *
 * @author DELL
 */
public class PhieuXuatBUS {
    public static PhieuXuatDAO pxDAO=PhieuXuatDAO.getInstance();
    public ArrayList<PhieuXuatDTO> list_px;
    
    public PhieuXuatBUS(){
        list_px= pxDAO.selectAll();
    }
    public ArrayList<PhieuXuatDTO> getAll(){
        return list_px;
    }
}
