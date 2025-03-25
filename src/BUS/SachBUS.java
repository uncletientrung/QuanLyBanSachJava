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
    public final SachDAO sDAO=new SachDAO();
    private  ArrayList<SachDTO> listSach;
    
    public SachBUS(){
        listSach= sDAO.selectAll();
    }
    public ArrayList<SachDTO> getSachAll(){
        return listSach;
    }
    public Boolean addSach(SachDTO sach ){
        boolean result=sDAO.insert(sach) !=0;
        if (result){
            listSach.add(sach);
        }
        return result;
    }
}
