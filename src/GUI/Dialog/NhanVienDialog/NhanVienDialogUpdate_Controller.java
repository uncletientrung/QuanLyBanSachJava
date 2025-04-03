/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhanVienDialog;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Minnie
 */
public class NhanVienDialogUpdate_Controller implements ActionListener{
    private final NhanVienDialogUpdate NVDU;
    
    public NhanVienDialogUpdate_Controller(NhanVienDialogUpdate NVDU){
        this.NVDU = NVDU;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sukien= e.getActionCommand();
        if(sukien.equals("Thêm dữ liệu")){
           if (NVDU.getTxfMa().getText().isEmpty() || 
                NVDU.getTxfHo().getText().isEmpty() || 
                NVDU.getTxfTen().getText().isEmpty() || 
                NVDU.getTxfGioitinh().getText().isEmpty() || 
                NVDU.getTxfNgaysinh().getText().isEmpty() || 
                NVDU.getTxfTrangthai().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                   // Thực hiện thêm nhân viên khi tất cả các trường đều có dữ liệu
                   int ma = Integer.parseInt(NVDU.getTxfMa().getText());
                   String ho = NVDU.getTxfHo().getText();
                   String ten = NVDU.getTxfTen().getText();
                   int gioitinh = Integer.parseInt(NVDU.getTxfGioitinh().getText());
                   String sdt = NVDU.getTxfSdt().getText();
                   int trangthai = Integer.parseInt(NVDU.getTxfSdt().getText());
                   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                   String dateString = NVDU.getTxfMa().getText();
                   Date ngaysinh = null;
               try {
                   ngaysinh = dateFormat.parse(dateString);
               } catch (ParseException ex) {
                   Logger.getLogger(NhanVienDialogUpdate_Controller.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               NhanVienDTO nv = new NhanVienBUS().getNVByID(ma);
               if(nv!=null){
                   nv.setHonv(ho);
                   nv.setTennv(ten);
                   nv.setGioitinh(gioitinh);
                   nv.setSdt(sdt);
                   nv.setTrangthai(trangthai);
                   nv.setNgaysinh(ngaysinh);
                   
                   NhanVienDTO nvUpdate = new NhanVienDTO(ma,ho,ten,gioitinh,sdt,ngaysinh,trangthai);
                   boolean result = new NhanVienBUS().updateNV_DB(nvUpdate);
                   if(result){
                       JOptionPane.showMessageDialog(null,"Chỉnh sửa thành công","THÔNG BÁO",JOptionPane.INFORMATION_MESSAGE);
                   }
                   else{
                        JOptionPane.showMessageDialog(null,"Có vẻ đã có lỗi xảy ra","Lỗi",JOptionPane.INFORMATION_MESSAGE);
                   }
                }else{
                   JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên để sửa","Lỗi",JOptionPane.INFORMATION_MESSAGE);
               }
           }
        }
        if(sukien.equals("Đóng")){
           NVDU.dispose();
        }
    }
}
