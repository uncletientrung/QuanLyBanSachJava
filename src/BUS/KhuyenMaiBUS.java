/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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
        return listKhuyenMai;
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
        Calendar cal = Calendar.getInstance(); // KHi tạo ra Km mãi nó lưu cả thời gian nữa nên ta nên set thời gian ngày chọn là 0 để nó đỡ bị
                                                                // gặp trường hợp giờ khuyến mãi trước giờ phiếu xuất 
        cal.setTime(currentDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp dateCreate= new Timestamp(cal.getTimeInMillis());
        
        for (KhuyenMaiDTO km : allKM) {
            if (km.getNgayBatDau().compareTo(dateCreate)<=0 && km.getNgayKetThuc().compareTo(dateCreate)>=0) {
                activeKM.add(km);
            }
        }
        return activeKM;
    }
    
    public KhuyenMaiDTO getBestKm(int TongBill, Date dateCreateBill){
        ArrayList<KhuyenMaiDTO> listkm=getActiveKhuyenMai(dateCreateBill);
        
        KhuyenMaiDTO result=null;
        double phanTram=0;
        for(KhuyenMaiDTO km: listkm){
            if(km.getPhanTramGiam()>phanTram && km.getDieuKienToiThieu()<TongBill){
                phanTram=km.getPhanTramGiam();
                result=km;
            }
        }
        return result;    
    }
    
    public KhuyenMaiDTO getKmCTPX(int TongBill,Date dateCreateBill){
        ArrayList<KhuyenMaiDTO> allKM = kmDAO.selectAll();
        KhuyenMaiDTO result=null;
        double phanTram=0;

        LocalDate dateCreate= dateCreateBill.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate();
        for(KhuyenMaiDTO km: allKM){
            LocalDate dateS=((java.sql.Date) km.getNgayBatDau()).toLocalDate();
            LocalDate dateE=((java.sql.Date) km.getNgayKetThuc()).toLocalDate();
            if(km.getPhanTramGiam()>phanTram && km.getDieuKienToiThieu()<TongBill &&
              dateS.compareTo(dateCreate)<=0 && dateE.compareTo(dateCreate)>=0 ){
                phanTram=km.getPhanTramGiam();
                result=km;
            }
        }
        return result;    
    }
    
 



    
}
