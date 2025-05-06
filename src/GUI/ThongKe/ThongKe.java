    package GUI.ThongKe;

import BUS.ThongKeBUS;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.SachDAO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import GUI.ThongKe.Support.itemTaskbar;
import GUI.ThongKe.ThongKeTongQuan;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class ThongKe extends JPanel {

    JTabbedPane tabbedPane;
    private JPanel tongquan, nhacungcap, khachhang, doanhthu;
    ThongKeTonKho nhapxuat;
    Color BackgroundColor = new Color(72, 118, 255); // Màu xanh
    ThongKeBUS thongkeBUS = new ThongKeBUS();

    public ThongKe() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        tongquan = new ThongKeTongQuan();
        nhapxuat = new ThongKeTonKho();
        khachhang = new ThongKeKhachHang();
        nhacungcap = new ThongKeNhaCungCap();
        doanhthu = new ThongKeDoanhThu(thongkeBUS);

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Tổng quan", tongquan);
        tabbedPane.addTab("Tồn kho", nhapxuat);
        tabbedPane.addTab("Doanh thu", doanhthu);
        tabbedPane.addTab("Nhà cung cấp", nhacungcap);
        tabbedPane.addTab("Khách hàng", khachhang);

        this.add(tabbedPane);
        tabbedPane.addChangeListener(e -> {      // Thêm action ChangeListener
            int selectedIndex = tabbedPane.getSelectedIndex();
            String title = tabbedPane.getTitleAt(selectedIndex);
            if (title.equals("Tổng quan")) {
                if (tongquan instanceof ThongKeTongQuan) {
                    String[][] getSt = {
                    {"Sách hiện có trong kho", "book.svg", Integer.toString(SachDAO.getInstance().selectAll().size())},
                    {"Khách từ trước đến nay", "stafff.svg", Integer.toString(KhachHangDAO.getInstance().selectAll().size())},
                    {"Nhân viên đang hoạt động", "customerr.svg", Integer.toString(NhanVienDAO.getInstance().selectAll().size())}};
                    ((ThongKeTongQuan) tongquan).getJp_top().removeAll();
                    itemTaskbar[] listitem = new itemTaskbar[getSt.length];
                    for (int i = 0; i < getSt.length; i++) {
                        listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][2], getSt[i][0], 0);
                        ((ThongKeTongQuan) tongquan).getJp_top().add(listitem[i]);            
                     }
                    ((ThongKeTongQuan) tongquan).refreshData();
                }
             }
            else if(title.equals("Tồn kho")){
                nhapxuat.refreshTable();
            }
            else if(title.equals("Nhà cung cấp")){
                ((ThongKeNhaCungCap) nhacungcap).refreshTable();
            }
            else if(title.equals("Khách hàng")){
               ((ThongKeKhachHang) khachhang).refreshTable();
            }else if(title.equals("Doanh thu")){
                ((ThongKeDoanhThu) doanhthu).getRefreshTKTheoNam(); // Viết tiếp bên TK doanh thu giống này
                ((ThongKeDoanhThu) doanhthu).getTabbedPane().setSelectedIndex(0);
            }
        });
        
    }
     public void getRefreshTKTongQuan(){
          String[][] getSt = {
                    {"Sách hiện có trong kho", "book.svg", Integer.toString(SachDAO.getInstance().selectAll().size())},
                    {"Khách từ trước đến nay", "stafff.svg", Integer.toString(KhachHangDAO.getInstance().selectAll().size())},
                    {"Nhân viên đang hoạt động", "customerr.svg", Integer.toString(NhanVienDAO.getInstance().selectAll().size())}};
        ((ThongKeTongQuan) tongquan).getJp_top().removeAll();
        itemTaskbar[] listitem = new itemTaskbar[getSt.length];
        for (int i = 0; i < getSt.length; i++) {
            listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][2], getSt[i][0], 0);
            ((ThongKeTongQuan) tongquan).getJp_top().add(listitem[i]);            
         }
        ((ThongKeTongQuan) tongquan).refreshData();
     }
    
    public ThongKeTonKho getNhapxuat() {
        return nhapxuat;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
    

    public JPanel getKhachhang() {
        return khachhang;
    }

    public JPanel getNhacungcap() {
        return nhacungcap;
    }

    public JPanel getDoanhthu() {
        return doanhthu;
    }
    
    
    public static void main(String[] args){
        ThongKe aKe=new ThongKe();
        aKe.setVisible(true);
    }
}
