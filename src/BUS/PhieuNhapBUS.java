/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.*;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import java.util.ArrayList;

/**
 *
 * @author Minnie
 */
public class PhieuNhapBUS {
    public static PhieuNhapDAO pnDAO;
    public static SachDAO sDAO;
    private final ChiTietPhieuNhapDAO ctpnDAO = ChiTietPhieuNhapDAO.getInstance();
    public ArrayList<PhieuNhapDTO> list_pn;
    
    public PhieuNhapBUS(){
        pnDAO = PhieuNhapDAO.getInstance();
        sDAO = SachDAO.getInstance();
        list_pn = pnDAO.selectAll();
    }
    
    public ArrayList<PhieuNhapDTO> getAll(){
        this.list_pn = pnDAO.selectAll();
        return this.list_pn;
    }

    public int getLastMaPhieuNhap(){
        int lastMaPhieuNhap = 0;
        for (PhieuNhapDTO pn : list_pn) {
            if (pn.getMaphieu() > lastMaPhieuNhap) {
                lastMaPhieuNhap = pn.getMaphieu();
            }
        }
        return lastMaPhieuNhap;
    }
    
    public void insert(PhieuNhapDTO pn, ArrayList<ChiTietPhieuNhapDTO> list_ctpn){
        pnDAO.insert(pn);
        ctpnDAO.insert(list_ctpn);
        for(ChiTietPhieuNhapDTO ctpn: list_ctpn){
            int soluong =- (ctpn.getSoluong());
            sDAO.UpdateSoLuong(ctpn.getMasach(), soluong);
        }
        list_pn.add(pn); // Thêm phiếu nhập vào mảng sau khi thêm vào database
    }
    
    public PhieuNhapDTO getPNById(int mapn){
        PhieuNhapDTO pn = new PhieuNhapDTO();
        for (PhieuNhapDTO p : list_pn){
            if((p.getMaphieu() == mapn)){
                pn = p;
                return  pn;
            }
        }
        return pn;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getListCTPNById(int mapn){
        ArrayList<ChiTietPhieuNhapDTO> result = ctpnDAO.selectAll(mapn + "");
        return  result;
    }

    public void delete(PhieuNhapDTO pn, ArrayList<ChiTietPhieuNhapDTO> list_ctpn){
        for(ChiTietPhieuNhapDTO ctpn: list_ctpn){
            int soluong = (ctpn.getSoluong());
            sDAO.UpdateSoLuong(ctpn.getMasach(), soluong);
        }
        pnDAO.cancel(pn.getMaphieu() + "");
        ctpnDAO.delete(pn.getMaphieu() + "");
        list_pn.remove(pn);
    }

    public Boolean addPN(PhieuNhapDTO pn){
         boolean result = pnDAO.insert(pn) != 0;
        
        if(result){
             list_pn.add(pn);
        }
        return result;
    }
}
