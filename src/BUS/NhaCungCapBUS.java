/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

/**
 *
 * @author Hi
 */
public class NhaCungCapBUS {
    private final ArrayList<NhaCungCapDTO> listNhaCungCap;
    private final NhaCungCapDAO nccDAO;
    
    public NhaCungCapBUS(){
        nccDAO=NhaCungCapDAO.getInstance();
        listNhaCungCap=nccDAO.selectAll();
    }
    
    public ArrayList<NhaCungCapDTO> getNhaCungCapAll(){
        return NhaCungCapDAO.getInstance().selectAll();
    }
    
    public ArrayList<NhaCungCapDTO> timkiem(String keywords) {
        ArrayList<NhaCungCapDTO> ketqua = new ArrayList<>();
        for (NhaCungCapDTO ncc : getNhaCungCapAll()) {
            boolean timtheoma = false;
            try {
                int mancc = Integer.parseInt(keywords);
                timtheoma = (mancc == ncc.getMancc());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma 
                    || ncc.getTenncc().toLowerCase().contains(keywords.toLowerCase())    
                    || ncc.getDiachincc().toLowerCase().contains(keywords.toLowerCase())
                    || ncc.getEmail().toLowerCase().contains(keywords.toLowerCase())
                    || ncc.getSdt().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(ncc);
            }
        }
        return ketqua;
    }
}
