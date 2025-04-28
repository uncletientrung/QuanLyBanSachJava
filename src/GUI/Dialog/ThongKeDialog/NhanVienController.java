
package GUI.Dialog.ThongKeDialog;

import BUS.NhanVienBUS;
import BUS.ThongKeBUS;
import DTO.NhanVienDTO;
import GUI.Dialog.NhanVienDialog.NhanVienDialogDetail;
import GUI.Dialog.ThongKeDialog.NhanVienDialog;
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



public class NhanVienController implements ActionListener,ListSelectionListener,DocumentListener,FocusListener,MouseListener{
    private NhanVienDialog nvf;
    private WorkFrame wk;    
       
    public NhanVienController(NhanVienDialog nccpn,WorkFrame wk){
        this.nvf=nccpn;
        this.wk=wk;      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JTable table = nvf.getTable();
          
        if(action.equals("chi tiết")){
            int selectRow = table.getSelectedRow();      
            if (selectRow >= 0) {  
                String ma = table.getValueAt(selectRow, 1).toString();
                for(NhanVienDTO nv : nvf.getListnv())
                    if(String.valueOf(nv.getManv()).equals(ma)){
                        String ho= nv.getHonv();
                        String ten = nv.getTennv();
                        String gt = (nv.getGioitinh()<1) ? "Nữ" : "Nam";
                        String ngaysinh = String.valueOf(nv.getNgaysinh());
                        String sdt = nv.getSdt();
                        new NhanVienDialogDetail(wk).ShowInfo2(ma, ho, ten, gt, sdt,ngaysinh, String.valueOf(1));
                    }               
            }
            else 
            JOptionPane.showMessageDialog(null, "Hãy chọn một nvach hang! ","Thông báo", JOptionPane.ERROR_MESSAGE);       
        }
        
        if (nvf.getCbbox().getSelectedItem().equals("Tất cả")){          
            nvf.getNgay().setVisible(false);
            nvf.getThang().setVisible(false);
            nvf.getQuy().setVisible(false);
            nvf.getNam().setVisible(false);
        }
        
        if (((String) nvf.getCbbox().getSelectedItem()).equals("Trước đến nay")){
            nvf.getNgay().setVisible(false);
            nvf.getThang().setVisible(false);
            nvf.getQuy().setVisible(false);
            nvf.getNam().setVisible(false);
        }
        
        if (nvf.getCbbox().getSelectedItem().equals("Ngày")){
            nvf.getNgay().setVisible(true);
            nvf.getThang().setVisible(false);
            nvf.getQuy().setVisible(false);
            nvf.getNam().setVisible(false);
        }
        
        if (nvf.getCbbox().getSelectedItem().equals("Tháng")){
            nvf.getNgay().setVisible(false);
            nvf.getThang().setVisible(true);
            nvf.getQuy().setVisible(false);
            nvf.getNam().setVisible(false);
        }
        
        if (nvf.getCbbox().getSelectedItem().equals("Quý")){
            nvf.getNgay().setVisible(false);
            nvf.getThang().setVisible(false);
            nvf.getQuy().setVisible(true);
            nvf.getNam().setVisible(false);
  }
        
        if (nvf.getCbbox().getSelectedItem().equals("Năm")){
            nvf.getNgay().setVisible(false);
            nvf.getThang().setVisible(false);
            nvf.getQuy().setVisible(false);
            nvf.getNam().setVisible(true);
  }

  
      
       
    if(action.equals("thống kê")){
        if (((String) nvf.getCbbox().getSelectedItem()).equals("Tất cả")){
            nvf.doitable(true,nvf.getList());
        }
        
        if (((String) nvf.getCbbox().getSelectedItem()).equals("Trước đến nay")){
            nvf.setListthongke(new ThongKeBUS().nvtatca());
            nvf.setList(nvf.List()); 
            nvf.doitable(false,nvf.getList());
        }      
        
        if (nvf.getCbbox().getSelectedItem().equals("Ngày")){                   
            if(nvf.getNgayBatDau1().getDate().getTime()>=nvf.getNgayKetThuc1().getDate().getTime())
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);      
            else{
                nvf.setListthongke(new ThongKeBUS().nvngay(nvf.getNgayBatDau1().getDate(),nvf.getNgayKetThuc1().getDate()));
                nvf.setList(nvf.List());
                nvf.doitable(false,nvf.getList());
            }
        }
        
        if (nvf.getCbbox().getSelectedItem().equals("Tháng")){
            if(nvf.getNamBatDau2().getYear()>nvf.getNamKetThuc2().getYear() || (nvf.getNamBatDau2().getYear()==nvf.getNamKetThuc2().getYear() && nvf.getThangBatDau2().getMonth()>=nvf.getThangKetThuc2().getMonth()) )
                JOptionPane.showMessageDialog(null, "tháng kết thúc phải lớn hơn thang bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);
            else{
                nvf.setListthongke(new ThongKeBUS().nvthang(nvf.getThangBatDau2().getMonth(),nvf.getThangKetThuc2().getMonth(),nvf.getNamBatDau2().getYear(),nvf.getNamKetThuc2().getYear()));
                nvf.setList(nvf.List());
                nvf.doitable(false,nvf.getList());
            } 
        }
        
        if (nvf.getCbbox().getSelectedItem().equals("Quý")){      
            if(nvf.getNamBatDau3().getYear()>nvf.getNamKetThuc3().getYear() || (nvf.getNamBatDau3().getYear()==nvf.getNamKetThuc3().getYear() && nvf.getQui1().getSelectedIndex()>=nvf.getQui2().getSelectedIndex())) 
                JOptionPane.showMessageDialog(null, "Quý kết thúc phải lớn hơn Quý bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);           
            else{
                nvf.setListthongke(new ThongKeBUS().nvquy(nvf.getQui1().getSelectedIndex(),nvf.getQui1().getSelectedIndex(),nvf.getNamBatDau3().getYear(),nvf.getNamKetThuc3().getYear()));
                nvf.setList(nvf.List());
                nvf.doitable(false,nvf.getList());
            }

          
        }
        
        if (nvf.getCbbox().getSelectedItem().equals("Năm")){
            if(nvf.getNamBatDau4().getYear()>=nvf.getNamKetThuc4().getYear()  )
                JOptionPane.showMessageDialog(null, "năm kết thúc phải lớn hơn năm bắt đầu","Thông báo", JOptionPane.ERROR_MESSAGE);          
            else{
                nvf.setListthongke(new ThongKeBUS().nvnam(nvf.getNamBatDau4().getYear(),nvf.getNamKetThuc4().getYear()));
                nvf.setList(nvf.List());
                nvf.doitable(false,nvf.getList());
            }
        }
    }        
}
    
    @Override
    public void focusGained(java.awt.event.FocusEvent evt) {
        if (nvf.getTxtTimKiem().getText().equals("Tìm kiếm.....")) {
            nvf.getTxtTimKiem().setText("");
            nvf.getTxtTimKiem().setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(java.awt.event.FocusEvent evt) {
        if (nvf.getTxtTimKiem().getText().trim().isEmpty()) {
            nvf.getTxtTimKiem().setText("Tìm kiếm.....");
            nvf.getTxtTimKiem().setForeground(Color.GRAY);
        }
    }
              
    @Override
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        nvf.timKiemKhiDangGo();
    }

    @Override
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        nvf.timKiemKhiDangGo();
    }

    @Override
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        nvf.timKiemKhiDangGo();
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
        int columnIndex = nvf.getTable().getTableHeader().columnAtPoint(e.getPoint());
        
        // Kiểm tra xem cột có hợp lệ nvông (tránh lỗi nvi nhấp vào ngoài cột)
        
        if (columnIndex != -1) {
            if(columnIndex==0){
                nvf.setListsx(new ThongKeBUS().TheoSTT(nvf.getList(), nvf.t0));
                nvf.sapxepTable();
                nvf.t0=!nvf.t0;
            }
            if(columnIndex==1){
                nvf.setListsx(new ThongKeBUS().TheoMa(nvf.getList(), nvf.t1));
                nvf.sapxepTable();
                nvf.t1=!nvf.t1;
            }
            if(columnIndex==2){
                nvf.setListsx(new ThongKeBUS().TheoHoTen(nvf.getList(), nvf.t2));
                nvf.sapxepTable();
                nvf.t2=!nvf.t2;
            }
            if(columnIndex==3){
                nvf.setListsx(new ThongKeBUS().TheoHoaDon(nvf.getList(), nvf.t3));
                nvf.sapxepTable();
                nvf.t3=!nvf.t3;
            }
            if(columnIndex==4){
                nvf.setListsx(new ThongKeBUS().TheoSach(nvf.getList(), nvf.t4));
                nvf.sapxepTable();
                nvf.t4=!nvf.t4;
            }
            if(columnIndex==5){
                nvf.setListsx(new ThongKeBUS().TheoDoanhThu(nvf.getList(), nvf.t5));
                nvf.sapxepTable();
                nvf.t5=!nvf.t5;
            }              
            if(columnIndex==6){
                nvf.setListsx(new ThongKeBUS().TheoLN(nvf.getList(), nvf.t6));
                nvf.sapxepTable();
                nvf.t6=!nvf.t6;
            }
        }
    }
}
