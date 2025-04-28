
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietPhieuXuatDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.PhieuNhapDAO;
import DAO.PhieuXuatDAO;
import DAO.SachDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import DTO.SachDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;


public class ThongKeBUS {
    
    //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    
    public ArrayList<Long> khtatca(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, 0, 1);
        Date calendar2 = new Date();  
        return khngay(calendar1.getTime(), calendar2);          
    }

    public ArrayList<Long> khngay(Date a,Date b){
        ArrayList<KhachHangDTO> listkh=new KhachHangDAO().selectAll();
        ArrayList<PhieuXuatDTO> listpx=new PhieuXuatDAO().selectAll();
        ArrayList<PhieuNhapDTO> listpn=new PhieuNhapDAO().selectAll();
        ArrayList<Long> thongke=new ArrayList();
        
        for(KhachHangDTO kh : listkh){
            long hd = 0, sl = 0, tien = 0,ln=0;
            String mas;
            for(PhieuXuatDTO px : listpx)
                if(px.getMakh()==kh.getMakh() && px.getThoigiantao().getTime()>=a.getTime() && px.getThoigiantao().getTime()<=b.getTime()){
                    hd++;
                    tien+=px.getTongTien();
                    ArrayList<ChiTietPhieuXuatDTO> listctpx=new ChiTietPhieuXuatDAO().selectAll(String.valueOf(px.getMaphieu()));
                    for(ChiTietPhieuXuatDTO ctpx : listctpx)
                        sl+=ctpx.getSoluong();  
                }
            for(PhieuNhapDTO pn : listpn){
                ArrayList<ChiTietPhieuNhapDTO> listctpn=new ChiTietPhieuNhapDAO().selectAll(String.valueOf(pn.getMaphieu()));
                if(true)
                for(ChiTietPhieuNhapDTO ctpn : listctpn)                  
                        ln=ctpn.getDongia();
            }
                         
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
            thongke.add(tien-ln*sl);
        }
        return thongke;
    }
    
    public ArrayList<Long> khthang(int a,int b,int c,int d){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, a, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, b, 1); 
        return khngay(calendar1.getTime(), calendar2.getTime());
    }
    
    public ArrayList<Long> khquy(int a,int b,int c,int d){
        return khthang(a*3+1 ,b*3+1, c, d);
     }
      
    public ArrayList<Long> khnam(int c,int d){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, 0, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, 0, 1); 
        return khngay(calendar1.getTime(), calendar2.getTime());
    }
     
    //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa      

     
    public ArrayList<Long> nvtatca(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, 0, 1);
        Date calendar2 = new Date();  
        return nvngay(calendar1.getTime(), calendar2);          
    }

    public ArrayList<Long> nvngay(Date a,Date b){
        ArrayList<NhanVienDTO> listkh=new NhanVienDAO().selectAll();
        ArrayList<PhieuXuatDTO> listpx=new PhieuXuatDAO().selectAll();
        ArrayList<PhieuNhapDTO> listpn=new PhieuNhapDAO().selectAll();
        ArrayList<Long> thongke=new ArrayList();
        
        for(NhanVienDTO kh : listkh){
            long hd = 0, sl = 0, tien = 0,ln=0;
            String mas;
            for(PhieuXuatDTO px : listpx)
                if(px.getManv()==kh.getManv() && px.getThoigiantao().getTime()>=a.getTime() && px.getThoigiantao().getTime()<=b.getTime()){
                    hd++;
                    tien+=px.getTongTien();
                    ArrayList<ChiTietPhieuXuatDTO> listctpx=new ChiTietPhieuXuatDAO().selectAll(String.valueOf(px.getMaphieu()));
                    for(ChiTietPhieuXuatDTO ctpx : listctpx)
                        sl+=ctpx.getSoluong();  
                }
            for(PhieuNhapDTO pn : listpn){
                ArrayList<ChiTietPhieuNhapDTO> listctpn=new ChiTietPhieuNhapDAO().selectAll(String.valueOf(pn.getMaphieu()));
                if(true)
                for(ChiTietPhieuNhapDTO ctpn : listctpn)                  
                        ln=ctpn.getDongia();
            }
                         
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
            thongke.add(tien-ln*sl);
        }
        return thongke;
    }
    
    public ArrayList<Long> nvthang(int a,int b,int c,int d){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, a, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, b, 1);    
        return nvngay(calendar1.getTime(), calendar2.getTime());
    }
    
    public ArrayList<Long> nvquy(int a,int b,int c,int d){
        return nvthang(a*3+1 ,b*3+1, c, d);
     }
      
    public ArrayList<Long> nvnam(int c,int d){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, 0, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, 0, 1); 
        return khngay(calendar1.getTime(), calendar2.getTime());
    }
     
    public ArrayList<Long> statca(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, 0, 1);
        Date calendar2 = new Date();  
        return sngay(calendar1.getTime(), calendar2);          
    }

    public ArrayList<Long> sngay(Date a,Date b){
        ArrayList<SachDTO> listkh=new SachDAO().selectAll();
        ArrayList<PhieuXuatDTO> listpx=new PhieuXuatDAO().selectAll();
        ArrayList<PhieuNhapDTO> listpn=new PhieuNhapDAO().selectAll();
        ArrayList<Long> thongke=new ArrayList();
        
        for(SachDTO kh : listkh){
            long hd = 0, sl = 0, tien = 0,ln=0;           
            for(PhieuXuatDTO px : listpx)
                if(px.getThoigiantao().getTime()>=a.getTime() && px.getThoigiantao().getTime()<=b.getTime()){
                    ArrayList<ChiTietPhieuXuatDTO> listctpx=new ChiTietPhieuXuatDAO().selectAll(String.valueOf(px.getMaphieu()));
                    for(ChiTietPhieuXuatDTO ctpx : listctpx)
                        if(kh.getMasach()==ctpx.getMasach()){
                            sl+=ctpx.getSoluong();
                            tien+=ctpx.getDongia();
                        }
                }
            for(PhieuNhapDTO pn : listpn)
                if(pn.getThoigiantao().getTime()>=a.getTime() && pn.getThoigiantao().getTime()<=b.getTime()){
                    ArrayList<ChiTietPhieuNhapDTO> listctpn=new ChiTietPhieuNhapDAO().selectAll(String.valueOf(pn.getMaphieu()));
                    for(ChiTietPhieuNhapDTO ctpn : listctpn)                  
                        ln=ctpn.getDongia();
                }
            
            
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
            thongke.add(tien-ln*sl);
        }
        return thongke;
    }
    
    public ArrayList<Long> sthang(int a,int b,int c,int d){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, a, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, b, 1);    
        return sngay(calendar1.getTime(), calendar2.getTime());
    }
    
    public ArrayList<Long> squy(int a,int b,int c,int d){
        return sthang(a*3+1 ,b*3+1, c, d);
     }
      
    public ArrayList<Long> snam(int c,int d){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, 0, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, 0, 1); 
        return sngay(calendar1.getTime(), calendar2.getTime());
    }
    
    // sap xep
    
    public ArrayList <Object[]> TheoHoTen(ArrayList <Object[]> list,boolean t){
        if(t)  Collections.sort(list, (o1, o2) -> ((String)o1[2]).compareTo((String)o2[2]));
        else   Collections.sort(list, (o1, o2) -> ((String)o2[2]).compareTo((String)o1[2]));
        return list;
    }
    
     public ArrayList <Object[]> TheoMa(ArrayList <Object[]> list,boolean t){
        if(t) Collections.sort(list, (o1, o2) -> Integer.compare((int)o1[1], (int)o2[1]));
        else  Collections.sort(list, (o1, o2) -> Integer.compare((int)o2[1], (int)o1[1]));
        return list;
    }
     
     public ArrayList <Object[]> TheoSTT(ArrayList <Object[]> list,boolean t){
        if(t) Collections.sort(list, (o1, o2) -> Integer.compare((int)o1[0], (int)o2[0]));
        else  Collections.sort(list, (o1, o2) -> Integer.compare((int)o2[0], (int)o1[0]));
        return list;
    }
    
    public ArrayList <Object[]> TheoHoaDon(ArrayList <Object[]> list,boolean t){
        if(t) Collections.sort(list, (o1, o2) -> Long.compare((Long)o1[3], (Long)o2[3]));
        else  Collections.sort(list, (o1, o2) -> Long.compare((Long)o2[3], (Long)o1[3]));
        return list;
    }
         
    public ArrayList <Object[]> TheoSach(ArrayList <Object[]> list,boolean t){
        if(t) Collections.sort(list, (o1, o2) -> Long.compare((Long)o1[4], (Long)o2[4]));
        else  Collections.sort(list, (o1, o2) -> Long.compare((Long)o2[4], (Long)o1[4]));
        return list;
    }
    
    public ArrayList <Object[]> TheoDoanhThu(ArrayList <Object[]> list,boolean t){
        if(t) Collections.sort(list, (o1, o2) -> Long.compare((Long)o1[5], (Long)o2[5]));
        else  Collections.sort(list, (o1, o2) -> Long.compare((Long)o2[5], (Long)o1[5]));
        return list;
    }
    
    public ArrayList <Object[]> TheoLN(ArrayList <Object[]> list,boolean t){
        if(t) Collections.sort(list, (o1, o2) -> Long.compare((Long)o1[6], (Long)o2[6]));
        else  Collections.sort(list, (o1, o2) -> Long.compare((Long)o2[6], (Long)o1[6]));
        return list;
    }
    
}
