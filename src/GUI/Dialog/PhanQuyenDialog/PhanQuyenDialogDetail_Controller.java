/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhanQuyenDialog;

import DAO.ChiTietQuyenDAO;
import DAO.DanhMucChucNangDAO;
import DAO.TaiKhoanDAO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hi
 */
public class PhanQuyenDialogDetail_Controller implements ActionListener {

    private PhanQuyenDialogDetail PQDD;
    private NhomQuyenDTO nhomQuyen;

    public PhanQuyenDialogDetail_Controller(PhanQuyenDialogDetail PQDD, NhomQuyenDTO nhomQuyen) {
        this.PQDD = PQDD;
        this.nhomQuyen = nhomQuyen;

        hienThiThongTin();
    }

    private void hienThiThongTin() {
        // 1. Set tên nhóm quyền
        PQDD.setTenNhomQuyen(nhomQuyen.getTennhomquyen());

        // 2. Lấy danh sách mã chức năng
        List<Integer> dsMaCN = new ChiTietQuyenDAO().getDanhSachChucNang(nhomQuyen.getManhomquyen());

        // 3. Lấy tên từng chức năng
        DanhMucChucNangDAO chucNangDAO = new DanhMucChucNangDAO();
        List<String> tenChucNangList = new ArrayList<>();
        for (int ma : dsMaCN) {
            String ten = chucNangDAO.getTenChucNangByMa(ma);
            if (ten != null) {
                tenChucNangList.add(ten);
            }
        }

        // 4. Set danh sách chức năng lên giao diện
        PQDD.setDanhSachChucNang(tenChucNangList);

        // 5. Lấy danh sách tài khoản thuộc nhóm quyền
        List<TaiKhoanDTO> dsTaiKhoan = new TaiKhoanDAO().getTaiKhoanTheoMaNhom(nhomQuyen.getManhomquyen());

        // 6. Đổ vào bảng
        PQDD.loadTableTaiKhoan(dsTaiKhoan);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Không xử lý gì ở đây vì chỉ là hiển thị
    }
}

