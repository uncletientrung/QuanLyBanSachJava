package GUI.Dialog.KhachHangDialog;

import BUS.KhachHangBUS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class KhachHangDialogDelete extends JDialog{
    private String ma;
    
    public KhachHangDialogDelete(JFrame parent,String ma) { 
        super(parent, "Danh mục xóa Khach hang", true);
        this.ma=ma; 
        
        setSize(350,150);
        setLocationRelativeTo(null);
        
        //Panel phía trên chính giữa
        Font font=new Font("Arial", Font.BOLD, 18);
        JPanel panelMain=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lbIcon=new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        JLabel lbNotice= new JLabel("Bạn có chắn chắn muốn xóa khách hàng này không!!");
        lbNotice.setFont(font);
        panelMain.add(lbIcon);panelMain.add(lbNotice);
        
        //Panel phía dưới phải
        JPanel panelFoot=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btndelete=GuiSupport.createButton("Xóa",new Color(244, 67, 54));
        JButton btnclose=GuiSupport.createButton("Đóng",new Color(156, 156, 156));
        panelFoot.add(btndelete);panelFoot.add(btnclose);
 
        // Thêm 2 Panel trên vào Panel tổng
        setLayout(new BorderLayout());
        add(panelMain,BorderLayout.CENTER);
        add(panelFoot,BorderLayout.SOUTH);
         
         // SU KIEN
       
        btndelete.addActionListener((ActionEvent e) -> {
            if(new KhachHangBUS().deleteById(Integer.parseInt(ma))){
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "database không cho phép xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
         
        btnclose.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
         
        pack(); 
        setVisible(true);
    }
    
}
