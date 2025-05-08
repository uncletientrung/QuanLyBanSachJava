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
import DTO.NhanVienDTO;
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
    private NhanVienDTO nvDTO = new NhanVienDTO();

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
        txfMaPhieu=createTextField(pnDTO.getMaphieu()+"");
        txfNV=createTextField(nvBUS.getHoTenNVById(pnDTO.getManv()) +"");
        txfNCC=createTextField(nccBUS.getTenNCC(pnDTO.getMancc())+"");
        txfTime=createTextField(pnDTO.getThoiGian()+"");
        txfTongHD=createTextField(pnDTO.getTongTien()+"");
        infoPanel.add(txfMaPhieu);
        infoPanel.add(txfNV);
        infoPanel.add(txfNCC);
        infoPanel.add(txfTime);
        infoPanel.add(txfTongHD);
        
        // Tạo bảng
        list_ctpn=pnBUS.getListCTPNById(pnDTO.getMaphieu());
        String[] columnNames = {"Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"};
        dataCTPN = new DefaultTableModel(columnNames, 0){
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
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0,1,2,3,4};
        for(int col : columnsToCenter){
            tableCTPN.getColumnModel().getColumn(col).setCellRenderer(center);
        }
        tableCTPN.getTableHeader().setReorderingAllowed(false);
        tableCTPN.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tableCTPN.getTableHeader().setForeground(Color.BLACK);
        
        JScrollPane tableScrollPane = new JScrollPane(tableCTPN);
        tableScrollPane.setViewportView(tableCTPN);

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

    public JTextField getTxfMaPhieu() {
        return txfMaPhieu;
    }

    public void setTxfMaPhieu(JTextField txfMaPhieu) {
        this.txfMaPhieu = txfMaPhieu;
    }

    public JTextField getTxfNV() {
        return txfNV;
    }

    public void setTxfNV(JTextField txfNV) {
        this.txfNV = txfNV;
    }

    public JTextField getTxfNCC() {
        return txfNCC;
    }

    public void setTxfNCC(JTextField txfNCC) {
        this.txfNCC = txfNCC;
    }

    public JTextField getTxfTime() {
        return txfTime;
    }

    public void setTxfTime(JTextField txfTime) {
        this.txfTime = txfTime;
    }

    public JTextField getTxfTongHD() {
        return txfTongHD;
    }

    public void setTxfTongHD(JTextField txfTongHD) {
        this.txfTongHD = txfTongHD;
    }

    public DefaultTableModel getDataCTPN() {
        return dataCTPN;
    }

    public void setDataCTPN(DefaultTableModel dataCTPN) {
        this.dataCTPN = dataCTPN;
    }

    public PhieuNhapBUS getPnBUS() {
        return pnBUS;
    }

    public void setPnBUS(PhieuNhapBUS pnBUS) {
        this.pnBUS = pnBUS;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getList_ctpn() {
        return list_ctpn;
    }

    public void setList_ctpn(ArrayList<ChiTietPhieuNhapDTO> list_ctpn) {
        this.list_ctpn = list_ctpn;
    }

    public SachBUS getsBUS() {
        return sBUS;
    }

    public void setsBUS(SachBUS sBUS) {
        this.sBUS = sBUS;
    }

    public NhaCungCapBUS getNccBUS() {
        return nccBUS;
    }

    public void setNccBUS(NhaCungCapBUS nccBUS) {
        this.nccBUS = nccBUS;
    }

    public NhanVienBUS getNvBUS() {
        return nvBUS;
    }

    public void setNvBUS(NhanVienBUS nvBUS) {
        this.nvBUS = nvBUS;
    }

    public JTable getTableCTPN() {
        return tableCTPN;
    }

    public void setTableCTPN(JTable tableCTPN) {
        this.tableCTPN = tableCTPN;
    }

    
}



    