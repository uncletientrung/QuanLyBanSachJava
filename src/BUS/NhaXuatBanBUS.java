/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaXuatBanDAO;
import DTO.NhaXuatBanDTO;
import GUI.Dialog.NhaXuatBanDialog.NhaXuatBanDialogAdd;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class NhaXuatBanBUS {
    private final ArrayList<NhaXuatBanDTO> listNhaXuatBan;
    private final NhaXuatBanDAO nxbDAO;
    private NhaXuatBanDialogAdd NXBDA;
    
    public NhaXuatBanBUS() {
        nxbDAO = NhaXuatBanDAO.getInstance();
        listNhaXuatBan = nxbDAO.selectAll();
    }
    
    public ArrayList<NhaXuatBanDTO> getNhaXuatBanAll() {
        return NhaXuatBanDAO.getInstance().selectAll();
    }
    
    public ArrayList<NhaXuatBanDTO> timkiem(String keywords) {
        ArrayList<NhaXuatBanDTO> ketqua = new ArrayList<>();
        for (NhaXuatBanDTO nxb : getNhaXuatBanAll()) {
            boolean timtheoma = false;
            try {
                int manxb = Integer.parseInt(keywords);
                timtheoma = (manxb == nxb.getManxb());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma 
                    || nxb.getTennxb().toLowerCase().contains(keywords.toLowerCase())    
                    || nxb.getDiachinxb().toLowerCase().contains(keywords.toLowerCase())
                    || nxb.getEmail().toLowerCase().contains(keywords.toLowerCase())
                    || nxb.getSdt().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(nxb);
            }
        }
        return ketqua;
    }
    
    public boolean themNhaXuatBan(String tenNxb, String diaChi, String sdt, String email) {
        ArrayList<NhaXuatBanDTO> danhSachNhaXuatBan = getNhaXuatBanAll();

        // Kiểm tra định dạng số điện thoại và email
        if (!isValidPhoneNumber(sdt)) {
            JOptionPane.showMessageDialog(NXBDA, "Vui lòng nhập đúng định dạng số điện thoại Việt Nam!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(NXBDA, "Vui lòng nhập đúng định dạng email!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra trùng lặp dữ liệu
        for (NhaXuatBanDTO nxb : danhSachNhaXuatBan) {
            if (nxb.getTennxb().equalsIgnoreCase(tenNxb)) {
                JOptionPane.showMessageDialog(NXBDA, "Tên nhà xuất bản đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (nxb.getDiachinxb().equalsIgnoreCase(diaChi)) {
                JOptionPane.showMessageDialog(NXBDA, "Địa chỉ này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (nxb.getSdt().equalsIgnoreCase(sdt)) {
                JOptionPane.showMessageDialog(NXBDA, "Số điện thoại này đã được sử dụng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (nxb.getEmail().equalsIgnoreCase(email)) {
                JOptionPane.showMessageDialog(NXBDA, "Email này đã được đăng ký!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Thêm vào database
        boolean isInserted = NhaXuatBanDAO.getInstance().insert(new NhaXuatBanDTO(0, tenNxb, diaChi, sdt, email)) > 0;

        if (isInserted) {
            
        } else {
            JOptionPane.showMessageDialog(NXBDA, "Thêm nhà xuất bản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    // Hàm update
    public boolean updateNhaXuatBan(NhaXuatBanDTO nhaXB) {
        int result = NhaXuatBanDAO.getInstance().update(nhaXB);
        if (result > 0) {
            listNhaXuatBan.clear();
            listNhaXuatBan.addAll(getNhaXuatBanAll()); // Cập nhật danh sách mới nhất từ DB
            return true;
        }
        return false;
    }
    
    public boolean isThongTinNhaXuatBanTrung(String diachi, String sdt, String email, int maNhaXuatBan) {
        ArrayList<NhaXuatBanDTO> danhSachNhaXuatBan = getNhaXuatBanAll();
        for (NhaXuatBanDTO nxb : danhSachNhaXuatBan) {
            if (nxb.getManxb() != maNhaXuatBan) { // Chỉ kiểm tra với nhà xuất bản khác
                if (nxb.getDiachinxb().equalsIgnoreCase(diachi)) {
                    JOptionPane.showMessageDialog(NXBDA, "Địa chỉ này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return true;
                }
                if (nxb.getSdt().equalsIgnoreCase(sdt)) {
                    JOptionPane.showMessageDialog(NXBDA, "Số điện thoại này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return true;
                }
                if (nxb.getEmail().equalsIgnoreCase(email)) {
                    JOptionPane.showMessageDialog(NXBDA, "Email này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return true;
                }
            }
        }
        return false;
    }
     
    public boolean xoaNhaXuatBan(int manxb) {
        return nxbDAO.delete(manxb) > 0;
    }

    public NhaXuatBanDTO getNXBById(int manxb){
        NhaXuatBanDTO result=new NhaXuatBanDTO();
        for(NhaXuatBanDTO nxb: listNhaXuatBan){
            if (nxb.getManxb() == manxb){
                result=nxb;
                break;
            }
        }
        return result;
    }
    public NhaXuatBanDTO getNXBByNameNXB(String tenNxb){
        NhaXuatBanDTO result=new NhaXuatBanDTO();
        for(NhaXuatBanDTO nxb: listNhaXuatBan){
            if(nxb.getTennxb().equals(tenNxb) ){
                result=nxb;
                break;
            }
        }
        return result;
    }
    public ArrayList<String> getAllNameNXB(){
        ArrayList<String> result=new ArrayList<>();
        for(NhaXuatBanDTO nxb: listNhaXuatBan){
            result.add(nxb.getTennxb());
        }
        return result;
    }
}
