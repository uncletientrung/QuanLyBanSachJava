///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package GUI;
//
//import GUI.Controller.WorkFrameController;
//import GUI.View.*;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//
///**
// *
// * @author DELL
// */
//public class WorkFrame extends JFrame{
//    public CardLayout cardLayout=new CardLayout();
//    public JPanel PanelCard=new JPanel(cardLayout);
//    public WorkFrame(){
//        this.init();
//        this.setVisible(true);
//    }
//    public void init(){
//        setTitle("Quản lý bán sách");
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//       // Main panel
//        JPanel mainPanel = new JPanel(new BorderLayout());
//
//        // Sidebar panel
//        JPanel sidebar = new JPanel();
//        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout để sắp xếp theo chiều dọc
//        sidebar.setPreferredSize(new Dimension(250, getHeight()));
//        sidebar.setBackground(new Color(230, 240, 250));
//
//        // Panel chứa avatar và thông tin người dùng
//        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Sử dụng FlowLayout để căn trái
//        userInfoPanel.setBackground(new Color(230, 240, 250)); // Màu nền giống sidebar
//
//        // Avatar
//        JLabel avatarLabel = new JLabel(new ImageIcon(getClass().getResource("/GUI/Image/avatar-default-icon.png")));
//                                                                             // Thay thế bằng đường dẫn hình ảnh thực tế
//        avatarLabel.setPreferredSize(new Dimension(80, 80)); // Đặt kích thước cho avatar
//
//        // Panel chứa tên và vai trò
//        JPanel textPanel = new JPanel();
//        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // Sắp xếp theo chiều dọc
//        textPanel.setBackground(new Color(230, 240, 250)); // Màu nền giống sidebar
//
//        // Tên và vai trò
//        JLabel nameLabel = new JLabel("Nhân viên");
//        nameLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Đặt font chữ
//        JLabel roleLabel = new JLabel("Quản lý kho");
//        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Đặt font chữ
//
//        // Thêm tên và vai trò vào textPanel
//        textPanel.add(nameLabel);
//        textPanel.add(roleLabel);
//
//        // Thêm avatar và textPanel vào userInfoPanel
//        userInfoPanel.add(avatarLabel);
//        userInfoPanel.add(textPanel);
//
//        // Thêm userInfoPanel vào sidebar
//        sidebar.add(Box.createVerticalStrut(20)); // Khoảng cách phía trên
//        sidebar.add(userInfoPanel);                   // Thêm phần thông tin người dùng
//        sidebar.add(Box.createVerticalStrut(20)); // Khoảng cách giữa thông tin người dùng và menu
//
//        // Menu items (viết từng mục riêng lẻ)
//        JButton btnTrangChu = createMenuButton("Trang chủ");
//        JButton btnSach = createMenuButton("Sách");
//        JButton btnPhieuXuat = createMenuButton("Phiếu xuất");
//        JButton btnPhieuNhap = createMenuButton("Phiếu nhập");
//        JButton btnKhachHang = createMenuButton("Khách hàng");
//        JButton btnNhanVien = createMenuButton("Nhân viên");
//        JButton btnThongTinChung = createMenuButton("Thông tin chung");
//        JButton btnTaiKhoan = createMenuButton("Tài khoản");
//        JButton btnThongKe = createMenuButton("Thống kê");
//        JButton btnPhanQuyen = createMenuButton("Phân quyền");
//        JButton btnKhuyenMai = createMenuButton("Khuyến mãi");
//        JButton btnDangXuat = createMenuButton("Đăng xuất");
//        JButton btnDong = createMenuButton("Đóng");
//                
//        // Thêm các nút vào sidebar
//        sidebar.add(btnTrangChu);
//        sidebar.add(Box.createVerticalStrut(5)); // Khoảng cách giữa các nút
//        sidebar.add(btnSach);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnPhieuXuat);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnPhieuNhap);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnKhachHang);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnNhanVien);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnThongTinChung);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnTaiKhoan);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnThongKe);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnPhanQuyen);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnKhuyenMai);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnDangXuat);
//        sidebar.add(Box.createVerticalStrut(5));
//        sidebar.add(btnDong);
//
//        // Thêm các Panel hay các Card vào PanelCard 
//        PanelCard.add(new HomePanel(),"Trang chủ");
//        PanelCard.add(new BookPanel(),"Sách");
//        PanelCard.add(new NhanVienPanel(),"Nhân viên");
//        PanelCard.add(new PhanQuyenPanel(),"Phân quyền");
//        PanelCard.add(new ThongTinChungPanel(this),"Thông tin chung");
//        PanelCard.add(new PhieuXuatPanel(),"Phiếu xuất");
//        PanelCard.add(new TaiKhoanPanel(),"Tài khoản");
//        PanelCard.add(new KhachhangPanel(),"Khách hàng");
//        
//
//        // Adding sidebar và centerPanel vào main panel
//        mainPanel.add(sidebar, BorderLayout.WEST);
//        mainPanel.add(PanelCard, BorderLayout.CENTER);
//
//        // Adding main panel to frame
//        add(mainPanel);
//        
//        ActionListener action= new WorkFrameController(this);
//        btnSach.addActionListener(action);
//        btnTrangChu.addActionListener(action);
//        btnNhanVien.addActionListener(action);
//        btnPhanQuyen.addActionListener(action);
//        btnDong.addActionListener(action);
//        btnThongTinChung.addActionListener(action);
//        btnPhieuXuat.addActionListener(action);
//        btnTaiKhoan.addActionListener(action);
//        btnKhachHang.addActionListener(action);
//    }
//
//    // Phương thức tạo nút menu với kích thước và căn chỉnh phù hợp
//   private JButton createMenuButton(String text) {
//        JButton button = new JButton(text);
//        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa các nút
//        button.setPreferredSize(new Dimension(200, 50)); // Đặt kích thước width và height
//        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//        button.setFont(new Font("Arial", Font.PLAIN, 14));
//        button.setBackground(new Color(240, 240, 240));
//        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Tạo padding
//        button.setFocusPainted(false);// Bỏ viền khi click
//        button.setBorderPainted(false);
//
//        // Hover effect
//        button.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button.setBackground(new Color(200, 220, 240));
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button.setBackground(new Color(240, 240, 240));
//            }
//        });
//
//        return button;
//    }
//}
//


package GUI;

import DTO.TaiKhoanDTO;
import GUI.Controller.WorkFrameController;
import GUI.View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author DELL
 */
public class WorkFrame extends JFrame {
    public CardLayout cardLayout = new CardLayout();
    public JPanel PanelCard = new JPanel(cardLayout);
    private TaiKhoanDTO taiKhoan;
    private JButton btnTaiKhoan, btnPhanQuyen, btnThongKe, btnNhanVien;

    public WorkFrame(TaiKhoanDTO taiKhoan) {
        this.taiKhoan = taiKhoan;
        this.init();
        this.setVisible(true);
    }

    public void init() {
        setTitle("Quản lý bán sách");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Sidebar panel
        JPanel sidebar = new JPanel(new GridBagLayout());
        sidebar.setPreferredSize(new Dimension(250, getHeight()));
        sidebar.setBackground(new Color(230, 240, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;

        // Panel chứa avatar và thông tin người dùng
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        userInfoPanel.setBackground(new Color(230, 240, 250));

        // Avatar
        JLabel avatarLabel = new JLabel(new ImageIcon(getClass().getResource("/GUI/Image/avatar-default-icon.png")));
        avatarLabel.setPreferredSize(new Dimension(80, 80));

        // Panel chứa tên và vai trò
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(230, 240, 250));
        textPanel.setPreferredSize(new Dimension(150, 80));
        textPanel.setMaximumSize(new Dimension(150, 80));

        // Tên và vai trò
        JLabel nameLabel = new JLabel(taiKhoan.getUsername());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel roleLabel = new JLabel(taiKhoan.getManhomquyen() == 1 ? "Quản lý" : "Nhân viên");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Căn giữa tên và vai trò theo chiều dọc với avatar
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        textPanel.add(Box.createVerticalGlue()); // Thêm khoảng trống phía trên
        textPanel.add(nameLabel);
        textPanel.add(roleLabel);
        textPanel.add(Box.createVerticalGlue()); // Thêm khoảng trống phía dưới

        // Thêm avatar và textPanel vào userInfoPanel
        userInfoPanel.add(avatarLabel);
        userInfoPanel.add(textPanel);

        // Thêm userInfoPanel vào sidebar
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        sidebar.add(userInfoPanel, gbc);

        // Menu items
        JButton btnTrangChu = createMenuButton("Trang chủ");
        JButton btnSach = createMenuButton("Sách");
        JButton btnPhieuXuat = createMenuButton("Phiếu xuất");
        JButton btnPhieuNhap = createMenuButton("Phiếu nhập");
        JButton btnKhachHang = createMenuButton("Khách hàng");
        btnNhanVien = createMenuButton("Nhân viên");
        JButton btnThongTinChung = createMenuButton("Thông tin chung");
        btnTaiKhoan = createMenuButton("Tài khoản");
        btnThongKe = createMenuButton("Thống kê");
        btnPhanQuyen = createMenuButton("Phân quyền");
        JButton btnKhuyenMai = createMenuButton("Khuyến mãi");
        JButton btnDangXuat = createMenuButton("Đăng xuất");
        JButton btnDong = createMenuButton("Đóng");

        // Thêm các nút vào sidebar với GridBagLayout
        gbc.insets = new Insets(3, 0, 3, 0);
        gbc.gridy = 1;
        gbc.ipady = 6;
        sidebar.add(btnTrangChu, gbc);

        gbc.gridy = 2;
        gbc.ipady = 6;
        sidebar.add(btnSach, gbc);

        gbc.gridy = 3;
        gbc.ipady = 6;
        sidebar.add(btnPhieuXuat, gbc);

        gbc.gridy = 4;
        gbc.ipady = 6;
        sidebar.add(btnPhieuNhap, gbc);

        gbc.gridy = 5;
        gbc.ipady = 6;
        sidebar.add(btnKhachHang, gbc);

        gbc.gridy = 6;
        gbc.ipady = 6;
        sidebar.add(btnNhanVien, gbc);

        gbc.gridy = 7;
        gbc.ipady = 6;
        sidebar.add(btnThongTinChung, gbc);

        gbc.gridy = 8;
        gbc.ipady = 6;
        sidebar.add(btnTaiKhoan, gbc);

        gbc.gridy = 9;
        gbc.ipady = 6;
        sidebar.add(btnThongKe, gbc);

        gbc.gridy = 10;
        gbc.ipady = 6;
        sidebar.add(btnPhanQuyen, gbc);

        gbc.gridy = 11;
        gbc.ipady = 6;
        sidebar.add(btnKhuyenMai, gbc);

        gbc.gridy = 12;
        gbc.ipady = 6;
        sidebar.add(btnDangXuat, gbc);

        gbc.gridy = 13;
        gbc.ipady = 6;
        sidebar.add(btnDong, gbc);

        // Thêm một thành phần "dãn" (glue) ở dưới cùng để đẩy các thành phần lên trên
        gbc.gridy = 14;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        sidebar.add(new JPanel(), gbc);

        // Thêm các Panel hay các Card vào PanelCard 
        PanelCard.add(new HomePanel(), "Trang chủ");
        PanelCard.add(new BookPanel(), "Sách");
        PanelCard.add(new NhanVienPanel(), "Nhân viên");
        PanelCard.add(new PhanQuyenPanel(), "Phân quyền");
        PanelCard.add(new ThongTinChungPanel(this), "Thông tin chung");
        PanelCard.add(new PhieuXuatPanel(), "Phiếu xuất");
        PanelCard.add(new PhieuNhapPanel(), "Phiếu nhập");
        PanelCard.add(new TaiKhoanPanel(), "Tài khoản");
        PanelCard.add(new KhachhangPanel(), "Khách hàng");

        // Adding sidebar và centerPanel vào main panel
        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(PanelCard, BorderLayout.CENTER);

        // Adding main panel to frame
        add(mainPanel);

        ActionListener action = new WorkFrameController(this);
        btnSach.addActionListener(action);
        btnTrangChu.addActionListener(action);
        btnNhanVien.addActionListener(action);
        btnPhanQuyen.addActionListener(action);
        btnDong.addActionListener(action);
        btnThongTinChung.addActionListener(action);
        btnPhieuXuat.addActionListener(action);
        btnPhieuNhap.addActionListener(action);
        btnTaiKhoan.addActionListener(action);
        btnKhachHang.addActionListener(action);

        applyPermission();
    }

    private void applyPermission() {
        if (taiKhoan == null) return;

        int quyen = taiKhoan.getManhomquyen(); // 1 = quản lý, 2 = nhân viên

        if (quyen == 2) { // nhân viên bị giới hạn quyền
            btnTaiKhoan.setVisible(false);
            btnPhanQuyen.setVisible(false);
            btnThongKe.setVisible(false);
            btnNhanVien.setVisible(false);
        }
    }

    // Phương thức tạo nút menu với kích thước và căn chỉnh phù hợp
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(220, 80)); // 
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Tăng chiều cao 
        button.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ 
        button.setBackground(new Color(240, 240, 240));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(200, 220, 240));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(240, 240, 240));
            }
        });

        return button;
    }
}

