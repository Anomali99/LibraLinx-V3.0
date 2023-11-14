/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import parsisten.Buku;

/**
 *
 * @author fatiq
 */
public class TabelBuku extends AbstractTableModel{

    private List<Buku> listBuku = new ArrayList<>();
    private final String[] headerName = {"NO", "ID Buku", "Judul", "Subjudul","ISBN", "Kategori", "Bahasa", "Pengarang"};
    
    public void clear (){
        listBuku.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Buku> listB){
        clear();
        this.listBuku.addAll(listB);
        fireTableDataChanged();
    }
    
    public void setData(int index, Buku mod){
        listBuku.set(index, mod);
        fireTableRowsUpdated(index, index);
    }
    
    @Override
    public int getRowCount() {
        return listBuku.size();
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
                case 0 : return listBuku.get(rowIndex).getIdBuku();
                case 1 : return listBuku.get(rowIndex).getJudul();
                case 2 : return listBuku.get(rowIndex).getSubjudul();
                case 3 : return listBuku.get(rowIndex).getIsbn();
                case 4 : return listBuku.get(rowIndex).getAllKategori();
                case 5 : return listBuku.get(rowIndex).getBahasa();
                case 6 : return listBuku.get(rowIndex).getAllPengarang();
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
