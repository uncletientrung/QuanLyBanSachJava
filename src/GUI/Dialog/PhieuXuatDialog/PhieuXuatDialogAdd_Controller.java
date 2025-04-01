/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuXuatDialog;

import BUS.SachBUS;
import DTO.SachDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author DELL
 */
public class PhieuXuatDialogAdd_Controller implements DocumentListener,ListSelectionListener,ActionListener{
    public PhieuXuatDialogAdd PXDA;
    public PhieuXuatDialogAdd_Controller(PhieuXuatDialogAdd PXDA){
        this.PXDA=PXDA;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }else if(e.getDocument() == PXDA.getTxfGiamGia().getDocument()){
        PXDA.CalcBill();
    }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }else if(e.getDocument() == PXDA.getTxfGiamGia().getDocument()){
        PXDA.CalcBill();
    }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }else if(e.getDocument() == PXDA.getTxfGiamGia().getDocument()){
        PXDA.CalcBill();
    }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable tableSach=PXDA.getTableChonSach();
        JTable tableChiTiet=PXDA.getTableListBan();
        if (tableSach.getSelectedRow()!=-1){
            int selectRow= tableSach.getSelectedRow();
            String maSach=tableSach.getValueAt(selectRow, 0).toString();
            SachDTO sach=new SachBUS().getSachById(Integer.parseInt(maSach));
            if (sach != null) {
                PXDA.setTxfDonGia(String.valueOf(sach.getDongia())); // Lấy giá sách từ mã sách
        }
        }
        if(tableChiTiet.getSelectedRow()!=-1){
            int selectRowV2=tableChiTiet.getSelectedRow();
            String tensach=tableChiTiet.getValueAt(selectRowV2, 0).toString();
            String soluong=tableChiTiet.getValueAt(selectRowV2, 1).toString();
            String dongia=tableChiTiet.getValueAt(selectRowV2, 2).toString();
            String tong=tableChiTiet.getValueAt(selectRowV2, 3).toString();
            PXDA.getTxfTenSachV2().setText(tensach);
            PXDA.getTxfSoLuongV2().setText(soluong);
            PXDA.getTxfDonGiaV2().setText(dongia);
            PXDA.getTxfThanhTienV2().setText(tong);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String sukien=e.getActionCommand();
       JTable tableSach=PXDA.getTableChonSach();
       JTable tableChiTiet=PXDA.getTableListBan();
       if (sukien.equals("Thêm")){
           if(!PXDA.getTxfKhachHang().getText().trim().isEmpty() 
                && !PXDA.getTxtSoLuong().getText().trim().isEmpty()
                && !PXDA.gettTxfDonGia().getText().trim().isEmpty()
                && tableSach.getSelectedRow() != -1){
                    String tenSach=tableSach.getValueAt(tableSach.getSelectedRow(), 1).toString();
                    String soluong=PXDA.getTxtSoLuong().getText();
                    String dongia=PXDA.gettTxfDonGia().getText();
                    String tong = String.valueOf(Integer.parseInt(soluong) * Integer.parseInt(dongia));
                    PXDA.getDataBan().addRow(new Object[]{tenSach,soluong,dongia,tong});
                    PXDA.CalcBill();
           }else{
           JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
       }
       }
       if(sukien.equals("Sửa")){
           if(tableChiTiet.getSelectedRow()!=-1){
               int selectRowV2=tableChiTiet.getSelectedRow();
               String soLuongNew= PXDA.getTxfSoLuongV2().getText().toString();
               String dongiaNew=PXDA.getTxfDonGiaV2().getText().toString();
               String tong = String.valueOf(Integer.parseInt(soLuongNew) * Integer.parseInt(dongiaNew));
               tableChiTiet.setValueAt(soLuongNew, selectRowV2, 1);
               tableChiTiet.setValueAt(dongiaNew, selectRowV2, 2);
               tableChiTiet.setValueAt(tong, selectRowV2, 3);
               PXDA.getTxfDonGiaV2().setText(dongiaNew);
               PXDA.getTxfThanhTienV2().setText(tong);
               PXDA.CalcBill();
           }else{
           JOptionPane.showMessageDialog(null, "Hãy chọn mục cần sửa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
             }
       
       }
       if (sukien.equals("Xóa")){
            if(tableChiTiet.getSelectedRow()!=-1){
                int selectRowV2=tableChiTiet.getSelectedRow();
                PXDA.getDataBan().removeRow(selectRowV2);
                // Gán các txf chỗ chỉnh sửa về rỗng sau khi update
                PXDA.getTxfDonGiaV2().setText("");
                PXDA.getTxfSoLuongV2().setText("");
                PXDA.getTxfThanhTienV2().setText("");
                PXDA.getTxfTenSachV2().setText("");
                PXDA.CalcBill();
            }else{
           JOptionPane.showMessageDialog(null, "Hãy chọn mục cần xóa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
       }
       if(sukien.equals("Xóa tất cả")){
           if(PXDA.getDataBan().getRowCount()!=0){
                JOptionPane.showMessageDialog(null, "** Viết 1 cái DiaLog cảnh báo Yes/No ở đây!", "Thông báo", JOptionPane.NO_OPTION);
                PXDA.getDataBan().setRowCount(0);
                // Gán các txf chỗ chỉnh sửa về rỗng sau khi update
                 PXDA.getTxfDonGiaV2().setText("");
                 PXDA.getTxfSoLuongV2().setText("");
                 PXDA.getTxfThanhTienV2().setText("");
                 PXDA.getTxfTenSachV2().setText("");
                 PXDA.CalcBill();
           }else{
               JOptionPane.showMessageDialog(null, "Danh sách bán chưa có gì để xóa", "Thông báo", JOptionPane.ERROR_MESSAGE);
           }
           
       }
    }
    
}
