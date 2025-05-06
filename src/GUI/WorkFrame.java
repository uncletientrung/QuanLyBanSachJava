

package GUI;

import BUS.NhanVienBUS;
import DAO.ChiTietQuyenDAO;
import DAO.PhanQuyenDAOo;
import DTO.TaiKhoanDTO;
import GUI.Controller.ChucNangConst;
import GUI.Controller.WorkFrameController;
import GUI.View.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import GUI.ThongKe.ThongKe;
/**
 *
 * @author DELL
 */
public class WorkFrame extends JFrame {
    public CardLayout cardLayout = new CardLayout();
    public JPanel PanelCard = new JPanel(cardLayout);
    public static TaiKhoanDTO taiKhoan;
    private NhanVienBUS nvBUS=new NhanVienBUS();
    private JButton btnTaiKhoan, btnPhanQuyen, btnThongKe, btnNhanVien,btnPhieuXuat,btnPhieuNhap,btnKhachHang;
    private JButton btnKhuyenMai,btnSach,btnTrangChu,btnThongTinChung;
   private JButton nutDangHoatDong; // Để theo dõi nút đang được chọn
   private ThongKe thongKe;
   private PhieuNhapPanel PNP;
   private PhieuXuatPanel PXP;

    public WorkFrame(TaiKhoanDTO taiKhoan) {
        this.taiKhoan = taiKhoan;
        this.init();
        this.setVisible(true);
        this.nutDangHoatDong = null; // Ban đầu không có nút nào được chọn
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
        JLabel nameLabel = new JLabel(nvBUS.getHoTenNVById(taiKhoan.getManv()));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        String tenNhomQuyen = PhanQuyenDAOo.getInstance().getTenNhomQuyen(taiKhoan.getManhomquyen());
        JLabel roleLabel = new JLabel(tenNhomQuyen);
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
        btnTrangChu = createMenuButton("Trang chủ","home1.png");
        btnSach = createMenuButton("Sách","book1.png");
        btnPhieuXuat = createMenuButton("Phiếu xuất","bill1.png");
        btnPhieuNhap = createMenuButton("Phiếu nhập","nhap1.png");
        btnKhachHang = createMenuButton("Khách hàng","customer1.png");
        btnNhanVien = createMenuButton("Nhân viên","staff1.png");
        btnThongTinChung = createMenuButton("Thông tin chung","ttc1.png");
        btnTaiKhoan = createMenuButton("Tài khoản","account1.png");
        btnThongKe = createMenuButton("Thống kê","analysis1.png");
        btnPhanQuyen = createMenuButton("Phân quyền","phanquyen1.png");
        btnKhuyenMai = createMenuButton("Khuyến mãi","sale1.png");
        JButton btnDangXuat = createMenuButton("Đăng xuất","logout1.png");

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


        // Thêm một thành phần "dãn" (glue) ở dưới cùng để đẩy các thành phần lên trên
        gbc.gridy = 14;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        sidebar.add(new JPanel(), gbc);
        
        // Thêm các Panel hay các Card vào PanelCard 
        thongKe=new ThongKe();
        PNP=new PhieuNhapPanel();
        PXP=new PhieuXuatPanel();
        PanelCard.add(new HomePanel(), "Trang chủ");
        PanelCard.add(new BookPanel(), "Sách");
        PanelCard.add(new NhanVienPanel(), "Nhân viên");
        PanelCard.add(new PhanQuyenPanel(), "Phân quyền");
        PanelCard.add(new ThongTinChungPanel(this,ChiTietQuyenDAO.getInstance().getDanhSachChucNang(taiKhoan.getManhomquyen())), "Thông tin chung");
        PanelCard.add(PXP, "Phiếu xuất");                                           // Truyền tai khaorn vào để hiện tên người dùng
        PanelCard.add(PNP, "Phiếu nhập");
        PanelCard.add(new TaiKhoanPanel(), "Tài khoản");
        PanelCard.add(new KhachhangPanel(), "Khách hàng");
        PanelCard.add(new KhuyenMaiPanel(), "Khuyến mãi");
        PanelCard.add(thongKe, "Thống kê");

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
        btnThongTinChung.addActionListener(action);
        btnPhieuXuat.addActionListener(action);
        btnPhieuNhap.addActionListener(action);
        btnTaiKhoan.addActionListener(action);
        btnKhachHang.addActionListener(action);
        btnKhuyenMai.addActionListener(action);
        btnDangXuat.addActionListener(action);
        btnThongKe.addActionListener(action);
        applyPermission();
    }
      private void applyPermission() {
        if (taiKhoan == null) return;
        List<Integer> chucNangDuocCap = ChiTietQuyenDAO.getInstance().getDanhSachChucNang(taiKhoan.getManhomquyen());
        // Ẩn hết trước
        //trang chu,sach,khuyenmai,dang xuat, dong ai cx tuong tac dc
        btnTaiKhoan.setVisible(false);
        btnPhanQuyen.setVisible(false);
        btnThongKe.setVisible(true);
        btnNhanVien.setVisible(false);  
        btnPhieuXuat.setVisible(false);
        btnPhieuNhap.setVisible(false);
        btnKhachHang.setVisible(false);
        btnThongKe.setVisible(false);


        for (Integer cn : chucNangDuocCap) {
            switch (cn) {
                case ChucNangConst.TAI_KHOAN:
                    btnTaiKhoan.setVisible(true);
                    break;
                case ChucNangConst.PHAN_QUYEN:
                    btnPhanQuyen.setVisible(true);
                    break;
                case ChucNangConst.NHAN_VIEN:
                    btnNhanVien.setVisible(true);
                    break;
                case ChucNangConst.THONG_KE:
                    btnThongKe.setVisible(true);
                    break;
                case ChucNangConst.PHIEU_XUAT:
                    btnPhieuXuat.setVisible(true);
                    break;
                case ChucNangConst.PHIEU_NHAP:
                    btnPhieuNhap.setVisible(true);
                    break;
                case ChucNangConst.KHACH_HANG:
                    btnKhachHang.setVisible(true);
                    break;      
            }
        }
}

    // Phương thức tạo nút menu với kích thước và căn chỉnh phù hợp
   private JButton createMenuButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(220, 40)); // Giảm chiều cao từ 80 xuống 40
        button.setFont(new Font("Arial", Font.BOLD, 20)); 
        button.setBackground(new Color(240, 240, 240));
        // Thêm padding trái 15px để dịch icon và text sang phải
        button.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 0)); // Tăng padding trái từ 0 lên 15
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Tải và điều chỉnh kích thước icon
        if (iconPath != null && !iconPath.isEmpty()) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/GUI/Image/" + iconPath));
            Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Giảm icon từ 40x40 xuống 24x24
            button.setIcon(new ImageIcon(scaledImage));

            // Đặt vị trí icon sát mép trái, text căn giữa
            button.setHorizontalAlignment(SwingConstants.LEFT); // Icon sát mép trái
            button.setHorizontalTextPosition(SwingConstants.RIGHT); // Text bên phải icon
            button.setVerticalTextPosition(SwingConstants.CENTER); // Text căn giữa theo chiều dọc
            button.setIconTextGap(20); // Khoảng cách 10px giữa icon và text
        }

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != nutDangHoatDong) {
                    button.setBackground(new Color(211,211,211));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != nutDangHoatDong) { // Chỉ đặt lại màu nếu không phải nút đang hoạt động
                    button.setBackground(new Color(240, 240, 240));
                }
            }
        });

        return button;
    }

    public JButton getNutDangHoatDong() {
        return nutDangHoatDong;
    }

    public void setNutDangHoatDong(JButton nutDangHoatDong) {
        this.nutDangHoatDong = nutDangHoatDong;
    }
    
   
    public JButton getBtnKhachHang() {
        return btnKhachHang;
    }

    public JButton getBtnThongKe() {
        return btnThongKe;
    }

    public JButton getBtnNhanVien() {
        return btnNhanVien;
    }

    public JButton getBtnTaiKhoan() {
        return btnTaiKhoan;
    }

    public JButton getBtnPhanQuyen() {
        return btnPhanQuyen;
    }

    public JButton getBtnPhieuNhap() {
        return btnPhieuNhap;
    }

    public JButton getBtnPhieuXuat() {
        return btnPhieuXuat;
    }

    public JButton getBtnSach() {
        return btnSach;
    }

    public JButton getBtnTrangChu() {
        return btnTrangChu;
    }

    public JButton getBtnKhuyenMai() {
        return btnKhuyenMai;
    }

    public JButton getBtnThongTinChung() {
        return btnThongTinChung;
    }

    public ThongKe getThongKe() {
        return thongKe;
    }

    public PhieuXuatPanel getPXP() {
        return PXP;
    }

    public PhieuNhapPanel getPNP() {
        return PNP;
    }
    
    
}

