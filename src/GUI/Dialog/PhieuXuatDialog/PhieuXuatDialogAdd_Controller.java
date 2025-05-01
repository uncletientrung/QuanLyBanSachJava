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
import GUI.Format.NumberFormatter;

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
    }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable tableSach=PXDA.getTableChonSach();
        JTable tableChiTiet=PXDA.getTableListBan();
        if (tableSach.getSelectedRow()!=-1){
            int selectRow= tableSach.getSelectedRow();
            String maSach=tableSach.getValueAt(selectRow, 0).toString();
            SachDTO sach=new SachBUS().getSachById(maSach);
            if (sach != null) {
                PXDA.setTxfDonGia((NumberFormatter.format(sach.getDongia()))); // Lấy giá sách từ mã sách
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
       if (sukien.equals("Thêm chi tiết")){
           if(!PXDA.getTxfKhachHang().getText().trim().isEmpty() && !PXDA.getTxtSoLuong().getText().trim().isEmpty()
           && !PXDA.gettTxfDonGia().getText().trim().isEmpty()  && tableSach.getSelectedRow() != -1){
                String tenSach=tableSach.getValueAt(tableSach.getSelectedRow(), 1).toString();
                // Kiểm tra sách đã thêm vào bên tablechi tiết chưa
                for(int i=0; i< tableChiTiet.getRowCount();i++){
                    if(tableChiTiet.getValueAt(i, 0).toString().equals(tenSach)){
                        int SoLuongCurrent= Integer.parseInt(tableChiTiet.getValueAt(i, 1).toString());
                        int SoLuongCongThem=Integer.parseInt(PXDA.getTxfSoLuong().getText().toString());
                        int DonGiaSach=Integer.parseInt(NumberFormatter.formatReverse(tableChiTiet.getValueAt(i, 2).toString()));
                         tableChiTiet.setValueAt(SoLuongCurrent+SoLuongCongThem, i, 1);
                         tableChiTiet.setValueAt((SoLuongCurrent+SoLuongCongThem)*DonGiaSach, i, 3);
                         PXDA.CalcBill();
                         
                         return;
                    }
                }

                String soluong=PXDA.getTxtSoLuong().getText().toString();
                String dongia=NumberFormatter.formatReverse((PXDA.gettTxfDonGia().getText()));
                String tong = NumberFormatter.format(Integer.parseInt(soluong) * Integer.parseInt(dongia));
                if(sBUS.getSoLuongById(tableSach.getValueAt(tableSach.getSelectedRow(), 0).toString()) < Integer.parseInt(soluong)){
                    JOptionPane.showMessageDialog(null, "Số lượng vượt quá mức", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else{
                    PXDA.getDataBan().addRow(new Object[]{tenSach,soluong,
                                            NumberFormatter.format(Integer.parseInt(dongia)),tong});
                     PXDA.CalcBill();

                }
                    
           }else{
           JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
       }
       }
       if(sukien.equals("Sửa")){
           if(tableChiTiet.getSelectedRow()!=-1){
               PXDA.CalcBill();
               int selectRowV2=tableChiTiet.getSelectedRow();
               String soLuongNew= PXDA.getTxfSoLuongV2().getText().toString();
               String dongiaNew=NumberFormatter.formatReverse(PXDA.getTxfDonGiaV2().getText().toString());
               String tong = NumberFormatter.format(Integer.parseInt(soLuongNew) * Integer.parseInt(dongiaNew));
               tableChiTiet.setValueAt(Integer.parseInt(soLuongNew), selectRowV2, 1);
               tableChiTiet.setValueAt(NumberFormatter.format(Integer.parseInt(dongiaNew)), selectRowV2, 2);
               tableChiTiet.setValueAt(tong, selectRowV2, 3);
               PXDA.getTxfDonGiaV2().setText(dongiaNew);
               PXDA.getTxfThanhTienV2().setText(tong);
               
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
                if(tableChiTiet.getRowCount()==0){
                     PXDA.getTxfTenKhuyenMai().setText("");
                     PXDA.getTxfPhanTramGiam().setText("");
                     PXDA.setUpDefault();
                     
                }
                
                
                PXDA.CalcBill();
            }else{
           JOptionPane.showMessageDialog(null, "Hãy chọn mục cần xóa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
       }
       if(sukien.equals("Làm mới")){
           if(PXDA.getDataBan().getRowCount()!=0){
                PXDA.getDataBan().setRowCount(0);
                 PXDA.CalcBill();
                // Sau khi ấn xóa  xong thì set tất cả về trạng thái ban đầu
                PXDA.setUpDefault();
                PXDA.setUpBlock(true); // truyền lại True để mở khóa
                PXDA.refreshTableChonSach();
           }else{
               JOptionPane.showMessageDialog(null, "Danh sách bán chưa có gì để làm mới", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
               Date current_day_type_date= PXDA.getDateChooser1().getDate();
               Timestamp current_day=new Timestamp(current_day_type_date.getTime());

                // Gọi mã phiếu xuất auto tiếp theo 
               int maPhieuXuat = pxDAO.getAutoIncrement();
                // Gọi tổng tiền và trạng thái auto là 1
                int tongtien=Integer.parseInt(NumberFormatter.formatReverse(PXDA.getTxfThanhToan().getText().toString().trim()));
                int trangthai=1;
               for(int i=0;i<PXDA.getDataBan().getRowCount();i++){
                   String tensach=PXDA.getDataBan().getValueAt(i, 0).toString();
                   int soluong=Integer.parseInt(PXDA.getDataBan().getValueAt(i, 1).toString());
                   int dongia=Integer.parseInt(NumberFormatter.formatReverse(PXDA.getDataBan().getValueAt(i, 2).toString()));
                   // Chuyển đổi tên sách thành mã sách từ hàm BUS
                   String masach=sBUS.getIdSachByNameSach(tensach);
                   // Tạo chi tiết phiếu sách mới
                   ChiTietPhieuXuatDTO ctpxNew=new ChiTietPhieuXuatDTO(maPhieuXuat, masach, soluong, dongia);
                   ListCTPhieuXuat.add(ctpxNew);
               }
               // Sau khi chạy for xong đủ hết chi tiết phiếu xuất thì thêm vào database
                phieuxuat= new PhieuXuatDTO(maPhieuXuat, maNV, maKH, current_day, (long)tongtien, trangthai);
                pxBUS.insert(phieuxuat, ListCTPhieuXuat);
                PXDA.refreshTableChonSach();
                
                
               JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công!", "Thông báo", JOptionPane.NO_OPTION);

                PXDA.setUpBlock(false); // Truyền False vào để Chặn các nút khi khi ấn thêm phiếu mở lại khi ấn xóa tất cả
           }else{
               JOptionPane.showMessageDialog(null, "Danh sách bán chưa có gì để xuất hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
           }

       }
    }
    
    
}
