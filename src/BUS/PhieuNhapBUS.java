/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.*;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SachDTO;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Minnie
 */
public class PhieuNhapBUS {
    public static PhieuNhapDAO pnDAO;
    public static SachDAO sDAO;
    private final ChiTietPhieuNhapDAO ctpnDAO = ChiTietPhieuNhapDAO.getInstance();
    public ArrayList<PhieuNhapDTO> list_pn;
    private NhanVienBUS nvBUS;
    private NhaCungCapBUS nccBUS;
    
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
            int soluong = (ctpn.getSoluong());
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
            int soluong = -(ctpn.getSoluong());
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
    public ArrayList<PhieuNhapDTO> search(String text){
        ArrayList<PhieuNhapDTO> result=new  ArrayList<PhieuNhapDTO>();
        nvBUS =new NhanVienBUS();
        nccBUS=new NhaCungCapBUS();
        text=text.toLowerCase();
        for(PhieuNhapDTO pn: list_pn){
            String fullNameNV=nvBUS.getHoTenNVById(pn.getManv());
            String nameNCC=nccBUS.getTenNCC(pn.getMancc());
            String maPN=String.valueOf(pn.getMaphieu()); // Chuyeenr qua String để dùng hàm contains
            if(fullNameNV.toLowerCase().contains(text)|| nameNCC.toLowerCase().contains(text)||maPN.toLowerCase().contains(text)){
                result.add(pn);
            }
        }
        return result;
    }
    public ArrayList<PhieuNhapDTO> LowToHighofBill(ArrayList<PhieuNhapDTO> list_can_sort){
        ArrayList<PhieuNhapDTO> ListSort=list_can_sort;
        ListSort.sort(Comparator.comparingLong(PhieuNhapDTO::getTongTien));
        return  ListSort;
    }
    public ArrayList<PhieuNhapDTO> HighToLowofBill(ArrayList<PhieuNhapDTO> list_can_sort){
        ArrayList<PhieuNhapDTO> ListSort=list_can_sort;
        ListSort.sort(Comparator.comparingLong(PhieuNhapDTO::getTongTien).reversed());
        return  ListSort;
    }
    public ArrayList<PhieuNhapDTO> LowToHighofDate(ArrayList<PhieuNhapDTO> list_can_sort){
        ArrayList<PhieuNhapDTO> ListSort=list_can_sort;
        ListSort.sort(Comparator.comparing(PhieuNhapDTO::getThoigiantao));
        return  ListSort;
    }
    public ArrayList<PhieuNhapDTO> HighToLowofDate(ArrayList<PhieuNhapDTO> list_can_sort){
        ArrayList<PhieuNhapDTO> ListSort=list_can_sort;
        ListSort.sort(Comparator.comparing(PhieuNhapDTO::getThoigiantao).reversed());
        return  ListSort;
    }
    
    
}
