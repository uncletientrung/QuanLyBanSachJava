/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.BookDialog;

import BUS.NhaXuatBanBUS;
import GUI.Dialog.BookDialog.BookDialogAdd;
import DTO.SachDTO;
import DAO.SachDAO;
import BUS.SachBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
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
    private NhaXuatBanBUS nxbBUS=new NhaXuatBanBUS();
    private TacGiaBUS tgBUS=new TacGiaBUS();
    private TheLoaiBUS tlBUS=new TheLoaiBUS();
    private  SachBUS SBUS=new SachBUS();
    public BookDiaLogAdd_Controller(BookDialogAdd BDA){
        this.BDA=BDA;
    }
    public void actionPerformed(ActionEvent e){
        String sukien= e.getActionCommand();
        if(sukien.equals("Thêm dữ liệu")){
           if (BDA.getTxfTensach().getText().isEmpty() || 
                BDA.getTxfSoluong().getText().isEmpty() || 
                BDA.getTxfNamxuatban().getText().isEmpty() || 
                BDA.getTxfDongia().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin Sách", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
               try {
                    // Thực hiện thêm sách khi tất cả các trường đều có dữ liệu
                    String tenSach = BDA.getTxfTensach().getText();
                    int soluong = Integer.parseInt(BDA.getTxfSoluong().getText());
                    String namXuatBan = BDA.getTxfNamxuatban().getText();
                    int donGia = Integer.parseInt(BDA.getTxfDongia().getText());      
                    int maSachAuto = SachDAO.getInstance().getAutoIncrement();  // Gọi hàm lấy giá trị mã Auto
                    // Chuyển các thông tin  từ tên NXB, TG, TL thành mã 
                    String nameNXB=BDA.getCbb_NXB().getSelectedItem().toString();
                    String nameTG=BDA.getCbb_TG().getSelectedItem().toString();
                    String nameTL=BDA.getCbb_TL().getSelectedItem().toString();
                    int maNXB = nxbBUS.getNXBByNameNXB(nameNXB).getManxb();
                    int maTG = tgBUS.getTgByNameTG(nameTG).getMatacgia();
                    int maTL = tlBUS.getTlByNameTL(nameTL).getMatheloai();
                    SachDTO sachNew = new SachDTO(maSachAuto, tenSach, maNXB, maTG, maTL, soluong, namXuatBan, donGia);
                    boolean result = SBUS.addSach(sachNew);

                    if (result) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        BDA.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
                    }
               } catch (Exception evt) {
                   JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng", "Error", JOptionPane.ERROR_MESSAGE);
               }
                
            }
        }
        if(sukien.equals("Xóa")){
            BDA.ClearTextField();
        }
    }
}
