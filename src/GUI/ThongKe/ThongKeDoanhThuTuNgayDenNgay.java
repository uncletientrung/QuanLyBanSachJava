package GUI.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeTonKhoDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import GUI.ThongKe.Support.Chart;
import GUI.ThongKe.Support.Formater;
import GUI.ThongKe.Support.JTableExporter;
import GUI.ThongKe.Support.PanelBorderRadius;

import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class ThongKeDoanhThuTuNgayDenNgay extends JPanel {

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    ArrayList<ThongKeTonKhoDTO> listSp;
    ThongKeBUS thongkeBUS;

    Chart chart;
    private JDateChooser dateFrom;
    private JDateChooser dateTo;
    private JButton btnThongKe, btnReset, btnExport;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    private Font font=new Font("Arial", Font.BOLD, 12);
    private Date currentDate= new Date();
    public ThongKeDoanhThuTuNgayDenNgay() {
        
        listSp = new ThongKeBUS().getTonKho();
        initComponent();
        loadThongKeTungNgayTrongThang(currentDate, currentDate);

    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblFrom = new JLabel("Từ ngày");
        lblFrom.setFont(font);
        dateFrom = new JDateChooser();
        dateFrom.setPreferredSize(new Dimension(100, 25));
        dateFrom.setDateFormatString("dd/MM/yyyy");
        JLabel lblTo = new JLabel("Đến ngày");
        lblTo.setFont(font);
        dateTo = new JDateChooser();
        dateTo.setPreferredSize(new Dimension(100, 25));
        dateTo.setDateFormatString("dd/MM/yyyy");
        btnThongKe = createButton("Thống kê",new Color(72, 118, 255));
        btnReset = createButton("Làm mới",new Color(72, 118, 255));
        btnExport = createButton("Xuất Excel", new Color(76, 175, 80));
        pnl_top.add(lblFrom);
        pnl_top.add(dateFrom);
        pnl_top.add(lblTo);
        pnl_top.add(dateTo);
        pnl_top.add(btnThongKe);
        pnl_top.add(btnReset);
        pnl_top.add(btnExport);



        dateFrom.addPropertyChangeListener("date", e -> {
            Date date = (Date) e.getNewValue();
            try {
                if (validateSelectDate()) {
                }
            } catch (ParseException ex) {
                Logger.getLogger(ThongKeDoanhThuTuNgayDenNgay.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        dateTo.addPropertyChangeListener("date", e -> {
            Date date = (Date) e.getNewValue();
            try {
                if (validateSelectDate()) {
                }
            } catch (ParseException ex) {
                Logger.getLogger(ThongKeDoanhThuTuNgayDenNgay.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Ngày", "Chi phí", "Doanh thu", "Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableThongKe.setModel(tblModel);
        tableThongKe.setRowHeight(37);
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);
        scrollTableThongKe.setViewportView(tableThongKe);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);
        tableThongKe.setFocusable(false);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 350));
        this.add(pnl_top, BorderLayout.NORTH);
        this.add(scrollTableThongKe, BorderLayout.CENTER);
        
        dateTo.setDate(currentDate);
        dateFrom.setDate(currentDate);

        btnExport.addActionListener((ActionEvent e) -> {
            try {
                JTableExporter.exportJTableToExcel(tableThongKe);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeDoanhThuTuNgayDenNgay.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validateSelectDate()) {
                        if (dateFrom.getDate() != null && dateTo.getDate() != null) {
                            
                            loadThongKeTungNgayTrongThang(dateFrom.getDate(), dateTo.getDate());
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ thông tin");
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ThongKeDoanhThuTuNgayDenNgay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateFrom.setDate(currentDate);
                dateTo.setDate(currentDate);
                loadThongKeTungNgayTrongThang(dateFrom.getDate(), dateTo.getDate());
            }

        });
    }

    public boolean validateSelectDate() throws ParseException {     
    Date time_start = dateFrom.getDate();
    Date time_end = dateTo.getDate();
    Date current_date = new Date();

    if (time_start == null || time_end == null) {
        
        return false;
    }

    LocalDate dateS = time_start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate dateE = time_end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    if (time_start.after(current_date)) {
        JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
        dateFrom.setDate(current_date);
        return false;
    }
    if (time_end.after(current_date)) {
        JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
        dateTo.setDate(current_date);
        return false;
    }
    if (dateS.isAfter(dateE)) {
        System.err.println(time_start);
        System.err.println(time_end);
        JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
        dateTo.setDate(current_date);
        return false;
    }
    return true;
}


    public void loadThongKeTungNgayTrongThang(Date start, Date end) {
        ArrayList<ThongKeTungNgayTrongThangDTO> list = new ThongKeBUS().getThongKeTuNgayDenNgay(start, end);
        tblModel.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            tblModel.addRow(new Object[]{
                list.get(i).getNgay(), Formater.FormatVND(list.get(i).getChiphi()), Formater.FormatVND(list.get(i).getDoanhthu()), Formater.FormatVND(list.get(i).getLoinhuan())
            });
        }
    }
    public JButton createButton(String text, Color bgColor) {
            JButton button = new JButton(text) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Xác định màu nền dựa trên trạng thái của button
                    Color actualBgColor = bgColor;
                    if (getModel().isPressed()) {
                        actualBgColor = bgColor.darker(); // Màu tối hơn khi nhấn
                    } else if (getModel().isRollover()) {
                        actualBgColor = bgColor.brighter(); // Màu sáng hơn khi hover
                    }
                    // Vẽ hình tròn làm nền
                    g2.setColor(actualBgColor);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Bo tròn góc 15px

                    super.paintComponent(g2);
                    g2.dispose();
                }
            };
            button.setFont(new Font("Arial", Font.BOLD, 11));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            button.setPreferredSize(new Dimension(100, 25));
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            return button;
    }
    public void refreshTable(){
        loadThongKeTungNgayTrongThang(currentDate, currentDate);
    }
}
