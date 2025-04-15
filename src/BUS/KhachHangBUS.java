/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class KhachHangBUS {
    public final KhachHangDAO khDAO=new KhachHangDAO();
    private  ArrayList<KhachHangDTO> listkh = null;
    
    public KhachHangBUS(){
        listkh= khDAO.selectAll();
    }
    public ArrayList<KhachHangDTO> getKhachHangAll(){
        if (listkh == null)
            listkh = KhachHangDAO.selectAll();
        
        return listkh;
    }
    public Boolean add(KhachHangDTO a ){
        boolean result=khDAO.insert(a) !=0;
        if (result){
            listkh.add(a);
//            listSach=sDAO.selectAll();
        }
        return result;
    }
    public KhachHangDTO getKhachHangById(int ma){ // Update sách trong List
        KhachHangDTO result=new KhachHangDTO();
        for(KhachHangDTO kh: listkh){
            if(kh.getMakh() == ma){
                return kh;
            }
        }
        return null;
    }
    public Boolean updateKhachHang_DB(KhachHangDTO a){ //Update sách lên database kết hợp với hàm updateByID_List
        boolean result=khDAO.update(a) !=0;
        return result;
    }
    public Boolean deleteById(int ma){ // Xóa trong List lẫn trong Database
        KhachHangDTO Xoa=getKhachHangById(ma);
        Boolean result=khDAO.delete(ma+"") !=0;
        if(result){
            for (KhachHangDTO kh: listkh){
                if(kh.equals(Xoa)){
                    listkh.remove(kh);
                    return result;
                }
            }
        }
        return result;
    }
    public ArrayList<KhachHangDTO> search(String text){
        text=text.toLowerCase();
        ArrayList<KhachHangDTO> result=new ArrayList<>();
        for(KhachHangDTO kh:listkh){
            if(Integer.toString(kh.getMakh()).toLowerCase().contains(text) || kh.getTenkh().toLowerCase().contains(text)){
                result.add(kh);
            }
        }
        return  result;
    }
//    public ArrayList<KhachHangDTO> LowToHighofPrice(ArrayList<SachDTO> sach){
//        ArrayList<SachDTO> ListSort=new ArrayList<>(sach); // Sao chép ListSach cu
//        ListSort.sort(Comparator.comparingInt(SachDTO::getDongia));
//        return  ListSort;
//    }
//    public ArrayList<SachDTO> HighToLowofPrice(ArrayList<SachDTO> sach){
//        ArrayList<SachDTO> ListSort=new ArrayList<>(sach); // Sao chép ListSach cu
//        ListSort.sort(Comparator.comparingInt(SachDTO::getDongia).reversed()); // Đảo ngược cao đến thấp
//        return  ListSort;
//    }
//    public ArrayList<SachDTO> LowToHighofNXB(ArrayList<SachDTO> sach){
//        ArrayList<SachDTO> result=new ArrayList<>(listSach);
//        result.sort(Comparator.comparing(SachDTO::getNamxuatban));
//        return  result;
//    }
//    public ArrayList<SachDTO> HighToLowofNXB(ArrayList<SachDTO> sach){
//        ArrayList<SachDTO> result=new ArrayList<>(listSach);
//        result.sort(Comparator.comparing(SachDTO::getNamxuatban).reversed());
//        return result;
//    }
    
    public KhachHangDTO getKHBySDT(String sdt){
        KhachHangDTO result= new KhachHangDTO();
        for (KhachHangDTO kh: listkh){
            if(kh.getSdt().equals(sdt)){
                result=kh;
                break;
            }
        }
        return result;
    }
    public ArrayList<String> getAllSDT(){
        ArrayList<String> result=new ArrayList<>();
        for (KhachHangDTO kh: listkh){
            result.add(kh.getSdt());
        }
        return  result;
    }

      public static boolean checkemail(String email){
    return email.contains("@gmail.com");
}

public static boolean checksdt(String sdt){
    if(sdt.length()==10&&sdt.charAt(0)=='0') return true;
    return false;
}

 public static Date checkngaysinh(String ngaysinh) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false); // Điều này giúp kiểm tra định dạng ngày đúng

        try {
            // Kiểm tra nếu ngaysinh có thể chuyển đổi sang Date hợp lệ
            Date date = formatter.parse(ngaysinh);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFullNameKHById(int makh){
        String result="";
        for (KhachHangDTO kh: listkh){
            if(kh.getMakh() == makh){
                result=(kh.getHokh() +" "+kh.getTenkh()).trim();
                return result;
            }
        }
        return result;
    }
    public ArrayList<String> getFullNameKH(){
        ArrayList<String> result=new ArrayList<>();
        for(KhachHangDTO kh: listkh){
            String FullName=kh.getHokh() + " "+ kh.getTenkh();
            result.add(FullName);
        }
        return result;
    }





    
}
