//
//package GUI.View;
//import GUI.Controller.ChucNangConst;
////import GUI.Dialog.ThongKeDialog.KhachHangDialog;
////import GUI.Dialog.ThongKeDialog.NhanVienDialog;
////import GUI.Dialog.ThongKeDialog.SachDialog;
//import GUI.ThongKe.ThongKe;
//
//import java.awt.*;
//import java.util.List;
//import javax.swing.*;
//
//public class ThongKepanel extends JPanel {
//    private JFrame parent;
//    private List<Integer> quyenDuocCap;
//
//    public ThongKepanel(JFrame parent, List<Integer> quyenDuocCap) {
//        this.parent = parent;
//        this.quyenDuocCap = quyenDuocCap;
//
//        // Tiêu đề
//        JPanel pn_tieuDe = new JPanel(new BorderLayout());
//        pn_tieuDe.setBackground(Color.decode("#B4CDCD"));
//        pn_tieuDe.setPreferredSize(new Dimension(100, 135));
//
//        JLabel lb_tieude = new JLabel("Thống Kê", JLabel.CENTER);
//        lb_tieude.setFont(new Font("Arial", Font.BOLD, 40));
//        lb_tieude.setForeground(new Color(0x333333));
//        pn_tieuDe.add(lb_tieude, BorderLayout.CENTER);
//
//        // Panel chứa nút
//        JPanel menu = new JPanel(new GridLayout(2, 2, 20, 20));
//        menu.setBorder(BorderFactory.createEmptyBorder(40, 50, 50, 50));
//
//        CustomButton btn_nhanvien = new CustomButton("Nhân Viên", "nxb.png", new Color(173, 216, 230));
//        CustomButton btn_khachhang = new CustomButton("Khách Hàng", "nhacungcap.png", new Color(255, 228, 181));
//        CustomButton btn_sach = new CustomButton("Sách", "theloai.png", new Color(152, 251, 152));
//        CustomButton btn_theLoai = new CustomButton("chưa", "theloai.png", new Color(255, 182, 193));
//
//        menu.add(btn_nhanvien);
//        menu.add(btn_khachhang);
//        menu.add(btn_sach);
//        menu.add(btn_theLoai);
//
//        // Gán quyền và hành động
//        ganQuyenChoNut(btn_nhanvien, ChucNangConst.NHAN_VIEN, () -> {
//            new NhanVienDialog(parent).setVisible(true);
//        });
//
//        ganQuyenChoNut(btn_khachhang, ChucNangConst.KHACH_HANG, () -> {
//            new KhachHangDialog(parent).setVisible(true);
//        });
//
//        ganQuyenChoNut(btn_sach, ChucNangConst.KHACH_HANG, () -> {
//            new SachDialog(parent).setVisible(true);
//        });
//
//        ganQuyenChoNut(btn_theLoai, ChucNangConst.THE_LOAI, () -> {
//            //new TheLoaiDialog(parent).setVisible(true);
//        });
//
//        // Layout chính
//        setLayout(new BorderLayout());
//        add(pn_tieuDe, BorderLayout.NORTH);
//        add(menu, BorderLayout.CENTER);
//    }
//
//    private boolean coQuyen(int machucnang) {
//        return quyenDuocCap.contains(machucnang);
//    }
//
//    private void ganQuyenChoNut(CustomButton button, int chucNang, Runnable action) {
//        button.addActionListener(e -> {
//            if (coQuyen(chucNang)) {
//                action.run();
//            } else {
//                JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập chức năng này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
//            }
//        });
//    }
//}
