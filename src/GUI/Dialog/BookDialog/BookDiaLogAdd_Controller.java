/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.BookDialog;

import GUI.Dialog.BookDialog.BookDiaLogAdd;
import DTO.SachDTO;
import DAO.SachDAO;
import BUS.SachBUS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BookDiaLogAdd_Controller implements  ActionListener{
    private BookDiaLogAdd BDA;
    private  SachBUS SBUS=new SachBUS();
    public BookDiaLogAdd_Controller(BookDiaLogAdd BDA){
        this.BDA=BDA;
    }
    public void actionPerformed(ActionEvent e){
        String sukien= e.getActionCommand();
        if(sukien.equals("Thêm dữ liệu")){
           if (BDA.getTxfTensach().getText().isEmpty() || 
                BDA.getTxfManxb().getText().isEmpty() || 
                BDA.getTxfMatacgia().getText().isEmpty() || 
                BDA.getTxfMatheloai().getText().isEmpty() || 
                BDA.getTxfSoluong().getText().isEmpty() || 
                BDA.getTxfNamxuatban().getText().isEmpty() || 
                BDA.getTxfDongia().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin Sách", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Thực hiện thêm sách khi tất cả các trường đều có dữ liệu
                String tenSach = BDA.getTxfTensach().getText();
                int maNXB = Integer.parseInt(BDA.getTxfManxb().getText());
                int maTG = Integer.parseInt(BDA.getTxfMatacgia().getText());
                int maTL = Integer.parseInt(BDA.getTxfMatheloai().getText());
                int soluong = Integer.parseInt(BDA.getTxfSoluong().getText());
                String namXuatBan = BDA.getTxfNamxuatban().getText();
                int donGia = Integer.parseInt(BDA.getTxfDongia().getText());      
                int maSachAuto = SachDAO.getInstance().getAutoIncrement();  // Gọi hàm lấy giá trị mã Auto
                SachDTO sachNew = new SachDTO(maSachAuto, tenSach, maNXB, maTG, maTL, soluong, namXuatBan, donGia);
                boolean result = SBUS.addSach(sachNew);

                if (result) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    BDA.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(sukien.equals("Xóa")){
            BDA.ClearTextField();
        }
    }
}
