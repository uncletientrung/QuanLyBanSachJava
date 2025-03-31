///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package BUS;
//import DAO.TaiKhoanDAO;
//import  DTO.TaiKhoanDTO;
//import java.util.ArrayList;
///**
// *
// * @author DELL
// */
//public class TaiKhoanBUS {
//    private final ArrayList<TaiKhoanDTO> listTaiKhoan;
//    public TaiKhoanBUS(){
//        this.listTaiKhoan =TaiKhoanDAO.getInstance().selectAll();
//    }
//    public ArrayList<TaiKhoanDTO> getTaiKhoanAll(){
//        return listTaiKhoan;
//    }
//    
//}


/*
 * Click nbfs://SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhanQuyenDAOo;
import DAO.TaiKhoanDAO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import com.mysql.cj.MysqlType;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class TaiKhoanBUS {
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