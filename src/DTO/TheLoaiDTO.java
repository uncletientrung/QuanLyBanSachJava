package DTO;
import java.util.Objects;

public class TheLoaiDTO {
    private int matheloai;
    private String tentheloai;

    public TheLoaiDTO() {}

    public TheLoaiDTO(int matheloai, String tentheloai) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
    }

    public int getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(int matheloai) {
        this.matheloai = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matheloai, tentheloai);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TheLoaiDTO other = (TheLoaiDTO) obj;
        return this.matheloai == other.matheloai && Objects.equals(this.tentheloai, other.tentheloai);
    }

    @Override
    public String toString() {
        return "TheLoaiDTO{ matheloai=" + matheloai + ", tentheloai=" + tentheloai + " }";
    }
}
