
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietPhieuXuatDAO;
import DAO.PhieuNhapDAO;
import DAO.PhieuXuatDAO;
import DAO.SachDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.KhachHangDTO;
import DTO.NhaCungCapDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import DTO.SachDTO;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeNhaCungCapDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
import DTO.ThongKe.ThongKeTonKhoDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import GUI.ThongKe.ThongKeTonKho;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;




public class ThongKeBUS {
    
    public static ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat(){
        ArrayList<ThongKeTungNgayTrongThangDTO> temp=new ArrayList<>();
        ArrayList<PhieuNhapDTO> pn=PhieuNhapDAO.getInstance().selectAll();
        ArrayList<PhieuXuatDTO> px=PhieuXuatDAO.getInstance().selectAll();
        LocalDate now=LocalDate.now();
        LocalDate pass = now.minusDays(7);
        for (LocalDate i = pass; i.isBefore(now) || i.isEqual(now); i = i.plusDays(1)){
            int tiennhap=0,doanhthu=0,loinhuan=0;
            for(PhieuNhapDTO j : pn){
                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.isEqual(i)){
                    tiennhap+=j.getTongTien();
                }
            }
            for(PhieuXuatDTO j : px){
                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.isEqual(i)){
                    doanhthu+=j.getTongTien();
                    
                }
            }
            Date a= Date.from(i.atStartOfDay(ZoneId.systemDefault()).toInstant());
            temp.add(new ThongKeTungNgayTrongThangDTO(a,tiennhap,doanhthu,loinhuan));
        }
        return temp;
    }
   
    public static ArrayList<ThongKeTonKhoDTO> getTonKho(){
        ArrayList<ThongKeTonKhoDTO> temp=new ArrayList<>();
        ArrayList<SachDTO> s=new SachDAO().selectAll();
        int stt=1;
        for(SachDTO i : s){
            if(i.getSoluongton()>0){
                temp.add(new ThongKeTonKhoDTO(i.getMasach(),i.getTensach(),i.getManxb(),i.getMatacgia(),i.getMatheloai(),i.getSoluongton(),i.getNamxuatban(),i.getDongia(),stt));
                stt+=1;
            }
        }
        return temp;
    }
    
    public static ArrayList<ThongKeNhaCungCapDTO> getThongKeNhaCungCap(LocalDate a,LocalDate b){
        ArrayList<ThongKeNhaCungCapDTO> temp=new ArrayList<>();
        ArrayList<PhieuNhapDTO> pn=PhieuNhapDAO.getInstance().selectAll();
        
        for(NhaCungCapDTO j : new NhaCungCapBUS().getNhaCungCapAll()){
            int tien=0,sl=0;
            for(PhieuNhapDTO i : pn){
                LocalDate date = i.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.equals(b)||date.equals(a)||(date.isAfter(a)&&date.isBefore(b))){
                    if(i.getMancc()==j.getMancc()){
                        tien+=i.getTongTien();
                        for(ChiTietPhieuNhapDTO k : new ChiTietPhieuNhapDAO().selectAll(String.valueOf(i.getMaphieu()))){
                            sl+=k.getSoluong();
                        }
                    }
                }
            }
            temp.add(new ThongKeNhaCungCapDTO(j.getMancc(),j.getTenncc(),sl,tien));
        }
        return temp;
    }
    
    public static ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(LocalDate a,LocalDate b){
        ArrayList<ThongKeKhachHangDTO> temp=new ArrayList<>();
        for(KhachHangDTO i : new KhachHangBUS().getKhachHangAll()){
            int stt=1;
            int hd=0,sl=0;
            long tien=0;
            for(PhieuXuatDTO j : PhieuXuatDAO.getInstance().selectAll()){
                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.equals(b)||date.equals(a)||(date.isAfter(a)&&date.isBefore(b))){
                    if(i.getMakh()==j.getMakh()){
                        hd++;
                        tien+=j.getTongTien();
                        for(ChiTietPhieuXuatDTO k : ChiTietPhieuXuatDAO.getInstance().selectAll(String.valueOf(j.getMaphieu()))){
                            sl+=k.getSoluong();
                        }
                    }
                }
            }
            temp.add(new ThongKeKhachHangDTO(i.getMakh(),i.getHokh()+" "+i.getTenkh(),hd,tien,sl,stt));
            stt++;
        }
        return temp;
    }
    
    public static ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam){
        ArrayList<ThongKeTheoThangDTO> temp=new ArrayList<>();
        LocalDate start=LocalDate.of(nam, 1, 1);
        LocalDate end=LocalDate.of(nam, 12, 31);
        
        for (LocalDate i = start; i.isBefore(end) || i.isEqual(end); i = i.plusMonths(1)){
            int tiennhap=0,doanhthu=0,loinhuan=0;
            LocalDate lastDayOfMonth = i.plusMonths(1).minusDays(1);
            for(PhieuNhapDTO j : PhieuNhapDAO.getInstance().selectAll()){
                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.isEqual(i)||date.isEqual(lastDayOfMonth)||(date.isBefore(lastDayOfMonth)&&date.isAfter(i))){
                    tiennhap+=j.getTongTien();
                }
            }
            for(PhieuXuatDTO j : PhieuXuatDAO.getInstance().selectAll()){
               LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.isEqual(i)||date.isEqual(lastDayOfMonth)||(date.isBefore(lastDayOfMonth)&&date.isAfter(i))){
                    doanhthu+=j.getTongTien();
                    
                }
            }
            
            temp.add(new ThongKeTheoThangDTO(i.getMonthValue(),tiennhap,doanhthu,loinhuan));
        }
        return temp;
    }
    
    public static ArrayList<ThongKeDoanhThuDTO> getThongKeTheoNam(int nam1,int nam2){
        ArrayList<ThongKeDoanhThuDTO> temp=new ArrayList<>();
        for(int i=nam1;i<=nam2;i++){
            long tiennhap=0,doanhthu=0,loinhuan=0;
            for(ThongKeTheoThangDTO j : getThongKeTheoThang(i)){
                tiennhap+=j.getChiphi();
                doanhthu+=j.getDoanhthu();
                loinhuan+=j.getLoinhuan();
            }
            temp.add(new ThongKeDoanhThuDTO(i,tiennhap,doanhthu,loinhuan));
        
        }
        return temp;
    }
}
