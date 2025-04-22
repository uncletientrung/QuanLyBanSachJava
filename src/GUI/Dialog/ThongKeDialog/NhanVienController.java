/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThongKeDialog;

import BUS.NhanVienBUS;
import BUS.ThongKeBUS;
import DTO.NhanVienDTO;
import GUI.Dialog.NhanVienDialog.NhanVienDialogDetail;
import GUI.Dialog.ThongKeDialog.NhanVienDialog;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Hi
 */
public class NhanVienController implements ActionListener,ListSelectionListener,DocumentListener{
    private NhanVienDialog nvf;
    private WorkFrame wk;
    private NhanVienBUS nvBUS;
    private NhanVienDialogDetail nvdd;
    private ThongKeBUS tkBUS=new ThongKeBUS() ;

   
    
    public NhanVienController(NhanVienDialog nccpn,WorkFrame wk){
        this.nvf=nccpn;
        this.wk=wk;
        this.nvBUS= new NhanVienBUS();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JTable tableB = nvf.getTable();
        
        JTable tableB2 = nvf.getTable2();
 if(action.equals("chi tiết")){
        
        int selectRow = tableB.getSelectedRow();
        if (selectRow >= 0) {  // Kiểm tra xem có hàng nào đang được chọn nvông
             String ma = tableB.getValueAt(selectRow, 0).toString();
             for(NhanVienDTO nv : nvf.listnv){
                 if(String.valueOf(nv.getManv()).equals(ma)){
                     String ho= nv.getHonv();
                    String ten = nv.getTennv();
                    String email = (nv.getGioitinh()>0) ? "Nam" : "Nữ";
                    String ngaysinh = String.valueOf(nv.getNgaysinh());
                    String sdt = nv.getSdt();
                    String tt=(nv.getTrangthai()>0) ? "Hoạt Động" : "Đã Bị Ban";
                    nvdd = new NhanVienDialogDetail(wk);
            nvdd.ShowInfo(ma, ho, ten, email, ngaysinh,sdt,tt); // Cập nhật dữ liệu trước
            nvdd.setVisible(true); // Hiển thị hộp thoại sau
             }
             }
            

            // Mở hộp thoại sửa và hiển thị thông tin
            
            
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một nvach hang! ","Thông báo", JOptionPane.ERROR_MESSAGE);
        }
 }
 if(action.equals("thống kê")){
        
        if (nvf.getCbbox().getSelectedItem().equals("Tất cả")){
            nvf.doitable(1,nvf.getScrollPane()  );
            nvf.panel.setVisible(false);
            nvf.panel2.setVisible(false);
            nvf.panel3.setVisible(false);
            nvf.panel4.setVisible(false);
        }
        if (((String) nvf.getCbbox().getSelectedItem()).equals("Trước đến nay")){
            nvf.panel.setVisible(false);
            nvf.panel2.setVisible(false);
            nvf.panel3.setVisible(false);
            nvf.panel4.setVisible(false);
            nvf.list=tkBUS.nvtatca(nvf.listnv, nvf.listpx, nvf.listctpx);
            nvf.doitable(2,nvf.getScrollPane()  );
            
            
        }else System.out.println((String) nvf.getCbbox().getSelectedItem());
        if (nvf.getCbbox().getSelectedItem().equals("Ngày")){
             if((nvf.aaChooser.getDate()==null || nvf.bbChooser.getDate()==null)&&nvf.panel.isVisible()){
                JOptionPane.showMessageDialog(null, "Hãy chọn ngày ","Thông báo", JOptionPane.ERROR_MESSAGE);
                
            }
            
            nvf.panel.setVisible(true);
            nvf.panel2.setVisible(false);
            nvf.panel3.setVisible(false);
            nvf.panel4.setVisible(false);
           
            if(nvf.bbChooser.getDate()!=null && nvf.aaChooser.getDate()!=null){
                if((nvf.bbChooser.getDate().getTime()-nvf.aaChooser.getDate().getTime()<0)){
                    JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    nvf.list=tkBUS.nvngay(nvf.listnv, nvf.listpx, nvf.listctpx,nvf.aaChooser.getDate(), nvf.bbChooser.getDate());
                    nvf.doitable(2,nvf.getScrollPane()  );
                }
            }
        }
        if (nvf.getCbbox().getSelectedItem().equals("Tháng")){
            nvf.panel.setVisible(false);
            nvf.panel2.setVisible(true);
            nvf.panel3.setVisible(false);
            nvf.panel4.setVisible(false);
           
          
            if(nvf.y2Chooser2.getYear()<nvf.y1Chooser2.getYear() || (nvf.y2Chooser2.getYear()==nvf.y1Chooser2.getYear() && nvf.m2Chooser.getMonth()<nvf.m1Chooser.getMonth()) ){
                JOptionPane.showMessageDialog(null, "tháng kết thúc phải lớn hơn thang bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            else{
                nvf.list=tkBUS.nvthang(nvf.listnv, nvf.listpx, nvf.listctpx,nvf.m1Chooser.getMonth()+1, nvf.m2Chooser.getMonth()+1,nvf.y1Chooser.getYear(),nvf.y2Chooser.getYear());
                nvf.doitable(2,nvf.getScrollPane()  );
            }
           
        }
        if (nvf.getCbbox().getSelectedItem().equals("Năm")){
            nvf.panel.setVisible(false);
            nvf.panel2.setVisible(false);
            nvf.panel3.setVisible(true);
            nvf.panel4.setVisible(false);
           
          
            if(nvf.y2Chooser.getYear()<nvf.y1Chooser.getYear()  ){
                JOptionPane.showMessageDialog(null, "năm kết thúc phải lớn hơn năm bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            else{
                nvf.list=tkBUS.nvnam(nvf.listnv, nvf.listpx, nvf.listctpx,nvf.y1Chooser.getYear(),nvf.y2Chooser.getYear());
                nvf.doitable(2,nvf.getScrollPane()  );
            }
        }
        if (nvf.getCbbox().getSelectedItem().equals("Quý")){
            nvf.panel.setVisible(false);
            nvf.panel2.setVisible(false);
            nvf.panel3.setVisible(false);
            nvf.panel4.setVisible(true);
           
          
            if(nvf.y2Chooser4.getYear()<nvf.y1Chooser4.getYear() || (nvf.y2Chooser4.getYear()==nvf.y1Chooser4.getYear() && String.valueOf(nvf.qui1.getSelectedItem()).length()>String.valueOf(nvf.qui2.getSelectedItem()).length()) ){
                JOptionPane.showMessageDialog(null, "Quý kết thúc phải lớn hơn Quý bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            else{
                nvf.list=tkBUS.nvquy(nvf.listnv, nvf.listpx, nvf.listctpx,nvf.y1Chooser4.getYear(),nvf.y2Chooser4.getYear(),String.valueOf(nvf.qui1.getSelectedItem()).length()-4,String.valueOf(nvf.qui2.getSelectedItem()).length()-4);
                nvf.doitable(2,nvf.getScrollPane());
            }
          
        }
 }   
                
    
        
        

        
    
        
}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
