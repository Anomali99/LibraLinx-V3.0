/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import parsisten.Petugas;

/**
 *
 * @author fatiq
 */
public class TabelPetugas extends AbstractTableModel{

    private List<Petugas> listAnggota = new ArrayList<>();
    private final String[] headerName = {"NO", "ID Petugas","Nama","Alamat","Email","Telephone","JK","Level"};
    
    public void clear (){
        listAnggota.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Petugas> list){
        clear();
        this.listAnggota.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Petugas mod){
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
                case 0 : return listAnggota.get(rowIndex).getIdPetugas();
                case 1 : return listAnggota.get(rowIndex).getNama();
                case 2 : return listAnggota.get(rowIndex).getAlamat();
                case 3 : return listAnggota.get(rowIndex).getEmail();
                case 4 : return listAnggota.get(rowIndex).getTelephone();
                case 5 : return listAnggota.get(rowIndex).getJenisKelamin();
                case 6 : return listAnggota.get(rowIndex).getLevel();
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
