package DTO;
import java.sql.Timestamp;

public class PhieuXuatDTO extends PhieuDTO {
    private int makh;

    public PhieuXuatDTO(int makh) {
        this.makh = makh;
    }

    public PhieuXuatDTO(int makh, int maphieu, int manv, Timestamp thoigiantao, long tongTien, int trangthai) {
        super(maphieu, manv, thoigiantao, tongTien, trangthai);
        this.makh = makh;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.makh;
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
        final PhieuXuatDTO other = (PhieuXuatDTO) obj;
        return this.makh == other.makh;
    }

    @Override
    public String toString() {
        return "PhieuXuatDTO{" + "makh=" + makh + '}' + super.toString();
    }
}
