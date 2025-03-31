/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;

/**
 *
 * @author Minnie
 */
public class NhanVienBUS {
    public final NhanVienDAO nvDAO = new NhanVienDAO();
    private ArrayList<NhanVienDTO> listNV;
    
    public NhanVienBUS(){
        listNV = nvDAO.selectAll();
    }
    
    public ArrayList<NhanVienDTO> getNVAll(){ return listNV;}
 
    public Boolean addNV(NhanVienDTO nv){
        boolean result = nvDAO.insert(nv) != 0;
        
        if(result){
            listNV.add(nv);
        }
        return result;
    }
    
    public NhanVienDTO getNVByID(int ma){
        for(NhanVienDTO nv: listNV){
            if(nv.getManv() == ma){
                return nv;
            }
        }
        return null;
    }
    
    public Boolean updateNV_DB(NhanVienDTO nv){ //Update sách lên database kết hợp với hàm updateByID_List
        boolean result=nvDAO.update(nv) !=0;
        return result;
    }
    
    public Boolean deleteById(int ma){ // Xóa trong List lẫn trong Database
        NhanVienDTO nvXoa=getNVByID(ma);
        Boolean result = nvDAO.delete(ma+"") !=0;
        if(result){
            for (NhanVienDTO nv: listNV){
                if(nv.equals(nvXoa)){
                    listNV.remove(nv);
                    return result;
                }
            }
        }
        return result;
    }
    
    public ArrayList<NhanVienDTO> search(String text){
        text=text.toLowerCase();
        ArrayList<NhanVienDTO> result=new ArrayList<>();
        for(NhanVienDTO nv:listNV){
            if(Integer.toString(nv.getManv()).toLowerCase().contains(text) || nv.getTennv().toLowerCase().contains(text)){
                result.add(nv);
            }
        }
        return  result;
    }
}
