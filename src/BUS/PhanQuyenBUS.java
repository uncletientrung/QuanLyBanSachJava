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

//    public boolean themNhomQuyen(String tenNhom) {
//        if (pqDAO.isTenNhomQuyenExists(tenNhom)) {
//            return false; // Nhóm quyền đã tồn tại
//        }
//        int newID = pqDAO.getAutoIncrement();
//        boolean success = pqDAO.insert(new NhomQuyenDTO(newID, tenNhom)) > 0;
//        if (success) {
//            listNhomQuyen.clear();
//            listNhomQuyen.addAll(pqDAO.selectAll()); // Load lại danh sách từ database
//        }
//        return success;
//    }
    
//    public boolean themNhomQuyen(String tenNhomQuyen) {
//    List<NhomQuyenDTO> danhSachNhomQuyen = getNhomQuyenAll();
//    System.out.println("Danh sách nhóm quyền hiện có:");
//    for (NhomQuyenDTO quyen : danhSachNhomQuyen) {
//        System.out.println(quyen.getTennhomquyen());
//    }
//    
//    for (NhomQuyenDTO quyen : danhSachNhomQuyen) {
//        if (quyen.getTennhomquyen().equalsIgnoreCase(tenNhomQuyen)) {
//            return false; // Nhóm quyền đã tồn tại
//        }
//    }
//    return PhanQuyenDAO.getInstance().insert(tenNhomQuyen);
//}
    
        public boolean themNhomQuyen(String tenNhomQuyen) {
    ArrayList<NhomQuyenDTO> danhSachNhomQuyen = getNhomQuyenAll();
    
    // Debug danh sách trước khi thêm
    System.out.println("Danh sách nhóm quyền hiện có:");
    for (NhomQuyenDTO quyen : danhSachNhomQuyen) {
        System.out.println(quyen.getTennhomquyen());
    }

    for (NhomQuyenDTO quyen : danhSachNhomQuyen) {
        if (quyen.getTennhomquyen().equalsIgnoreCase(tenNhomQuyen)) {
            System.out.println("Nhóm quyền đã tồn tại! Không thêm.");
            return false; // Ngăn chặn thêm trùng
        }
    }

    return PhanQuyenDAOo.getInstance().insert(new NhomQuyenDTO(0, tenNhomQuyen)) > 0;
}


}
