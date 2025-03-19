package DTO;

import java.util.Objects;

public class DanhMucChucNangDTO {
    private int machucnang;
    private String tenchucnang;

    public DanhMucChucNangDTO() {
    }

    public DanhMucChucNangDTO(int machucnang, String tenchucnang) {
        this.machucnang = machucnang;
        this.tenchucnang = tenchucnang;
    }

    public int getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(int machucnang) {
        this.machucnang = machucnang;
    }

    public String getTenchucnang() {
        return tenchucnang;
    }

    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }

    @Override
    public int hashCode() {
        return Objects.hash(machucnang, tenchucnang);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final DanhMucChucNangDTO other = (DanhMucChucNangDTO) obj;
        return this.machucnang == other.machucnang &&
               Objects.equals(this.tenchucnang, other.tenchucnang);
    }

    @Override
    public String toString() {
        return "DanhMucChucNangDTO{" +
                "machucnang=" + machucnang +
                ", tenchucnang='" + tenchucnang + '\'' +
                '}';
    }
}
