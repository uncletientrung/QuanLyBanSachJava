package GUI.ThongKe;

import BUS.ThongKeBUS;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeDoanhThu extends JPanel {

    JTabbedPane tabbedPane;
    ThongKeDoanhThuTrongThang thongketrongthang;
    ThongKeDoanhThuTungNam thongketungnam;
    ThongKeDoanhThuTungThang thongkedoanhthutungthang;
    ThongKeDoanhThuTuNgayDenNgay thongkedoanhthutungaydenngay;
    Color BackgroundColor = new Color(72, 118, 255); // Màu xanh
    ThongKeBUS thongkeBUS;

    public ThongKeDoanhThu(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        thongketrongthang = new ThongKeDoanhThuTrongThang();
        thongketungnam = new ThongKeDoanhThuTungNam();
        thongkedoanhthutungthang = new ThongKeDoanhThuTungThang();
        thongkedoanhthutungaydenngay = new ThongKeDoanhThuTuNgayDenNgay();

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Thống kê theo năm", thongketungnam);
        tabbedPane.addTab("Thống kê từng tháng trong năm", thongkedoanhthutungthang);
        tabbedPane.addTab("Thống kê từng ngày trong tháng", thongketrongthang);
        tabbedPane.addTab("Thống kê từ ngày đến ngày", thongkedoanhthutungaydenngay);

        this.add(tabbedPane);
        tabbedPane.addChangeListener(e -> {      // Thêm action ChangeListener
            int selectedIndex = tabbedPane.getSelectedIndex();
            String title = tabbedPane.getTitleAt(selectedIndex);
            if(title.equals("Thống kê theo năm")){
                thongketungnam.refreshTable();
            }else if(title.equals("Thống kê từng tháng trong năm")){
                thongkedoanhthutungthang.refreshTable();
            }else if(title.equals("Thống kê từng ngày trong tháng")){
                thongketrongthang.refreshTable();
            }else if(title.equals("Thống kê từ ngày đến ngày")){
                thongkedoanhthutungaydenngay.refreshTable();
            }
        
        });
        
    }
    public void getRefreshTKTheoNam(){
            thongketungnam.refreshTable();
        }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
    
}
