/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuNhapDialog;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SachBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SachDTO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Minnie
 */
public class PhieuNhapDialogDetail extends JDialog{
    private JTextField txfMaPhieu, txfNV, txfNCC, txfTime, txfTongHD;
    private DefaultTableModel dataCTPN;
    private PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    private ArrayList<ChiTietPhieuNhapDTO> list_ctpn;
    private SachBUS sBUS = new SachBUS();
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private JTable tableCTPN;

    public PhieuNhapDialogDetail(JFrame parent, PhieuNhapDTO pnDTO) {
        super(parent,"Danh mục xem chi tiết phiếu nhập",true);
        // Thiết lập layout chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        // ========== PHẦN TIÊU ĐỀ ==========
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(72, 118, 255)); // Màu xanh dương
        titlePanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        JLabel titleLabel = new JLabel("THÔNG TIN PHIẾU NHẬP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
    
        // ========== PHẦN THÔNG TIN ==========
        JPanel infoPanel = new JPanel(new GridLayout(2, 5, 10, 10)); // 2 hàng, 5 cột
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Dòng 1: Label
        infoPanel.add(createLabel("Mã phiếu"));
        infoPanel.add(createLabel("Nhân viên thực hiện"));
        infoPanel.add(createLabel("Nhà cung cấp"));
        infoPanel.add(createLabel("Thời gian tạo"));
        infoPanel.add(createLabel("Tổng hóa đơn"));
        // Dòng 2: TextField
        infoPanel.add(createTextField(pnDTO.getMaphieu()+""));
        infoPanel.add(createTextField(pnDTO.getManv()+""));
        infoPanel.add(createTextField(pnDTO.getMancc()+""));
        infoPanel.add(createTextField(pnDTO.getThoiGian()+""));
        infoPanel.add(createTextField(pnDTO.getTongTien()+""));
        // ========== PHẦN NỘI DUNG ==========
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(600, 300));
        // Tạo bảng
        String[] columnNames = {"Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel dataCTPN = new DefaultTableModel(columnNames, 0);
        JTable tableCTPN = new JTable(dataCTPN) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa ô trong bảng
            }
        };
        
        tableCTPN = new JTable(dataCTPN); 
        for (int i=0; i < list_ctpn.size(); i++){
            ChiTietPhieuNhapDTO ctpn = list_ctpn.get(i);
            SachDTO sach = sBUS.getSachById(ctpn.getMasach());
            Object[] rowData = {
                String.valueOf(ctpn.getMasach()),
                String.valueOf(sach.getTensach()),
                String.valueOf(ctpn.getSoluong()),
                String.valueOf(ctpn.getDongia()),
                String.valueOf(ctpn.getSoluong() * ctpn.getDongia())
            };
            dataCTPN.addRow(rowData);
        }
        
        tableCTPN.setRowHeight(30);
        tableCTPN.setFont(new Font("Arial", Font.PLAIN, 14));
        tableCTPN.setFillsViewportHeight(true);
        tableCTPN.setSelectionBackground(new Color(72, 118, 255)); // Màu xanh dương
        tableCTPN.setSelectionForeground(Color.WHITE); // Màu chữ khi chọn
        tableCTPN.setBackground(Color.WHITE); // Màu nền bảng
        tableCTPN.setForeground(Color.BLACK); // Màu chữ bảng

        tableCTPN.getColumnModel().getColumn(0).setPreferredWidth(50); // Cột mã sách
        tableCTPN.getColumnModel().getColumn(1).setPreferredWidth(150); // Cột tên sách
        tableCTPN.getColumnModel().getColumn(2).setPreferredWidth(50); // Cột số lượng
        tableCTPN.getColumnModel().getColumn(3).setPreferredWidth(50); // Cột đơn giá
        tableCTPN.getColumnModel().getColumn(4).setPreferredWidth(50); // Cột thành tiền
        tableCTPN.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    cell.setBackground(new Color(72, 118, 255)); // Màu xanh dương khi chọn
                    cell.setForeground(Color.WHITE); // Màu chữ trắng khi chọn
                } else {
                    cell.setBackground(Color.WHITE); // Màu nền trắng khi không chọn
                    cell.setForeground(Color.BLACK); // Màu chữ đen khi không chọn
                }
                return cell;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableCTPN);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Khoảng cách giữa bảng và viền
        scrollPane.setPreferredSize(new Dimension(600, 200)); // Kích thước bảng
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(infoPanel, BorderLayout.NORTH); // Thêm panel thông tin vào phía trên bảng
        // ========== PHẦN NÚT BẤM ==========
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton exportButton = createButton("Xuất file Excel", new Color(76, 175, 80));  // Màu xanh dương
        JButton cancelButton = createButton("Đóng", new Color(244, 67, 54));   // Màu đỏ
        
        buttonPanel.add(exportButton);
        buttonPanel.add(cancelButton);

        // ========== GỘP BẢNG VÀ NÚT BẤM ==========
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // ========== GỘP TIÊU ĐỀ, NỘI DUNG VÀ NÚT BẤM ==========
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);
        // ========== THIẾT LẬP DIALOG ==========
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600); // Kích thước dialog

        // ========== Thêm Action ==========
        ActionListener action=new PhieuNhapDialogDetail_Controller(this);
        cancelButton.addActionListener(action);
        exportButton.addActionListener(action);

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
}