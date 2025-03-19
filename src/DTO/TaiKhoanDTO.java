package DTO;
import java.util.Objects;

public class TaiKhoanDTO {
    private int manv;
    private String username;
    private String matkhau;
    private int manhomquyen;
    private int trangthai;
    public TaiKhoanDTO() {}

    public TaiKhoanDTO(int manv, String username, String matkhau, int manhomquyen, int trangthai) {
        this.manv = manv;
        this.username = username;
        this.matkhau = matkhau;
        this.manhomquyen = manhomquyen;
        this.trangthai = trangthai;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manv, username, matkhau, manhomquyen, trangthai);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TaiKhoanDTO other = (TaiKhoanDTO) obj;
        return this.manv == other.manv &&
               this.manhomquyen == other.manhomquyen &&
               this.trangthai == other.trangthai &&
               Objects.equals(this.username, other.username) &&
               Objects.equals(this.matkhau, other.matkhau);
    }

    @Override
    public String toString() {
        return "TaiKhoanDTO{" + "manv=" + manv + ", username=" + username + ", matkhau=" + matkhau + ", manhomquyen=" + manhomquyen + ", trangthai=" + trangthai + '}';
    }
}
