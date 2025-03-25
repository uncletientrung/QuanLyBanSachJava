/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhanQuyenDao;
import DTO.NhomQuyenDTO;
import java.util.ArrayList;

/**
 *
 * @author Hi
 */
public class PhanQuyenBUS {
    private final ArrayList<NhomQuyenDTO> listNhomQuyen;
    // goi ham selct ben DAO de lay du lieu tu data base truyen vo arraylust
    public PhanQuyenBUS(){
        listNhomQuyen=PhanQuyenDao.getInstance().selectAll();
    }
    // ham lay danh sach ra
    public ArrayList<NhomQuyenDTO> getNhomQuyenAll(){
        return  listNhomQuyen;
    }
     //viet ham tim kiem
    public ArrayList<NhomQuyenDTO> timkiem(String keywords) {
    ArrayList<NhomQuyenDTO> ketqua = new ArrayList<>();

    for (NhomQuyenDTO q : getNhomQuyenAll()) {
        boolean timtheoma = false;

        // ktra neu ngta nhap so
        try {
            int manhomquyen = Integer.parseInt(keywords);
            timtheoma = (manhomquyen == q.getManhomquyen());
        } catch (NumberFormatException e) {
            //khong lam gi neu nhap chu
        }

        //kiem tra theo ma va ten
        if (timtheoma || q.getTennhomquyen().toLowerCase().contains(keywords.toLowerCase())) {
            ketqua.add(q);
        }
    }
    return ketqua;
}

}
