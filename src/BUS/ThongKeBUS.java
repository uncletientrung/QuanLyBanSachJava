/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.ChiTietPhieuXuatDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.PhieuXuatDTO;
import DTO.SachDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ThongKeBUS {
    
    
    
    
    public ArrayList<Long> khachhangtatca(ArrayList<KhachHangDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx){
        ArrayList<Long> thongke=new ArrayList();
        for(KhachHangDTO kh : listkh){
            long hd=0;
            long sl=0;
            long tien=0;
            for(PhieuXuatDTO px : listpx){
                if(px.getMakh()==kh.getMakh()){
                    hd++;
                    tien+=px.getTongTien();
                    for(ChiTietPhieuXuatDTO ctpx : listctpx){
                        if(ctpx.getMaphieu()==px.getMaphieu()){
                            sl+=ctpx.getSoluong();
                        }
                    }
                }
            }
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
        }
        System.out.println(thongke.size());
        return thongke;
    }
    
      public ArrayList<Long> khachhangngay(ArrayList<KhachHangDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,Date a,Date b){
        ArrayList<Long> thongke=new ArrayList();
        
        for(KhachHangDTO kh : listkh){
            long hd = 0, sl = 0, tien = 0;
            
            for(PhieuXuatDTO px : listpx){
                if(px.getMakh()==kh.getMakh() && px.getThoigiantao().getTime()>=a.getTime() && px.getThoigiantao().getTime()<=b.getTime()){
                    hd++;
                    tien+=px.getTongTien();
                    
                    for(ChiTietPhieuXuatDTO ctpx : listctpx){
                        if(ctpx.getMaphieu()==px.getMaphieu()){
                            sl+=ctpx.getSoluong();
                        }
                    }
                }
            }
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
        }
        System.out.println(thongke.size());
        return thongke;
    }
    
     public ArrayList<Long> khachhangthang(ArrayList<KhachHangDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int a,int b,int c,int d){
        ArrayList<Long> thongke=new ArrayList();
        
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, a-1, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, b-1, 1); 
        
        for(KhachHangDTO kh : listkh){
            long hd = 0, sl = 0, tien = 0;
            
            for(PhieuXuatDTO px : listpx){
                if(px.getMakh()==kh.getMakh() && px.getThoigiantao().getTime()>=calendar1.getTime().getTime() && px.getThoigiantao().getTime()<=calendar2.getTime().getTime() ){
                    hd++;
                    tien+=px.getTongTien();
                    
                    for(ChiTietPhieuXuatDTO ctpx : listctpx){
                        if(ctpx.getMaphieu()==px.getMaphieu()){
                            sl+=ctpx.getSoluong();
                        }
                    }
                }
            }
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
        }
        System.out.println(thongke.size());
        return thongke;
    }
      
     public ArrayList<Long> khachhangnam(ArrayList<KhachHangDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int c,int d){
        ArrayList<Long> thongke=new ArrayList();
              
        for(KhachHangDTO kh : listkh){
            long hd = 0, sl = 0, tien = 0;
            
            for(PhieuXuatDTO px : listpx){
                if(px.getMakh()==kh.getMakh() && px.getThoigiantao().getYear()+1900>=c && px.getThoigiantao().getYear()+1900<=d ){
                    hd++;
                    tien+=px.getTongTien();
                    
                    for(ChiTietPhieuXuatDTO ctpx : listctpx){
                        if(ctpx.getMaphieu()==px.getMaphieu()){
                            sl+=ctpx.getSoluong();
                        }
                    }
                }
            }
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
        }
        System.out.println(thongke.size());
        return thongke;
    }
     
     public ArrayList<Long> khachhangquy(ArrayList<KhachHangDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int c,int d,int a,int b){
        return khachhangthang(listkh, listpx, listctpx ,a*3+1 ,b*3+1, c, d);
              
      
          
    }
     
     public ArrayList<Long> nvngay(ArrayList<NhanVienDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,Date a,Date b){
        ArrayList<Long> thongke=new ArrayList();
        
        for(NhanVienDTO kh : listkh){
            long hd = 0, sl = 0, tien = 0;
            
            for(PhieuXuatDTO px : listpx){
                if(px.getManv()==kh.getManv() && px.getThoigiantao().getTime()>=a.getTime() && px.getThoigiantao().getTime()<=b.getTime()){
                    hd++;
                    tien+=px.getTongTien();
                    
                    for(ChiTietPhieuXuatDTO ctpx : listctpx){
                        if(ctpx.getMaphieu()==px.getMaphieu()){
                            sl+=ctpx.getSoluong();
                        }
                    }
                }
            }
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
        }
        System.out.println(thongke.size());
        return thongke;
    }
    
     public ArrayList<Long> nvthang(ArrayList<NhanVienDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int a,int b,int c,int d){
        
        
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, a-1, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, b-1, 1); 
        
        return nvngay(listkh, listpx, listctpx, calendar1.getTime(), calendar2.getTime());
    }
      
     public ArrayList<Long> nvnam(ArrayList<NhanVienDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int c,int d){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, 0, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, 0, 1); 
              
        
         return nvngay(listkh, listpx, listctpx, calendar1.getTime(), calendar2.getTime());
    }
     
     public ArrayList<Long> nvquy(ArrayList<NhanVienDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int c,int d,int a,int b){
        return nvthang(listkh, listpx, listctpx ,a*3+1 ,b*3+1, c, d);
     }
              
     public ArrayList<Long> nvtatca(ArrayList<NhanVienDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx){
         Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, 0, 1); 
        
        Date calendar2 = new Date();
        
     
         return nvngay(listkh, listpx, listctpx, calendar1.getTime(), calendar2); 
          
    }
     
      public ArrayList<Long> sngay(ArrayList<SachDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,Date a,Date b){
        ArrayList<Long> thongke=new ArrayList();
        
        for(SachDTO kh : listkh){
            long hd = 0, sl = 0, tien = 0;
            

                    
                    for(ChiTietPhieuXuatDTO ctpx : listctpx){
                        if(ctpx.getMasach()==kh.getMasach()){
                            sl+=ctpx.getSoluong();
                            tien+=ctpx.getDongia();
                        }
                    }
                
            
            thongke.add(hd);
            thongke.add(tien);
            thongke.add(sl);
        }
        System.out.println(thongke.size());
        return thongke;
    }
    
     public ArrayList<Long> sthang(ArrayList<SachDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int a,int b,int c,int d){
        
        
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, a-1, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, b-1, 1); 
        
        return sngay(listkh, listpx, listctpx, calendar1.getTime(), calendar2.getTime());
    }
      
     public ArrayList<Long> snam(ArrayList<SachDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int c,int d){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(c, 0, 1); 
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(d, 0, 1); 
              
        
         return sngay(listkh, listpx, listctpx, calendar1.getTime(), calendar2.getTime());
    }
     
     public ArrayList<Long> squy(ArrayList<SachDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx,int c,int d,int a,int b){
        return sthang(listkh, listpx, listctpx ,a*3+1 ,b*3+1, c, d);
     }
              
     public ArrayList<Long> statca(ArrayList<SachDTO> listkh,ArrayList<PhieuXuatDTO> listpx,ArrayList<ChiTietPhieuXuatDTO> listctpx){
         Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, 0, 1); 
        
        Date calendar2 = new Date();
        
     
         return sngay(listkh, listpx, listctpx, calendar1.getTime(), calendar2); 
          
    }
}
