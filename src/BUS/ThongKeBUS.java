
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
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
        
        //ThongkehoadontheoNam(2025);
//        thongkehoadontheoquy(2025);
//        getthongketheoquy(2025);
//tongdoanhthutheotungthangtrongnam(2025);
//tongchiphinhaphangtrongtungquy(2025);
//thongkeloilotheothang(2025);
//thongkesosachbantungthang(2025);
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
//        System.out.println(result);
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
//    public static double ThongkehoadontheoNam(int nam){
//        double doanhthu=0;
//        ArrayList<PhieuXuatDTO> list_px = new PhieuXuatDAO().selectAll();
//        
//        for( PhieuXuatDTO px : list_px){
//            LocalDate ngaytaopx= px.getThoigiantao().toLocalDateTime().toLocalDate();
//            if(ngaytaopx.getYear()==nam){
//                doanhthu+=px.getTongTien();
//            }
//        }
//        System.out.printf("tong tien :%.0f",doanhthu);
//        return doanhthu;
//    }
//   
    

//    public static ArrayList<Double> thongkehoadontheoquy(int nam) {
//    ArrayList<Double> result = new ArrayList<>();
//    
//    double quy1 = 0;
//    double quy2 = 0;
//    double quy3 = 0;
//    double quy4 = 0;
//
//    ArrayList<PhieuXuatDTO> list = new PhieuXuatDAO().selectAll();
//
//    for (PhieuXuatDTO px : list) {
//        LocalDate ngaypx = px.getThoigiantao().toLocalDateTime().toLocalDate();
//        if (ngaypx.getYear() == nam) {
//            int thang = ngaypx.getMonthValue();
//            double tien = px.getTongTien();
//
//            if (thang >= 1 && thang <= 3) {
//                quy1 += tien;
//            } else if (thang >= 4 && thang <= 6) {
//                quy2 += tien;
//            } else if (thang >= 7 && thang <= 9) {
//                quy3 += tien;
//            } else if (thang >= 10 && thang <= 12) {
//                quy4 += tien;
//            }
//        }
//    }
//
//    result.add(quy1);
//    result.add(quy2);
//    result.add(quy3);
//    result.add(quy4);
//
//    System.out.println(result);
//    return result;
//}
//    public static ArrayList<Double> tongdoanhthutheotungthangtrongnam(int nam){
//        ArrayList<Double> result = new ArrayList<>();
//        double thang1=0;
//        double thang2=0;
//        double thang3=0;
//        double thang4=0;
//        double thang5=0;
//        double thang6=0;
//        double thang7=0;
//        double thang8=0;
//        double thang9=0;
//        double thang10=0;
//        double thang11=0;
//        double thang12=0;
//        ArrayList<PhieuXuatDTO> list = new PhieuXuatDAO().selectAll();
//        
//        
//        for(PhieuXuatDTO px : list){
//            LocalDate ngaypx =px.getThoigiantao().toLocalDateTime().toLocalDate();//lay ngay
//            int thang = ngaypx.getMonthValue();
//            
//            if(ngaypx.getYear()==nam){
//                switch (ngaypx.getMonthValue()) {
//                    case 1:
//                        thang1+=px.getTongTien();
//                        break;
//                    case 2:
//                        thang2+=px.getTongTien();
//                        break;
//                    case 3:
//                        thang3+=px.getTongTien();
//                        break;
//                    case 4:
//                        thang4+=px.getTongTien();
//                        break;
//                    case 5:
//                        thang5+=px.getTongTien();
//                        break;
//                    case 6:
//                        thang6+=px.getTongTien();
//                        break;
//                    case 7:
//                        thang7+=px.getTongTien();
//                        break;
//                    case 8:
//                        thang8+=px.getTongTien();
//                        break;
//                    case 9:
//                        thang9+=px.getTongTien();
//                        break;
//                    case 10:
//                        thang10+=px.getTongTien();
//                        break;
//                    case 11:
//                        thang11+=px.getTongTien();
//                        break;
//                    case 12:
//                        thang12+=px.getTongTien();
//                        break;
//                    default:
//                        throw new AssertionError();
//                }
//            }
//        }
//        result.add(thang1);
//        result.add(thang2);
//        result.add(thang3);
//        result.add(thang4);
//        result.add(thang5);
//        result.add(thang6);
//        result.add(thang7);
//        result.add(thang8);
//        result.add(thang9);
//        result.add(thang10);
//        result.add(thang11);
//        result.add(thang12);
//        System.out.println(result);
//        return  result;
//        
//        
//    }
    
//    public static ArrayList<Double> tongchiphinhaphangtrongtungquy(int nam){
//        ArrayList<Double> result = new ArrayList<>();
//        double quy1=0;
//        double quy2=0;
//        double quy3=0;
//        double quy4=0;
//        ArrayList<PhieuNhapDTO> list = new PhieuNhapDAO().selectAll();
//        for(PhieuNhapDTO pn : list){
//            LocalDate ngaypn = pn.getThoigiantao().toLocalDateTime().toLocalDate();
//            int thang = ngaypn.getMonthValue();
//            if(ngaypn.getYear()==nam){
//                if(thang >=1 && thang <=3){
//                    quy1+=pn.getTongTien();
//                }
//                if(thang >=4 && thang <=6){
//                    quy2+=pn.getTongTien();
//                }
//                if(thang >=7 && thang <=9){
//                    quy3+=pn.getTongTien();
//                }
//                if(thang >=10 && thang <=12){
//                    quy4+=pn.getTongTien();
//                }
//            }
//        }
//        result.add(quy1);
//        result.add(quy2);
//        result.add(quy3);
//        result.add(quy4);
//        System.out.println(result);
//        return result;
//    }

//    public static ArrayList<Double> thongkeloilotheothang(int nam){
//        ArrayList<Double> result = new ArrayList<>();
//        double thang1=0;
//        double thang2=0;
//        double thang3=0;
//        double thang4=0;
//        double thang5=0;
//        double thang6=0;
//        double thang7=0;
//        double thang8=0;
//        double thang9=0;
//        double thang10=0;
//        double thang11=0;
//        double thang12=0;
//        ArrayList<PhieuNhapDTO> list_pn= new PhieuNhapDAO().selectAll();
//        ArrayList<PhieuXuatDTO> list_px = new PhieuXuatDAO().selectAll();
//        
//        
//        for(PhieuXuatDTO px : list_px){
//            LocalDate ngaypx= px.getThoigiantao().toLocalDateTime().toLocalDate();
//            int thang = ngaypx.getMonthValue();
//            if(ngaypx.getYear()== nam){
//                switch (thang) {
//                    case 1:
//                        thang1+=px.getTongTien();
//                        break;
//                    case 2:
//                        thang2+=px.getTongTien();
//                        break;
//                    case 3:
//                        thang3+=px.getTongTien();
//                        break;
//                    case 4:
//                        thang4+=px.getTongTien();
//                        break;
//                    case 5:
//                        thang5+=px.getTongTien();
//                        break;
//                    case 6:
//                        thang6+=px.getTongTien();
//                        break;
//                    case 7:
//                        thang7+=px.getTongTien();
//                        break;
//                    case 8:
//                        thang8+=px.getTongTien();
//                        break;
//                    case 9:
//                        thang9+=px.getTongTien();
//                        break;
//                    case 10:
//                        thang10+=px.getTongTien();
//                        break;
//                    case 11:
//                        thang11+=px.getTongTien();
//                        break;
//                    case 12:
//                        thang12+=px.getTongTien();
//                        break;
//                    default:
//                        throw new AssertionError();
//                }
//            }
//        }
//        for(PhieuNhapDTO pn : list_pn){
//            LocalDate ngaypn = pn.getThoigiantao().toLocalDateTime().toLocalDate();
//            int thang= ngaypn.getMonthValue();
//            if(ngaypn.getYear()==nam){
//                switch (thang) {
//                    case 1:
//                        thang1-=pn.getTongTien();
//                        break;
//                    case 2:
//                        thang2-=pn.getTongTien();
//                        break;
//                    case 3:
//                        thang3-=pn.getTongTien();
//                        break;
//                    case 4:
//                        thang4-=pn.getTongTien();
//                        break;
//                    case 5:
//                        thang5-=pn.getTongTien();
//                        break;
//                    case 6:
//                        thang6-=pn.getTongTien();
//                        break;
//                    case 7:
//                        thang7-=pn.getTongTien();
//                        break;
//                    case 8:
//                        thang8-=pn.getTongTien();
//                        break;
//                    case 9:
//                        thang9-=pn.getTongTien();
//                        break;
//                    case 10:
//                        thang10-=pn.getTongTien();
//                        break;
//                    case 11:
//                        thang11-=pn.getTongTien();
//                        break;
//                    case 12:
//                        thang12-=pn.getTongTien();
//                        break;
//                    default:
//                        throw new AssertionError();
//                }
//            }
//            
//        }
//        result.add(thang1);
//        result.add(thang2);
//        result.add(thang3);
//        result.add(thang4);
//        result.add(thang5);
//        result.add(thang6);
//        result.add(thang7);
//        result.add(thang8);
//        result.add(thang9);
//        result.add(thang10);
//        result.add(thang11);
//        result.add(thang12);
//        System.out.println(result);
//        return  result;
//    }
//    
//    public static ArrayList<Integer> thongkesosachbantungthang(int nam){
//        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(12, 0));//tao array 12 pt rong co ja tri 0
//        ArrayList<PhieuXuatDTO> list = new PhieuXuatDAO().selectAll();
//        ArrayList<ChiTietPhieuXuatDTO> list_ctpx = new ChiTietPhieuXuatDAO().selectAll2();
//        
//        for(PhieuXuatDTO px : list){
//            LocalDate ngaypx= px.getThoigiantao().toLocalDateTime().toLocalDate();
//            if(ngaypx.getYear()==nam){
//                int thang= ngaypx.getMonthValue()-1;
//                int ma = px.getMaphieu();
//                for(ChiTietPhieuXuatDTO ctpx : list_ctpx){
//                    if(ctpx.getMaphieu()==ma){
//                        result.set(thang, result.get(thang)+ ctpx.getSoluong());
//                    }
//                    
//                }
//            }
//        }
//        System.out.println(result);
//        return result;
//    }
    
//     public static ArrayList<String> thongkesachbanchaynhattheotungquy(int nam){
//        ArrayList<String> result = new ArrayList<>(Collections.nCopies(4, ""));
//        ArrayList<PhieuXuatDTO> list = new PhieuXuatDAO().selectAll();
//        ArrayList<ChiTietPhieuXuatDTO> list_ctpx = new ChiTietPhieuXuatDAO().selectAll2();
//        ArrayList<SachDTO> list_sach = new SachDAO().selectAll();
//        
//        for(PhieuXuatDTO px : list){
//            LocalDate ngaypx=px.getThoigiantao().toLocalDateTime().toLocalDate();
//            
//            if(ngaypx.getYear()==nam){
//                int thang = ngaypx.getMonthValue();
//                int ma= px.getMaphieu();
//                int max=0;
//                if(thang >= 1 && thang <=3){
//                    for(ChiTietPhieuXuatDTO ctpx : list_ctpx){
//                        if(ctpx.getMaphieu()==ma){
//                        
//                            if(ctpx.getSoluong()>max){
//                                max=ctpx.getSoluong();
//                        }
//                            
//                        
//                    }
//                    
//                }
//                }
//                
//            }
//        }
//        
//    }
//    public static ArrayList<String> thongKeSachBanChayNhatTheoTungQuy(int nam) {
//        ArrayList<String> result = new ArrayList<>(Collections.nCopies(4, ""));
//
//        ArrayList<PhieuXuatDTO> listPhieu = new PhieuXuatDAO().selectAll();
//        ArrayList<ChiTietPhieuXuatDTO> listCTPX = new ChiTietPhieuXuatDAO().selectAll2();
//        ArrayList<SachDTO> listSach = new SachDAO().selectAll();
//
//        // Mỗi quý lưu 1 Map<masach, soluong>
//        Map<Integer, Integer>[] mapQuy = new Map[4];
//        for (int i = 0; i < 4; i++) {
//            mapQuy[i] = new HashMap<>();
//        }
//
//        // Tạo map: maphieu -> quý
//        Map<Integer, Integer> phieuToQuy = new HashMap<>();
//        for (PhieuXuatDTO px : listPhieu) {
//            LocalDate ngay = px.getThoigiantao().toLocalDateTime().toLocalDate();
//            if (ngay.getYear() == nam) {
//                int thang = ngay.getMonthValue();
//                int quy = (thang - 1) / 3;
//                phieuToQuy.put(px.getMaphieu(), quy);
//            }
//        }
//
//        // Cộng dồn số lượng theo sách trong từng quý
//        for (ChiTietPhieuXuatDTO ctpx : listCTPX) {
//            Integer quy = phieuToQuy.get(ctpx.getMaphieu());
//            if (quy != null) {
//                int masach = ctpx.getMasach();
//                mapQuy[quy].put(masach, mapQuy[quy].getOrDefault(masach, 0) + ctpx.getSoluong());
//            }
//        }
//
//        // Tìm sách bán chạy nhất trong từng quý
//        for (int i = 0; i < 4; i++) {
//            int maxSoLuong = -1;
//            int maSachMax = -1;
//
//            for (Map.Entry<Integer, Integer> entry : mapQuy[i].entrySet()) {
//                if (entry.getValue() > maxSoLuong) {
//                    maxSoLuong = entry.getValue();
//                    maSachMax = entry.getKey();
//                }
//            }
//
//            if (maSachMax != -1) {
//                for (SachDTO sach : listSach) {
//                    if (sach.getMasach() == maSachMax) {
//                        result.set(i, sach.getTensach() + " (SL: " + maxSoLuong + ")");
//                        break;
//                    }
//                }
//            } else {
//                result.set(i, "Không có dữ liệu");
//            }
//        }
//
//        System.out.println(result);
//        return result;
//    }

}
