/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import parsisten.Peminjaman;

/**
 *
 * @author fatiq
 */
public class TabelPeminjaman extends AbstractTableModel{

    private List<Peminjaman> list = new ArrayList<>();
    private final String[] headerName = {"NO", "NO Peminjaman", "Tanggal Pinjam", 
        "Tanggal Kembali", "Keterangan","Status","ID Peminjam","Nama Peminjam"};
    
    public void clear (){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Peminjaman> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Peminjaman mod){
        list.set(index, mod);
        fireTableRowsUpdated(index, index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
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
                case 0 : return list.get(rowIndex).getPeminjamanPK().getNoPeminjaman();
                case 1 : return list.get(rowIndex).getTglPinjam();
                case 2 : return list.get(rowIndex).getTglKembali();
                case 3 : return list.get(rowIndex).getKeterangan();
                case 4 : return list.get(rowIndex).getStatus();
                case 5 : return list.get(rowIndex).getPeminjam().getIdPeminjam();
                case 6 : return list.get(rowIndex).getPeminjam().getNama();
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
