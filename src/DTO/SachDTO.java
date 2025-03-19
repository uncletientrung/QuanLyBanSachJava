package DTO;
import java.util.Objects;

public class SachDTO {
    private int masach;
    private String tensach;
    private int manxb;
    private int matacgia;
    private int matheloai;
    private int soluongton;
    private int namxuatban;
    private double dongia;

    public SachDTO() {}

    public SachDTO(int masach, String tensach, int manxb, int matacgia, int matheloai, int soluongton, int namxuatban, double dongia) {
        this.masach = masach;
        this.tensach = tensach;
        this.manxb = manxb;
        this.matheloai = matheloai;
        this.soluongton = soluongton;
        this.namxuatban = namxuatban;
        this.matacgia = matacgia;
        this.dongia = dongia;
    }

    public int getMasach() {
        return masach;
    }

    public String getTensach() {
        return tensach;
    }

    public int getManxb() {
        return manxb;
    }

    public double getDongia() {
        return dongia;
    }

    public int getMatacgia() {
        return matacgia;
    }

    public int getMatheloai() {
        return matheloai;
    }

    public int getNamxuatban() {
        return namxuatban;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public void setManxb(int manxb) {
        this.manxb = manxb;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public void setMatacgia(int matacgia) {
        this.matacgia = matacgia;
    }

    public void setMatheloai(int matheloai) {
        this.matheloai = matheloai;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    @Override
    public int hashCode() {
        final int prime = 57;
        int result = 3;
        result = prime * result + Objects.hashCode(masach);
        result = prime * result + Objects.hashCode(tensach);
        result = prime * result + Objects.hashCode(matacgia);
        result = prime * result + Objects.hashCode(manxb);
        result = prime * result + Objects.hashCode(matheloai);
        result = prime * result + this.soluongton;
        result = prime * result + this.namxuatban;
        result = prime * result + Double.hashCode(dongia);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SachDTO other = (SachDTO) obj;
        return this.masach == other.masach &&
               Objects.equals(this.tensach, other.tensach) &&
               this.manxb == other.manxb &&
               this.matacgia == other.matacgia &&
               this.matheloai == other.matheloai &&
               this.soluongton == other.soluongton &&
               Double.compare(this.dongia, other.dongia) == 0 &&
               this.namxuatban == other.namxuatban;
    }

    @Override
    public String toString() {
        return "SachDTO { masach=" + masach + ", tensach=" + tensach + ", matacgia=" + matacgia + ", manxb=" + manxb +
               ", namxuatban=" + namxuatban + ", matheloai=" + matheloai + ", soluongton=" + soluongton + ", dongia=" + dongia + "}";
    }
}
