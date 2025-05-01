package DTO.ThongKe;

import BUS.NhaXuatBanBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeTonKhoDTO {

    private String masach;
    private String tensach;
    private String manxb;
    private String matacgia;
    private String matheloai;
    private int soluongton;
    private String namxuatban;
    private int dongia;
    private int stt;

    public ThongKeTonKhoDTO() {
    }

    public ThongKeTonKhoDTO(String masach, String tensach, int manxb, int matacgia, int matheloai, int soluongton, String namxuatban, int dongia,int stt) {
        this.masach = masach;
        this.tensach = tensach;
        this.manxb =new NhaXuatBanBUS().getNXBById(manxb).getTennxb();       
        this.matacgia = new TacGiaBUS().getTGById(matacgia).getHotentacgia();      
        this.matheloai = new TheLoaiBUS().getTlbyId(matheloai).getTentheloai();      
        this.soluongton = soluongton;
        this.namxuatban = namxuatban;
        this.dongia = dongia;
        this.stt=stt;
    }

    public String getMasach() {
        return masach;
    }

    public String getTensach() {
        return tensach;
    }

    public String getManxb() {
        return manxb;
    }

    public String getMatacgia() {
        return matacgia;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public String getNamxuatban() {
        return namxuatban;
    }

    public int getDongia() {
        return dongia;
    }

    public int getStt() {
        return stt;
    }

   

   
    
}
