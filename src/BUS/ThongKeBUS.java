/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.ChiTietPhieuXuatDTO;
import DTO.KhachHangDTO;
import DTO.PhieuXuatDTO;
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
}
