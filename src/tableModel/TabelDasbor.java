/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import parsisten.Buku;
import parsisten.Skripsi;

/**
 *
 * @author fatiq
 */
public class TabelDasbor extends AbstractTableModel{

    private List<Buku> listBuku = new ArrayList<>();
    private List<Skripsi> listSkripsi = new ArrayList<>();
    private final String[] headerName = {"NO", "ID Buku", "Judul", "Subjudul", "Kategori", "Bahasa", "Pengarang"};
    
    public void clear (){
        listBuku.clear();
        listSkripsi.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Buku> listB, List<Skripsi> listS){
        clear();
        this.listBuku.addAll(listB);
        this.listSkripsi.addAll(listS);
        fireTableDataChanged();
    }
    
    public void setData(int index, Buku mod){
        listBuku.set(index, mod);
        fireTableRowsUpdated(index, index);
    }
    
    @Override
    public int getRowCount() {
        return listBuku.size()+listSkripsi.size();
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
            if(rowIndex < listBuku.size()){
                switch (columnIndex-1){
                    case 0 : return listBuku.get(rowIndex).getIdBuku();
                    case 1 : return listBuku.get(rowIndex).getJudul();
                    case 2 : return listBuku.get(rowIndex).getSubjudul();
                    case 3 : return listBuku.get(rowIndex).getAllKategori();
                    case 4 : return listBuku.get(rowIndex).getBahasa();
                    case 5 : return listBuku.get(rowIndex).getAllPengarang();
                    default : return null;
                }
            } else {
                int newRowIndex = rowIndex-listBuku.size();
                switch (columnIndex-1){
                    case 0 : return listSkripsi.get(newRowIndex).getIdSkripsi();
                    case 1 : return listSkripsi.get(newRowIndex).getJudul();
                    case 2 : return null;
                    case 3 : return listSkripsi.get(newRowIndex).getAllKategori();
                    case 4 : return listSkripsi.get(newRowIndex).getBahasa();
                    case 5 : return listSkripsi.get(newRowIndex).getPenulis();
                    default : return null;
                }
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
