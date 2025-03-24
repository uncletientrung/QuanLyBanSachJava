/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author DELL
 */
public class BookPanel extends JPanel{
    public BookPanel(){
        // Tạo Panel toolBar cho thanh công cụ trên cùng
        JPanel toolBar= new JPanel(new GridLayout(1,2));
        JPanel toolBar_Left=new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));
        JPanel toolBar_Right=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,30));
        Font font=new Font("Arial", Font.BOLD, 16);

        // Tạo các nút CRUD cho JPanel toolBar_Left
        JButton btnAdd= createToolBarButton("Thêm", "insert1.png");
        JButton btnUpdate= createToolBarButton("Sửa", "update1.png");
        JButton btndelete= createToolBarButton("Xóa", "trash.png");
        JButton btndetail= createToolBarButton("Chi tiết", "detail1.png");
        btnAdd.setFont(font);btnUpdate.setFont(font);btndelete.setFont(font);btndetail.setFont(font);

        // Tạo phần tìm kiếm cho JPanel toolBar_Right
        String[] List_Combobox={"Tất cả","Giá thấp đến cao ⬆","Giá cao đến thấp ⬇"};
        JComboBox<String> cbbox=new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150,35));

        JTextField txfind=new JTextField("Tìm kiếm.....");
        txfind.setPreferredSize(new Dimension(200,35));
        txfind.setForeground(Color.GRAY);

        JButton btnfind=createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50,50));
        

        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btnUpdate);
        toolBar_Left.add(btndelete);
        toolBar_Left.add(btndetail);

        toolBar_Right.add(cbbox);
        toolBar_Right.add(txfind);
        toolBar_Right.add(btnfind);

        toolBar.add(toolBar_Left);
        toolBar.add(toolBar_Right);

        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        
    }

    // Phương thức tạo nút menu với kích thước và căn chỉnh phù hợp
    private JButton createToolBarButton(String text,String imageLink) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/GUI/Image/" + imageLink));
        JButton button = new JButton(text, imageIcon); // Đặt ảnh và chữ
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Căn chữ vào giữa
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Đặt chữ dưới ảnh
        button.setFocusPainted(false); // Bỏ viền khi click
        button.setBorderPainted(false); // Ẩn viền nút
        // button.setContentAreaFilled(false); // Ẩn nền nút
        button.setBackground(new Color(240, 240, 240)); // Màu nền nhẹ
        return button;
    }
}
