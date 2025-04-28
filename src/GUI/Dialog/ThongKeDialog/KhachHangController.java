
package GUI.Dialog.ThongKeDialog;

import BUS.KhachHangBUS;
import BUS.ThongKeBUS;
import DTO.KhachHangDTO;
import GUI.Dialog.KhachHangDialog.KhachHangDialogDetail;
import GUI.Dialog.ThongKeDialog.KhachHangDialog;
import GUI.WorkFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class KhachHangController implements ActionListener,ListSelectionListener,DocumentListener,FocusListener,MouseListener{
    private KhachHangDialog khf;
    private WorkFrame wk;    
       
    public KhachHangController(KhachHangDialog nccpn,WorkFrame wk){
        this.khf=nccpn;
        this.wk=wk;      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JTable table = khf.getTable();
          
        if(action.equals("chi tiết")){
            int selectRow = table.getSelectedRow();      
            if (selectRow >= 0) {  
                String ma = table.getValueAt(selectRow, 1).toString();
                for(KhachHangDTO kh : khf.getListkh())
                    if(String.valueOf(kh.getMakh()).equals(ma)){
                        String ho= kh.getHokh();
                        String ten = kh.getTenkh();
                        String email = kh.getemail();
                        String ngaysinh = String.valueOf(kh.getNgaysinh());
                        String sdt = kh.getSdt();
                        new KhachHangDialogDetail(wk).ShowInfo2(ma, ho, ten, email, ngaysinh, sdt);
                    }               
            }
            else 
            JOptionPane.showMessageDialog(null, "Hãy chọn một khach hang! ","Thông báo", JOptionPane.ERROR_MESSAGE);       
        }
        
        if (khf.getCbbox().getSelectedItem().equals("Tất cả")){          
            khf.getNgay().setVisible(false);
            khf.getThang().setVisible(false);
            khf.getQuy().setVisible(false);
            khf.getNam().setVisible(false);
        }
        
        if (((String) khf.getCbbox().getSelectedItem()).equals("Trước đến nay")){
            khf.getNgay().setVisible(false);
            khf.getThang().setVisible(false);
            khf.getQuy().setVisible(false);
            khf.getNam().setVisible(false);
        }
        
        if (khf.getCbbox().getSelectedItem().equals("Ngày")){
            khf.getNgay().setVisible(true);
            khf.getThang().setVisible(false);
            khf.getQuy().setVisible(false);
            khf.getNam().setVisible(false);
        }
        
        if (khf.getCbbox().getSelectedItem().equals("Tháng")){
            khf.getNgay().setVisible(false);
            khf.getThang().setVisible(true);
            khf.getQuy().setVisible(false);
            khf.getNam().setVisible(false);
        }
        
        if (khf.getCbbox().getSelectedItem().equals("Quý")){
            khf.getNgay().setVisible(false);
            khf.getThang().setVisible(false);
            khf.getQuy().setVisible(true);
            khf.getNam().setVisible(false);
  }
        
        if (khf.getCbbox().getSelectedItem().equals("Năm")){
            khf.getNgay().setVisible(false);
            khf.getThang().setVisible(false);
            khf.getQuy().setVisible(false);
            khf.getNam().setVisible(true);
  }

  
      
       
    if(action.equals("thống kê")){
        if (((String) khf.getCbbox().getSelectedItem()).equals("Tất cả")){
            khf.doitable(true,khf.getList());
        }
        
        if (((String) khf.getCbbox().getSelectedItem()).equals("Trước đến nay")){
            khf.setListthongke(new ThongKeBUS().khtatca());
            khf.setList(khf.List()); 
            khf.doitable(false,khf.getList());
        }      
        
        if (khf.getCbbox().getSelectedItem().equals("Ngày")){                   
            if(khf.getNgayBatDau1().getDate().getTime()>=khf.getNgayKetThuc1().getDate().getTime())
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);      
            else{
                khf.setListthongke(new ThongKeBUS().khngay(khf.getNgayBatDau1().getDate(),khf.getNgayKetThuc1().getDate()));
                khf.setList(khf.List());
                khf.doitable(false,khf.getList());
            }
        }
        
        if (khf.getCbbox().getSelectedItem().equals("Tháng")){
            if(khf.getNamBatDau2().getYear()>khf.getNamKetThuc2().getYear() || (khf.getNamBatDau2().getYear()==khf.getNamKetThuc2().getYear() && khf.getThangBatDau2().getMonth()>=khf.getThangKetThuc2().getMonth()) )
                JOptionPane.showMessageDialog(null, "tháng kết thúc phải lớn hơn thang bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            else{
                khf.setListthongke(new ThongKeBUS().khthang(khf.getThangBatDau2().getMonth(),khf.getThangKetThuc2().getMonth(),khf.getNamBatDau2().getYear(),khf.getNamKetThuc2().getYear()));
                khf.setList(khf.List());
                khf.doitable(false,khf.getList());
            } 
        }
        
        if (khf.getCbbox().getSelectedItem().equals("Quý")){      
            if(khf.getNamBatDau3().getYear()>khf.getNamKetThuc3().getYear() || (khf.getNamBatDau3().getYear()==khf.getNamKetThuc3().getYear() && khf.getQui1().getSelectedIndex()>=khf.getQui2().getSelectedIndex())) 
                JOptionPane.showMessageDialog(null, "Quý kết thúc phải lớn hơn Quý bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);           
            else{
                khf.setListthongke(new ThongKeBUS().khquy(khf.getQui1().getSelectedIndex(),khf.getQui1().getSelectedIndex(),khf.getNamBatDau3().getYear(),khf.getNamKetThuc3().getYear()));
                khf.setList(khf.List());
                khf.doitable(false,khf.getList());
            }

          
        }
        
        if (khf.getCbbox().getSelectedItem().equals("Năm")){
            if(khf.getNamBatDau4().getYear()>=khf.getNamKetThuc4().getYear()  )
                JOptionPane.showMessageDialog(null, "năm kết thúc phải lớn hơn năm bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);          
            else{
                khf.setListthongke(new ThongKeBUS().khnam(khf.getNamBatDau4().getYear(),khf.getNamKetThuc4().getYear()));
                khf.setList(khf.List());
                khf.doitable(false,khf.getList());
            }
        }
    }        
}
    
    @Override
    public void focusGained(java.awt.event.FocusEvent evt) {
        if (khf.getTxtTimKiem().getText().equals("Tìm kiếm.....")) {
            khf.getTxtTimKiem().setText("");
            khf.getTxtTimKiem().setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(java.awt.event.FocusEvent evt) {
        if (khf.getTxtTimKiem().getText().trim().isEmpty()) {
            khf.getTxtTimKiem().setText("Tìm kiếm.....");
            khf.getTxtTimKiem().setForeground(Color.GRAY);
        }
    }
              
    @Override
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        khf.timKiemKhiDangGo();
    }

    @Override
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        khf.timKiemKhiDangGo();
    }

    @Override
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        khf.timKiemKhiDangGo();
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     @Override
    public void mousePressed(MouseEvent e) {
        // Xử lý sự kiện mousePressed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Xử lý sự kiện mouseReleased
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Xử lý sự kiện mouseEntered
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Xử lý sự kiện mouseExited
    }
    
    
     @Override
    public void mouseClicked(MouseEvent e) {
        // Lấy cột được nhấp vào
        int columnIndex = khf.getTable().getTableHeader().columnAtPoint(e.getPoint());
        
        // Kiểm tra xem cột có hợp lệ không (tránh lỗi khi nhấp vào ngoài cột)
        
        if (columnIndex != -1) {
            if(columnIndex==0){
                khf.setListsx(new ThongKeBUS().TheoSTT(khf.getListsx(), khf.t0));
                khf.sapxepTable();
                khf.t0=!khf.t0;
            }
            if(columnIndex==1){
                khf.setListsx(new ThongKeBUS().TheoMa(khf.getListsx(), khf.t1));
                khf.sapxepTable();
                khf.t1=!khf.t1;
            }
            if(columnIndex==2){
                khf.setListsx(new ThongKeBUS().TheoHoTen(khf.getListsx(), khf.t2));
                khf.sapxepTable();
                khf.t2=!khf.t2;
            }
            if(columnIndex==3){
                khf.setListsx(new ThongKeBUS().TheoHoaDon(khf.getListsx(), khf.t3));
                khf.sapxepTable();
                khf.t3=!khf.t3;
            }
            if(columnIndex==4){
                khf.setListsx(new ThongKeBUS().TheoSach(khf.getListsx(), khf.t4));
                khf.sapxepTable();
                khf.t4=!khf.t4;
            }
            if(columnIndex==5){
                khf.setListsx(new ThongKeBUS().TheoDoanhThu(khf.getListsx(), khf.t5));
                khf.sapxepTable();
                khf.t5=!khf.t5;
            }              
            if(columnIndex==6){
                khf.setListsx(new ThongKeBUS().TheoLN(khf.getListsx(), khf.t6));
                khf.sapxepTable();
                khf.t6=!khf.t6;
            }
        }
    }
}
