/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.SachDAO;
import DTO.SachDTO;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author DELL
 */
public class SachBUS {
    public final SachDAO sDAO=new SachDAO();
    private  ArrayList<SachDTO> listSach;
    
    public SachBUS(){
        listSach= sDAO.selectAll();
    }
    public ArrayList<SachDTO> getSachAll(){
        return listSach;
    }
    public Boolean addSach(SachDTO sach ){
        boolean result=sDAO.insert(sach) !=0;
        if (result){
            listSach.add(sach);
//            listSach=sDAO.selectAll();
        }
        return result;
    }
    public SachDTO getSachById(int maSach){ // Update sách trong List
        SachDTO result=new SachDTO();
        for(SachDTO sach: listSach){
            if(sach.getMasach() == maSach){
                
                result=sach;
                return result;
            }
        }
        return result;
    }
    public Boolean updateSach_DB(SachDTO sach){ //Update sách lên database kết hợp với hàm updateByID_List
        boolean result=sDAO.update(sach) !=0;
        if(result){ // Nếu update đc database sẽ update lên mảng Array
            for(int i=0;i<listSach.size()-1;i++){
                if(listSach.get(i).getMasach()==sach.getMasach()){
                    listSach.set(i,sach);
                }
            }
        }
        return result;
    }
    public Boolean deleteById(int masach){ // Xóa trong List lẫn trong Database
        SachDTO sachXoa=getSachById(masach);
        Boolean result=sDAO.delete(masach+"") !=0;
        if(result){
            for (SachDTO sach: listSach){
                if(sach.equals(sachXoa)){
                    listSach.remove(sach);
                    return result;
                }
            }
        }
        return result;
    }
    public ArrayList<SachDTO> search(String text){
        text=text.toLowerCase();
        ArrayList<SachDTO> result=new ArrayList<>();
        for(SachDTO sach:listSach){
            if(Integer.toString(sach.getMasach()).toLowerCase().contains(text) || sach.getTensach().toLowerCase().contains(text)){
                result.add(sach);
            }
        }
        return  result;
    }
    public ArrayList<SachDTO> LowToHighofPrice(ArrayList<SachDTO> sach){
        ArrayList<SachDTO> ListSort=new ArrayList<>(sach); // Sao chép ListSach cu
        ListSort.sort(Comparator.comparingInt(SachDTO::getDongia));
        return  ListSort;
    }
    public ArrayList<SachDTO> HighToLowofPrice(ArrayList<SachDTO> sach){
        ArrayList<SachDTO> ListSort=new ArrayList<>(sach); // Sao chép ListSach cu
        ListSort.sort(Comparator.comparingInt(SachDTO::getDongia).reversed()); // Đảo ngược cao đến thấp
        return  ListSort;
    }
    public ArrayList<SachDTO> LowToHighofNXB(ArrayList<SachDTO> sach){
        ArrayList<SachDTO> result=new ArrayList<>(listSach);
        result.sort(Comparator.comparing(SachDTO::getNamxuatban));
        return  result;
    }
    public ArrayList<SachDTO> HighToLowofNXB(ArrayList<SachDTO> sach){
        ArrayList<SachDTO> result=new ArrayList<>(listSach);
        result.sort(Comparator.comparing(SachDTO::getNamxuatban).reversed());
        return result;
    }
    public int getIdSachByNameSach(String tenSach){
        int result=0;
        for(SachDTO sach: listSach){
            if(sach.getTensach().equals(tenSach)){
                result=sach.getMasach();
            }
        }
        return result;
    }
    public int getSoLuongById(String maSach){
        int idSach=Integer.parseInt(maSach);
        int result=0;
        for (SachDTO s: listSach){
            if(s.getMasach() == idSach){
                result =s.getSoluongton();
                return result;
            }
        }
        return result;
    }
    
    

}
