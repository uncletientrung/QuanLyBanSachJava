package BUS;

import DAO.PhanQuyenDAOo;
import DTO.NhomQuyenDTO;
import java.util.ArrayList;
import java.util.List;

public class PhanQuyenBUS {
    private final ArrayList<NhomQuyenDTO> listNhomQuyen;
    private final PhanQuyenDAOo pqDAO;

    public PhanQuyenBUS(){
        pqDAO = PhanQuyenDAOo.getInstance();
        listNhomQuyen = pqDAO.selectAll();
    }

    public ArrayList<NhomQuyenDTO> getNhomQuyenAll(){
//        return listNhomQuyen;
          return PhanQuyenDAOo.getInstance().selectAll();
    }

    public ArrayList<NhomQuyenDTO> timkiem(String keywords) {
        ArrayList<NhomQuyenDTO> ketqua = new ArrayList<>();
        for (NhomQuyenDTO q : getNhomQuyenAll()) {
            boolean timtheoma = false;
            try {
                int manhomquyen = Integer.parseInt(keywords);
                timtheoma = (manhomquyen == q.getManhomquyen());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma || q.getTennhomquyen().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(q);
            }
        }
        return ketqua;
    }


    
    public boolean themNhomQuyen(String tenNhomQuyen) {
        ArrayList<NhomQuyenDTO> danhSachNhomQuyen = getNhomQuyenAll();

        for (NhomQuyenDTO quyen : danhSachNhomQuyen) {
            if (quyen.getTennhomquyen().equalsIgnoreCase(tenNhomQuyen)) {
                System.out.println("Nhóm quyền đã tồn tại! Không thêm.");
                return false; // Ngăn chặn thêm trùng
            }
        }

            return PhanQuyenDAOo.getInstance().insert(new NhomQuyenDTO(0, tenNhomQuyen)) > 0;
}
    //hàm update
    public boolean updateNhomQuyen(NhomQuyenDTO nhomQuyen){
        int result = PhanQuyenDAOo.getInstance().update(nhomQuyen);
        if(result > 0){
            listNhomQuyen.clear();
            listNhomQuyen.addAll(getNhomQuyenAll()); // Cập nhật danh sách mới nhất từ DB
            return true;
        }
        return false;
        
    }
    //hàm kiểm tra coi khi sửa có lỡ sửa cùng tên hay không
    public boolean isTenNhomQuyenTrung(String tenNhomQuyen, int maNhomQuyen) {
        ArrayList<NhomQuyenDTO> danhSachNhomQuyen = getNhomQuyenAll();
    for (NhomQuyenDTO nq : danhSachNhomQuyen) {
        if (nq.getTennhomquyen().equalsIgnoreCase(tenNhomQuyen) && nq.getManhomquyen() != maNhomQuyen) {
            return true; // Đã tồn tại nhóm quyền khác có cùng tên
        }
    }
    return false;
}
    
    
    public boolean xoaNhomQuyen(int maNhomQuyen) {
        return pqDAO.delete(maNhomQuyen) > 0;
}

 



}
