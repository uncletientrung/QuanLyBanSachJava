/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.SachDAO;
import DTO.SachDTO;
import java.util.ArrayList;
/**
 *
 * @author DELL
 */
public class SachBUS {
    private  final ArrayList<SachDTO> listSach;
    public SachBUS(){
        listSach= SachDAO.getInstance().selectAll();
    }
    public ArrayList<SachDTO> getSachAll(){
        return listSach;
    }
}
