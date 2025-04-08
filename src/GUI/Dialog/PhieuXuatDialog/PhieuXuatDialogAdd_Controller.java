/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuXuatDialog;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import BUS.SachBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.KhachHangDTO;
import DTO.PhieuXuatDTO;
import DTO.SachDTO;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuXuatDTO;
import GUI.View.PhieuXuatPanel;
import GUI.WorkFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class PhieuXuatDialogAdd_Controller implements DocumentListener,ListSelectionListener,ActionListener{
    public PhieuXuatDialogAdd PXDA;
    private NhanVienBUS nvBUS=new NhanVienBUS();
    private KhachHangBUS khBUS=new KhachHangBUS();
    private PhieuXuatBUS pxBUS=new PhieuXuatBUS();
    private PhieuXuatDAO pxDAO=new PhieuXuatDAO();
    private SachBUS sBUS=new SachBUS();

    public PhieuXuatDialogAdd_Controller(PhieuXuatDialogAdd PXDA){
        this.PXDA=PXDA;

    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }else if(e.getDocument() == PXDA.getTxfGiamGia().getDocument()){
        PXDA.CalcBill();
    }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }else if(e.getDocument() == PXDA.getTxfGiamGia().getDocument()){
        PXDA.CalcBill();
    }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }else if(e.getDocument() == PXDA.getTxfGiamGia().getDocument()){
        PXDA.CalcBill();
    }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable tableSach=PXDA.getTableChonSach();
        JTable tableChiTiet=PXDA.getTableListBan();
        if (tableSach.getSelectedRow()!=-1){
            int selectRow= tableSach.getSelectedRow();
            String maSach=tableSach.getValueAt(selectRow, 0).toString();
            SachDTO sach=new SachBUS().getSachById(Integer.parseInt(maSach));
            if (sach != null) {
                PXDA.setTxfDonGia(String.valueOf(sach.getDongia())); // Lấy giá sách từ mã sách
        }
        }
        if(tableChiTiet.getSelectedRow()!=-1){
            int selectRowV2=tableChiTiet.getSelectedRow();
            String tensach=tableChiTiet.getValueAt(selectRowV2, 0).toString();
            String soluong=tableChiTiet.getValueAt(selectRowV2, 1).toString();
            String dongia=tableChiTiet.getValueAt(selectRowV2, 2).toString();
            String tong=tableChiTiet.getValueAt(selectRowV2, 3).toString();
            PXDA.getTxfTenSachV2().setText(tensach);
            PXDA.getTxfSoLuongV2().setText(soluong);
            PXDA.getTxfDonGiaV2().setText(dongia);
            PXDA.getTxfThanhTienV2().setText(tong);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String sukien=e.getActionCommand();
       JTable tableSach=PXDA.getTableChonSach();
       JTable tableChiTiet=PXDA.getTableListBan();
       if (sukien.equals("Thêm")){
           if(!PXDA.getTxfKhachHang().getText().trim().isEmpty() 
                && !PXDA.getTxtSoLuong().getText().trim().isEmpty()
                && !PXDA.gettTxfDonGia().getText().trim().isEmpty()
                && tableSach.getSelectedRow() != -1){
                    String tenSach=tableSach.getValueAt(tableSach.getSelectedRow(), 1).toString();
                    String soluong=PXDA.getTxtSoLuong().getText();
                    String dongia=PXDA.gettTxfDonGia().getText();
                    String tong = String.valueOf(Integer.parseInt(soluong) * Integer.parseInt(dongia));
                    PXDA.getDataBan().addRow(new Object[]{tenSach,soluong,dongia,tong});
                    PXDA.CalcBill();
           }else{
           JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
       }
       }
       if(sukien.equals("Sửa")){
           if(tableChiTiet.getSelectedRow()!=-1){
               int selectRowV2=tableChiTiet.getSelectedRow();
               String soLuongNew= PXDA.getTxfSoLuongV2().getText().toString();
               String dongiaNew=PXDA.getTxfDonGiaV2().getText().toString();
               String tong = String.valueOf(Integer.parseInt(soLuongNew) * Integer.parseInt(dongiaNew));
               tableChiTiet.setValueAt(soLuongNew, selectRowV2, 1);
               tableChiTiet.setValueAt(dongiaNew, selectRowV2, 2);
               tableChiTiet.setValueAt(tong, selectRowV2, 3);
               PXDA.getTxfDonGiaV2().setText(dongiaNew);
               PXDA.getTxfThanhTienV2().setText(tong);
               PXDA.CalcBill();
           }else{
           JOptionPane.showMessageDialog(null, "Hãy chọn mục cần sửa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
             }
       
       }
       if (sukien.equals("Xóa")){
            if(tableChiTiet.getSelectedRow()!=-1){
                int selectRowV2=tableChiTiet.getSelectedRow();
                PXDA.getDataBan().removeRow(selectRowV2);
                // Gán các txf chỗ chỉnh sửa về rỗng sau khi update
                PXDA.getTxfDonGiaV2().setText("");
                PXDA.getTxfSoLuongV2().setText("");
                PXDA.getTxfThanhTienV2().setText("");
                PXDA.getTxfTenSachV2().setText("");
                PXDA.CalcBill();
            }else{
           JOptionPane.showMessageDialog(null, "Hãy chọn mục cần xóa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
       }
       if(sukien.equals("Xóa tất cả")){
           if(PXDA.getDataBan().getRowCount()!=0){
                JOptionPane.showMessageDialog(null, "** Viết 1 cái DiaLog cảnh báo Yes/No ở đây!", "Thông báo", JOptionPane.NO_OPTION);
                PXDA.getDataBan().setRowCount(0);
                // Gán các txf chỗ chỉnh sửa về rỗng sau khi update
                 PXDA.getTxfDonGiaV2().setText("");
                 PXDA.getTxfSoLuongV2().setText("");
                 PXDA.getTxfThanhTienV2().setText("");
                 PXDA.getTxfTenSachV2().setText("");
                 PXDA.CalcBill();
           }else{
               JOptionPane.showMessageDialog(null, "Danh sách bán chưa có gì để xóa", "Thông báo", JOptionPane.ERROR_MESSAGE);
           }
           
       }
       if(sukien.equals("Thêm phiếu")){
           ArrayList<ChiTietPhieuXuatDTO> ListCTPhieuXuat=new ArrayList<>();
           PhieuXuatDTO phieuxuat;
           if(PXDA.getDataBan().getRowCount()!=0){
               // Lọc mã nhân viên từ tên nhân viên
               String tenNv=PXDA.getTxfNhanVien().getText().toString().trim();
               int maNV=nvBUS.getIdNvByNameNv(tenNv);
               // Lọc mã khách từ SDT
               String sdt=PXDA.getTxfSDT().getText().toString().trim();
               KhachHangDTO KH=khBUS.getKHBySDT(sdt);
               int maKH=KH.getMakh();
               // Chuyển JDateChosser sang Date rồi sang TimeStamp
//               Date current_day_type_date= PXDA.getDateChooser1().getDate();
//               Timestamp current_day=new Timestamp(current_day_type_date.getTime());
               //Xài LocalDate để thay thế cho JDate bị lỗi
                LocalDate  current_day_type_date = LocalDate.now();
               // Chuyển từ LocalDate sang LocalDateTime
                LocalDateTime current_day_type_datetime = current_day_type_date.atStartOfDay();
                // Chuyển từ LocalDateTime sang Timestamp
                Timestamp current_day = Timestamp.valueOf(current_day_type_datetime);
                // Gọi mã phiếu xuất auto tiếp theo 
               int maPhieuXuat = pxDAO.getAutoIncrement();
                // Gọi tổng tiền và trạng thái auto là 1
                int tongtien=Integer.parseInt(PXDA.getTxfThanhToan().getText().toString().trim());
                int trangthai=1;
               for(int i=0;i<PXDA.getDataBan().getRowCount();i++){
                   String tensach=PXDA.getDataBan().getValueAt(i, 0).toString();
                   int soluong=Integer.parseInt(PXDA.getDataBan().getValueAt(i, 1).toString());
                   int dongia=Integer.parseInt(PXDA.getDataBan().getValueAt(i, 2).toString());
                   // Chuyển đổi tên sách thành mã sách từ hàm BUS
                   int masach=sBUS.getIdSachByNameSach(tensach);
                   // Tạo chi tiết phiếu sách mới
                   ChiTietPhieuXuatDTO ctpxNew=new ChiTietPhieuXuatDTO(maPhieuXuat, masach, soluong, dongia);
                   ListCTPhieuXuat.add(ctpxNew);
               }
               // Sau khi chạy for xong đủ hết chi tiết phiếu nhập thì thêm vào database
                phieuxuat= new PhieuXuatDTO(maPhieuXuat, maNV, maKH, current_day, (long)tongtien, trangthai);
                pxBUS.insert(phieuxuat, ListCTPhieuXuat);

                
               JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công!", "Thông báo", JOptionPane.NO_OPTION);
               
           }else{
               JOptionPane.showMessageDialog(null, "Danh sách bán chưa có gì để xuất hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
           }

       }
    }
    
}
