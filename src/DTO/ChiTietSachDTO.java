package DTO;

import java.util.Objects;

public class ChiTietSachDTO {
    private int masach, maphieunhap, maphieuxuat;
    private int tinhtrang;

    public ChiTietSachDTO() {}

    public ChiTietSachDTO(int masach, int maphieunhap, int maphieuxuat, int tinhtrang) {
        this.masach = masach;
        this.maphieunhap = maphieunhap;
        this.maphieuxuat = maphieuxuat;
        this.tinhtrang = tinhtrang;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    @Override
    public int hashCode() {
        return Objects.hash(masach, maphieunhap, maphieuxuat, tinhtrang);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietSachDTO other = (ChiTietSachDTO) obj;
        return this.masach == other.masach &&
               this.maphieunhap == other.maphieunhap &&
               this.maphieuxuat == other.maphieuxuat &&
               this.tinhtrang == other.tinhtrang;
    }

    @Override
    public String toString() {
        return "ChiTietSachDTO{" +
                "masach=" + masach +
                ", maphieunhap=" + maphieunhap +
                ", maphieuxuat=" + maphieuxuat +
                ", tinhtrang=" + tinhtrang +
                '}';
    }
}
