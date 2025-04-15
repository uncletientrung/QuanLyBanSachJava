/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import DTO.NhomQuyenDTO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Hi
 */
public class KhuyenMaiBUS {
    private final ArrayList<KhuyenMaiDTO> listKhuyenMai;
    private final KhuyenMaiDAO kmDAO;
    
    public KhuyenMaiBUS(){
        kmDAO= KhuyenMaiDAO.getInstance();
        listKhuyenMai=kmDAO.selectAll();
    }
    
    public ArrayList<KhuyenMaiDTO> getAllKhuyenMai(){
        return KhuyenMaiDAO.getInstance().selectAll();
    }
    
    public ArrayList<KhuyenMaiDTO> timkiem(String keywords) {
        ArrayList<KhuyenMaiDTO> ketqua = new ArrayList<>();
        for (KhuyenMaiDTO q : getAllKhuyenMai()) {
            boolean timtheoma = false;
            try {
                int makm = Integer.parseInt(keywords);
                timtheoma = (makm == q.getMaKM());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma || q.getTenChuongTrinh().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(q);
            }
        }
        return ketqua;
    }
    
 



    
}
