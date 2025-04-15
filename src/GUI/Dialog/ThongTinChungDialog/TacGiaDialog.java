package GUI.Dialog.ThongTinChungDialog;

import BUS.TacGiaBUS;
import DTO.TacGiaDTO;
import GUI.Controller.TacGiaController;
import GUI.WorkFrame;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class TacGiaDialog extends JDialog {
    private JTable table;
    private DefaultTableModel tableTacGia;
    private JTextField txtTimKiem;
    private WorkFrame workFrame;
    private Boolean checkTimkiem = false;
    private ImageIcon icon;
    private JButton btnThem, btnSua, btnXoa;
    public ArrayList<TacGiaDTO> listTacGia = new TacGiaBUS().getTacGiaAll();

    public TacGiaDialog(JFrame parent) {
        super(parent, "Quản lý Tác Giả", true);
        setSize(800, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10)); // Khoảng cách giữa các phần

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(100, 100));
        
        JLabel lbTitle = new JLabel("THÔNG TIN TÁC GIẢ", JLabel.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lbTitle.setForeground(Color.BLACK);
        lbTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0)); // Tạo khoảng cách dưới tiêu đề
        headerPanel.add(lbTitle, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0)); // Tạo khoảng cách giữa tìm kiếm và tiêu đề
        txtTimKiem = new JTextField("Tìm kiếm.....");
        txtTimKiem.setPreferredSize(new Dimension(200, 35));
        txtTimKiem.setForeground(Color.GRAY);
        
        // Gắn icon cho nút
        icon = new ImageIcon(getClass().getResource("/GUI/Image/find.png"));
        Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        JButton btnTimKiem = new JButton(new ImageIcon(img));

        searchPanel.add(txtTimKiem);
        searchPanel.add(btnTimKiem);
        headerPanel.add(searchPanel, BorderLayout.SOUTH);

        // Thêm headerPanel vào phần NORTH 
        add(headerPanel, BorderLayout.NORTH);
        
        // Tìm kiếm
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter(){
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtTimKiem.getText().equals("Tìm kiếm.....")) {
                    txtTimKiem.setText("");
                    txtTimKiem.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtTimKiem.getText().trim().isEmpty()) {
                    txtTimKiem.setText("Tìm kiếm.....");
                    txtTimKiem.setForeground(Color.GRAY);
                }
            }
        });
        
        // Tìm kiếm khi đang gõ hoặc ấn nút
        txtTimKiem.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                timKiemKhiDangGo();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                timKiemKhiDangGo();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                timKiemKhiDangGo();
            }
        });
        btnTimKiem.addActionListener(e -> {
            checkTimkiem = true;
            timKiemKhiDangGo();
        });

        // Bảng dữ liệu
        String[] columnTacGia = {"Mã Tác Giả", "Tên Tác Giả"};
        tableTacGia = new DefaultTableModel(columnTacGia, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa các ô
            }
        };
        
        table = new JTable(tableTacGia);
        
        // Làm tiêu đề đậm hơn
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen

        for (TacGiaDTO tg : listTacGia) {
            tableTacGia.addRow(new Object[]{tg.getMatacgia(), tg.getHotentacgia()});
        }
              
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        // Căn giữa cột: Mã Tác Giả (0)
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getTableHeader().setReorderingAllowed(false);

        // Căn trái cột: Tên Tác Giả (1)
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

        // Điều chỉnh kích thước width và height của các cột
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(600);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel chứa 3 nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnThem = createButton("Thêm", new Color(46, 204, 113));
        btnSua = createButton("Sửa", new Color(122, 197, 205));
        btnXoa = createButton("Xóa", new Color(231, 76, 60));
        btnThem.setFont(new Font("Arial", Font.BOLD, 16));
        btnSua.setFont(new Font("Arial", Font.BOLD, 16));
        btnXoa.setFont(new Font("Arial", Font.BOLD, 16));
        
        
        TacGiaController controller= new TacGiaController(this, workFrame);
        btnThem.addActionListener(controller);
        btnSua.addActionListener(controller);
        btnXoa.addActionListener(controller);

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        add(buttonPanel, BorderLayout.SOUTH);

        // Hàm này để không focus con trỏ vào ô tìm kiếm
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                table.requestFocusInWindow();
            }
        });
    }

    private void timKiemKhiDangGo() {
        String keyword = txtTimKiem.getText().trim().toLowerCase();

        ArrayList<TacGiaDTO> danhsachmoi;
        if (keyword.isEmpty() || keyword.equals("tìm kiếm.....")) {
            danhsachmoi = new TacGiaBUS().getTacGiaAll();
        } else {
            danhsachmoi = new TacGiaBUS().timkiem(keyword);
        }

        capNhatBang(danhsachmoi);
        // Khi ấn tìm mới kiểm tra tìm thấy, tìm không thấy mới thông báo
        if (checkTimkiem && danhsachmoi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        checkTimkiem = false;
    }

    public void capNhatBang(ArrayList<TacGiaDTO> danhSach) {
        tableTacGia.setRowCount(0); // Xóa bảng cũ

        for (TacGiaDTO tg : danhSach) {
            tableTacGia.addRow(new Object[]{tg.getMatacgia(), tg.getHotentacgia()});
        }
    }
    
    //load lai du lieu khi moi them vo
    public void loadData() {
    listTacGia = new TacGiaBUS().getTacGiaAll(); // Lấy danh sách mới
    capNhatBang(listTacGia); // Cập nhật lại bảng
}
    
    //hàm kiểm tra coi dòng trong bảng có được click chọn hay không để sửa
    public int getSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tác giả để sửa!");
        }
        return selectedRow;
    }
    
    
    //lấy đối tượng nhóm quyền đang được click để hàm update biết
    public TacGiaDTO getSelectedTacGia() {
        int selectedRow = table.getSelectedRow(); // Lấy chỉ số hàng đang chọn
        if (selectedRow == -1) return null; // Nếu không chọn gì, trả về null

        int maTacGia = (int) table.getValueAt(selectedRow, 0); // 
        String tenTacGia = (String) table.getValueAt(selectedRow, 1); //

        return new TacGiaDTO(maTacGia, tenTacGia); // Tạo đối tượng
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
                    actualBgColor = bgColor.darker(); // Màu tối hơn khi nhấn
                } else if (getModel().isRollover()) {
                    actualBgColor = bgColor.brighter(); // Màu sáng hơn khi hover
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
}