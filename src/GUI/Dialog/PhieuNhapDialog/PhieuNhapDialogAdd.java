/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Dialog.PhieuNhapDialog;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class PhieuNhapDialogAdd extends JDialog {
    public PhieuNhapDialogAdd(Frame parent, String maPhieuNhap, String nhanVienNhap, String nhaCungCap, int tongTien, ArrayList<Object[]> chiTietPhieuNhap) {
        super(parent, "Thông tin phiếu nhập", true);

        // Tạo bảng hiển thị chi tiết phiếu nhập
        String[] columnNames = {"Mã sách", "Tên sách", "Số lượng nhập", "Đơn giá", "Thành tiền"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Object[] row : chiTietPhieuNhap) {
            tableModel.addRow(row);
        }
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setEnabled(false);

        // Căn giữa chữ trong các ô của bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Tạo các nhãn hiển thị thông tin phiếu nhập
        JLabel lbMaPhieuNhap = new JLabel("Mã phiếu nhập: " + maPhieuNhap);
        JLabel lbNhanVienNhap = new JLabel("Nhân viên nhập: " + nhanVienNhap);
        JLabel lbNhaCungCap = new JLabel("Nhà cung cấp: " + nhaCungCap);
        JLabel lbTongTien = new JLabel("Tổng tiền: " + tongTien + "đ");

        // Định dạng giao diện
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        infoPanel.add(lbMaPhieuNhap);
        infoPanel.add(lbNhanVienNhap);
        infoPanel.add(lbNhaCungCap);
        infoPanel.add(lbTongTien);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setPreferredSize(new Dimension(500, 150));


        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> {
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnClose);

        // Thêm các thành phần vào JDialog
        setLayout(new BorderLayout(10, 10));
        add(infoPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 400);
        setLocationRelativeTo(parent);
    }
}