package BUS;

import DAO.ChiTietQuyenDAO;
import DAO.PhanQuyenDAOo;
import DTO.ChiTietQuyenDTO;
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

 
   public boolean themNhomQuyen(String tenNhomQuyen, List<Integer> danhSachChucNang) {
            ArrayList<NhomQuyenDTO> danhSachNhomQuyen = getNhomQuyenAll();

            for (NhomQuyenDTO quyen : danhSachNhomQuyen) {
                if (quyen.getTennhomquyen().equalsIgnoreCase(tenNhomQuyen)) {
                    System.out.println("Nhóm quyền đã tồn tại! Không thêm.");
                    return false;
                }
            }

            int maNhomMoi = PhanQuyenDAOo.getInstance().getNextAvailableMaNhomQuyen();
            NhomQuyenDTO nhomMoi = new NhomQuyenDTO(maNhomMoi, tenNhomQuyen);

            boolean themNhomQuyen = pqDAO.insert(nhomMoi) > 0;
            if (!themNhomQuyen) return false;

            for (int maCN : danhSachChucNang) {
                ChiTietQuyenDTO ct = new ChiTietQuyenDTO();
                ct.setManhomquyen(maNhomMoi);
                ct.setMachucnang(maCN);
                ct.setHanhdong("CRUD"); // nếu cần set thủ công
                ChiTietQuyenDAO.getInstance().insert(ct);
            }

            return true;
}

    public int themNhomQuyenVaLayMa(String tenNhomQuyen) {
        ArrayList<NhomQuyenDTO> danhSach = getNhomQuyenAll();

        for (NhomQuyenDTO nq : danhSach) {
            if (nq.getTennhomquyen().equalsIgnoreCase(tenNhomQuyen)) {
                return -1; // đã tồn tại
            }
        }


        int maMoi = layMaNhomQuyenMoi(); // bạn cần có hàm này
        NhomQuyenDTO nhom = new NhomQuyenDTO(maMoi, tenNhomQuyen);
        boolean success = pqDAO.insert(nhom) > 0;
        return success ? maMoi : -1;
}
    
    public void themChiTietQuyen(int maNhom, int maCN) {
        ChiTietQuyenDTO ct = new ChiTietQuyenDTO();
        ct.setManhomquyen(maNhom);
        ct.setMachucnang(maCN);
        ct.setHanhdong("CRUD");
        ChiTietQuyenDAO.getInstance().insert(ct);
}
   public void updateChiTietQuyen(int maNhomQuyen, List<Integer> dsChucNangMoi) {
    ChiTietQuyenDAO dao = new ChiTietQuyenDAO();
    dao.deleteByMaNhom(maNhomQuyen);
    for (int maCN : dsChucNangMoi) {
        ChiTietQuyenDTO ct = new ChiTietQuyenDTO(maNhomQuyen, maCN, "ALL");
        dao.insert(ct);
    }
}





    public int layMaNhomQuyenMoi() {
        ArrayList<NhomQuyenDTO> danhSach = getNhomQuyenAll();
        int ma = 1;
        while (true) {
            boolean trung = false;
            for (NhomQuyenDTO nq : danhSach) {
                if (nq.getManhomquyen() == ma) {
                    trung = true;
                    break;
                }
            }
            if (!trung) return ma;
            ma++;
        }
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
    
    
//    public boolean xoaNhomQuyen(int maNhomQuyen) {
//        return pqDAO.delete(maNhomQuyen) > 0;
//}
public boolean xoaNhomQuyen(int maNhomQuyen) {
    try {
        // 1. Xoá chi tiết quyền trước
        new ChiTietQuyenDAO().deleteByMaNhom(maNhomQuyen);

        // 2. Xoá nhóm quyền
        int result = new PhanQuyenDAOo().delete(maNhomQuyen);

        return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public  String getTenquyenbyid(int id){
     String result = "";
     for( NhomQuyenDTO nq : listNhomQuyen){
         if(nq.getManhomquyen()==id){
             result=nq.getTennhomquyen();
             return result;
         }
     }
     return result;
 }

public int getIdquyenbyTen(String tenquyen) {
    for (NhomQuyenDTO nq : listNhomQuyen) {
        if (nq.getTennhomquyen().equalsIgnoreCase(tenquyen)) {
            return nq.getManhomquyen();
        }
    }
    return -1; // hoặc throw exception nếu không tìm thấy
}



 



}
