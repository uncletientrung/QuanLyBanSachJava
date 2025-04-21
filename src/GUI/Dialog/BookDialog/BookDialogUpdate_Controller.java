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
public class BookDialogUpdate_Controller implements  ActionListener{
    private BookDialogUpdate BDU;
    private SachBUS sachBUS;
    private ArrayList<SachDTO> listSach;
    private NhaXuatBanBUS nxbBUS=new NhaXuatBanBUS();
    private TacGiaBUS tgBUS=new TacGiaBUS();
    private TheLoaiBUS tlBUS=new TheLoaiBUS();
    public BookDialogUpdate_Controller(BookDialogUpdate BDU){
        this.BDU=BDU;
    }
    public void actionPerformed(ActionEvent e){
        String sukien=e.getActionCommand();
        if(sukien.equals("Lưu thông tin")){
            if (BDU.getTxfTensach().getText().isEmpty() || 
                BDU.getTxfSoluong().getText().isEmpty() || 
                BDU.getTxfNamxuatban().getText().isEmpty() || 
                BDU.getTxfDongia().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin sửa Sách", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Thực hiện sửa sách khi tất cả các trường đều có dữ liệu
                    String maSach=BDU.getTxfMasach().getText();
                    String tenSach = BDU.getTxfTensach().getText();
                    String nameNXB=BDU.getCbb_NXB().getSelectedItem().toString();
                    String nameTG=BDU.getCbb_TG().getSelectedItem().toString();
                    String nameTL=BDU.getCbb_TL().getSelectedItem().toString();
                    int maNXB = nxbBUS.getNXBByNameNXB(nameNXB).getManxb();
                    int maTG = tgBUS.getTgByNameTG(nameTG).getMatacgia();
                    int maTL = tlBUS.getTlByNameTL(nameTL).getMatheloai();
                    int soluong = Integer.parseInt(BDU.getTxfSoluong().getText());
                    String namXuatBan = BDU.getTxfNamxuatban().getText();
                    int donGia = Integer.parseInt(BDU.getTxfDongia().getText());   

                    SachDTO sach=new SachBUS().getSachById(maSach); // Lấy thông tin sách từ mã sách
                    if (sach!=null){
                        sach.setTensach(tenSach);
                        sach.setManxb(maNXB);
                        sach.setMatacgia(maTG);
                        sach.setMatheloai(maTL);
                        sach.setSoluongton(soluong);
                        sach.setNamxuatban(namXuatBan);
                        sach.setDongia(donGia);
                        SachDTO sachUpdate=new SachDTO(maSach, tenSach, maNXB, maTG, maTL, soluong, namXuatBan, donGia);
                        Boolean result=new SachBUS().updateSach_DB(sachUpdate); // Chỉnh sửa sách trong Database
                        if(result){
                             JOptionPane.showMessageDialog(null, "Chỉnh sửa sách thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            BDU.dispose();
                        }
                        else{ 
                             JOptionPane.showMessageDialog(null, "Lỗi gì đó của sách", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sách để sửa", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                
            }
        }
        if(sukien.equals("Đóng")){
            BDU.dispose();
        }
    }
}
