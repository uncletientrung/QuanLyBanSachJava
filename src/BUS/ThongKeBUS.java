
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
        ArrayList<ThongKeTungNgayTrongThangDTO> result=new ArrayList<>();
        ArrayList<PhieuNhapDTO> pn=PhieuNhapDAO.getInstance().selectAll();
        ArrayList<PhieuXuatDTO> px=PhieuXuatDAO.getInstance().selectAll();
        LocalDate now=LocalDate.now();
        LocalDate pass = now.minusDays(7);
        for (LocalDate i = pass; i.isBefore(now) || i.isEqual(now); i = i.plusDays(1)){
            int tiennhap=0,doanhthu=0;
            for(PhieuNhapDTO j : pn){
//                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date = j.getThoigiantao().toLocalDateTime().toLocalDate(); // Chuyển Timestamp về LocalDate
                if(date.isEqual(i)){
                    tiennhap+=j.getTongTien();
                }
            }
            for(PhieuXuatDTO j : px){
                LocalDate date = j.getThoigiantao().toLocalDateTime().toLocalDate(); // Chuyển Timestamp về LocalDate
                if(date.isEqual(i)){
                    doanhthu+=j.getTongTien();
                    
                }
            }
            int loinhuan=doanhthu-tiennhap;
            Date a= Date.from(i.atStartOfDay(ZoneId.systemDefault()).toInstant());
            result.add(new ThongKeTungNgayTrongThangDTO(a,tiennhap,doanhthu,loinhuan));
        }
        return result;
    }
   
    public static ArrayList<ThongKeTonKhoDTO> getTonKho(){
        ArrayList<ThongKeTonKhoDTO> result=new ArrayList<>();
        ArrayList<SachDTO> s=new SachDAO().selectAll();
        int stt=1;
        for(SachDTO i : s){
            if(i.getSoluongton()>0){
                result.add(new ThongKeTonKhoDTO(i.getMasach(),i.getTensach(),i.getManxb(),i.getMatacgia(),i.getMatheloai(),i.getSoluongton(),i.getNamxuatban(),i.getDongia(),stt));
                stt+=1;
            }
        }
        return result;
    }
    
//    public static ArrayList<ThongKeNhaCungCapDTO> getThongKeNhaCungCap(LocalDate a,LocalDate b){
//        ArrayList<ThongKeNhaCungCapDTO> temp=new ArrayList<>();
//        ArrayList<PhieuNhapDTO> pn=PhieuNhapDAO.getInstance().selectAll();
//        
//        for(NhaCungCapDTO j : new NhaCungCapBUS().getNhaCungCapAll()){
//            int tien=0,sl=0;
//            for(PhieuNhapDTO i : pn){
//                LocalDate date = i.getThoigiantao().toLocalDateTime().toLocalDate();
//                if(date.equals(b)||date.equals(a)||(date.isAfter(a)&&date.isBefore(b))){
//                    if(i.getMancc()==j.getMancc()){
//                        tien+=i.getTongTien();
//                        for(ChiTietPhieuNhapDTO k : new ChiTietPhieuNhapDAO().selectAll(String.valueOf(i.getMaphieu()))){
//                            sl+=k.getSoluong();
//                        }
//                    }
//                }
//            }
//            temp.add(new ThongKeNhaCungCapDTO(j.getMancc(),j.getTenncc(),sl,tien));
//        }
//        return temp;
//    }
    public static ArrayList<ThongKeNhaCungCapDTO> getThongKeNhaCungCap(LocalDate dateS,LocalDate dateE){
        ArrayList<ThongKeNhaCungCapDTO> result=new ArrayList<>();
        ArrayList<PhieuNhapDTO> list_pn=PhieuNhapDAO.getInstance().selectAll();
        ArrayList<NhaCungCapDTO> list_ncc=new NhaCungCapBUS().getNhaCungCapAll();
        for(NhaCungCapDTO ncc: list_ncc){
            int tien=0, soluong=0;
            for(PhieuNhapDTO pn: list_pn){
                LocalDate date=pn.getThoigiantao().toLocalDateTime().toLocalDate();
                if(date.equals(dateS)||date.equals(dateE)||(date.isAfter(dateS)&&date.isBefore(dateE))){
                    if(ncc.getMancc()==pn.getMancc()){
                        tien+=pn.getTongTien();
                        ArrayList<ChiTietPhieuNhapDTO> list_ctpx=new ChiTietPhieuNhapDAO().selectAll(String.valueOf(pn.getMaphieu()));
                        for(ChiTietPhieuNhapDTO ctpx: list_ctpx){
                            soluong+=ctpx.getSoluong();
                        }
                    }
                }
            }
            result.add(new ThongKeNhaCungCapDTO(ncc.getMancc(),ncc.getTenncc(),soluong, tien));
        }
        return result;
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
            loinhuan=doanhthu-tiennhap;
            temp.add(new ThongKeTheoThangDTO(i.getMonthValue(),tiennhap,doanhthu,loinhuan));
        }
        return temp;
    }   
    
    public static ArrayList<ThongKeDoanhThuDTO> getThongKeTheoNam(int nam1,int nam2){
        ArrayList<ThongKeDoanhThuDTO> temp=new ArrayList<>();
        for(int i=nam1;i<=nam2;i++){
            long tiennhap=0,doanhthu=0,loinhuan=0;
            ArrayList<ThongKeTheoThangDTO> list_doanhthuOfYear=getThongKeTheoThang(i);
            for(ThongKeTheoThangDTO j : list_doanhthuOfYear){
                tiennhap+=j.getChiphi();
                doanhthu+=j.getDoanhthu();
                loinhuan+=j.getLoinhuan();
            }
            temp.add(new ThongKeDoanhThuDTO(i,tiennhap,doanhthu,loinhuan));
        
        }
        return temp;
    }
    
    public static ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTuNgayDenNgay(Date a,Date b){
        ArrayList<ThongKeTungNgayTrongThangDTO> temp=new ArrayList<>();
        LocalDate from = a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate to = b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for( LocalDate i =from;i.isBefore(to)||i.isEqual(to);i=i.plusDays(1)){
            int tiennhap=0,doanhthu=0,loinhuan=0;
            for(PhieuXuatDTO j : new PhieuXuatBUS().getAll()){
                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.equals(i)){
                    doanhthu+=j.getTongTien();
                }
            }
            for(PhieuNhapDTO j : new PhieuNhapBUS().getAll()){
                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.equals(i)){
                    tiennhap+=j.getTongTien();
                }
            }
            Date date = Date.from(i.atStartOfDay(ZoneId.systemDefault()).toInstant());
            loinhuan=doanhthu-tiennhap;
            temp.add(new ThongKeTungNgayTrongThangDTO(date,tiennhap,doanhthu,loinhuan));
        }
        return temp;
    }
    
    public static ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int t,int n){
        ArrayList<ThongKeTungNgayTrongThangDTO> temp=new ArrayList<>();
        LocalDate from = LocalDate.of(n, t, 1);
        for( LocalDate i =from;i.isBefore(from.plusMonths(1).minusDays(1))||i.isEqual(from.plusMonths(1).minusDays(1)) ;i=i.plusDays(1)){
            int tiennhap=0,doanhthu=0,loinhuan=0;
            for(PhieuXuatDTO j : new PhieuXuatBUS().getAll()){
                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.equals(i)){
                    doanhthu+=j.getTongTien();
                }
            }
            for(PhieuNhapDTO j : new PhieuNhapBUS().getAll()){
                LocalDate date = j.getThoigiantao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(date.equals(i)){
                    tiennhap+=j.getTongTien();
                }
            }
            Date date = Date.from(i.atStartOfDay(ZoneId.systemDefault()).toInstant());
            temp.add(new ThongKeTungNgayTrongThangDTO(date,tiennhap,doanhthu,loinhuan));
        }
        return temp;
    }
//    
//    public static ArrayList<Integer> getthongketheoquy(int nam){
//        ArrayList<Integer> result= new ArrayList<>();
//        int quy1=0,quy2=0,quy3,quy4=0;
//        LocalDate DateS= LocalDate.of(nam, 1, 1);
//        LocalDate DateE=LocalDate.of(nam,12, 31);
//        int loinhuan=0;
//        ArrayList<PhieuXuatDTO> list_px=new PhieuXuatDAO().selectAll();
//        for(LocalDate i=DateS ; i.isBefore(DateE)|| i.equals(DateE); i= i.plusMonths(1) ){
//            LocalDate LastDayofMoth=i.plusMonths(1).minusDays(1);
//            for(PhieuXuatDTO px: list_px){
//                LocalDate datePX=px.getThoigiantao().toLocalDateTime().toLocalDate();
//                if(datePX.compareTo(i) >=0 && datePX.compareTo(LastDayofMoth)<=0){
//                    loinhuan+=px.getTongTien();
//                }
//            }
//            if(LastDayofMoth.getMonthValue()==3){
//                quy1=loinhuan;
//                result.add(quy1);
//                loinhuan=0;
//            }else if(LastDayofMoth.getMonthValue()==6){
//                quy2=loinhuan;
//                result.add(quy2);
//                loinhuan=0;
//            }else if(LastDayofMoth.getMonthValue()==(9)){
//                quy3=loinhuan;
//                result.add(quy3);
//                loinhuan=0;
//            }else if(LastDayofMoth.getMonthValue()==(12)){
//                quy4=loinhuan;
//                result.add(quy4);
//                loinhuan=0;
//            }
//        }
//        return  result;
//    }
    // Thống kê từng này trong năm, tháng
//    public static ArrayList<ThongKeTungNgayTrongThangDTO> abc(int moth, int year){
//        ArrayList<ThongKeTungNgayTrongThangDTO> result= new ArrayList<>();
//        LocalDate dateS=LocalDate.of(year, moth, 1);
//        LocalDate dateE=dateS.plusMonths(1).minusDays(1);
//        ArrayList<PhieuXuatDTO> list_px=new PhieuXuatDAO().selectAll();
//        ArrayList<PhieuNhapDTO> list_pn=new PhieuNhapDAO().selectAll();
//        for(LocalDate i=dateS; i.compareTo(dateE)<=0;i=i.plusDays(1)){
//            long chiphi=0,doanhthu=0,loinhuan=0;
//            for (PhieuXuatDTO px: list_px){
//                LocalDate date_px=px.getThoigiantao().toLocalDateTime().toLocalDate();
//                if(date_px.equals(i)){
//                    doanhthu+=px.getTongTien();
//                }
//            }
//            for(PhieuNhapDTO pn: list_pn){
//                LocalDate date_px=pn.getThoigiantao().toLocalDateTime().toLocalDate();
//                if(date_px.equals(i)){
//                    chiphi+=pn.getTongTien();
//                }
//            }
//            loinhuan=doanhthu-chiphi;
//            Date d=Date.from(i.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            result.add(new ThongKeTungNgayTrongThangDTO(d,(int)chiphi,(int)doanhthu,(int)loinhuan));
//        }
//        return result;
//    }
    
    
}
