/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.TacGiaDAO;
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
   
    public boolean themTacGia(String tenTacGia){
        for(TacGiaDTO tg: getTacGiaAll()){
            if(tg.getHotentacgia().equals(tenTacGia)){
                return false;
            }
        }
        return TacGiaDAO.getInstance().insert(new TacGiaDTO(0,tenTacGia))>0;
    }
    
    public boolean updateTacGia(TacGiaDTO tacGia){
        int result = TacGiaDAO.getInstance().update(tacGia);
        if(result > 0){
            listTacGia.clear();
            listTacGia.addAll(getTacGiaAll()); // Cập nhật danh sách mới nhất từ DB
            return true;
        }
        return false;
        
    }
    //hàm kiểm tra coi khi sửa có lỡ sửa cùng tên hay không
    public boolean isTenTacGiaTrung(String tenTacGia, int maTacGia) {
        
    for (TacGiaDTO tg : getTacGiaAll()) {
        if (tg.getHotentacgia().equalsIgnoreCase(tenTacGia) && tg.getMatacgia()!= maTacGia) {
            return true; // Đã tồn tại nhóm quyền khác có cùng tên
        }
    }
    return false;
}
    
    
    public boolean xoaTacGia(int maTacGia) {
        return tgDAO.delete(maTacGia) > 0;
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
    public ArrayList<String> getAllNameTG(){
        ArrayList<String> result=new ArrayList<>();
        for(TacGiaDTO nxb: listTacGia){
            result.add(nxb.getHotentacgia());
        }
        return result;
    }

    
}



