package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

public class KhachHangBUS {
    
    public static ArrayList<KhachHangDTO> getKhachHangAll(){
        return KhachHangDAO.selectAll(); 
    }
    
    public static Boolean add(KhachHangDTO a ){
        return (KhachHangDAO.insert(a) !=0);
    }
    
    public static Boolean updateKhachHang_DB(KhachHangDTO a){ 
        return KhachHangDAO.update(a) !=0; 
    }
    public static Boolean deleteById(int ma){ 
        return (KhachHangDAO.delete(ma +"")!=0);
    }
    
    public static ArrayList<KhachHangDTO> searchAll(String text){
        text=text.toLowerCase();
        ArrayList<KhachHangDTO> result=new ArrayList<>();
        for(KhachHangDTO kh:getKhachHangAll()){
            if( Integer.toString(kh.getMakh()).toLowerCase().contains(text)  ||
                kh.getTenkh().toLowerCase().contains(text) ||
                kh.getHokh().toLowerCase().contains(text)  ||
                kh.getemail().toLowerCase().contains(text) ||
                kh.getSdt().contains(text)){
                result.add(kh);
            }
        }
        return  result;
    }
    
    public static ArrayList<KhachHangDTO> search(String text){
        text=text.toLowerCase();
        ArrayList<KhachHangDTO> result=new ArrayList<>();
        for(KhachHangDTO kh:getKhachHangAll()){
            if(Integer.toString(kh.getMakh()).toLowerCase().contains(text)){
                result.add(kh);
            }
        }
        return  result;
    }
    
    public static ArrayList<KhachHangDTO> searchName(String text){
        text=text.toLowerCase();
        ArrayList<KhachHangDTO> result=new ArrayList<>();
        for(KhachHangDTO kh:getKhachHangAll()){
            if(kh.getTenkh().toLowerCase().contains(text)){
                result.add(kh);
            }
        }
        return  result;
    }
    
    public static ArrayList<KhachHangDTO> searchHo(String text){
        text=text.toLowerCase();
        ArrayList<KhachHangDTO> result=new ArrayList<>();
        for(KhachHangDTO kh:getKhachHangAll()){
            if(kh.getHokh().toLowerCase().contains(text)){
                result.add(kh);
            }
        }
        return  result;
    }
     
    public static ArrayList<KhachHangDTO> searchEmail(String text){
        text=text.toLowerCase();
        ArrayList<KhachHangDTO> result=new ArrayList<>();
        for(KhachHangDTO kh:getKhachHangAll()){
            if(kh.getemail().toLowerCase().contains(text)){
                result.add(kh);
            }
        }
        return  result;
    }
    
    public static ArrayList<KhachHangDTO> searchSDTKh(String text){
        ArrayList<KhachHangDTO> result=new ArrayList<>();
        for(KhachHangDTO kh:getKhachHangAll()){
            if(kh.getSdt().contains(text)){
                result.add(kh);
            }
        }
        return  result;
    }
    
    public static int getAutoIncrement(){
        int max=-999;
        for (KhachHangDTO kh: getKhachHangAll()){
            if(kh.getMakh()>max) max=kh.getMakh();
        }
        return max+1;
    }
    
    public KhachHangDTO getKHBySDT(String sdt){
        KhachHangDTO result= new KhachHangDTO();
        for (KhachHangDTO kh: getKhachHangAll()){
            if(kh.getSdt().equals(sdt)){
                result=kh;
                break;
            }
        }
        return result;
    }// dung ben class PhieuXuatDialogAdd_Controller
    
    public ArrayList<String> getAllSDT(){
        ArrayList<String> result=new ArrayList<>();
        for (KhachHangDTO kh: getKhachHangAll()){
            result.add(kh.getSdt());
        }
        return  result;
    }// dung ben class PhieuXuatDialogAdd

    public String getFullNameKHById(int makh){
        String result="";
        for (KhachHangDTO kh: getKhachHangAll()){
            if(kh.getMakh() == makh){
                result=(kh.getHokh() +" "+kh.getTenkh()).trim();
                return result;
            }
        }
        return result;
    }// dung ben class PhieuXuat
    
    public ArrayList<String> getFullNameKH(){
        ArrayList<String> result=new ArrayList<>();
        for(KhachHangDTO kh: getKhachHangAll()){
            String FullName=kh.getHokh() + " "+ kh.getTenkh();
            result.add(FullName);
        }
        return result;
    }// dung ben class PhieuXuatDialogAdd





    
}
