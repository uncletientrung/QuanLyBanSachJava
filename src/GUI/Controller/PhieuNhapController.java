/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;
import GUI.Dialog.PhieuNhapDialog.AddPanel;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogAdd;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogDelete;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogDetail;
import GUI.View.PhieuNhapPanel;
import GUI.WorkFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Minnie
 */
public class PhieuNhapController implements ActionListener, ChangeListener{
    
    private PhieuNhapPanel pnp;
    private WorkFrame wf;
    private PhieuNhapBUS pnBUS;
    private PhieuNhapDialogAdd PNDA;
    private PhieuNhapDialogDelete PNDD;
    private PhieuNhapDialogDetail PNDX;
    
    public PhieuNhapController(){
    }
    
    public PhieuNhapController(PhieuNhapPanel pnp, WorkFrame wf){
        this.pnp = pnp;
        this.wf = wf;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String evt = e.getActionCommand();
        switch (evt) {
            case "Trang chủ":    
                pnp.getPanelCenter().removeAll(); // Xóa tất cả các thành phần hiện tại trong tab
                pnp.getScrollPanePhieuNhap().setViewportView(pnp.getTablePhieuNhap());
                pnp.getPanelCenter().setLayout(new GridLayout(1,1));
                pnp.getPanelCenter().add(pnp.getScrollPanePhieuNhap());
                pnp.refreshTablePn();
                break;
            case "Thêm":
                System.out.println("Nut them da duoc nhan chon");
                pnp.getPanelCenter().removeAll(); // Xóa tất cả các thành phần hiện tại trong tab
                pnp.getPanelCenter().setLayout(new BorderLayout()); // Đặt layout cho tab
                
                // Thêm AddPanel vào PanelCenter
                AddPanel addPanel = new AddPanel();
                pnp.getPanelCenter().add(addPanel, BorderLayout.CENTER);
                
//                PhieuNhapDialogAdd pnda = new PhieuNhapDialogAdd();
//                pnp.getPanelCenter().add(pnda, BorderLayout.CENTER);
                
                pnp.getPanelCenter().revalidate(); // Làm mới giao diện
                pnp.getPanelCenter().repaint(); // Vẽ lại giao diện
                break;
            case "Chi tiết":
                JTable tablePN = pnp.getTablePhieuNhap();
                
                // Kiểm tra bảng có dữ liệu hay không
                if (tablePN.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để chọn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Kiểm tra hàng được chọn
                int selectedRow = tablePN.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng trong bảng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Xử lý dữ liệu hàng được chọn
                try {
                    int id = Integer.parseInt(tablePN.getValueAt(selectedRow, 0).toString());
                    pnBUS = new PhieuNhapBUS();
                    PhieuNhapDTO phieu = pnBUS.getPNById(id);
                    if (phieu != null) {
                        PNDX = new PhieuNhapDialogDetail(wf, phieu);
                        pnp.refreshTablePn();
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Hủy bỏ":
                JTable tablePNC = pnp.getTablePhieuNhap();
                
                // Kiểm tra bảng có dữ liệu hay không
                if (tablePNC.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để chọn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Kiểm tra hàng được chọn
                int selectedRow2 = tablePNC.getSelectedRow();
                if (selectedRow2 == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng trong bảng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    int id = Integer.parseInt(tablePNC.getValueAt(selectedRow2, 0).toString());
                    pnBUS = new PhieuNhapBUS();
                    PhieuNhapDTO phieu = pnBUS.getPNById(id);
                    if (phieu != null){
                        PNDD = new PhieuNhapDialogDelete(wf, phieu);
                        pnp.refreshTablePn();
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }   catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                    break;
            default:
                break;
        }
       
    }
    
    


    @Override
    public void stateChanged(ChangeEvent e) {
        
    }
    
    
}
