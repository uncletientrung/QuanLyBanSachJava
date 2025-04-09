
package GUI.Dialog.PhanQuyenDialog;
import DTO.TaiKhoanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PhanQuyenDialogDetail extends JDialog {

    private JTextField txTennhomquyen;
    private JTextArea taChucNang;
    private JTable tableTaiKhoan;
    private DefaultTableModel tableModel;

    public PhanQuyenDialogDetail(JFrame parent) {
        super(parent, "Chi tiết quyền", true);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("THÔNG TIN CHI TIẾT QUYỀN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(52, 152, 219));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);

        // Nội dung chính
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        contentPanel.setBackground(new Color(245, 245, 245));

        // Tên nhóm quyền
        JPanel pnTen = new JPanel(new BorderLayout(10, 0));
        JLabel lbTen = new JLabel("Tên nhóm quyền:");
        lbTen.setFont(new Font("Arial", Font.PLAIN, 16));
        txTennhomquyen = new JTextField();
        txTennhomquyen.setEditable(false);
        txTennhomquyen.setFont(new Font("Arial", Font.PLAIN, 16));
        txTennhomquyen.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        pnTen.add(lbTen, BorderLayout.WEST);
        pnTen.add(txTennhomquyen, BorderLayout.CENTER);
        pnTen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        contentPanel.add(pnTen);
        contentPanel.add(Box.createVerticalStrut(20));

        // Chức năng
        JPanel pnCN = new JPanel(new BorderLayout(0, 10));
        JLabel lbCN = new JLabel("Chức năng:");
        lbCN.setFont(new Font("Arial", Font.PLAIN, 16));
        taChucNang = new JTextArea(5, 20);
        taChucNang.setEditable(false);
        taChucNang.setFont(new Font("Arial", Font.PLAIN, 14));
        taChucNang.setLineWrap(true);
        taChucNang.setWrapStyleWord(true);
        taChucNang.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        JScrollPane scrollCN = new JScrollPane(taChucNang);
        pnCN.add(lbCN, BorderLayout.NORTH);
        pnCN.add(scrollCN, BorderLayout.CENTER);
        contentPanel.add(pnCN);
        contentPanel.add(Box.createVerticalStrut(20));

        // Table tài khoản
        JPanel tablePanel = new JPanel(new BorderLayout()); // Thêm panel để căn chỉnh
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Không padding để sát lề
        JLabel lbTable = new JLabel("Danh sách tài khoản thuộc nhóm quyền:");
        lbTable.setFont(new Font("Arial", Font.PLAIN, 16));
        String[] cols = {"Mã NV", "Tên tài khoản", "Mật khẩu"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa dữ liệu trong bảng
            }
        };
        tableTaiKhoan = new JTable(tableModel);
        tableTaiKhoan.setRowHeight(30);
        tableTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 14));
        
        
        JTableHeader header = tableTaiKhoan.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(52, 152, 219));      
        header.setOpaque(true);
        header.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219)));

        JScrollPane scrollTable = new JScrollPane(tableTaiKhoan);
        scrollTable.setPreferredSize(new Dimension(0, 200));

        tablePanel.add(lbTable, BorderLayout.NORTH);
        tablePanel.add(scrollTable, BorderLayout.CENTER);
        
        contentPanel.add(tablePanel); // Thêm tablePanel thay vì lbTable và scrollTable riêng lẻ

        add(contentPanel, BorderLayout.CENTER);
    }

    public void setTenNhomQuyen(String ten) {
        txTennhomquyen.setText(ten);
    }

    public void setDanhSachChucNang(List<String> chucNangList) {
        taChucNang.setText(String.join(", ", chucNangList));
    }

    public void loadTableTaiKhoan(List<TaiKhoanDTO> list) {
        tableModel.setRowCount(0); // Clear cũ
        for (TaiKhoanDTO tk : list) {
            tableModel.addRow(new Object[]{
                tk.getManv(),
                tk.getUsername(),
                tk.getMatkhau()
            });
        }
    }
}