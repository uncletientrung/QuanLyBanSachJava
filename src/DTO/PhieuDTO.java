package DTO;

import java.sql.Timestamp;
import java.util.Objects;

public class PhieuDTO {
    private int maphieu;
    private int manv;
    private Timestamp thoigiantao;
    private long tongtien;
    private int trangthai;

    public PhieuDTO() {
    }

    public PhieuDTO(int maphieu, int manv, Timestamp thoigiantao, long tongtien, int trangthai) {
        this.maphieu = maphieu;
        this.manv = manv;
        this.thoigiantao = thoigiantao;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public Timestamp getThoigiantao() {
        return thoigiantao;
    }

    public void setThoigiantao(Timestamp thoigiantao) {
        this.thoigiantao = thoigiantao;
    }

    public long getTongTien() {
        return tongtien;
    }

    public void setTongTien(long tongTien) {
        this.tongtien = tongTien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maphieu, manv, thoigiantao, tongtien, trangthai);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PhieuDTO other = (PhieuDTO) obj;
        return this.maphieu == other.maphieu &&
               this.manv==other.manv &&
               this.tongtien == other.tongtien &&
               this.trangthai == other.trangthai &&
               Objects.equals(this.thoigiantao, other.thoigiantao);
    }

    @Override
    public String toString() {
        return "PhieuDTO{" +
               "maphieu=" + maphieu +
               ", manv='" + manv + '\'' +
               ", thoigiantao=" + thoigiantao +
               ", tongTien=" + tongtien +
               ", trangthai=" + trangthai +
               '}';
    }
}
