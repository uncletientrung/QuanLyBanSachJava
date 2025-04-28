
package GUI.Dialog.ThongKeDialog;

import BUS.SachBUS;
import BUS.ThongKeBUS;
import DTO.SachDTO;
import GUI.Dialog.BookDialog.BookDialogDetail;

import GUI.Dialog.ThongKeDialog.SachDialog;
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



public class SachController implements ActionListener,ListSelectionListener,DocumentListener,FocusListener,MouseListener{
    private SachDialog sf;
    private WorkFrame wk;    
       
    public SachController(SachDialog nccpn,WorkFrame wk){
        this.sf=nccpn;
        this.wk=wk;      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JTable table = sf.getTable();
          
        if(action.equals("chi tiết")){
            int selectRow = table.getSelectedRow();      
            if (selectRow >= 0) {  
                String ma = table.getValueAt(selectRow, 1).toString();
                for(SachDTO s : sf.getLists())
                    if(String.valueOf(s.getMasach()).equals(ma)){
                        
                        String ten = s.getTensach();
                        String nxb=String.valueOf(s.getManxb());
                        String tg=String.valueOf(s.getMatacgia());
                        String tl=String.valueOf(s.getMatheloai());
                        String slt=String.valueOf(s.getSoluongton());
                         String nam=String.valueOf(s.getNamxuatban());
                          String dongia=String.valueOf(s.getDongia());
                        new BookDialogDetail(wk).ShowInfo2(ma,  ten, nxb, tg, tl,slt,nam,dongia);
                    }               
            }
            else 
            JOptionPane.showMessageDialog(null, "Hãy chọn một sach hang! ","Thông báo", JOptionPane.ERROR_MESSAGE);       
        }
        
        if (sf.getCbbox().getSelectedItem().equals("Tất cả")){          
            sf.getNgay().setVisible(false);
            sf.getThang().setVisible(false);
            sf.getQuy().setVisible(false);
            sf.getNam().setVisible(false);
        }
        
        if (((String) sf.getCbbox().getSelectedItem()).equals("Trước đến nay")){
            sf.getNgay().setVisible(false);
            sf.getThang().setVisible(false);
            sf.getQuy().setVisible(false);
            sf.getNam().setVisible(false);
        }
        
        if (sf.getCbbox().getSelectedItem().equals("Ngày")){
            sf.getNgay().setVisible(true);
            sf.getThang().setVisible(false);
            sf.getQuy().setVisible(false);
            sf.getNam().setVisible(false);
        }
        
        if (sf.getCbbox().getSelectedItem().equals("Tháng")){
            sf.getNgay().setVisible(false);
            sf.getThang().setVisible(true);
            sf.getQuy().setVisible(false);
            sf.getNam().setVisible(false);
        }
        
        if (sf.getCbbox().getSelectedItem().equals("Quý")){
            sf.getNgay().setVisible(false);
            sf.getThang().setVisible(false);
            sf.getQuy().setVisible(true);
            sf.getNam().setVisible(false);
  }
        
        if (sf.getCbbox().getSelectedItem().equals("Năm")){
            sf.getNgay().setVisible(false);
            sf.getThang().setVisible(false);
            sf.getQuy().setVisible(false);
            sf.getNam().setVisible(true);
  }

  
      
       
    if(action.equals("thống kê")){
        if (((String) sf.getCbbox().getSelectedItem()).equals("Tất cả")){
            sf.doitable(true,sf.getList());
        }
        
        if (((String) sf.getCbbox().getSelectedItem()).equals("Trước đến nay")){
            sf.setListthongke(new ThongKeBUS().statca());
            sf.setList(sf.List()); 
            sf.doitable(false,sf.getList());
        }      
        
        if (sf.getCbbox().getSelectedItem().equals("Ngày")){                   
            if(sf.getNgayBatDau1().getDate().getTime()>=sf.getNgayKetThuc1().getDate().getTime())
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);      
            else{
                sf.setListthongke(new ThongKeBUS().sngay(sf.getNgayBatDau1().getDate(),sf.getNgayKetThuc1().getDate()));
                sf.setList(sf.List());
                sf.doitable(false,sf.getList());
            }
        }
        
        if (sf.getCbbox().getSelectedItem().equals("Tháng")){
            if(sf.getNamBatDau2().getYear()>sf.getNamKetThuc2().getYear() || (sf.getNamBatDau2().getYear()==sf.getNamKetThuc2().getYear() && sf.getThangBatDau2().getMonth()>=sf.getThangKetThuc2().getMonth()) )
                JOptionPane.showMessageDialog(null, "tháng kết thúc phải lớn hơn thang bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            else{
                sf.setListthongke(new ThongKeBUS().sthang(sf.getThangBatDau2().getMonth(),sf.getThangKetThuc2().getMonth(),sf.getNamBatDau2().getYear(),sf.getNamKetThuc2().getYear()));
                sf.setList(sf.List());
                sf.doitable(false,sf.getList());
            } 
        }
        
        if (sf.getCbbox().getSelectedItem().equals("Quý")){      
            if(sf.getNamBatDau3().getYear()>sf.getNamKetThuc3().getYear() || (sf.getNamBatDau3().getYear()==sf.getNamKetThuc3().getYear() && sf.getQui1().getSelectedIndex()>=sf.getQui2().getSelectedIndex())) 
                JOptionPane.showMessageDialog(null, "Quý kết thúc phải lớn hơn Quý bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);           
            else{
                sf.setListthongke(new ThongKeBUS().squy(sf.getQui1().getSelectedIndex(),sf.getQui1().getSelectedIndex(),sf.getNamBatDau3().getYear(),sf.getNamKetThuc3().getYear()));
                sf.setList(sf.List());
                sf.doitable(false,sf.getList());
            }

          
        }
        
        if (sf.getCbbox().getSelectedItem().equals("Năm")){
            if(sf.getNamBatDau4().getYear()>=sf.getNamKetThuc4().getYear()  )
                JOptionPane.showMessageDialog(null, "năm kết thúc phải lớn hơn năm bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);          
            else{
                sf.setListthongke(new ThongKeBUS().snam(sf.getNamBatDau4().getYear(),sf.getNamKetThuc4().getYear()));
                sf.setList(sf.List());
                sf.doitable(false,sf.getList());
            }
        }
    }        
}
    
    @Override
    public void focusGained(java.awt.event.FocusEvent evt) {
        if (sf.getTxtTimKiem().getText().equals("Tìm kiếm.....")) {
            sf.getTxtTimKiem().setText("");
            sf.getTxtTimKiem().setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(java.awt.event.FocusEvent evt) {
        if (sf.getTxtTimKiem().getText().trim().isEmpty()) {
            sf.getTxtTimKiem().setText("Tìm kiếm.....");
            sf.getTxtTimKiem().setForeground(Color.GRAY);
        }
    }
              
    @Override
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        sf.timKiemKhiDangGo();
    }

    @Override
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        sf.timKiemKhiDangGo();
    }

    @Override
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        sf.timKiemKhiDangGo();
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
        int columnIndex = sf.getTable().getTableHeader().columnAtPoint(e.getPoint());
        
        // Kiểm tra xem cột có hợp lệ sông (tránh lỗi si nhấp vào ngoài cột)
        
        if (columnIndex != -1) {
            if(columnIndex==0){
                sf.setListsx(new ThongKeBUS().TheoSTT(sf.getList(), sf.t0));
                sf.sapxepTable();
                sf.t0=!sf.t0;
            }
            if(columnIndex==1){
                sf.setListsx(new ThongKeBUS().TheoMas(sf.getList(), sf.t1));
                sf.sapxepTable();
                sf.t1=!sf.t1;
            }
            if(columnIndex==2){
                sf.setListsx(new ThongKeBUS().TheoHoTen(sf.getList(), sf.t2));
                sf.sapxepTable();
                sf.t2=!sf.t2;
            }
            if(columnIndex==3){
                sf.setListsx(new ThongKeBUS().TheoHoaDon(sf.getList(), sf.t3));
                sf.sapxepTable();
                sf.t3=!sf.t3;
            }
            if(columnIndex==4){
                sf.setListsx(new ThongKeBUS().TheoSach(sf.getList(), sf.t4));
                sf.sapxepTable();
                sf.t4=!sf.t4;
            }
            if(columnIndex==5){
                sf.setListsx(new ThongKeBUS().TheoDoanhThu(sf.getList(), sf.t5));
                sf.sapxepTable();
                sf.t5=!sf.t5;
            }              
            if(columnIndex==6){
                sf.setListsx(new ThongKeBUS().TheoLN(sf.getList(), sf.t6));
                sf.sapxepTable();
                sf.t6=!sf.t6;
            }
        }
    }
}
