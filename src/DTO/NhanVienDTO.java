package DTO;
import java.util.Date;
import java.util.Objects;

public class NhanVienDTO {
    private int manv;
    private String honv;
    private String tennv;
    private String gioitinh;
    private String sdt;
    private int trangthai;
    private Date ngaysinh;

    public NhanVienDTO() {}

    public NhanVienDTO(int manv, String honv, String tennv, String gioitinh, String sdt, int trangthai, Date ngaysinh) {
        this.manv = manv;
        this.honv = honv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.trangthai = trangthai;
        this.ngaysinh = ngaysinh;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getHonv() {
        return honv;
    }

    public void setHonv(String honv) {
        this.honv = honv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manv, honv, tennv, gioitinh, sdt, trangthai, ngaysinh);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final NhanVienDTO other = (NhanVienDTO) obj;
        return this.manv == other.manv &&
               this.trangthai == other.trangthai &&
               Objects.equals(this.honv, other.honv) &&
               Objects.equals(this.tennv, other.tennv) &&
               Objects.equals(this.gioitinh, other.gioitinh) &&
               Objects.equals(this.sdt, other.sdt) &&
               Objects.equals(this.ngaysinh, other.ngaysinh);
    }

    @Override
    public String toString() {
        return "NhanVienDTO{ " + "manv=" + manv + ", hoten=" + honv + " " + tennv + ", gioitinh=" + gioitinh + ", ngaysinh=" + ngaysinh + ", SDT=" + sdt + ", trangthai=" + trangthai + " }";
    }
}
