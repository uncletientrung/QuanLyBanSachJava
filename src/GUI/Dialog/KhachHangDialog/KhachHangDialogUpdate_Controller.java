/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.KhachHangDialog;

import GUI.Dialog.KhachHangDialog.KhachHangDialogAdd;
import DTO.KhachHangDTO;
import DAO.KhachHangDAO;
import BUS.KhachHangBUS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class KhachHangDialogUpdate_Controller implements  ActionListener{
    private KhachHangDialogUpdate khDU;
    private KhachHangBUS khBUS;
    private ArrayList<KhachHangDTO> listkh;
    public KhachHangDialogUpdate_Controller(KhachHangDialogUpdate khDU){
        this.khDU=khDU;
    }
    public void actionPerformed(ActionEvent e){
        String sukien=e.getActionCommand();
        if(sukien.equals("Lưu thông tin")){
            if (khDU.getTxfHo().getText().isEmpty() || 
                khDU.getTxfTen().getText().isEmpty() || 
                khDU.getTxfEmail().getText().isEmpty() || 
                khDU.getTxfNgaySinh().getText().isEmpty() || 
                khDU.getTxfSdt().getText().isEmpty() )
          {
                JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin sửa khach hang", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Thực hiện sửa sách khi tất cả các trường đều có dữ liệu
                int ma=Integer.parseInt(khDU.getTxfMa().getText());
                String ho = khDU.getTxfHo().getText();
                String ten = khDU.getTxfTen().getText();
                String email = khDU.getTxfEmail().getText();
                String ns = khDU.getTxfNgaySinh().getText();
                String sdt = khDU.getTxfSdt().getText();
                
                KhachHangDTO khach=new KhachHangBUS().getKhachHangById(ma); // Lấy thông tin sách từ mã sách
                if (khach!=null){
                    khach.setHokh(ho);
                    khach.setTenkh(ten);
                    khach.setemail(email);
                    
                    if (!khBUS.checkemail(email)){
                     JOptionPane.showMessageDialog(null, "Lỗi định dạng email\nĐịnh dạng : Phải bao gồm '@gmail.com' ", "Error", JOptionPane.ERROR_MESSAGE);
                }     
                
               
                 else if (khBUS.checkngaysinh(ns)==null){
                     JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày sinh\nĐịnh dạng : yyyy-mm-dd", "Error", JOptionPane.ERROR_MESSAGE);
                } 
                
                 else if (!khBUS.checksdt(sdt)){
                     JOptionPane.showMessageDialog(null, "Lỗi định dạng số điện thoại\nĐịnh dạng : gồm 10 số và bắt đầu bằng số 0", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    khach.setNgaysinh(khBUS.checkngaysinh(ns));
                    
                    
                    khach.setSdt(sdt);
                    
                    KhachHangDTO khUpdate=new KhachHangDTO(ma, ho, ten, email, khBUS.checkngaysinh(ns), sdt);
                    Boolean result=new KhachHangBUS().updateKhachHang_DB(khUpdate); // Chỉnh sửa sách trong Database
                    if(result){
                         JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        khDU.dispose();
                    }
                    else{ 
                         JOptionPane.showMessageDialog(null, "Lỗi gì đó ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }}else{
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khach hang để sửa", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
        if(sukien.equals("Đóng")){
            khDU.dispose();
        }
    }
}
