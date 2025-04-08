/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.PhieuNhapDTO;
import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;
import DAO.*;

/**
 *
 * @author Minnie
 */
public class PhieuNhapBUS {
    public static PhieuNhapDAO pnDAO;
    private final ChiTietPhieuNhapDAO ctpnDAO = ChiTietPhieuNhapDAO.getInstance();
    public ArrayList<PhieuNhapDTO> list_pn;
    
    public PhieuNhapBUS(){
        pnDAO = PhieuNhapDAO.getInstance();
        list_pn = pnDAO.selectAll();
    }
    
    public ArrayList<PhieuNhapDTO> getAll(){
        return list_pn;
    }
    
    public void insert(PhieuNhapDTO pn, ArrayList<ChiTietPhieuNhapDTO> ctpn){
        pnDAO.insert(pn);
        ctpnDAO.insert(ctpn);
    }
    
    public Boolean addPN(PhieuNhapDTO pn){
        boolean result = pnDAO.insert(pn) != 0;
        
        if(result){
            list_pn.add(pn);
        }
        return result;
    }
}
