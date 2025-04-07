/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuXuatDialog;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import BUS.SachBUS;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuXuatDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PhieuXuatDialogDetail extends JDialog{
    private JTextField  txfMaPhieu,txfNV,txfKhachHang,txfTime,txfTongHD;
    private DefaultTableModel dataCTPX;
    private PhieuXuatBUS pxBUS=new PhieuXuatBUS();
    private ArrayList<ChiTietPhieuXuatDTO> list_ctpx;
    private SachBUS sBUS=new SachBUS();
    private NhanVienBUS nvBUS=new NhanVienBUS();
    private KhachHangBUS khBUS=new KhachHangBUS();
    public PhieuXuatDialogDetail(JFrame parent, PhieuXuatDTO pxDTO) {
        super(parent, "Danh mục  xem chi tiết", true);

        // ========== Panel chính ==========
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // ========== PHẦN TIÊU ĐỀ ==========
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(72, 118, 255)); // Màu xanh dương
        titlePanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        JLabel titleLabel = new JLabel("THÔNG TIN PHIẾU XUẤT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // ========== PHẦN THÔNG TIN ==========
        JPanel infoPanel = new JPanel(new GridLayout(2, 5, 10, 10)); // 2 hàng, 5 cột
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Dòng 1: Label
        infoPanel.add(createLabel("Mã phiếu"));
        infoPanel.add(createLabel("Nhân viên thực hiện"));
        infoPanel.add(createLabel("Khách hàng"));
        infoPanel.add(createLabel("Thời gian tạo"));
        infoPanel.add(createLabel("Tổng hóa đơn"));

        // Dòng 2: TextField  
        txfMaPhieu=createTextField(pxDTO.getMaphieu()+"");
        txfNV=createTextField(nvBUS.getHoTenNVById(pxDTO.getManv()));
        txfKhachHang=createTextField(khBUS.getFullNameKHById(pxDTO.getMakh()));
        txfTime=createTextField(pxDTO.getThoigiantao()+"");
        txfTongHD=createTextField(pxDTO.getTongTien()+"");
        infoPanel.add(txfMaPhieu);
        infoPanel.add(txfNV);
        infoPanel.add(txfKhachHang);
        infoPanel.add(txfTime);
        infoPanel.add(txfTongHD);

        // ========== BẢNG CHI TIẾT ==========
        list_ctpx=pxBUS.getListCTPXById(pxDTO.getMaphieu());
        String[] columnNames = {"STT", "Mã sách", "Tên sách", "Đơn giá", "Số lượng", "Thành tiền"};
        dataCTPX =new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa tất cả các ô
            }
        };
        
        JTable tableCTPX = new JTable(dataCTPX);
        for (int i=0;i<list_ctpx.size();i++){
            String STT=String.valueOf(i+1);
            String maSach=String.valueOf(list_ctpx.get(i).getMasach());
            String tenSach=sBUS.getSachById(list_ctpx.get(i).getMasach()).getTensach();
            String donGia=String.valueOf(list_ctpx.get(i).getDongia());
            String soLuong=String.valueOf(list_ctpx.get(i).getSoluong());
            String thanhTien=String.valueOf(list_ctpx.get(i).getSoluong()*list_ctpx.get(i).getDongia());
            dataCTPX.addRow(new Object[]{STT,maSach,tenSach,donGia,soLuong,thanhTien});
        }
        tableCTPX.setRowHeight(30);
        tableCTPX.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableCTPX.getColumnModel().getColumn(1).setPreferredWidth(30);
        tableCTPX.getColumnModel().getColumn(2).setPreferredWidth(250);
        tableCTPX.getColumnModel().getColumn(3).setPreferredWidth(80);
        tableCTPX.getColumnModel().getColumn(4).setPreferredWidth(50);
        tableCTPX.getColumnModel().getColumn(5).setPreferredWidth(80);
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter1 = {0 , 1,2,3,4,5}; // Căn giữa tất cả trừ tên sách và tên nbx
        for (int col : columnsToCenter1) {
            tableCTPX.getColumnModel().getColumn(col).setCellRenderer(centerRenderer1);
        }
        tableCTPX.getTableHeader().setReorderingAllowed(false); // Ngăn di chuyển giữa các cột
        tableCTPX.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tableCTPX.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen
  
        JScrollPane tableScrollPane = new JScrollPane(tableCTPX);
        tableScrollPane.setViewportView(tableCTPX);

        // ========== NÚT BẤM ==========
        JPanel buttonPanel = new JPanel();
        JButton exportButton = createButton("Xuất file Excel", new Color(76, 175, 80));  // Màu xanh dương
        JButton cancelButton = createButton("Đóng", new Color(244, 67, 54));   // Màu đỏ

        buttonPanel.add(exportButton);
        buttonPanel.add(cancelButton);

        // ========== GỘP BẢNG VÀ NÚT BẤM ==========
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        // ========== GỘP THÔNG TIN VÀ BẢNG ==========
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(infoPanel, BorderLayout.NORTH);
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        // ========== Thêm vào mainPanel ==========
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // ========== Thêm Action ==========
        ActionListener action=new PhieuXuatDialogDetail_Controller(this);
        cancelButton.addActionListener(action);

        add(mainPanel);
        setSize(900, 500);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    // Hàm tạo Label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    // Hàm tạo TextField
    private JTextField createTextField(String value) {
        JTextField textField = new JTextField(value);
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        return textField;
    }

    // Hàm tạo Button với hiệu ứng và màu sắc tùy chỉnh
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Xác định màu nền dựa trên trạng thái của button
                Color actualBgColor = bgColor;
                if (getModel().isPressed()) {
                    actualBgColor = bgColor.darker();
                } else if (getModel().isRollover()) {
                    actualBgColor = bgColor.brighter();
                }

                g2.setColor(actualBgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Không vẽ border mặc định
            }
        };

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));  // Kích thước nút

        return button;
    }

    
    public DefaultTableModel getDataCTPX() {
        return dataCTPX;
    }

    
    
}
