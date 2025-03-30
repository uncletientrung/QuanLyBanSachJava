/*
 * Click nbfs://SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TheLoaiDAO;
import DTO.TheLoaiDTO;
import java.util.ArrayList;

/**
 *
 * @author Hi
 */
public class TheLoaiBUS {
    private final ArrayList<TheLoaiDTO> listTheLoai;
    private final TheLoaiDAO tlDAO;

    public TheLoaiBUS() {
        tlDAO = TheLoaiDAO.getInstance();
        listTheLoai = tlDAO.selectAll();
    }

    public ArrayList<TheLoaiDTO> getTheLoaiAll() {
        return TheLoaiDAO.getInstance().selectAll();
    }

    public ArrayList<TheLoaiDTO> timkiem(String keywords) {
        ArrayList<TheLoaiDTO> ketqua = new ArrayList<>();
        for (TheLoaiDTO tl : getTheLoaiAll()) {
            boolean timtheoma = false;
            try {
                int matl = Integer.parseInt(keywords);
                timtheoma = (matl == tl.getMatheloai());
            } catch (NumberFormatException e) {
                // Nếu không phải số, bỏ qua
            }
            if (timtheoma || tl.getTentheloai().toLowerCase().contains(keywords.toLowerCase())) {
                ketqua.add(tl);
            }
        }
        return ketqua;
    }
}