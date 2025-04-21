package DTO;

import java.util.Objects;

public class ChiTietPhieuXuatDTO {
    private int maphieu;
    private String masach;
    private int soluong, dongia;
    
    public ChiTietPhieuXuatDTO() {}

    public ChiTietPhieuXuatDTO(int maphieu, String masach, int soluong, int dongia) {
        this.maphieu = maphieu;
        this.masach = masach;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getDongia() {
        return dongia;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public String getMasach() {
        return masach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maphieu, masach, soluong, dongia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChiTietPhieuXuatDTO other = (ChiTietPhieuXuatDTO) obj;
        return maphieu == other.maphieu &&
               masach == other.masach &&
               soluong == other.soluong &&
               dongia == other.dongia;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuXuatDTO{" +
               "maphieu=" + maphieu +  
               ", masach=" + masach +
               ", soluong=" + soluong +
               ", dongia=" + dongia +
               '}';
    }
}
