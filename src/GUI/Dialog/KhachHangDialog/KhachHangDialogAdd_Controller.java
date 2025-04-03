
package GUI.Dialog.KhachHangDialog;

import GUI.Dialog.KhachHangDialog.KhachHangDialogAdd;
import DTO.SachDTO;
import DAO.KhachHangDAO;
import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class KhachHangDialogAdd_Controller implements  ActionListener{
    private KhachHangDialogAdd KHDA;
    private  KhachHangBUS KHBUS=new KhachHangBUS();
    public KhachHangDialogAdd_Controller(KhachHangDialogAdd KHDA){
        this.KHDA=KHDA;
    }
    public void actionPerformed(ActionEvent e){
        String sukien= e.getActionCommand();
        if(sukien.equals("Thêm dữ liệu")){
            System.out.println("them");
           if ( KHDA.getTxfHo().getText().isEmpty() || 
                KHDA.getTxfTen().getText().isEmpty() || 
                KHDA.getTxfEmail().getText().isEmpty() || 
                KHDA.getTxfNgaySinh().getText().isEmpty() || 
                KHDA.getTxfSdt().getText().isEmpty()  ) {
                JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Thực hiện thêm sách khi tất cả các trường đều có dữ liệu
                String ho = KHDA.getTxfHo().getText();
                String ten = KHDA.getTxfTen().getText();
                String email = KHDA.getTxfEmail().getText();
   /*:)) */     String ngaysinh = KHDA.getTxfNgaySinh().getText();
                String sdt = KHDA.getTxfSdt().getText();
                
               
                 
                 if (!KHBUS.checkemail(email)){
                     JOptionPane.showMessageDialog(null, "Lỗi định dạng email\nĐịnh dạng : Phải bao gồm '@gmail.com' ", "Error", JOptionPane.ERROR_MESSAGE);
                }     
                
               
                 else if (KHBUS.checkngaysinh(ngaysinh)==null){
                     JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày sinh\nĐịnh dạng : yyyy-mm-dd", "Error", JOptionPane.ERROR_MESSAGE);
                } 
                
                 else if (!KHBUS.checksdt(sdt)){
                     JOptionPane.showMessageDialog(null, "Lỗi định dạng số điện thoại\nĐịnh dạng : gồm 10 số và bắt đầu bằng số 0", "Error", JOptionPane.ERROR_MESSAGE);
                } 
                 else{
                 Date date=KHBUS.checkngaysinh(ngaysinh);
                int maAuto = KhachHangDAO.getInstance().getAutoIncrement();  // Gọi hàm lấy giá trị mã Auto
                KhachHangDTO KHNew = new KhachHangDTO(maAuto, ho, ten, email, date, sdt);
                boolean result = KHBUS.add(KHNew);

                if (result) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    KHDA.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
                }
                 }
            }
        }
        if(sukien.equals("Xóa")){
            KHDA.ClearTextField();
        }
    }
    

   
 
}
