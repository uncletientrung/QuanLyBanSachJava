package DTO;

import java.util.Objects;

public class ChiTietKhuyenMaiDTO {
    private int maKM, masach;
    private double tiLeKM;

    public ChiTietKhuyenMaiDTO() {
    }

    public ChiTietKhuyenMaiDTO(int maKM, int masach, double tiLeKM) {
        this.maKM = maKM;
        this.masach = masach;
        this.tiLeKM = tiLeKM;
    }

    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public int getmasach() {
        return masach;
    }

    public void setmasach(int masach) {
        this.masach = masach;
    }

    public double getTiLeKM() {
        return tiLeKM;
    }

    public void setTiLeKM(double tiLeKM) {
        this.tiLeKM = tiLeKM;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKM, masach, tiLeKM);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietKhuyenMaiDTO other = (ChiTietKhuyenMaiDTO) obj;
        return this.maKM == other.maKM &&
               this.masach == other.masach &&
               Double.compare(this.tiLeKM, other.tiLeKM) == 0;
    }

    @Override
    public String toString() {
        return "ChiTietKhuyenMaiDTO{" +
                "maKM=" + maKM +
                ", masach=" + masach +
                ", tiLeKM=" + tiLeKM +
                '}';
    }

    public static int maSPTangdan(ChiTietKhuyenMaiDTO a, ChiTietKhuyenMaiDTO b) {
        return Integer.compare(a.getmasach(), b.getmasach());
    }
}
