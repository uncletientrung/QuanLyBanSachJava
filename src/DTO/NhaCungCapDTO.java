package DTO;
import java.util.Objects;
public class NhaCungCapDTO {

    private int mancc;
    private String tenncc;
    private String diachincc;
    private String sdt;
    private String email;

    public NhaCungCapDTO(){}
    public NhaCungCapDTO(int mancc,String tenncc,String diachincc, String sdt, String email){
        this.mancc=mancc;
        this.tenncc=tenncc;
        this.diachincc=diachincc;
        this.sdt=sdt;
        this.email=email;
    }
    public int getMancc() {
        return mancc;
    }
    public String getTenncc() {
        return tenncc;
    }
    public String getDiachincc() {
        return diachincc;
    }
    public String getEmail() {
        return email;
    }
    public String getSdt() {
        return sdt;
    }
    public void setMancc(int mancc) {
        this.mancc = mancc;
    }
    public void setDiachincc(String diachincc) {
        this.diachincc = diachincc;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }
    @Override
    public int hashCode() {
        final int prime=53;
        int result=3;
        result=prime*result+mancc;
        result=prime*result+Objects.hashCode(tenncc);
        result=prime*result+Objects.hashCode(sdt);
        result=prime*result+Objects.hashCode(email);
        result=prime*result+Objects.hashCode(diachincc);
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(getClass() !=obj.getClass()){
            return false;
        }
        final NhaCungCapDTO other=(NhaCungCapDTO) obj;
        if(this.mancc!=other.mancc){
            return false;
        }
        if(!Objects.equals(this.tenncc, other.tenncc)){
            return false;
        }
        if(!Objects.equals(this.sdt, other.sdt)){
            return false;
        }
        if(!Objects.equals(this.diachincc, other.diachincc)){
            return false;
        }
        if(!Objects.equals(this.email, other.email)){
            return false;
        }
        return true;
    }
    @Override
    public String toString(){
        return "NhaCungCapDTO{ mancc="+mancc+", tenncc="+tenncc+", sdt="+sdt+", diachincc="+diachincc+", email="+email +"}";
    }
}
