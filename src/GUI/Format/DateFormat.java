/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class DateFormat {
    public static String fomat(String inputDateTime) {
        try {
            // Định dạng đầu vào: yyyy-MM-dd HH:mm:ss.S
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            // Parse chuỗi đầu vào thành đối tượng Date
            Date date = inputFormat.parse(inputDateTime);

            // Định dạng đầu ra: dd/MM/yyyy HH:mm
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy ");
            // Format đối tượng Date thành chuỗi định dạng mong muốn
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi: Không thể chuyển đổi định dạng ngày giờ.";
        }
    }
}
