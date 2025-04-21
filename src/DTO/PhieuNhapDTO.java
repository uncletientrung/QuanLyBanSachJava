package DTO;

import java.sql.Timestamp;

public class PhieuNhapDTO extends PhieuDTO {
    private int mancc;
    
    public PhieuNhapDTO(){
    }
    
    public PhieuNhapDTO(int maphieu, int manv, int mancc, Timestamp thoigiantao, long tongTien, int trangthai) {
        super(maphieu, manv, thoigiantao, tongTien, trangthai);
        this.mancc = mancc;
    }
    
    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.mancc;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PhieuNhapDTO other = (PhieuNhapDTO) obj;
        return this.mancc == other.mancc;
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" +
                "maphieu=" + getMaphieu() +
                ", manv=" + getManv() +
                ", mancc=" + getMancc() +
                ", thoigiantao=" + getThoigiantao() +
                ", tongTien=" + getTongTien() +
                ", trangthai=" + getTrangthai() +
                '}';
    }

    public String getThoiGian() {
        return String.valueOf(getThoigiantao());
    }
    
}
