/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.KhuyenMaiDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import BUS.KhuyenMaiBUS;
import DTO.KhuyenMaiDTO;
import java.util.Date;
import GUI.View.KhuyenMaiPanel;

public class KhuyenMaiDialogUpdate_Controller implements ActionListener {
    private KhuyenMaiDialogUpdate KMDialogUpdate;
    private KhuyenMaiBUS KMBUS = new KhuyenMaiBUS();
    private final KhuyenMaiPanel KMPanel;

    public KhuyenMaiDialogUpdate_Controller(KhuyenMaiDialogUpdate KMDialogUpdate, KhuyenMaiPanel KMPanel) {
        this.KMDialogUpdate = KMDialogUpdate;
        this.KMPanel = KMPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Lưu thông tin")) {
            String tenChuongTrinhMoi = KMDialogUpdate.getTenChuongTrinh();
            String hoaDonToiThieuMoi = KMDialogUpdate.getHoaDonToiThieu();
            String phanTramGiamMoi = KMDialogUpdate.getPhanTramGiam();
            Date ngayBatDauMoi = KMDialogUpdate.getNgayBatDau();
            Date ngayKetThucMoi = KMDialogUpdate.getNgayKetThuc();

            // Kiểm tra dữ liệu nhập vào
            if (tenChuongTrinhMoi.isEmpty()) {
                JOptionPane.showMessageDialog(KMDialogUpdate, "Vui lòng nhập tên chương trình khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (hoaDonToiThieuMoi.isEmpty()) {
                JOptionPane.showMessageDialog(KMDialogUpdate, "Vui lòng nhập hóa đơn tối thiểu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (phanTramGiamMoi.isEmpty()) {
                JOptionPane.showMessageDialog(KMDialogUpdate, "Vui lòng nhập phần trăm giảm giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ngayBatDauMoi == null || ngayKetThucMoi == null) {
                JOptionPane.showMessageDialog(KMDialogUpdate, "Vui lòng chọn ngày bắt đầu và kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ngayBatDauMoi.after(ngayKetThucMoi)) {
                JOptionPane.showMessageDialog(KMDialogUpdate, "Ngày bắt đầu không thể sau ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy đối tượng chương trình khuyến mãi cần sửa
            KhuyenMaiDTO khuyenMaiCanSua = KMDialogUpdate.getKhuyenMai();

            // Kiểm tra nếu thông tin mới có bị trùng
            if (KMBUS.isThongTinKhuyenMaiTrung(tenChuongTrinhMoi, khuyenMaiCanSua.getMaKM())) {
                JOptionPane.showMessageDialog(KMDialogUpdate, "Chương trình khuyến mãi đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật đối tượng chương trình khuyến mãi
            khuyenMaiCanSua.setTenChuongTrinh(tenChuongTrinhMoi);
            khuyenMaiCanSua.setDieuKienToiThieu(Double.parseDouble(hoaDonToiThieuMoi));
            khuyenMaiCanSua.setPhanTramGiam(Double.parseDouble(phanTramGiamMoi));
            khuyenMaiCanSua.setNgayBatDau(ngayBatDauMoi);
            khuyenMaiCanSua.setNgayKetThuc(ngayKetThucMoi);

            // Gọi BUS để cập nhật vào database
            try {
                Boolean result = KMBUS.updateKhuyenMai(khuyenMaiCanSua);
                if (result) {
                    JOptionPane.showMessageDialog(KMDialogUpdate, "Cập nhật chương trình khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    KMDialogUpdate.dispose();
                    KMPanel.capNhatBang(KMBUS.getAllKhuyenMai()); // Cập nhật lại danh sách khuyến mãi
                } else {
                    JOptionPane.showMessageDialog(KMDialogUpdate, "Cập nhật không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(KMDialogUpdate, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}
