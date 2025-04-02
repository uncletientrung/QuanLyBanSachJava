/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.ChiTietPhieuXuatDAO;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuXuatDTO;
import java.util.ArrayList;
/**
 *
 * @author DELL
 */
public class PhieuXuatBUS {
    public static PhieuXuatDAO pxDAO;
    private final ChiTietPhieuXuatDAO chiTietPhieuXuatDAO=ChiTietPhieuXuatDAO.getInstance();
    public ArrayList<PhieuXuatDTO> list_px;
    
    public PhieuXuatBUS(){
        pxDAO=PhieuXuatDAO.getInstance();
        list_px= pxDAO.selectAll();
    }
    public ArrayList<PhieuXuatDTO> getAll(){
        return list_px;
    }
    public void insert(PhieuXuatDTO px, ArrayList<ChiTietPhieuXuatDTO> ctpx){
        pxDAO.insert(px);
        chiTietPhieuXuatDAO.insert(ctpx);
    }
    
}
