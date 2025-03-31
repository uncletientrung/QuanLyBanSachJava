/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TacGiaDAO;
import DTO.NhaXuatBanDTO;
import DTO.TacGiaDTO;
import java.util.ArrayList;

/**
 *
 * @author Hi
 */
public class TacGiaBUS {
    private final ArrayList<TacGiaDTO> listTacGia;
    private final TacGiaDAO tgDAO;

    public TacGiaBUS(){
        tgDAO = TacGiaDAO.getInstance();
        listTacGia = tgDAO.selectAll();
    }

    public ArrayList<TacGiaDTO> getTacGiaAll(){
          return TacGiaDAO.getInstance().selectAll();
    }

    public ArrayList<TacGiaDTO> timkiem(String keywords) {
        ArrayList<TacGiaDTO> ketqua = new ArrayList<>();
        for (TacGiaDTO tg : getTacGiaAll()) {
            boolean timtheoma = false;
            try {
                int matg = Integer.parseInt(keywords);
                timtheoma = (matg == tg.getMatacgia());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma || tg.getHotentacgia().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(tg);
            }
        }
        return ketqua;
    }
    public TacGiaDTO getTGById(int manxb){
        TacGiaDTO result=new TacGiaDTO();
        for(TacGiaDTO tg: listTacGia){
            if (tg.getMatacgia()== manxb){
                result=tg;
                break;
            }
        }
        return result;
    }
    public TacGiaDTO getTgByNameTG(String tentacgia){
        TacGiaDTO result= new TacGiaDTO();
        for(TacGiaDTO tg:listTacGia){
            if(tg.getHotentacgia().equals(tentacgia)){
                result=tg;
                break;
            }
        }
        return result;
    }
      
    
}
