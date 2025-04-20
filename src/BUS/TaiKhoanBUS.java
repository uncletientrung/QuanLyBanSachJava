
/*
 * Click nbfs://SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.Dialog.TaiKhoanDialog.TaiKhoanDialogAdd;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class TaiKhoanBUS {
    private TaiKhoanDialogAdd TKDA;
    private final ArrayList<TaiKhoanDTO> listTaiKhoan;
    private final TaiKhoanDAO tkDAO;

    public TaiKhoanBUS() {
        this.tkDAO = TaiKhoanDAO.getInstance();
        this.listTaiKhoan = tkDAO.selectAll();
    }

    public ArrayList<TaiKhoanDTO> getTaiKhoanAll() {
        return TaiKhoanDAO.getInstance().selectAll();
    }
    
    public ArrayList<TaiKhoanDTO> timkiem(String keywords) {
        ArrayList<TaiKhoanDTO> ketqua = new ArrayList<>();
        for (TaiKhoanDTO tk : getTaiKhoanAll()) {
            boolean timtheoma = false;
            try {
                int manv = Integer.parseInt(keywords);
                timtheoma = (manv == tk.getManv());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma || tk.getUsername().toLowerCase().contains(keywords.toLowerCase())
                    || tk.getMatkhau().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(tk);
            }
        }
        return ketqua;
 }
    
    public boolean themTaiKhoan(String tentk,String mk,int manhomquyen) {
        int soluongnhanvien=TaiKhoanDAO.getInstance().getNextManv();
        int soluongtaikhoan=TaiKhoanDAO.getInstance().getSoLuongtkhientai();
        if(soluongnhanvien==soluongtaikhoan){
           JOptionPane.showMessageDialog(TKDA, "Vui lòng thêm nhân viên trước khi thêm tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
           return false;
        }
        

        for (TaiKhoanDTO tk : getTaiKhoanAll()) {
            if (tk.getUsername().equalsIgnoreCase(tentk)) {
                
                return false; // Ngăn chặn thêm trùng
            }
        }

        return TaiKhoanDAO.getInstance().insert(new TaiKhoanDTO(0, tentk,mk,manhomquyen,1)) > 0;
}
    //hàm update
    public boolean updateTaiKhoan(TaiKhoanDTO taiKhoan){
        int result = TaiKhoanDAO.getInstance().update(taiKhoan);
        if(result > 0){
            listTaiKhoan.clear();
            listTaiKhoan.addAll(getTaiKhoanAll()); // Cập nhật danh sách mới nhất từ DB
            return true;
        }
        return false;
        
    }
    //hàm kiểm tra coi khi sửa có lỡ sửa cùng tên hay không
public boolean isTenTaiKhoanTrung(String tentk) {
    for (TaiKhoanDTO tk : getTaiKhoanAll()) {
        if (tk.getUsername().equalsIgnoreCase(tentk)) {
            return true;
        }
    }
    return false;
}

    public boolean xoaTaiKhoan(int manv) {
        return tkDAO.delete(manv) > 0;
}

    // Thêm phương thức đăng nhập
    public TaiKhoanDTO dangNhap(String username, String matkhau) {
        return tkDAO.kiemTraDangNhap(username, matkhau);
    }

    // Thêm phương thức lấy vai trò (tuỳ chọn, nếu muốn dùng)
    public String getVaiTro(TaiKhoanDTO taiKhoan) {
        if (taiKhoan == null) {
            return "INVALID";
        }
        switch (taiKhoan.getManhomquyen()) {
            case 1:
                return "ADMIN";
            case 2:
                return "NHANVIEN";
            default:
                return "UNKNOWN";
        }
    }
}

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package BUS;
//
//import DAO.NhanVienDAO;
//import DTO.NhanVienDTO;
//import java.util.ArrayList;
//
///**
// *
// * @author Minnie
// */
//public class NhanVienBUS {
//    public final NhanVienDAO nvDAO = new NhanVienDAO();
//    private ArrayList<NhanVienDTO> listNV;
//    
//    public NhanVienBUS(){
//        listNV = nvDAO.selectAll();
//    }
//    
//    public ArrayList<NhanVienDTO> getNVAll(){ return listNV;}
// 
//    public Boolean addNV(NhanVienDTO nv){
//        boolean result = nvDAO.insert(nv) != 0;
//        
//        if(result){
//            listNV.add(nv);
//        }
//        return result;
//    }
//    
//    public NhanVienDTO getNVByID(int ma){
//        for(NhanVienDTO nv: listNV){
//            if(nv.getManv() == ma){
//                return nv;
//            }
//        }
//        return null;
//    }
//
//    public String getTenNV(int ma){
//        String result = null;
//        for (NhanVienDTO nv: listNV){
//            if(nv.getManv() == ma){
//                result = nv.getTennv();
//            }
//        }
//        return result;
//    }
//    
//    public String getHoTenNVById(int ma){
//        String result = null;
//        for (NhanVienDTO nv: listNV){
//            if(nv.getManv() == ma){
//                result = nv.getHonv() + " " + nv.getTennv();
//            }
//        }
//        return result;
//    }
//    
//    public Boolean updateNV_DB(NhanVienDTO nv){ //Update sách lên database kết hợp với hàm updateByID_List
//        boolean result=nvDAO.update(nv) !=0;
//        return result;
//    }
//    
//    public Boolean deleteById(int ma){ // Xóa trong List lẫn trong Database
//        NhanVienDTO nvXoa=getNVByID(ma);
//        Boolean result = nvDAO.delete(ma+"") !=0;
//        if(result){
//            for (NhanVienDTO nv: listNV){
//                if(nv.equals(nvXoa)){
//                    listNV.remove(nv);
//                    return result;
//                }
//            }
//        }
//        return result;
//    }
//    
//    public ArrayList<NhanVienDTO> search(String text){
//        text=text.toLowerCase();
//        ArrayList<NhanVienDTO> result=new ArrayList<>();
//        for(NhanVienDTO nv:listNV){
//            if(Integer.toString(nv.getManv()).toLowerCase().contains(text) || nv.getTennv().toLowerCase().contains(text)){
//                result.add(nv);
//            }
//        }
//        return  result;
//    }
//    public int getIdNvByNameNv(String tenNv) {
//    for (NhanVienDTO nv : listNV) {
//        String fullName = (nv.getHonv() + " " + nv.getTennv()).trim();
//        if (fullName.equalsIgnoreCase(tenNv.trim())) {
//            return nv.getManv();
//        }
//    }
//    return -1;
//}
//}