/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogAdd;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class NhaCungCapBUS {
    private final ArrayList<NhaCungCapDTO> listNhaCungCap;
    private final NhaCungCapDAO nccDAO;
    private NhaCungCapDialogAdd NCCDA;
    
    public NhaCungCapBUS(){
        nccDAO=NhaCungCapDAO.getInstance();
        listNhaCungCap=nccDAO.selectAll();
    }
    
    public ArrayList<NhaCungCapDTO> getNhaCungCapAll(){
        return NhaCungCapDAO.getInstance().selectAll();
    }


    public String getTenNCC(int maNCC) {
        for (NhaCungCapDTO ncc : listNhaCungCap) {
            if (ncc.getMancc() == maNCC) {
                return ncc.getTenncc();
            }
        }
        return null;
    }

    public ArrayList<String> getAllTenNCC() {
        ArrayList<String> tenNCC = new ArrayList<>();
        for (NhaCungCapDTO ncc : listNhaCungCap) {
            tenNCC.add(ncc.getTenncc());
        }
        return tenNCC;
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
    
     public boolean themNhaCungCap(String tenNcc, String diaChi, String sdt, String email) {
            ArrayList<NhaCungCapDTO> danhSachNhaCungCap = getNhaCungCapAll();

            // Kiểm tra định dạng số điện thoại và email
            if (!isValidPhoneNumber(sdt)) {
                JOptionPane.showMessageDialog(NCCDA, "Vui lòng nhập đúng định dạng số điện thoại Việt Nam!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(NCCDA, "Vui lòng nhập đúng định dạng email!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Kiểm tra trùng lặp dữ liệu
            for (NhaCungCapDTO ncc : danhSachNhaCungCap) {
                if (ncc.getTenncc().equalsIgnoreCase(tenNcc)) {
                    JOptionPane.showMessageDialog(NCCDA, "Tên nhà cung cấp đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (ncc.getDiachincc().equalsIgnoreCase(diaChi)) {
                    JOptionPane.showMessageDialog(NCCDA, "Địa chỉ này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (ncc.getSdt().equalsIgnoreCase(sdt)) {
                    JOptionPane.showMessageDialog(NCCDA, "Số điện thoại này đã được sử dụng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (ncc.getEmail().equalsIgnoreCase(email)) {
                    JOptionPane.showMessageDialog(NCCDA, "Email này đã được đăng ký!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            // Thêm vào database
            boolean isInserted = NhaCungCapDAO.getInstance().insert(new NhaCungCapDTO(0, tenNcc, diaChi, sdt, email)) > 0;

            if (isInserted) {
                
            } else {
                JOptionPane.showMessageDialog(NCCDA, "Thêm nhà cung cấp thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            return isInserted;
}


     public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
     }
     
     
     public boolean isValidPhoneNumber(String phoneNumber) {
            // Loại bỏ dấu ngoặc và khoảng trắng
            String cleanPhoneNumber = phoneNumber.replaceAll("[\\(\\)\\s-]", "");

            // Biểu thức chính quy cho các số điện thoại di động và cố định Việt Nam
            String regex = "^(0[3|5|7|8|9|10|11|12|13|14|15|16|17|18|19][0-9]{8}|(024|028|029|022|023|025|026|027|021)\\d{8})$";  
            // Kiểm tra:
            // - Số di động: bắt đầu bằng 0 và có đầu số từ 03, 05, 07, 08, 09, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 theo sau 8 chữ số
            // - Số cố định: bắt đầu bằng (024), (028), (029), (022), (023), (025), (026), (027), (021), theo sau 7 chữ số

            return cleanPhoneNumber.matches(regex);
}
        //hàm update
    public boolean updateNhaCungCap(NhaCungCapDTO nhaCC){
        int result = NhaCungCapDAO.getInstance().update(nhaCC);
        if(result > 0){
            listNhaCungCap.clear();
            listNhaCungCap.addAll(getNhaCungCapAll()); // Cập nhật danh sách mới nhất từ DB
            return true;
        }
        return false;
        
    }
    
     public boolean isThongTinNhaCungCapTrung(String diachi,String sdt,String email,int maNhaCungCap) {
        ArrayList<NhaCungCapDTO> danhSachNhaCungCap = getNhaCungCapAll();
    for (NhaCungCapDTO ncc : danhSachNhaCungCap) {
        if (ncc.getMancc() != maNhaCungCap) { // Chỉ kiểm tra với nhà cung cấp khác
            if (ncc.getDiachincc().equalsIgnoreCase(diachi)) {
                JOptionPane.showMessageDialog(NCCDA, "Địa chỉ này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return true;
            }
            if (ncc.getSdt().equalsIgnoreCase(sdt)) {
                JOptionPane.showMessageDialog(NCCDA, "Số điện thoại này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return true;
            }
            if (ncc.getEmail().equalsIgnoreCase(email)) {
                JOptionPane.showMessageDialog(NCCDA, "Email này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
    }
    return false;
}
     
     public boolean xoaNhaCungCap(int mancc){
         return nccDAO.delete(mancc)>0;
     }

    public int getMaNccByNameNCC(String tenNCC) {
        for (NhaCungCapDTO ncc : listNhaCungCap) {
            if (ncc.getTenncc().equalsIgnoreCase(tenNCC)) {
                return ncc.getMancc();
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

}
