package BUS;
import DAO.TheLoaiDAO;
import DTO.TheLoaiDTO;
import java.util.ArrayList;

public class TheLoaiBUS {
    private final ArrayList<TheLoaiDTO> listTheLoai;
    private final TheLoaiDAO tlDAO;

    public TheLoaiBUS() {
        tlDAO = TheLoaiDAO.getInstance();
        listTheLoai = tlDAO.selectAll();
    }

    public ArrayList<TheLoaiDTO> getTheLoaiAll() {
        return TheLoaiDAO.getInstance().selectAll();
    }

    public ArrayList<TheLoaiDTO> timkiem(String keywords) {
        ArrayList<TheLoaiDTO> ketqua = new ArrayList<>();
        for (TheLoaiDTO tl : getTheLoaiAll()) {
            boolean timTheoMa = false;
            try {
                int matl = Integer.parseInt(keywords);
                timTheoMa = (matl == tl.getMatheloai());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timTheoMa || tl.getTentheloai().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(tl);
            }
        }
        return ketqua;
    }
   
    public boolean themTheLoai(String tenTheLoai) {
        for (TheLoaiDTO tl : getTheLoaiAll()) {
            if (tl.getTentheloai().equals(tenTheLoai)) {
                return false;
            }
        }
        return TheLoaiDAO.getInstance().insert(new TheLoaiDTO(0, tenTheLoai)) > 0;
    }
    
    public boolean updateTheLoai(TheLoaiDTO theLoai) {
        int result = TheLoaiDAO.getInstance().update(theLoai);
        if (result > 0) {
            listTheLoai.clear();
            listTheLoai.addAll(getTheLoaiAll()); // Cập nhật danh sách mới nhất từ DB
            return true;
        }
        return false;
    }
    
    public boolean isTenTheLoaiTrung(String tenTheLoai, int maTheLoai) {
        for (TheLoaiDTO tl : getTheLoaiAll()) {
            if (tl.getTentheloai().equalsIgnoreCase(tenTheLoai) && tl.getMatheloai() != maTheLoai) {
                return true; // Đã tồn tại thể loại khác có cùng tên
            }
        }
        return false;
    }
    
    public boolean xoaTheLoai(int maTheLoai) {
        return tlDAO.delete(maTheLoai) > 0;
    }

public TheLoaiDTO getTlbyId(int matheloai){
        TheLoaiDTO result=new TheLoaiDTO();
        for (TheLoaiDTO theloai: listTheLoai){
            if (theloai.getMatheloai() == matheloai){
                result=theloai;
                break;
            }
        }
        return result;
    }
    public TheLoaiDTO getTlByNameTL(String tenTL){
        TheLoaiDTO result=new TheLoaiDTO();
        for(TheLoaiDTO theloai: listTheLoai){
            if(theloai.getTentheloai().equals(tenTL)){
                result=theloai;
                break;
            }
        }
        return result;
    }
    public ArrayList<String> getAllNameTL(){
        ArrayList<String> result=new ArrayList<>();
        for(TheLoaiDTO tl: listTheLoai){
            result.add(tl.getTentheloai());
        }
        return result;
    }
}


