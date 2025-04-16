/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class KhuyenMaiBUS {
    private final ArrayList<KhuyenMaiDTO> listKhuyenMai;
    private final KhuyenMaiDAO kmDAO;
    
    public KhuyenMaiBUS(){
        kmDAO= KhuyenMaiDAO.getInstance();
        listKhuyenMai=kmDAO.selectAll();
    }
    
    public ArrayList<KhuyenMaiDTO> getAllKhuyenMai(){
        return KhuyenMaiDAO.getInstance().selectAll();
    }
    
    public ArrayList<KhuyenMaiDTO> timkiem(String keywords) {
        ArrayList<KhuyenMaiDTO> ketqua = new ArrayList<>();
        for (KhuyenMaiDTO q : getAllKhuyenMai()) {
            boolean timtheoma = false;
            try {
                int makm = Integer.parseInt(keywords);
                timtheoma = (makm == q.getMaKM());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma || q.getTenChuongTrinh().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(q);
            }
        }
        return ketqua;
    }
    
    public boolean themKhuyenMai(String tenChuongTrinh, String ngayBatDau, String ngayKetThuc,
                                 double dieuKienToiThieu, double phanTramGiam) {

      for(KhuyenMaiDTO km : getAllKhuyenMai()){
          if(km.getTenChuongTrinh().equalsIgnoreCase(tenChuongTrinh)){
              return false;
          }
      }

         try {
        // Chuyển String thành java.sql.Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilNgayBatDau = sdf.parse(ngayBatDau);
                java.util.Date utilNgayKetThuc = sdf.parse(ngayKetThuc);

                Date sqlNgayBatDau = new Date(utilNgayBatDau.getTime());
                Date sqlNgayKetThuc = new Date(utilNgayKetThuc.getTime());

                KhuyenMaiDTO dto = new KhuyenMaiDTO(0, tenChuongTrinh, sqlNgayBatDau, sqlNgayKetThuc, dieuKienToiThieu, phanTramGiam);

                return KhuyenMaiDAO.getInstance().insert(dto) > 0;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
       
        
    }
    
     // Cập nhật khuyến mãi
    public boolean updateKhuyenMai(KhuyenMaiDTO km) {
        int result = KhuyenMaiDAO.getInstance().update(km);
        if (result > 0) {
            listKhuyenMai.clear();
            listKhuyenMai.addAll(getAllKhuyenMai()); // Cập nhật danh sách mới nhất
            return true;
        }
        return false;
    }

    // Xóa khuyến mãi
    public boolean deleteKhuyenMai(int maKhuyenMai) {
        int result = KhuyenMaiDAO.getInstance().delete(maKhuyenMai);
        if (result > 0) {
            listKhuyenMai.removeIf(km -> km.getMaKM()== maKhuyenMai);
            return true;
        }
        return false;
    }

    // Kiểm tra khuyến mãi trùng lặp (theo tên khuyến mãi)
    public boolean isThongTinKhuyenMaiTrung(String tenKhuyenMai, int makm) {
        for (KhuyenMaiDTO km : getAllKhuyenMai()) {
            if (km.getTenChuongTrinh().equalsIgnoreCase(tenKhuyenMai) && km.getMaKM()!=makm) {
                JOptionPane.showMessageDialog(null, "Tên khuyến mãi đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<KhuyenMaiDTO> getActiveKhuyenMai(Date currentDate) {
        ArrayList<KhuyenMaiDTO> allKM = kmDAO.selectAll();
        ArrayList<KhuyenMaiDTO> activeKM = new ArrayList<>();
        for (KhuyenMaiDTO km : allKM) {
            if (!currentDate.before(km.getNgayBatDau()) && !currentDate.after(km.getNgayKetThuc())) {
                activeKM.add(km);
            }
        }
        return activeKM;
    }
    
 



    
}
