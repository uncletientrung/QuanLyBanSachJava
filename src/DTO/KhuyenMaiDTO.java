package DTO;

import java.util.Objects;

public class KhuyenMaiDTO {
    private int maKM;
    private String TenChuongTrinh,
            NgayBatDau, NgayKetThuc;

    public KhuyenMaiDTO() {
    }

    public KhuyenMaiDTO(int maKM, String TenChuongTrinh,
                        String NgayBatDau, String NgayKetThuc) {
        this.maKM = maKM;
        this.TenChuongTrinh = TenChuongTrinh;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
    }

    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public String getTenChuongTrinh() {
        return TenChuongTrinh;
    }

    public void setTenChuongTrinh(String TenChuongTrinh) {
        this.TenChuongTrinh = TenChuongTrinh;
    }



    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKM, TenChuongTrinh, NgayBatDau, NgayKetThuc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final KhuyenMaiDTO other = (KhuyenMaiDTO) obj;
        return this.maKM == other.maKM &&
               Objects.equals(this.TenChuongTrinh, other.TenChuongTrinh) &&
               Objects.equals(this.NgayBatDau, other.NgayBatDau) &&
               Objects.equals(this.NgayKetThuc, other.NgayKetThuc);
    }

    @Override
    public String toString() {
        return "KhuyenMaiDTO{" +
                "maKM=" + maKM +
                ", TenChuongTrinh='" + TenChuongTrinh + '\'' +
                ", NgayBatDau='" + NgayBatDau + '\'' +
                ", NgayKetThuc='" + NgayKetThuc + '\'' +
                '}';
    }
}
