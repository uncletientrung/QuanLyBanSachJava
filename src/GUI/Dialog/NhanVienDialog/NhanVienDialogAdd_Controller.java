/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhanVienDialog;
import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Dialog.NhanVienDialog.NhanVienDialogAdd;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
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
public class NhanVienDialogAdd_Controller implements ActionListener{
    private NhanVienDialogAdd NVDA;
    private NhanVienBUS NVBUS = new NhanVienBUS();

    public NhanVienDialogAdd_Controller(NhanVienDialogAdd NVDA) {
        this.NVDA = NVDA;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String sukien= e.getActionCommand();
        if(sukien.equals("Thêm dữ liệu")){
           if ( //NVDA.getTxfMa().getText().isEmpty() || 
                NVDA.getTxfHo().getText().isEmpty() || 
                NVDA.getTxfTen().getText().isEmpty() || 
                NVDA.getTxfGioitinh().getText().isEmpty() || 
                NVDA.getTxfNgaysinh().getText().isEmpty() ) 
//                NVDA.getTxfTrangthai().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                   // Thực hiện thêm nhân viên khi tất cả các trường đều có dữ liệu
                   //int ma = Integer.parseInt(NVDA.getTxfMa().getText());
                   String ho = NVDA.getTxfHo().getText();
                   String ten = NVDA.getTxfTen().getText();
                   int gioitinh = Integer.parseInt(NVDA.getTxfGioitinh().getText());
                   String sdt = NVDA.getTxfSdt().getText();
                   int trangthai = Integer.parseInt(NVDA.getTxfTrangthai().getText());
                   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                   String dateString = NVDA.getTxfNgaysinh().getText();
                   Date ngaysinh = null;
               try {
                   ngaysinh = dateFormat.parse(dateString);
               } catch (ParseException ex) {
                   Logger.getLogger(NhanVienDialogAdd_Controller.class.getName()).log(Level.SEVERE, null, ex);
               }
                   int maAuto = NhanVienDAO.getInstance().getAutoIncrement();
                   
                   // Gọi hàm lấy giá trị mã Auto
                   NhanVienDTO nvnew = new NhanVienDTO(maAuto, ho, ten, gioitinh, sdt, ngaysinh, trangthai);
                   boolean result = NVBUS.addNV(nvnew);
                           
                           if (result) {
                               JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                               NVDA.dispose();
                           } else {
                               JOptionPane.showMessageDialog(null, "Thêm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
                           }
            }
        }
        if(sukien.equals("Xóa")){
            NVDA.ClearTextField();
        }
    } 
}
