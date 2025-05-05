/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.ChiTietPhieuXuatDAO;
import DAO.PhieuXuatDAO;
import DAO.SachDAO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuXuatDTO;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
/**
 *
 * @author DELL
 */
public class PhieuXuatBUS {
    public static PhieuXuatDAO pxDAO;
    public static SachDAO sDAO;
    private final ChiTietPhieuXuatDAO chiTietPhieuXuatDAO=ChiTietPhieuXuatDAO.getInstance();
    private ArrayList<PhieuXuatDTO> list_px;
    private NhanVienBUS nvBUS=new NhanVienBUS();
    private KhachHangBUS khBUS=new KhachHangBUS();
    
    public PhieuXuatBUS(){
        pxDAO=PhieuXuatDAO.getInstance();
        sDAO=SachDAO.getInstance();
        list_px= pxDAO.selectAll();
    }
    public ArrayList<PhieuXuatDTO> getAll(){
        return list_px;
    }
    public void insert(PhieuXuatDTO px, ArrayList<ChiTietPhieuXuatDTO> List_ctpx){ // Thêm phiếu xuất0
        pxDAO.insert(px);
        chiTietPhieuXuatDAO.insert(List_ctpx);
        for(ChiTietPhieuXuatDTO ctpx: List_ctpx){
            int soluongTru=-(ctpx.getSoluong());
            sDAO.UpdateSoLuong(ctpx.getMasach(), soluongTru);
        }
        list_px.add(px); // Thêm px vào mảng sau khi thêm vào database

    }
    public PhieuXuatDTO getPXById(int mapx){
        PhieuXuatDTO px=new PhieuXuatDTO();
        for (PhieuXuatDTO p:list_px){
            if((p.getMaphieu() == mapx)){
                px=p;
                return  px;
            }
        }
        return px;
    }
    public ArrayList<ChiTietPhieuXuatDTO> getListCTPXById(int mapx){
        ArrayList<ChiTietPhieuXuatDTO> result=chiTietPhieuXuatDAO.selectAll(mapx+"");
        return  result;
    }
    public void delete(PhieuXuatDTO px, ArrayList<ChiTietPhieuXuatDTO> list_ctpx){
        for(ChiTietPhieuXuatDTO ctpx: list_ctpx){
            int soluong=(ctpx.getSoluong());
            sDAO.UpdateSoLuong(ctpx.getMasach(), soluong);
        }
        pxDAO.cancel(px.getMaphieu()+"");
        chiTietPhieuXuatDAO.delete(px.getMaphieu()+"");
        list_px.remove(px);
    }
    public ArrayList<PhieuXuatDTO> Filter(String NameNV,String NameKH,Timestamp dateStart, Timestamp dateEnd, String min, String max ){
        ArrayList<PhieuXuatDTO> result=new ArrayList<>();
        if(NameNV.equals("Tất cả")){ NameNV="";}
        if(NameKH.equals("Tất cả")){ NameKH="";}
        if(min.equals("")){min="0";}
        if(max.equals("")){max="999999999999";}
        long minn=Long.parseLong(min);
        long maxx=Long.parseLong(max);
        for(PhieuXuatDTO px:list_px){
            if(nvBUS.getHoTenNVById(px.getManv()).contains(NameNV) && khBUS.getFullNameKHById(px.getMakh()).contains(NameKH)
                    && dateStart.compareTo(px.getThoigiantao()) <=0 && dateEnd.compareTo(px.getThoigiantao()) >=0
                    && minn<=px.getTongTien() && px.getTongTien()<=maxx){   
                    result.add(px);
            }
            
        }
        return result;
    }
    
//    public int tongtientheoNam(int nam){
//        int result=0;
//        LocalDate dateS=LocalDate.of(nam, 1, 1);
//        LocalDate dateE=LocalDate.of(nam, 12, 31);
//        for (PhieuXuatDTO px: list_px ){
//            LocalDate date= px.getThoigiantao().toLocalDateTime().toLocalDate();
//            if(date.compareTo(dateS)>=0 && date.compareTo(dateE)<=0){
//                result+=px.getTongTien();
//            }
//        }
//        return result;
//    } 
}
    

