package GUI.Controller;
import BUS.TheLoaiBUS;
import DTO.TheLoaiDTO;
import GUI.Dialog.TheLoaiDialog.TheLoaiDialogAdd;
import GUI.Dialog.TheLoaiDialog.TheLoaiDialogAdd_Controller;
import GUI.Dialog.TheLoaiDialog.TheLoaiDialogDelete;
import GUI.Dialog.TheLoaiDialog.TheLoaiDialogUpdate;
import GUI.Dialog.TheLoaiDialog.TheLoaiDialogUpdate_Controller;
import GUI.Dialog.ThongTinChungDialog.TheLoaiDialog;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TheLoaiController implements ActionListener, ListSelectionListener, DocumentListener {
    private TheLoaiDialog tlp;
    private WorkFrame wk;
    private TheLoaiBUS theLoaiBus;
    
    public TheLoaiController(TheLoaiDialog tlp, WorkFrame wk) {
        this.tlp = tlp;
        this.wk = wk;
        this.theLoaiBus = new TheLoaiBUS();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if ("Thêm".equals(action)) {  
            TheLoaiDialogAdd dialog = new TheLoaiDialogAdd(wk, tlp);
            TheLoaiDialogAdd_Controller controller = new TheLoaiDialogAdd_Controller(dialog, tlp);
            dialog.setController(controller);
            dialog.setVisible(true);
        }
        
        if ("Sửa".equals(action)) {
            TheLoaiDTO theLoaiDTO = tlp.getSelectedTheLoai();
            if (theLoaiDTO == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn thể loại cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            TheLoaiDialogUpdate dialog = new TheLoaiDialogUpdate(wk, tlp, theLoaiDTO);
            TheLoaiDialogUpdate_Controller controller = new TheLoaiDialogUpdate_Controller(dialog, tlp);
            dialog.setController(controller);
            dialog.setVisible(true);
        }
        
        if ("Xóa".equals(e.getActionCommand())) {
            TheLoaiDTO theLoaiCanXoa = tlp.getSelectedTheLoai();
            if (theLoaiCanXoa == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn thể loại để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            TheLoaiDialogDelete dialog = new TheLoaiDialogDelete(wk, theLoaiCanXoa);
            dialog.setVisible(true);
            
            if (dialog.isXacNhan()) {
                boolean result = theLoaiBus.xoaTheLoai(theLoaiCanXoa.getMatheloai());
                if (result) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    tlp.capNhatBang(theLoaiBus.getTheLoaiAll());
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại! Nhóm quyền có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
