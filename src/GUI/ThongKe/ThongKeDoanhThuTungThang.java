package GUI.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeTheoThangDTO;

import GUI.ThongKe.Support.Chart;

import GUI.ThongKe.Support.Formater;
import GUI.ThongKe.Support.JTableExporter;
import GUI.ThongKe.Support.ModelChart;
import GUI.ThongKe.Support.PanelBorderRadius;
import GUI.ThongKe.Support.TableSorter;


import com.toedter.calendar.JYearChooser;

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
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
public final class ThongKeDoanhThuTungThang extends JPanel implements ActionListener{

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    ThongKeBUS thongkeBUS;
    JYearChooser yearchooser;
    Chart chart;
    JButton export;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    private Font font=new Font("Arial", Font.BOLD, 12);

    public ThongKeDoanhThuTungThang() {
        
        initComponent();
        loadThongKeThang(yearchooser.getYear());
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonNam = new JLabel("Chọn năm thống kê");
        lblChonNam.setFont(font);
        yearchooser = new JYearChooser();
        yearchooser.setPreferredSize(new Dimension(60, 25)); // ví dụ: rộng 100px, cao 30px
        yearchooser.addPropertyChangeListener("year", (PropertyChangeEvent e) -> {
            int year = (Integer) e.getNewValue();
            loadThongKeThang(year);
        });

        export = createButton("Xuất Excel", new Color(76, 175, 80)); // Sửa lại thành createButton 
        export.addActionListener(this);
        pnl_top.add(lblChonNam);
        pnl_top.add(yearchooser);
        pnl_top.add(export);

        pnlChart = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(pnlChart, BoxLayout.Y_AXIS);
        pnlChart.setLayout(boxly);
        chart = new Chart();
        chart.addLegend("Vốn", new Color(12, 84, 175));
        chart.addLegend("Doanh thu", new Color(54, 4, 143));
        chart.addLegend("Lợi nhuận", new Color(211, 84, 0));
        pnlChart.add(chart);
        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Tháng", "Chi phí", "Doanh thu", "Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableThongKe.setModel(tblModel);
        tableThongKe.setRowHeight(30);
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);
        scrollTableThongKe.setViewportView(tableThongKe);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);
        tableThongKe.setFocusable(false);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 300));

        TableSorter.configureTableColumnSorter(tableThongKe, 0, TableSorter.STRING_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 2, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 3, TableSorter.VND_CURRENCY_COMPARATOR);

        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);
    }

    public void loadThongKeThang(int nam) {
        ArrayList<ThongKeTheoThangDTO> list = new ThongKeBUS().getThongKeTheoThang(nam);
        pnlChart.remove(chart);
        chart = new Chart();
        chart.addLegend("Vốn", new Color(12, 84, 175));
        chart.addLegend("Doanh thu", new Color(54, 4, 143));
        chart.addLegend("Lợi nhuận", new Color(211, 84, 0));
        for (int i = 0; i < list.size(); i++) {
            chart.addData(new ModelChart("Tháng " + (i + 1), new double[]{list.get(i).getChiphi(), list.get(i).getDoanhthu(), list.get(i).getLoinhuan()}));
        }
        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();
        tblModel.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            tblModel.addRow(new Object[]{
                "Tháng " + (i + 1), Formater.FormatVND(list.get(i).getChiphi()), Formater.FormatVND(list.get(i).getDoanhthu()), Formater.FormatVND(list.get(i).getLoinhuan())
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == export) {
            try {
                JTableExporter.exportJTableToExcel(tableThongKe);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeDoanhThuTungThang.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    public void  refreshTable(){
        loadThongKeThang(yearchooser.getYear());
    }
}
