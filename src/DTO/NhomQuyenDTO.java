package DTO;
import java.util.Objects;

public class NhomQuyenDTO {
    private int manhomquyen;
    private String tennhomquyen;

    public NhomQuyenDTO() {
    }

    public NhomQuyenDTO(int manhomquyen, String tennhomquyen) {
        this.manhomquyen = manhomquyen;
        this.tennhomquyen = tennhomquyen;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public String getTennhomquyen() {
        return tennhomquyen;
    }

    public void setTennhomquyen(String tennhomquyen) {
        this.tennhomquyen = tennhomquyen;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manhomquyen, tennhomquyen);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final NhomQuyenDTO other = (NhomQuyenDTO) obj;
        return this.manhomquyen == other.manhomquyen &&
               Objects.equals(this.tennhomquyen, other.tennhomquyen);
    }

    @Override
    public String toString() {
        return "NhomQuyenDTO{" + "manhomquyen=" + manhomquyen + ", tennhomquyen=" + tennhomquyen + '}';
    }
}
