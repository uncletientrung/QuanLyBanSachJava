package GUI.View;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import BUS.SachBUS;
import DTO.SachDTO;
import GUI.WorkFrame;
import GUI.Controller.BookController;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import BUS.NhaXuatBanBUS;
import DTO.NhaXuatBanDTO;
import DTO.TacGiaDTO;
import BUS.TacGiaBUS;
import DTO.TheLoaiDTO;
import BUS.TheLoaiBUS;
import GUI.Format.NumberFormatter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Comparator;

public class BookPanel extends JPanel {
    private JTable tableBook;
    private WorkFrame workFrame;
    private SachBUS sachBUS;
    private ArrayList<SachDTO> listSach;
    private DefaultTableModel dataBook;
    private JTextField txfFind;
    private JComboBox<String> cbbox;
    private NhaXuatBanBUS NxbBUS = new NhaXuatBanBUS();
    private TacGiaBUS TgBUS = new TacGiaBUS();
    private TheLoaiBUS TlBUS = new TheLoaiBUS();

    public BookPanel() {
        // Tạo Panel toolBar cho thanh công cụ trên cùng
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS)); // Sử dụng BoxLayout theo chiều ngang
        JPanel toolBar_Left = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        JPanel toolBar_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 30));
        Font font = new Font("Arial", Font.BOLD, 16);

        // Tạo các nút CRUD cho JPanel toolBar_Left
        JButton btnAdd = createToolBarButton("Thêm", "insert1.png");
        JButton btnUpdate = createToolBarButton("Sửa", "update1.png");
        JButton btnDelete = createToolBarButton("Xóa", "trash.png");
        JButton btnDetail = createToolBarButton("Chi tiết", "detail1.png");
        JButton btnExport = createToolBarButton("Xuất Excel", "export_excel.png");
        JButton btnImport = createToolBarButton("Nhập Excel", "import_excel.png");
        btnAdd.setFont(font);
        btnUpdate.setFont(font);
        btnDelete.setFont(font);
        btnDetail.setFont(font);
        btnExport.setFont(font);
        btnImport.setFont(font);

        // Tạo phần tìm kiếm cho JPanel toolBar_Right
        String[] List_Combobox = {"Tất cả", "Giá thấp đến cao ⬆", "Giá cao đến thấp ⬇", "NXB thấp đến cao ⬆", "NXB cao đến thấp ⬇"};
        cbbox = new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150, 35));
        
        String Text_placeholder = "Nhập mã hoặc tên sách....";
        txfFind = new JTextField("");
        txfFind.setPreferredSize(new Dimension(200, 35));
        txfFind.setForeground(Color.GRAY);

        

        JButton btnFind = createToolBarButton("", "find.png");
        btnFind.setPreferredSize(new Dimension(50, 50));

        // Tạo JTable cho BookPanel
        listSach = new SachBUS().getSachAll();
        String[] columnBook = {"Mã sách", "Tên sách", "Nhà xuất bản", "Tác Giả", "Thể loại", "Số lượng", "Năm xuất bản", "Giá"};
        dataBook = new DefaultTableModel(columnBook, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa tất cả các ô
            }
        };

        tableBook = new JTable(dataBook);
        tableBook.getTableHeader().setReorderingAllowed(false); // Tắt tính năng thay đổi thứ tự cột
        tableBook.getTableHeader().setResizingAllowed(false);
        tableBook.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tableBook.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen

        // Thêm dữ liệu từ sách vào Frame
        for (SachDTO s : listSach) {
            // Tìm tên nhà xuất bản
            String tenNxb = "";
            NhaXuatBanDTO NxbFind = NxbBUS.getNXBById(s.getManxb());
            tenNxb = NxbFind.getTennxb();
            // Tìm tên tác giả
            String tenTg = "";
            TacGiaDTO TgFind = TgBUS.getTGById(s.getMatacgia());
            tenTg = TgFind.getHotentacgia();
            // Tìm tên thể loại
            String tenTl = "";
            TheLoaiDTO TLFind = TlBUS.getTlbyId(s.getMatheloai());
            tenTl = TLFind.getTentheloai();

            dataBook.addRow(new Object[]{s.getMasach(), s.getTensach(), tenNxb, tenTg, tenTl,
                    NumberFormatter.format(s.getSoluongton()), 
                    s.getNamxuatban(), NumberFormatter.format(s.getDongia())
                    });
        }

        // Tạo renderer để căn giữa dữ liệu trong TableBook
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(centerRenderer.CENTER);
        tableBook.getTableHeader().setDefaultRenderer(centerRenderer);
        int[] columnsToCenter = {0, 1, 2, 3, 4, 5, 6, 7}; // Căn giữa tất cả trừ tên sách và tên nxb
        for (int col : columnsToCenter) {
            tableBook.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }


        // Điều chỉnh kích thước width và height của các cột tableBook
        tableBook.setRowHeight(40);
        tableBook.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableBook.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableBook.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableBook.getColumnModel().getColumn(3).setPreferredWidth(115);
        tableBook.getColumnModel().getColumn(4).setPreferredWidth(115);
        tableBook.getColumnModel().getColumn(5).setPreferredWidth(30);
        tableBook.getColumnModel().getColumn(6).setPreferredWidth(30);
        tableBook.getColumnModel().getColumn(7).setPreferredWidth(60);
        
        // Tạo ScrollPane cho Table để tên cột column hiện
        JScrollPane SPBook = new JScrollPane(tableBook);

        // Thêm các nút vào toolBar_Left
        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btnUpdate);
        toolBar_Left.add(btnDelete);
        toolBar_Left.add(btnDetail);
        toolBar_Left.add(btnExport);
        toolBar_Left.add(btnImport);

        // Thêm các thành phần vào toolBar_Right
        toolBar_Right.add(cbbox);
        toolBar_Right.add(txfFind);
        toolBar_Right.add(btnFind);

        // Thêm toolBar_Left và toolBar_Right vào toolBar
        toolBar.add(toolBar_Left);
        toolBar.add(Box.createHorizontalGlue()); // Thêm khoảng trống để đẩy toolBar_Right sang phải
        toolBar.add(toolBar_Right);

        // Thiết lập layout cho BookPanel
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(SPBook, BorderLayout.CENTER);

        // Gắn sự kiện vào Table để khi ấn vào hiện lên bản chỉnh sửa
        tableBook.getSelectionModel().addListSelectionListener(new BookController(this, workFrame));
        // Thêm sự kiện cho nút
        ActionListener action = new BookController(this, workFrame);
        btnAdd.addActionListener(action);
        btnUpdate.addActionListener(action);
        btnDelete.addActionListener(action);
        btnDetail.addActionListener(action);
        btnExport.addActionListener(action);
        btnImport.addActionListener(action);
        cbbox.addActionListener(action);
        // Thêm sự kiện DocumentListener
        DocumentListener document = new BookController(this, workFrame);
        txfFind.getDocument().addDocumentListener(document);
    }

    // Phương thức tạo nút menu với kích thước và căn chỉnh phù hợp
    private JButton createToolBarButton(String text, String imageLink) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/GUI/Image/" + imageLink));
        JButton button = new JButton(text, imageIcon); // Đặt ảnh và chữ
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Căn chữ vào giữa
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Đặt chữ dưới ảnh
        button.setFocusPainted(false); // Bỏ viền khi click
        button.setBorderPainted(false); // Ẩn viền nút
        button.setBackground(new Color(240, 240, 240)); // Màu nền nhẹ
        return button;
    }

    public JTable getTable() {
        return tableBook;
    }

    public JTextField getTxfFind() {
        return txfFind;
    }

    public JComboBox<String> getCbbox() {
        return cbbox;
    }

    public ArrayList<SachDTO> getListSach() {
        return listSach;
    }

    public void refreshTableData() {
        listSach = new SachBUS().getSachAll();
        listSach.sort(Comparator.comparingInt(s -> Integer.parseInt(s.getMasach().substring(1)))); // Sắp xếp thự tự mã
        dataBook.setRowCount(0);
        for (SachDTO s : listSach) {
            // Tìm tên nhà xuất bản
            String tenNxb = "";
            NhaXuatBanDTO NxbFind = NxbBUS.getNXBById(s.getManxb());
            tenNxb = NxbFind.getTennxb();
            // Tìm tên tác giả
            String tenTg = "";
            TacGiaDTO TgFind = TgBUS.getTGById(s.getMatacgia());
            tenTg = TgFind.getHotentacgia();
            // Tìm tên thể loại
            String tenTl = "";
            TheLoaiDTO TLFind = TlBUS.getTlbyId(s.getMatheloai());
            tenTl = TLFind.getTentheloai();
            dataBook.addRow(new Object[]{s.getMasach(), s.getTensach(), tenNxb, tenTg, tenTl,
                    NumberFormatter.format(s.getSoluongton()), 
                    s.getNamxuatban(), NumberFormatter.format(s.getDongia())
                    });
        }
    }

    public void FindTableData(String text) {
        if(txfFind.getText().equals("") && cbbox.getSelectedIndex()==0){
            refreshTableData();
            return;
        }
        listSach = new SachBUS().search(text);
        dataBook.setRowCount(0);
        for (SachDTO s : listSach) {
            // Tìm tên nhà xuất bản
            String tenNxb = "";
            NhaXuatBanDTO NxbFind = NxbBUS.getNXBById(s.getManxb());
            tenNxb = NxbFind.getTennxb();
            // Tìm tên tác giả
            String tenTg = "";
            TacGiaDTO TgFind = TgBUS.getTGById(s.getMatacgia());
            tenTg = TgFind.getHotentacgia();
            // Tìm tên thể loại
            String tenTl = "";
            TheLoaiDTO TLFind = TlBUS.getTlbyId(s.getMatheloai());
            tenTl = TLFind.getTentheloai();
            dataBook.addRow(new Object[]{s.getMasach(), s.getTensach(), tenNxb, tenTg, tenTl,
                    NumberFormatter.format(s.getSoluongton()), 
                    s.getNamxuatban(), NumberFormatter.format(s.getDongia())
                    });
        }
    }

    public void FilterTableData(ArrayList<SachDTO> list_Sort) {
        dataBook.setRowCount(0);
        for (SachDTO s : list_Sort) {
            // Tìm tên nhà xuất bản
            String tenNxb = "";
            NhaXuatBanDTO NxbFind = NxbBUS.getNXBById(s.getManxb());
            tenNxb = NxbFind.getTennxb();
            // Tìm tên tác giả
            String tenTg = "";
            TacGiaDTO TgFind = TgBUS.getTGById(s.getMatacgia());
            tenTg = TgFind.getHotentacgia();
            // Tìm tên thể loại
            String tenTl = "";
            TheLoaiDTO TLFind = TlBUS.getTlbyId(s.getMatheloai());
            tenTl = TLFind.getTentheloai();
            dataBook.addRow(new Object[]{s.getMasach(), s.getTensach(), tenNxb, tenTg, tenTl,
                    NumberFormatter.format(s.getSoluongton()), 
                    s.getNamxuatban(), NumberFormatter.format(s.getDongia())
                    });
        }
    }
}