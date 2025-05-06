package GUI.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeDoanhThuDTO;

import GUI.ThongKe.Support.Chart;
import GUI.ThongKe.Support.Formater;
import GUI.ThongKe.Support.JTableExporter;
import GUI.ThongKe.Support.ModelChart;
import GUI.ThongKe.Support.NumericDocumentFilter;
import GUI.ThongKe.Support.PanelBorderRadius;
import GUI.ThongKe.Support.TableSorter;
import GUI.ThongKe.Support.Validation;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class ThongKeDoanhThuTungNam extends JPanel implements ActionListener {

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    ThongKeBUS thongkeBUS;
    JTextField yearchooser_start, yearchooser_end;
    Chart chart;
    JButton btnreset, btnthongke, btnexport;

    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    private ArrayList<ThongKeDoanhThuDTO> dataset;
    private int current_year;
    private Font font=new Font("Arial", Font.BOLD, 12);

    public ThongKeDoanhThuTungNam() {
        
        this.current_year = LocalDate.now().getYear();
        this.dataset = new ThongKeBUS().getThongKeTheoNam(current_year - 6, current_year);
        initComponent();
        loadDataTalbe(dataset);
    }

    public void loadDataTalbe(ArrayList<ThongKeDoanhThuDTO> list) {
        tblModel.setRowCount(0);
        for (ThongKeDoanhThuDTO i : dataset) {
            tblModel.addRow(new Object[]{
                i.getThoigian(), Formater.FormatVND(i.getVon()), Formater.FormatVND(i.getDoanhthu()), Formater.FormatVND(i.getLoinhuan())
            });
        }
    }

    public void loadDataChart(ArrayList<ThongKeDoanhThuDTO> list) {
        pnlChart.removeAll();
        chart = new Chart();
        chart.addLegend("Vốn", new Color(12, 84, 175));
        chart.addLegend("Doanh thu", new Color(54, 4, 143));
        chart.addLegend("Lợi nhuận", new Color(211, 84, 0));
        for (ThongKeDoanhThuDTO i : dataset) {
            chart.addData(new ModelChart("Năm " + i.getThoigian(), new double[]{i.getVon(), i.getDoanhthu(), i.getLoinhuan()}));
        }
        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonNamBatDau, lblChonNamKetThuc;
        lblChonNamBatDau = new JLabel("Từ năm");
        lblChonNamBatDau.setFont(font);
        lblChonNamKetThuc = new JLabel("Đến năm");
        lblChonNamKetThuc.setFont(font);

        yearchooser_start = new JTextField("",10);
        yearchooser_start.setPreferredSize(new Dimension(100, 25));
        yearchooser_end = new JTextField("",10);
        yearchooser_end.setPreferredSize(new Dimension(100, 25));
        

        PlainDocument doc_start = (PlainDocument) yearchooser_start.getDocument();
        doc_start.setDocumentFilter(new NumericDocumentFilter());
        PlainDocument doc_end = (PlainDocument) yearchooser_end.getDocument();
        doc_end.setDocumentFilter(new NumericDocumentFilter());

        btnthongke = createButton("Thống kê",new Color(72, 118, 255));
        btnreset = createButton("Làm mới",new Color(72, 118, 255));
        btnexport = createButton("Xuất Excel", new Color(76, 175, 80));
        btnthongke.addActionListener(this);
        btnreset.addActionListener(this);
        btnexport.addActionListener(this);

        pnl_top.add(lblChonNamBatDau);
        pnl_top.add(yearchooser_start);
        pnl_top.add(lblChonNamKetThuc);
        pnl_top.add(yearchooser_end);
        pnl_top.add(btnthongke);
        pnl_top.add(btnreset);
        pnl_top.add(btnexport);

        pnlChart = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(pnlChart, BoxLayout.Y_AXIS);
        pnlChart.setLayout(boxly);

        loadDataChart(dataset);

        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Năm", "Vốn", "Doanh thu", "Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableThongKe.setModel(tblModel);
        tableThongKe.setRowHeight(37);  // THêm kích thước hieght
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);
        scrollTableThongKe.setViewportView(tableThongKe);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);
        tableThongKe.setFocusable(false);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 300));

        TableSorter.configureTableColumnSorter(tableThongKe, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 2, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 3, TableSorter.VND_CURRENCY_COMPARATOR);

        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);

        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnthongke) {
            System.out.println(yearchooser_start.getText());
            if (!new Validation().isEmpty(yearchooser_start.getText()) || !new Validation().isEmpty(yearchooser_end.getText())) {
                int nambd = Integer.parseInt(yearchooser_start.getText());
                int namkt = Integer.parseInt(yearchooser_end.getText());
                if (nambd > current_year || namkt > current_year) {
                    JOptionPane.showMessageDialog(this, "Năm không được lớn hơn năm hiện tại");
                } else {
                    if (namkt < nambd || namkt <= 2015 || nambd <= 2015) {
                        JOptionPane.showMessageDialog(this, "Năm kết thúc không được bé hơn năm bắt đầu và phải lớn hơn 2015");
                    } else {
                        this.dataset = new ThongKeBUS().getThongKeTheoNam(nambd, namkt);
                        loadDataChart(dataset);
                        loadDataTalbe(dataset);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ !");
            }

        } else if (source == btnreset) {
            yearchooser_start.setText("");
            yearchooser_end.setText("");
            this.dataset = new ThongKeBUS().getThongKeTheoNam(current_year - 6, current_year);
            loadDataChart(dataset);
            loadDataTalbe(dataset);
        } else if (source == btnexport) {
            try {
                JTableExporter.exportJTableToExcel(tableThongKe);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeDoanhThuTungNam.class.getName()).log(Level.SEVERE, null, ex);
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
    public void refreshTable(){
        this.current_year = LocalDate.now().getYear();
        this.dataset = new ThongKeBUS().getThongKeTheoNam(current_year - 6, current_year);
        loadDataTalbe(dataset);
    }
}
