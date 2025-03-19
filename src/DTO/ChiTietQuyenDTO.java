package DTO;

import java.util.Objects;

public class ChiTietQuyenDTO {
    private int manhomquyen;
    private int machucnang;
    private String hanhdong;

    public ChiTietQuyenDTO() {
    }

    public ChiTietQuyenDTO(int manhomquyen, int machucnang, String hanhdong) {
        this.manhomquyen = manhomquyen;
        this.machucnang = machucnang;
        this.hanhdong = hanhdong;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(int machucnang) {
        this.machucnang = machucnang;
    }

    public String getHanhdong() {
        return hanhdong;
    }

    public void setHanhdong(String hanhdong) {
        this.hanhdong = hanhdong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manhomquyen, machucnang, hanhdong);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietQuyenDTO other = (ChiTietQuyenDTO) obj;
        return this.manhomquyen == other.manhomquyen &&
               this.machucnang == other.machucnang &&
               Objects.equals(this.hanhdong, other.hanhdong);
    }

    @Override
    public String toString() {
        return "ChiTietQuyenDTO{" + 
               "manhomquyen=" + manhomquyen + 
               ", machucnang=" + machucnang + 
               ", hanhdong='" + hanhdong + '\'' + 
               '}';
    }
}
