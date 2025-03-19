package DTO;
import java.util.Objects;

public class TacGiaDTO {
    private int matacgia;
    private String hotentacgia;
    
    public TacGiaDTO() {}

    public TacGiaDTO(int matacgia, String hotentacgia) {
        this.matacgia = matacgia;
        this.hotentacgia = hotentacgia;
    }

    public int getMatacgia() {
        return matacgia;
    }

    public String getHotentacgia() {
        return hotentacgia;
    }

    public void setMatacgia(int matacgia) {
        this.matacgia = matacgia;
    }

    public void setHotentacgia(String hotentacgia) {
        this.hotentacgia = hotentacgia;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int result = 1;
        result = prime * result + Integer.hashCode(matacgia);
        result = prime * result + Objects.hashCode(hotentacgia);
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
        final TacGiaDTO other = (TacGiaDTO) obj;
        return this.matacgia == other.matacgia &&
               Objects.equals(this.hotentacgia, other.hotentacgia);
    }

    @Override 
    public String toString() {
        return "TacGiaDTO{ matacgia=" + matacgia + ", hotentacgia=" + hotentacgia + "}";
    }
}
