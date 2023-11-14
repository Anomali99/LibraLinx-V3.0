/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import parsisten.Peminjam;

/**
 *
 * @author fatiq
 */
public class TabelAnggota extends AbstractTableModel{

    private List<Peminjam> listAnggota = new ArrayList<>();
    private final String[] headerName = {"NO", "ID Anggota", "Nama", "NIM",
        "Fakultas","Program Studi","Semester","Telepnone","Email","Alamat"};
    
    public void clear (){
        listAnggota.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Peminjam> list){
        clear();
        this.listAnggota.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Peminjam mod){
        listAnggota.set(index, mod);
        fireTableRowsUpdated(index, index);
    }
    
    @Override
    public int getRowCount() {
        return listAnggota.size();
    }

    @Override
    public int getColumnCount() {
        return headerName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0){
            return "   "+ (rowIndex +1);
        } else {
            switch (columnIndex-1){
                case 0 : return listAnggota.get(rowIndex).getIdPeminjam();
                case 1 : return listAnggota.get(rowIndex).getNama();
                case 2 : return listAnggota.get(rowIndex).getNim();
                case 3 : return listAnggota.get(rowIndex).getFakultas();
                case 4 : return listAnggota.get(rowIndex).getProgramStudi();
                case 5 : return listAnggota.get(rowIndex).getSemester();
                case 6 : return listAnggota.get(rowIndex).getTelephone();
                case 7 : return listAnggota.get(rowIndex).getEmail();
                case 8 : return listAnggota.get(rowIndex).getAlamat();
                default : return null;
            }
            
        }
    }
    
    
    @Override
    public String getColumnName(int column){
        if (column == 0){
            return "   "+headerName[column];
        } else {
            return headerName[column];
        }
    }
    
}
