/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThongKeDialog;

import BUS.KhachHangBUS;
import BUS.ThongKeBUS;
import DTO.KhachHangDTO;
import GUI.Dialog.KhachHangDialog.KhachHangDialogDetail;
import GUI.Dialog.ThongKeDialog.KhachHangDialog;
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
public class KhachHangController implements ActionListener,ListSelectionListener,DocumentListener{
    private KhachHangDialog khf;
    private WorkFrame wk;
    private KhachHangBUS khBUS;
    private KhachHangDialogDetail khdd;
    private ThongKeBUS tkBUS=new ThongKeBUS() ;

   
    
    public KhachHangController(KhachHangDialog nccpn,WorkFrame wk){
        this.khf=nccpn;
        this.wk=wk;
        this.khBUS= new KhachHangBUS();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JTable tableB = khf.getTable();
        
        JTable tableB2 = khf.getTable2();
 if(action.equals("chi tiết")){
        
        int selectRow = tableB.getSelectedRow();
        if (selectRow >= 0) {  // Kiểm tra xem có hàng nào đang được chọn không
             String ma = tableB.getValueAt(selectRow, 0).toString();
             for(KhachHangDTO kh : khf.listkh){
                 if(String.valueOf(kh.getMakh()).equals(ma)){
                     String ho= kh.getHokh();
                    String ten = kh.getTenkh();
                    String email = kh.getemail();
                    String ngaysinh = String.valueOf(kh.getNgaysinh());
                    String sdt = kh.getSdt();
                    
                    khdd = new KhachHangDialogDetail(wk);
            khdd.ShowInfo(ma, ho, ten, email, ngaysinh,sdt); // Cập nhật dữ liệu trước
            khdd.setVisible(true); // Hiển thị hộp thoại sau
             }
             }
            

            // Mở hộp thoại sửa và hiển thị thông tin
            
            
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một khach hang! ","Thông báo", JOptionPane.ERROR_MESSAGE);
        }
 }
 if(action.equals("thống kê")){
        
        if (khf.getCbbox().getSelectedItem().equals("Tất cả")){
            khf.doitable(1,khf.getScrollPane()  );
            khf.panel.setVisible(false);
            khf.panel2.setVisible(false);
            khf.panel3.setVisible(false);
            khf.panel4.setVisible(false);
        }
        if (((String) khf.getCbbox().getSelectedItem()).equals("Trước đến nay")){
            khf.panel.setVisible(false);
            khf.panel2.setVisible(false);
            khf.panel3.setVisible(false);
            khf.panel4.setVisible(false);
            khf.list=tkBUS.khachhangtatca(khf.listkh, khf.listpx, khf.listctpx);
            khf.doitable(2,khf.getScrollPane()  );
            
            
        }else System.out.println((String) khf.getCbbox().getSelectedItem());
        if (khf.getCbbox().getSelectedItem().equals("Ngày")){
             if((khf.aaChooser.getDate()==null || khf.bbChooser.getDate()==null)&&khf.panel.isVisible()){
                JOptionPane.showMessageDialog(null, "Hãy chọn ngày ","Thông báo", JOptionPane.ERROR_MESSAGE);
                
            }
            
            khf.panel.setVisible(true);
            khf.panel2.setVisible(false);
            khf.panel3.setVisible(false);
            khf.panel4.setVisible(false);
           
            if(khf.bbChooser.getDate()!=null && khf.aaChooser.getDate()!=null){
                if((khf.bbChooser.getDate().getTime()-khf.aaChooser.getDate().getTime()<0)){
                    JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    khf.list=tkBUS.khachhangngay(khf.listkh, khf.listpx, khf.listctpx,khf.aaChooser.getDate(), khf.bbChooser.getDate());
                    khf.doitable(2,khf.getScrollPane()  );
                }
            }
        }
        if (khf.getCbbox().getSelectedItem().equals("Tháng")){
            khf.panel.setVisible(false);
            khf.panel2.setVisible(true);
            khf.panel3.setVisible(false);
            khf.panel4.setVisible(false);
           
          
            if(khf.y2Chooser2.getYear()<khf.y1Chooser2.getYear() || (khf.y2Chooser2.getYear()==khf.y1Chooser2.getYear() && khf.m2Chooser.getMonth()<khf.m1Chooser.getMonth()) ){
                JOptionPane.showMessageDialog(null, "tháng kết thúc phải lớn hơn thang bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            else{
                khf.list=tkBUS.khachhangthang(khf.listkh, khf.listpx, khf.listctpx,khf.m1Chooser.getMonth()+1, khf.m2Chooser.getMonth()+1,khf.y1Chooser.getYear(),khf.y2Chooser.getYear());
                khf.doitable(2,khf.getScrollPane()  );
            }
           
        }
        if (khf.getCbbox().getSelectedItem().equals("Năm")){
            khf.panel.setVisible(false);
            khf.panel2.setVisible(false);
            khf.panel3.setVisible(true);
            khf.panel4.setVisible(false);
           
          
            if(khf.y2Chooser.getYear()<khf.y1Chooser.getYear()  ){
                JOptionPane.showMessageDialog(null, "năm kết thúc phải lớn hơn năm bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            else{
                khf.list=tkBUS.khachhangnam(khf.listkh, khf.listpx, khf.listctpx,khf.y1Chooser.getYear(),khf.y2Chooser.getYear());
                khf.doitable(2,khf.getScrollPane()  );
            }
        }
        if (khf.getCbbox().getSelectedItem().equals("Quý")){
            khf.panel.setVisible(false);
            khf.panel2.setVisible(false);
            khf.panel3.setVisible(false);
            khf.panel4.setVisible(true);
           
          
            if(khf.y2Chooser4.getYear()<khf.y1Chooser4.getYear() || (khf.y2Chooser4.getYear()==khf.y1Chooser4.getYear() && String.valueOf(khf.qui1.getSelectedItem()).length()>String.valueOf(khf.qui2.getSelectedItem()).length()) ){
                JOptionPane.showMessageDialog(null, "Quý kết thúc phải lớn hơn Quý bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            else{
                khf.list=tkBUS.khachhangquy(khf.listkh, khf.listpx, khf.listctpx,khf.y1Chooser4.getYear(),khf.y2Chooser4.getYear(),String.valueOf(khf.qui1.getSelectedItem()).length()-4,String.valueOf(khf.qui2.getSelectedItem()).length()-4);
                khf.doitable(2,khf.getScrollPane());
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
