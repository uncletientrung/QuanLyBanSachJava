/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.KhuyenMaiDialog;

import BUS.KhuyenMaiBUS;
import BUS.NhaCungCapBUS;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogAdd;
import GUI.Dialog.ThongTinChungDialog.NhaCungCapDialog;
import GUI.View.KhuyenMaiPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class KhuyenMaiDialogAdd_Controller implements ActionListener{
    private KhuyenMaiDialogAdd KMDA;
    private KhuyenMaiBUS KMBUS = new KhuyenMaiBUS();
    private final KhuyenMaiPanel KMPanel;
    
    public KhuyenMaiDialogAdd_Controller(KhuyenMaiDialogAdd KMDA,KhuyenMaiPanel KMPanel){
        this.KMDA =KMDA;
        this.KMPanel=KMPanel;
    }

   @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Thêm dữ liệu")) {
                String tenct = KMDA.getTenChuongTrinh();
                Date ngaybatdau = KMDA.getNgayBatDau();
                Date ngayketthuc = KMDA.getNgayKetThuc();
                String billtoithieu = KMDA.getHoaDonToiThieu();
                String phantramgiam = KMDA.getPhanTramGiam();

                if (tenct.isEmpty()) {
                    JOptionPane.showMessageDialog(KMDA, "Vui lòng nhập tên chương trình khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (ngaybatdau == null) {
                    JOptionPane.showMessageDialog(KMDA, "Vui lòng chọn ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (ngayketthuc == null) {
                    JOptionPane.showMessageDialog(KMDA, "Vui lòng chọn ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (billtoithieu.isEmpty()) {
                    JOptionPane.showMessageDialog(KMDA, "Vui lòng nhập hóa đơn tối thiểu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (phantramgiam.isEmpty()) {
                    JOptionPane.showMessageDialog(KMDA, "Vui lòng nhập phần trăm giảm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double dkToiThieu = Double.parseDouble(billtoithieu);
                    double ptGiam = Double.parseDouble(phantramgiam);

                    // Format ngày thành yyyy-MM-dd
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    String strNgayBatDau = sdf.format(ngaybatdau);
                    String strNgayKetThuc = sdf.format(ngayketthuc);

                    boolean result = KMBUS.themKhuyenMai(tenct, strNgayBatDau, strNgayKetThuc, dkToiThieu, ptGiam);

                    if (result) {
                        JOptionPane.showMessageDialog(KMDA, "Thêm khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        KMDA.dispose();
                        KMPanel.capNhatBang(KMBUS.getAllKhuyenMai());
                    } else {
                        JOptionPane.showMessageDialog(KMDA, "Tên chương trình đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(KMDA, "Hóa đơn tối thiểu và phần trăm giảm phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(KMDA, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
}

  
    
}
