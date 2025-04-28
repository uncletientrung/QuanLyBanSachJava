
package GUI.Dialog.ThongKeDialog;

import BUS.SachBUS;
import DTO.SachDTO;
import GUI.WorkFrame;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Date;
import javax.swing.table.TableColumn;

public class SachDialog extends JDialog {

    private JTable table;
    private DefaultTableModel tables;
   
    private WorkFrame workFrame;
    private ImageIcon icon;
    
    private JTextField txtTimKiem;

    private JComboBox<String> cbbox;
    
    private JComboBox<String> kieusapxep;
    private JComboBox<String> sapxep;
    
    private JDateChooser NgayBatDau1;
    private JDateChooser NgayKetThuc1;
    
    private JMonthChooser ThangBatDau2;
    private JMonthChooser ThangKetThuc2;
    private JYearChooser NamBatDau2;
    private JYearChooser  NamKetThuc2;
    
    private JYearChooser NamBatDau3;
    private JYearChooser  NamKetThuc3;
   
    private JYearChooser NamBatDau4;
    private JYearChooser  NamKetThuc4;
    
    private JPanel ngay,thang,quy,nam;
    
    private JComboBox<String> qui1;
    private JComboBox<String> qui2;

    private ArrayList<SachDTO> lists = new SachBUS().getSachAll();
    private ArrayList<Long> listthongke = new ArrayList();

    private ArrayList<Object[]> list=List();
    private ArrayList<Object[]> listsx=new ArrayList<>(list);
    
    public boolean t0=true;
    public boolean t1=true;
    public boolean t2=true;
    public boolean t3=true;
    public boolean t4=true;
    public boolean t5=true;
    public boolean t6=true;
    
    public SachDialog(JFrame parent) {
        super(parent, "Thống kê sách hàng", true);
        setSize(1300, 700);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(100, 100));

        JLabel lbTitle = new JLabel("Thông kê sách hàng", JLabel.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lbTitle.setForeground(Color.BLACK);
        lbTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0)); // Tạo soảng cách dưới tiêu đề
        headerPanel.add(lbTitle, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();
         // Tạo soảng cách giữa tìm kiếm và tiêu đề
        
        String[] List_Combobox1 = {"Tăng","Giảm"};
        kieusapxep = new JComboBox<String>(List_Combobox1);
        String[] List_Combobox2 = {"Theo Mã","Theo Họ Tên"};
        sapxep = new JComboBox<String>(List_Combobox2);
         
        JLabel a = new JLabel("Theo : ");
        String[] List_Combobox = {"Tất cả","Trước đến nay", "Ngày", "Tháng","Quý", "Năm"};
        cbbox = new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150, 35));

        JLabel from1 = new JLabel("Từ : ");
        JLabel to1 = new JLabel("Đến : ");
        NgayBatDau1 = new JDateChooser(new Date());
        NgayKetThuc1 = new JDateChooser(new Date());
        NgayBatDau1.setPreferredSize(new Dimension(100, 30));
        NgayKetThuc1.setPreferredSize(new Dimension(100, 30));
        ngay=new JPanel();
        ngay.add(from1);
        ngay.add(NgayBatDau1);
        ngay.add(to1);
        ngay.add(NgayKetThuc1);
        ngay.setVisible(false);
        
        JLabel from2 = new JLabel("Từ : ");
        JLabel to2 = new JLabel("Đến : ");
        ThangBatDau2 = new JMonthChooser();
        ThangKetThuc2 = new JMonthChooser();
        NamBatDau2=new JYearChooser();
        NamKetThuc2=new JYearChooser();
        thang=new JPanel();
        thang.add(from2);
        thang.add(ThangBatDau2);
        thang.add(NamBatDau2);
        thang.add(to2);
        thang.add(ThangKetThuc2);
        thang.add(NamKetThuc2);
        thang.setVisible(false);
        
        JLabel from3 = new JLabel("Từ : ");
        JLabel to3 = new JLabel("Đến : ");
        String[] Qui1 = {"Quý I","Quý II","Quý III","Quý IV  "};
        String[] Qui2 = {"Quý I","Quý II","Quý III","Quý IV  "};
        qui1 = new JComboBox<String>(Qui1);
        qui2 = new JComboBox<String>(Qui2);
        NamBatDau3=new JYearChooser();
        NamKetThuc3=new JYearChooser();
        quy=new JPanel();
        quy.add(from3);
        quy.add(qui1);
        quy.add(NamBatDau3);
        quy.add(to3);
        quy.add(qui2);
        quy.add(NamKetThuc3);
        quy.setVisible(false);
        
        JLabel from4 = new JLabel("Từ : ");
        JLabel to4 = new JLabel("Đến : ");
        NamBatDau4 = new JYearChooser();
        NamKetThuc4 = new JYearChooser();
        nam=new JPanel();
        nam.add(from4);
        nam.add(NamBatDau4);
        nam.add(to4);
        nam.add(NamKetThuc4);
        nam.setVisible(false);
        
        txtTimKiem = new JTextField("Tìm kiếm.....");
        txtTimKiem.setPreferredSize(new Dimension(200, 35));
        txtTimKiem.setForeground(Color.GRAY);

        // gắn icon cho nút
        icon = new ImageIcon(getClass().getResource("/GUI/Image/find.png"));
        Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        JButton btnTimKiem = new JButton(new ImageIcon(img));

        
        searchPanel.add(a);
        searchPanel.add(cbbox);
        searchPanel.add(ngay);
        searchPanel.add(thang);
        searchPanel.add(quy);
        searchPanel.add(nam);
        searchPanel.add(txtTimKiem);
        searchPanel.add(btnTimKiem);
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        // Thêm headerPanel vào phần NORTH 
        add(headerPanel, BorderLayout.NORTH);

        //tim kiem
        txtTimKiem.addFocusListener(new SachController(this, workFrame));
        //tìm kiem si dang go hoac an nut
        txtTimKiem.getDocument().addDocumentListener(new SachController(this, workFrame));
        btnTimKiem.addActionListener(e -> {
            
            timKiemKhiDangGo();

        });

        //bảng dữ liệu
        String[] columnNCC = {"STT","Mã", "Tên","Số Khách","Doanh Thu","Tổng Sách","Lợi Nhuận"};
        tables = new DefaultTableModel(columnNCC, 0) {       
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// chặn chỉnh sửa các ô      
            }
        };

        table = new JTable(tables);
        
        JTableHeader header = table.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        table.getTableHeader().setReorderingAllowed(false); // Tắt tính năng thay đổi thứ tự cột

        //lam tieu de no dam hơn 
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen
        
        int n=0;
        for (int i=0;i<lists.size();i++){ 
            tables.addRow(list.get(i));   
            n+=3;
        }
        doitable(true,listsx);
        // Căn giữa các cột: Mã NCC (0), SĐT (3)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0, 1, 2,3,4,5,6};
        for (int col : columnsToCenter) {
            table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        // Điều chỉnh kích thước width và hieght của các cột tableBook 
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        
        header.addMouseListener(new SachController(this, workFrame));
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        //ngay chứa 3 
        JButton btnct = new JButton("chi tiết");
        JButton btntk = new JButton("thống kê");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnct = createButton("chi tiết", new Color(46, 204, 113));
        btntk = createButton("thống kê", new Color(60, 123, 231));        
        btnct.setFont(new Font("Arial", Font.BOLD, 16));
        btntk.setFont(new Font("Arial", Font.BOLD, 16));

        SachController controller = new SachController(this, workFrame);
        btnct.addActionListener(controller);
        btntk.addActionListener(controller);
        
        cbbox.addActionListener(controller);
        
        buttonPanel.add(btnct);
        buttonPanel.add(btntk);

        add(buttonPanel, BorderLayout.SOUTH);
        //ham nay de cho song focus con tro vao o tim kiem
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                table.requestFocusInWindow();
            }
        });

    }

    public void timKiemKhiDangGo() {
        String keyword = txtTimKiem.getText().trim().toLowerCase();
        ArrayList<SachDTO> danhsachmoi;
        if (keyword.isEmpty() || keyword.equals("tìm kiếm.....")) {
            danhsachmoi = new SachBUS().getSachAll();
        } else {
            danhsachmoi = new SachBUS().search(keyword);
        }
        capNhatBang(danhsachmoi);
    }

    public void capNhatBang(ArrayList<SachDTO> danhSach) { 
        listsx=new ArrayList<>();
        tables.setRowCount(0);
        for (int i=0;i<danhSach.size();i++) {
            for (int j=0;j<list.size();j++)
                if(String.valueOf(danhSach.get(i).getMasach()).equals(String.valueOf(list.get(j)[1]))){
                    tables.addRow(list.get(j));
                    listsx.add(list.get(j));
                }
        }
    }

    // ======= Tạo button đồng bộ với phong cách UI =======
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Xác định màu nền dựa trên trạng thái của button
                Color actualBgColor = bgColor;
                if (getModel().isPressed()) {
                    actualBgColor = bgColor.darker(); // Màu tối hơn si nhấn
                } else if (getModel().isRollover()) {
                    actualBgColor = bgColor.brighter(); // Màu sáng hơn si hover
                }
                // Vẽ bo tròn góc cho nút
                g2.setColor(actualBgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Bo tròn góc 15px

                super.paintComponent(g2);
                g2.dispose();
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(140, 40));

        return button;
    }
    
    public void doitable(boolean t,ArrayList<Object[]> list){
        if(t){
            // 3 4 5 6
            for(int i=3;i<=6;i++){
                TableColumn column = table.getColumnModel().getColumn(i);
                column.setMinWidth(0);
                column.setMaxWidth(0);
                column.setPreferredWidth(0);
                column.setResizable(false);
            }
        }
        else{
            for(int i=3;i<=6;i++){
                TableColumn column = table.getColumnModel().getColumn(i);
                column.setMinWidth(80);
                column.setMaxWidth(80);
                column.setPreferredWidth(80);
                column.setResizable(false);
            }
        }
        tables.setRowCount(0);
            for(int i=0;i<list.size();i++){
                tables.addRow(list.get(i));
            }
    }
    
    public void sapxepTable(){
        if(table.getColumnModel().getColumn(5).getWidth()==0) doitable(true,listsx);
        else  doitable(false,listsx);
    }

    public  ArrayList <Object[]> List(){
        ArrayList <Object[]> list1=new ArrayList();
        int t=0;
        for(int i=0;i<lists.size();i++){
            if(list!=null){
                list1.add(new Object[]{i+1,lists.get(i).getMasach(),lists.get(i).getTensach(),listthongke.get(t),listthongke.get(t+1),listthongke.get(t+2),listthongke.get(t+3)});
                t+=4;
            }else 
                list1.add(new Object[]{i+1,lists.get(i).getMasach(),lists.get(i).getTensach(),-1,-1,-1,-1});
        }

        return list1;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTables() {
        return tables;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public JTextField getTxtTimKiem() {
        return txtTimKiem;
    }

    public JComboBox<String> getCbbox() {
        return cbbox;
    }

    public JDateChooser getNgayBatDau1() {
        return NgayBatDau1;
    }

    public JDateChooser getNgayKetThuc1() {
        return NgayKetThuc1;
    }

    public JMonthChooser getThangBatDau2() {
        return ThangBatDau2;
    }

    public JMonthChooser getThangKetThuc2() {
        return ThangKetThuc2;
    }

    public JYearChooser getNamBatDau2() {
        return NamBatDau2;
    }

    public JYearChooser getNamKetThuc2() {
        return NamKetThuc2;
    }

    public JYearChooser getNamBatDau3() {
        return NamBatDau3;
    }

    public JYearChooser getNamKetThuc3() {
        return NamKetThuc3;
    }

    public JYearChooser getNamBatDau4() {
        return NamBatDau4;
    }

    public JYearChooser getNamKetThuc4() {
        return NamKetThuc4;
    }

    public JPanel getNgay() {
        return ngay;
    }

    public JPanel getThang() {
        return thang;
    }

    public JPanel getQuy() {
        return quy;
    }

    public JPanel getNam() {
        return nam;
    }

    public JComboBox<String> getQui1() {
        return qui1;
    }

    public JComboBox<String> getQui2() {
        return qui2;
    }

    public ArrayList<SachDTO> getLists() {
        return lists;
    }

    public ArrayList<Long> getListthongke() {
        return listthongke;
    }

    public ArrayList<Object[]> getList() {
        return list;
    }

    public void setListthongke(ArrayList<Long> listthongke) {
        this.listthongke = listthongke;
    }

    public void setList(ArrayList<Object[]> list) {
        this.list = list;
    }

    public void setListsx(ArrayList<Object[]> listsx) {
        this.listsx = listsx;
    }

    public ArrayList<Object[]> getListsx() {
        return listsx;
    }



}
