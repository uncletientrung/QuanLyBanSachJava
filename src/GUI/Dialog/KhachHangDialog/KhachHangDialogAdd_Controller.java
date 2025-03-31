
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
                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date(1000) ;
//                try {
//                    date = formatter.parse(ngaysinh);
//                    
//                } catch (Exception a) {
//                    a.printStackTrace();
//                }
//                    

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
        if(sukien.equals("Xóa")){
            KHDA.ClearTextField();
        }
    }
}
