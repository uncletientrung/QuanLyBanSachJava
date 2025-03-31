/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaXuatBanDAO;
import DTO.NhaXuatBanDTO;
import java.util.ArrayList;

/**
 *
 * @author Hi
 */
public class NhaXuatBanBUS {
    private final ArrayList<NhaXuatBanDTO> listNhaXuatBan;
    private final NhaXuatBanDAO nxbDAO;
    
    public NhaXuatBanBUS(){
        nxbDAO=NhaXuatBanDAO.getInstance();
        listNhaXuatBan=nxbDAO.selectAll();
    }
    public ArrayList<NhaXuatBanDTO> getNhaXuatBanAll(){
        return NhaXuatBanDAO.getInstance().selectAll();
    }
    
    public ArrayList<NhaXuatBanDTO> timkiem(String keywords) {
        ArrayList<NhaXuatBanDTO> ketqua = new ArrayList<>();
        for (NhaXuatBanDTO nxb : getNhaXuatBanAll()) {
            boolean timtheoma = false;
            try {
                int manxb = Integer.parseInt(keywords);
                timtheoma = (manxb == nxb.getManxb());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma 
                    || nxb.getTennxb().toLowerCase().contains(keywords.toLowerCase())
                    || nxb.getDiachinxb().toLowerCase().contains(keywords.toLowerCase())
                    || nxb.getEmail().toLowerCase().contains(keywords.toLowerCase())
                    || nxb.getSdt().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(nxb);
            }
        }
        return ketqua;
    }
    public NhaXuatBanDTO getNXBById(int manxb){
        NhaXuatBanDTO result=new NhaXuatBanDTO();
        for(NhaXuatBanDTO nxb: listNhaXuatBan){
            if (nxb.getManxb() == manxb){
                result=nxb;
                break;
            }
        }
        return result;
    }
    public NhaXuatBanDTO getNXBByNameNXB(String tenNxb){
        NhaXuatBanDTO result=new NhaXuatBanDTO();
        for(NhaXuatBanDTO nxb: listNhaXuatBan){
            if(nxb.getTennxb().equals(tenNxb) ){
                result=nxb;
                break;
            }
        }
        return result;
    }
    
}
