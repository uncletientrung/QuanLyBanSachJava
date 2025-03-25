/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.BookDialog;

import GUI.Dialog.BookDialog.BookDialogAdd;
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
    private BookDialogAdd BDA;
    private  SachBUS SBUS=new SachBUS();
    public BookDiaLogAdd_Controller(BookDialogAdd BDA){
        this.BDA=BDA;
    }
    public void actionPerformed(ActionEvent e){
        String sukien= e.getActionCommand();
        if(sukien.equals("Thêm dữ liệu")){
            if(BDA.getTxfTensach().getText()!=null || BDA.getTxfManxb().getText()!=null || BDA.getTxfMatacgia().getText()!=null|| BDA.getTxfMatheloai().getText()!=null ||
                BDA.getTxfSoluong().getText()!=null || BDA.getTxfNamxuatban().getText()!=null || BDA.getTxfDongia().getText()!=null  ){
                String tenSach=BDA.getTxfTensach().getText();
                int maNXB=Integer.parseInt(BDA.getTxfManxb().getText());
                int maTG=Integer.parseInt(BDA.getTxfMatacgia().getText());
                int maTL=Integer.parseInt(BDA.getTxfMatheloai().getText());
                int soluong=Integer.parseInt(BDA.getTxfSoluong().getText());
                String namXuatBan=BDA.getTxfNamxuatban().getText();
                int donGia=Integer.parseInt(BDA.getTxfDongia().getText());      
                int maSachAuto=SachDAO.getInstance().getAutoIncrement();  // Gọi hàm lấy giá trị mã Auto
                SachDTO sachNew=new SachDTO(maSachAuto, tenSach, maNXB, maTG, maTL, soluong, namXuatBan, donGia);
                boolean result=SBUS.addSach(sachNew);
            }else{
                JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin Sách", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(sukien.equals("Xóa")){
            BDA.getTxfTensach().setText("");BDA.getTxfManxb().setText("");BDA.getTxfMatacgia().setText("");
            BDA.getTxfMatheloai().setText("");BDA.getTxfSoluong().setText("");BDA.getTxfNamxuatban().setText("");
            BDA.getTxfDongia().setText("");
        }
    }
}
